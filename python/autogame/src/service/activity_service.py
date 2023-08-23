"""
# @Time: 2023年08月23日23:56
# @Author: orcakill
# @File: activity_service.py
# @Description: 活动
"""
import time

from src.model.enum import Onmyoji
from src.model.models import GameAccount
from src.service.complex_service import ComplexService
from src.service.image_service import ImageService
from src.service.ocr_service import OcrService
from src.utils.my_logger import logger

# 服务接口
image_service = ImageService()
complex_service = ComplexService()
ocr_service = OcrService()


class ActivityService:
    @staticmethod
    def current_activity(game_task: []):
        # 当前活动 2023-08-23 至 2023-08-30   爬塔-真火切磋
        ActivityService.climbing_tower_20230823(game_task)

    @staticmethod
    def climbing_tower_20230823(game_task: []):
        """
        爬塔  真火切磋
        :param game_task: 任务信息
        :return:
        """
        game_account = GameAccount(game_task[2])
        logger.debug("爬塔-真火切磋-{}", game_account.game_name)
        # 初始化图片路径
        p_buy = r"活动\20230823\购买"
        p_fight = r"活动\20230823\挑战"
        p_fight_win = r"活动\20230823\战斗胜利"
        p_fight_fail = r"活动\20230823\战斗失败"
        p_fight_again = r"活动\20230823\再次挑战"
        p_fight_quit = r"活动\20230823\退出挑战"
        p_return = r"活动\20230823\返回"
        p_reward = r"活动\20230823\获得奖励"
        p_select = r"活动\20230823\选定"
        p_soul_select = r"活动\20230823\御魂选定"
        p_step1 = r"活动\20230823\入口1"
        p_step2 = r"活动\20230823\入口2"
        p_shiShenLu = r"活动\20230823\式神录"
        p_top_plus_sign = r"活动\20230823\顶部加号"
        p_unlock = r"活动\20230823\解锁阵容"
        p_double_up_arrow = r"活动\20230823\双向上箭头"
        p_gift = r"活动\20230823\礼包"
        # 开始活动
        logger.debug("进入")
        image_service.touch(p_step1)
        image_service.touch(p_step2)
        logger.debug("判断是否有选定")
        is_select = image_service.exists(p_select)
        if is_select:
            image_service.touch(p_soul_select)
            image_service.touch(p_select)
        logger.debug("买票")
        coordinate1 = image_service.exists(p_top_plus_sign)
        coordinate2 = image_service.exists(p_shiShenLu)
        if coordinate1 and coordinate2:
            coordinate3 = (coordinate2[0], coordinate1[1])
            logger.debug("进入买票")
            image_service.touch_coordinate(coordinate3)
            logger.debug("最大票数")
            image_service.touch(p_double_up_arrow)
            logger.debug("购买")
            image_service.touch(p_buy)
            is_reward = complex_service.get_reward(p_reward)
            if not is_reward:
                logger.debug("没票了,点击一次礼包退出")
                complex_service.get_reward(p_gift)
        logger.debug("锁定阵容")
        image_service.touch(p_unlock)
        num = 0
        for i in range(80):
            logger.debug("第{}次挑战", i + 1)
            is_fight = image_service.touch(p_fight)
            if not is_fight:
                logger.debug("挑战未点击成功，判断是否有退出挑战")
                is_quit = image_service.touch(p_fight_quit)
                logger.debug("挑战未点击成功，判断是否悬赏封印")
                is_offer_reward = image_service.touch(Onmyoji.comm_FH_XSFYHSCH)
                if is_quit or is_offer_reward:
                    num = 0
            time.sleep(3)
            is_result = complex_service.fight_end(p_fight_win, p_fight_fail, p_fight_again, p_fight_quit, p_fight, 60,
                                                  1)
            if is_result is None:
                if num > 1:
                    num = num + 1
                else:
                    num = 0
                if num > 10:
                    logger.debug("连续10次战斗结果判断为空")
                    break
        logger.debug("返回首页")
        image_service.touch(p_return)
        image_service.touch(p_return)