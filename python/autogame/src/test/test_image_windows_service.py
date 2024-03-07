# @Time: 2024年01月26日 16:19
# @Author: orcakill
# @File: test_image_windows_service.py
# @Description: TODO
from unittest import TestCase

from src.model.enum import Onmyoji
from src.service.image_windows_service import ImageWindowsService


class ImageWindowsServiceTest(TestCase):

    @staticmethod
    def test_resolution_hwnd():
        ImageWindowsService.resolution_hwnd(123)

    @staticmethod
    def test_screenshot():
        ImageWindowsService.screenshot(131740,'测试',True)

    @staticmethod
    def test_exists():
        ImageWindowsService.exists(131740, Onmyoji.windows_test1,is_click=True)
