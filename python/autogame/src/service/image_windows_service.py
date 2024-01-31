# @Time: 2024年01月26日 14:38
# @Author: orcakill
# @File: image_windows_service.py
# @Description: 图像识别，根据win32实现
import time

import cv2
import imageio
import numpy as np
import psutil
import win32con
import win32gui
import win32process
import win32ui

from src.model.enum import Cvstrategy
from src.service.airtest_service import AirtestService
from src.service.image_service import ImageService
from src.utils.my_logger import my_logger as logger

# 图像识别算法
CVSTRATEGY = Cvstrategy.sift
# 单张图片识别时间
TIMEOUT = 0.5
# 图片组识别时间
TIMEOUTS = 5
# 图像识别阈值
THRESHOLD = 0.7
# 图片识别轮次
REC_ROUND = 1
# 图片等待识别时间(秒）·
WAIT = 2
# 图片识别间隔(秒）·
INTERVAL = 1
# 点击次数
TIMES = 1
# 按住时间
DURATION = 0.01

THROW = True


class ImageWindowsService:

    @staticmethod
    def exists(windows_title: str, folder_path: str, cvstrategy: [] = CVSTRATEGY, timeouts: float = TIMEOUTS,
               threshold: float = THRESHOLD, wait: float = WAIT, interval: float = INTERVAL, is_throw: bool = THROW,
               is_click: bool = False, rgb: bool = False, x1: float = 0, x2: float = 1, y1: float = 0, y2: float = 1):
        """
        根据文件夹名获取图片进行图像识别，判断图片是否存在

        :param windows_title: 窗口标题
        :param folder_path: 图片文件夹路径
        :param cvstrategy: 图像识别算法
        :param threshold: 图像识别阈值
        :param interval: 图片识别点击间隔时间
        :param rgb: 带颜色
        :param timeouts: 图片组超时时间
        :param wait: 图片等待识别时间
        :param is_click: 是否点击坐标
        :param is_throw: 是否显示异常
        :param y2: y2坐标比例
        :param x2: x2坐标比例
        :param y1: y1坐标比例
        :param x1: x1坐标比例
        :return:
        """
        if 1 == 1:
            time.sleep(wait)
            resolution = ImageWindowsService.resolution_hwnd(windows_title)
            template_list = ImageService.get_template_list(folder_path, rgb, threshold)
            time_start = time.time()
            while time.time() - time_start < timeouts:
                for template in template_list:
                    # 设备截图
                    image1 = ImageWindowsService.screenshot(windows_title, x1, x2, y1, y2)
                    match = AirtestService.cv_match(template, image1, cvstrategy)
                    pos = match['result']
                    if pos and pos is not None:
                        # 根据截取的比例，修正坐标
                        if (x1, y1, x2, y2) not in [0, 1, 0, 1]:
                            pos = (pos[0] + resolution[0] * x1, pos[1] + resolution[1] * y1)
                        if not is_click:
                            if is_throw:
                                logger.debug("图像识别成功:{},{}", folder_path, template.filename)
                            else:
                                logger.debug("图像识别成功:{}", folder_path)
                            return pos
                        if is_click:
                            time.sleep(interval)
                            re1 = match['rectangle'][0]
                            re2 = match['rectangle'][2]
                            ImageWindowsService.draw_rectangle(image1, re1[0], re1[1], re2[0], re2[1])
                            # 点击指定坐标
                            ImageWindowsService.click(windows_title, pos[0], pos[1])
                            logger.debug("图像识别点击成功:{}", folder_path)
                            return True
            return False

    @staticmethod
    def screenshot(windows_title: str, x1: float = 0, x2: float = 1, y1: float = 0, y2: float = 1):
        """
        设备截图，根据截图比例确定位置
        :param windows_title:
        :param x1:
        :param x2:
        :param y1:
        :param y2:
        :return:
        """
        # 获取窗口句柄
        hwnd = win32gui.FindWindow(None, windows_title)
        if hwnd:
            # 判断窗口是否最大化
            # if not win32gui.IsIconic(hwnd):
            #     # 将窗口最大化
            #     win32gui.ShowWindow(hwnd, win32con.SW_MAXIMIZE)
            #     time.sleep(0.5)
            # 获取窗口位置和大小
            rect = win32gui.GetWindowRect(hwnd)
            x, y, w, h = rect
            # 创建一个与窗口大小相同的设备上下文
            hdc = win32gui.GetWindowDC(hwnd)
            dcObj = win32ui.CreateDCFromHandle(hdc)
            memDC = dcObj.CreateCompatibleDC()
            # 创建一个位图对象
            bitmap = win32ui.CreateBitmap()
            bitmap.CreateCompatibleBitmap(dcObj, w, h)
            memDC.SelectObject(bitmap)
            # 将窗口内容绘制到位图上
            memDC.BitBlt((0, 0), (w, h), dcObj, (0, 0), win32con.SRCCOPY)
            # 将位图保存为文件
            # bitmap.SaveBitmapFile(memDC, "D://screenshot.png")

            # bitmap转换ndarray
            signedIntsArray = bitmap.GetBitmapBits(True)
            ndarray_image = np.fromstring(signedIntsArray, dtype='uint8')
            ndarray_image.shape = (h, w, 4)
            ndarray_image = cv2.cvtColor(ndarray_image, cv2.COLOR_BGRA2RGB)
            # 释放位图资源
            win32gui.DeleteObject(bitmap.GetHandle())
            memDC.DeleteDC()
            dcObj.DeleteDC()
            win32gui.ReleaseDC(hwnd, hdc)

            # 保存图片到磁盘
            imageio.imsave("D://screenshot2.png", ndarray_image)

            coordinate1 = (int(w * x1), int(h * y1))
            coordinate2 = (int(w * x2), int(h * y2))

            # 截取图片
            # cropped_image = ndarray_image[0:525,960:1920]
            cropped_image = ndarray_image[coordinate1[1]:coordinate2[1], coordinate1[0]:coordinate2[0]]

            # 保存图片到磁盘
            imageio.imsave("D://screenshot3.png", cropped_image)

            return cropped_image
        else:
            logger.debug("没有找到窗口句柄")

    @staticmethod
    def resolution_hwnd(windows_title: str):
        # 获取窗口句柄
        hwnd = win32gui.FindWindow(None, windows_title)
        # 判断窗口是否最大化
        if not win32gui.IsIconic(hwnd):
            # 将窗口最大化
            win32gui.ShowWindow(hwnd, win32con.SW_MAXIMIZE)
            time.sleep(0.5)
        # 获取窗口位置和大小
        rect = win32gui.GetWindowRect(hwnd)
        x, y, w, h = rect
        resolution = (w, h)
        return resolution

    @staticmethod
    def find_window_handles_by_exe(exe_path):
        handles = []

        def callback(hwnd, _):
            _, pid = win32process.GetWindowThreadProcessId(hwnd)
            try:
                process = psutil.Process(pid)
                if process.exe() == exe_path:
                    handles.append(hwnd)
            except psutil.NoSuchProcess:
                pass

            return True

        win32gui.EnumWindows(callback, None)

        logger.debug(handles)
        return handles

    @staticmethod
    def draw_rectangle(screen, x1, y1, x2, y2):
        cv2.rectangle(screen, (x1, y1), (x2, y2), (0, 0, 255), 2)
        # 保存图片到本地磁盘
        cv2.imwrite('D://draw.png', screen)

    @staticmethod
    def click(window_title: str, x, y, hwnd=None):
        if window_title is not None and hwnd is None:
            hwnd = win32gui.FindWindow(None, window_title)
        if not hwnd:
            raise Exception("找不到窗口")
        try:
            win32gui.PostMessage(hwnd, win32con.WM_LBUTTONDOWN, win32con.MK_LBUTTON, (x, y))
            win32gui.PostMessage(hwnd, win32con.WM_LBUTTONUP, 0, (x, y))
        except Exception as e:
            print(f"点击失败：{e}")
