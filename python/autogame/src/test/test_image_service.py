# @Time    : 2023年06月20日 15:17
# @Author  : orcakill
# @File    : test_ImageService.py
# @Description : 图像识别测试类

import datetime
from unittest import TestCase

from src.model.enum import Onmyoji, Cvstrategy
from src.service.complex_service import ComplexService
from src.service.image_service import ImageService
from src.service_onmyoji_impl import impl_house
from src.utils.my_logger import logger


class TestImageService(TestCase):
    def test_exists(self):
        ImageService.auto_setup("0")
        now = datetime.datetime.now()
        is_border = ImageService.exists(Onmyoji.friends_HYYM)
        logger.debug(is_border)
        now1 = datetime.datetime.now()
        print(now1 - now)

    def test_exists_coordinate(self):
        ImageService.auto_setup("0")
        now = datetime.datetime.now()
        ComplexService.fight_end(Onmyoji.border_ZDSL, Onmyoji.border_ZDSB,
                                  Onmyoji.border_ZCTZ, Onmyoji.home_TS, Onmyoji.border_GRJJ, None, 60, 1)
        now1 = datetime.datetime.now()
        print(now1 - now)

    def test_touch(self):
        ImageService.auto_setup("0")
        now = datetime.datetime.now()
        # 测试代码
        logger.debug("开始")
        ImageService.exists(Onmyoji.home_DBCDDK)
        logger.debug("结束")
        now1 = datetime.datetime.now()
        print(now1 - now)

    def test_snapshot(self):
        test=Cvstrategy.default
        logger.debug(test)
        ImageService.auto_setup("0")
        now = datetime.datetime.now()
        # 测试代码
        logger.debug("开始")
        ImageService.snapshot("1")
        logger.debug("结束")
        now1 = datetime.datetime.now()
        print(now1 - now)

    def test_crop_image(self):
        ImageService.auto_setup("0")
        now = datetime.datetime.now()
        # 测试代码
        logger.debug("开始")
        ImageService.crop_image(100, 100, 200, 200)
        logger.debug("结束")
        now1 = datetime.datetime.now()
        print(now1 - now)

    def test_find_all(self):
        """
        多图查找
        :return:
        """

        ImageService.auto_setup("0")
        now = datetime.datetime.now()
        # 测试代码
        logger.debug("开始")
        result = ImageService.find_all(Onmyoji.region_GP)
        logger.debug(result)
        logger.debug(len(result))
        logger.debug("结束")
        now1 = datetime.datetime.now()
        logger.debug(now1 - now)

    def test_calculate_proportion(self):
        """
        计算比例
        :return:
        """
        ImageService.auto_setup("1")
        now = datetime.datetime.now()
        # 测试代码
        logger.debug("测试-开始")
        logger.debug("测试-开获取上方好友坐标")
        coordinate_friend = ImageService.exists(Onmyoji.foster_SFHY)
        logger.debug("测试-开获取上方跨区坐标")
        coordinate_region = ImageService.exists(Onmyoji.foster_SFKQ)
        logger.debug("测试-开计算起始位置1,测试系数")
        coordinate_difference = 0.8228571428571428*(coordinate_region[0] - coordinate_friend[0])
        coordinate_start = (coordinate_region[0], coordinate_region[1])
        logger.debug("测试-开计算起始位置2")
        coordinate_end = (coordinate_region[0], coordinate_region[1] + coordinate_difference)
        c1=ImageService.exists(Onmyoji.test_TEST1)
        logger.debug(coordinate_end)
        logger.debug(c1)
        logger.debug((c1[1]-coordinate_start[1])/coordinate_difference)
        logger.debug("测试-开结束")
        now1 = datetime.datetime.now()
        logger.debug(now1 - now)
