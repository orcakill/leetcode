# @Time: 2023年09月01日 16:56
# @Author: orcakill
# @File: impl_ghost.py
# @Description: 逢魔之时、地域鬼王
import datetime
import time

from src.model.enum import Onmyoji, Cvstrategy
from src.model.models import GameAccount
from src.service.complex_service import ComplexService
from src.service.image_service import ImageService
from src.utils.my_logger import logger


def ghost_king(game_task: []):
    # 开始时间
    time_start = time.time()
    # 账号信息
    game_account = GameAccount(game_task[2])
    logger.debug(game_account.game_name)
    now = datetime.datetime.now()
    current_hour = now.hour
    if 6 <= current_hour <= 24:
        logger.debug("进入探索")
        ImageService.touch(Onmyoji.home_TS, wait=3)
        logger.debug("进入地域鬼王")
        ImageService.touch(Onmyoji.ghost_DYGWTB, wait=3)
        logger.debug("点击今日挑战")
        ImageService.touch(Onmyoji.ghost_JRTZ, wait=6)
        logger.debug("判断是否有未挑战")
        is_select = ImageService.exists(Onmyoji.ghost_WXZ, cvstrategy=Cvstrategy.default, wait=3)
        if is_select:
            logger.debug("收藏鬼王")
            for i in range(6):
                logger.debug("点击筛选")
                ImageService.touch(Onmyoji.ghost_SX, wait=3)
                logger.debug("点击收藏")
                is_collection = ImageService.touch(Onmyoji.ghost_SC, wait=2)
                if not is_collection:
                    logger.debug("无收藏，重新点击筛选")
                    ImageService.touch(Onmyoji.ghost_SX, wait=2)
                    logger.debug("点击收藏")
                    ImageService.touch(Onmyoji.ghost_SC, wait=2)
                if i in [0, 3]:
                    logger.debug("鸟巢")
                    ImageService.touch(Onmyoji.ghost_SCGW_NC, wait=2)
                elif i in [1, 4]:
                    logger.debug("少林寺藏经阁")
                    ImageService.touch(Onmyoji.ghost_SCGW_SLSCJG, wait=2)
                elif i in [2, 5]:
                    logger.debug("黄鹤楼")
                    ImageService.touch(Onmyoji.ghost_SXGW_HHL, wait=2)
                logger.debug("进入鬼王挑战页面")
                if game_account.account_class == "0":
                    logger.debug("大号，挑战极地域鬼王")
                    logger.debug("判断是否有极标志")
                    is_ordinary = ImageService.exists(Onmyoji.ghost_JBZ)
                    if is_ordinary:
                        logger.debug("点击极标志，切换成极地域鬼王")
                        ImageService.touch_coordinate(is_ordinary)
                if game_account.account_class != "0":
                    logger.debug("小号，挑战等级一的地域鬼王")
                    is_first = ImageService.exists(Onmyoji.ghost_DJY)
                    if not is_first:
                        logger.debug("不是等级一,点击减号")
                        is_minus = ImageService.exists(Onmyoji.ghost_JH)
                        for i_minus in range(60):
                            ImageService.touch_coordinate(is_minus)
                            is_first = ImageService.exists(Onmyoji.ghost_DJY)
                            if is_first:
                                logger.debug("检查到等级一")
                                break
                logger.debug("判读是否有无字")
                is_no_word = ImageService.exists(Onmyoji.ghost_W)
                if is_no_word:
                    logger.debug("挑战")
                    ImageService.touch(Onmyoji.ghost_TZ, wait=3)
                    logger.debug("准备一次")
                    ImageService.touch(Onmyoji.ghost_ZB, wait=6)
                    logger.debug("准备两次")
                    ImageService.touch(Onmyoji.ghost_ZB, wait=3)
                    logger.debug("等待战斗结果")
                    ComplexService.fight_end(Onmyoji.ghost_ZDSL, Onmyoji.ghost_ZDSB, Onmyoji.ghost_ZCTZ,
                                             Onmyoji.ghost_TCTZ, Onmyoji.ghost_TZ, 10 * 60, 1)
                logger.debug("已挑战,返回到鬼王首页")
                ImageService.touch(Onmyoji.comm_FH_YSJHDBSCH)
                logger.debug("点击今日挑战")
                is_day_fight=ImageService.touch(Onmyoji.ghost_JRTZ,wait=5)
                logger.debug("判断是否有未挑战")
                is_select = ImageService.exists(Onmyoji.ghost_WXZ)
                if is_day_fight and not is_select:
                    logger.debug("结束地域鬼王")
                    break
        else:
            logger.debug("无未选择，退出地域鬼王")
        logger.debug("返回首页")
        ImageService.touch(Onmyoji.comm_FH_ZSJLDYXBSXYH, wait=3)
        ImageService.touch(Onmyoji.comm_FH_ZSJLDYXBSXYH, wait=3)
        ImageService.touch(Onmyoji.comm_FH_ZSJLDYXBSXYH, wait=3)
    time_end = time.time() - time_start
    logger.info("地域鬼王,用时{}秒", round(time_end))


