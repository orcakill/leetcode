# @Time: 2023年08月07日 11:43
# @Author: orcakill
# @File: ocr_service.py
# @Description: 图像文字识别
import re

import pytesseract

from src.model.enum import Onmyoji, Cvstrategy
from src.service.airtest_service import AirtestService
from src.service.image_service import ImageService
from src.utils.my_logger import logger

image_service = ImageService()
airtest_service = AirtestService()


class OcrService:
    @staticmethod
    def get_word(folder_path: str, lang: str = 'eng'):
        """
        获取局部截图的文本或数字信息

        :param folder_path: 局部路径
        :param lang: 语言类型 eng  英语 chi_sim 简体中文
        :return:
        """
        # 结界突破区域
        logger.debug("获取{}的位置", folder_path)
        result = image_service.cv_match(folder_path, cvstrategy=Cvstrategy.default)
        if result:
            pos1 = result['rectangle'][0]
            pos2 = result['rectangle'][2]
            # 截图
            img = airtest_service.crop_image(pos1[0], pos1[1], pos2[0], pos2[1])
            # 图像文字识别
            pil_image = airtest_service.cv2_2_pil(img)
            # pil_image.save("D:/a.png", quality=99, optimize=True)
            # image = Image.open('D:/a.png')
            # 打开图像
            image = pil_image.convert('RGBA')
            # 使用 Tesseract 进行文字识别
            text = pytesseract.image_to_string(image, lang=lang)
            if text and text is not None:
                if folder_path == Onmyoji.border_JJTZJQY:
                    text = OcrService.re_search(r'\d+(?=/30)', text)
                if folder_path == Onmyoji.friends_HYSQY:
                    text = OcrService.re_search(r'\d+(?=/200)', text)
                if folder_path == Onmyoji.deed_MQSS:
                    text = OcrService.re_search(r'\d+(?=/30)', text)
                if folder_path == Onmyoji.explore_DQLHSL:
                    text = OcrService.re_search(r'\d+(?=/50)', text)
                if folder_path == Onmyoji.region_TZCS:
                    text = OcrService.re_search(r'\d+(?=/6)', text)
                if folder_path == Onmyoji.foster_JJK_GYWZ:
                    text = text.split('+')[-1].strip() if '+' in text else text
            if text:
                logger.debug(text)
            else:
                logger.debug("无{}", folder_path)
            return text
            # 文字判断
        else:
            logger.debug("未找到{}", folder_path)
        return None

    @staticmethod
    def re_search(pattern: str, text: str):
        text = re.search(pattern, text)
        if text:
            text = text.group()
        return text