def encounter_demons(game_task: []):
    """
    逢魔之时
    :param game_task: 任务信息
    :return:
    """
    # 开始时间
    time_start = time.time()
    # 账号信息
    game_account = GameAccount(game_task[2])
    logger.debug(game_account.game_name)
    now = datetime.datetime.now()
    current_hour = now.hour
    is_gathering = False
    if 17 <= current_hour <= 22:
        logger.debug("进入町中")
        ImageService.touch(Onmyoji.home_DZ)
        logger.debug("判断是否有庭院")
        is_courtyard = ImageService.exists(Onmyoji.demon_TY)
        if is_courtyard:
            logger.debug("旧版町中，点击旧版逢魔之时入口")
            ImageService.touch(Onmyoji.demon_JBFMZSRK)
        logger.debug("判断是否已领取现世奖励")
        is_get_reward = ImageService.exists(Onmyoji.demon_FMLD, wait=5)
        if is_get_reward:
            logger.debug("未点击现时逢魔，开始点击")
            for i_present in range(2):
                ImageService.touch(Onmyoji.demon_XSFM, wait=3)
                ImageService.touch(Onmyoji.demon_XSFM, wait=3)
                ImageService.touch(Onmyoji.demon_QD, wait=3)
                ImageService.touch(Onmyoji.demon_XSFM, wait=3)
                ImageService.touch(Onmyoji.demon_XSFM, wait=3)
                logger.debug("重新判断是否已领取现世奖励")
                is_get_reward = ImageService.exists(Onmyoji.demon_FMLD, wait=1)
                if not is_get_reward:
                    break
            logger.debug("领取奖励")
            ImageService.touch(Onmyoji.demon_HSDM, wait=3)
            logger.debug("识别获得奖励")
            is_reward = ImageService.exists(Onmyoji.store_HDJL, wait=3)
            if is_reward:
                ImageService.touch_coordinate((1 / 2 * is_reward[0], 1 / 2 * is_reward[1]))
        #  逢魔boss
        logger.debug("逢魔boss")
        logger.debug("判断是否有首领极")
        is_extremely = ImageService.exists(Onmyoji.demon_SLJ)
        for i_boss in range(20):
            if is_extremely and game_account.account_class == 0 and i_boss < 10:
                logger.debug("有首领极，且是大号,打首领极")
                ImageService.touch(Onmyoji.demon_SLJ)
            else:
                logger.debug("无首领极或是小号，打首领")
                ImageService.touch(Onmyoji.demon_SL)
            logger.debug("点击左下集结")
            is_left_gathering = ImageService.touch(Onmyoji.demon_ZXJJ)
            if is_left_gathering:
                logger.debug("判断是否有集结挑战")
                is_fight = ImageService.exists(Onmyoji.demon_JJTZ)
                if is_fight:
                    logger.debug("连点5次")
                    for i_click in range(5):
                        ImageService.touch_coordinate(is_fight, wait=1)
                logger.debug("判断是否有一天一层")
                is_one = ImageService.exists(Onmyoji.demon_YTYC, wait=0)
                if is_one:
                    logger.debug("当日已完成挑战")
                    ImageService.touch(Onmyoji.demon_YSJYXHDBSCH, wait=2)
                    break
                logger.debug("判断是否有集结")
                is_gathering = ImageService.exists(Onmyoji.demon_ZCJJ)
                if is_gathering:
                    logger.debug("已进入逢魔集结")
                    break
                else:
                    logger.debug("返回到逢魔页面")
                    ImageService.touch(Onmyoji.demon_YSJYXHDBSCH, wait=2)
            else:
                logger.debug("未找到首领")
        if is_gathering:
            logger.debug("进入boss战,十分钟战斗时间")
            time_start = time.time()
            while time.time() - time_start < 10 * 60:
                ImageService.exists(Onmyoji.demon_ZB, is_click=True)
                ImageService.exists(Onmyoji.demon_ZDSL, is_click=True)
                is_again = ImageService.exists(Onmyoji.demon_ZCTZ, timeouts=1)
                if is_again:
                    ImageService.touch(Onmyoji.demon_ZDSB)
                ImageService.exists(Onmyoji.demon_TCTZ, is_click=True)
                ImageService.exists(Onmyoji.demon_YJHD, is_click=True)
                is_achievements = ImageService.exists(Onmyoji.demon_ZCZJ)
                if is_achievements:
                    logger.debug("已有战绩，尝试退出")
                    logger.debug("点击退出集结场景")
                    ImageService.touch(Onmyoji.comm_FH_ZSJZKDZSHXJT)
                    logger.debug("点击确定")
                    ImageService.touch(Onmyoji.demon_QD)
                is_boss = ImageService.exists(Onmyoji.demon_SL)
                if is_boss:
                    logger.debug("逢魔之时首页，已退出逢魔boss战")
                    break
                time.sleep(10)
        logger.debug("退出逢魔之时")
        ImageService.touch(Onmyoji.comm_FH_ZSJLDYXBSXYH)
        logger.debug("退出町中")
        if is_courtyard:
            logger.debug("旧版町中，点击庭院")
            ImageService.touch(Onmyoji.demon_TY)
    else:
        logger.debug("不在逢魔时间内")
    time_end = time.time() - time_start
    logger.info("逢魔之时,用时{}秒", round(time_end))
