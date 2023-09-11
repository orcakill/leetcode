"""
# @Time: 2023年08月31日01:34
# @Author: orcakill
# @File: impl_house.py
# @Description: 式神寄养
"""
import time

from src.model.enum import Onmyoji, Cvstrategy
from src.model.models import GameAccount
from src.service.complex_service import ComplexService
from src.service.image_service import ImageService
from src.service_onmyoji_impl import impl_initialization
from src.utils.my_logger import logger


def foster_care(game_task: []):
    """
    式神寄养
    :param game_task:
    :return:
    """
    # 寄养开始时间
    time_start = time.time()
    # 寄养轮次
    num_round = 0
    # 寄养检查好友
    num_friend = 0
    # 连续类型判断失败次数
    num_type_fail = 0
    # 寄养结果
    foster_result = None
    # 账号信息
    game_account = GameAccount(game_task[2])
    # 默认未找到目标
    is_target = False
    # 获取设备分辨率
    resolution = ImageService.resolving_power()
    logger.debug(game_account.game_name)
    logger.debug("式神寄养")
    for i_time in range(2):
        logger.debug("点击阴阳寮")
        ImageService.touch(Onmyoji.foster_YYLTB)
        logger.debug("点击结界")
        ImageService.touch(Onmyoji.foster_JJTB)
        logger.debug("点击式神育成")
        is_growing = ImageService.touch(Onmyoji.foster_SSYC)
        if not is_growing:
            logger.debug("式神育成点击失败，重新点击")
            ImageService.touch(Onmyoji.foster_SSYC)
        logger.debug("判断是否可寄养")
        is_foster = ImageService.exists(Onmyoji.foster_KJYBZ)
        if is_foster:
            logger.debug("可寄养")
            logger.debug("开始寄养，从六星太鼓到四星斗鱼")
            # 结界卡，默认六星太鼓
            target_type = Onmyoji.foster_JJK_TG
            target_card = Onmyoji.foster_JJK_LXTG
            for i_type in range(7):
                num_round = num_round + 1
                logger.debug("初始化目标结界卡")
                if i_type == 0:
                    target_type = Onmyoji.foster_JJK_TG
                    target_card = Onmyoji.foster_JJK_LXTG
                elif i_type == 1:
                    target_type = Onmyoji.foster_JJK_TG
                    target_card = Onmyoji.foster_JJK_WXTG
                elif i_type == 2:
                    target_type = Onmyoji.foster_JJK_TG
                    target_card = Onmyoji.foster_JJK_SXTG1
                elif i_type == 3:
                    target_type = Onmyoji.foster_JJK_TG
                    target_card = Onmyoji.foster_JJK_SXTG
                elif i_type == 4:
                    target_type = Onmyoji.foster_JJK_DY
                    target_card = Onmyoji.foster_JJK_LXDY
                elif i_type == 5:
                    target_type = Onmyoji.foster_JJK_DY
                    target_card = Onmyoji.foster_JJK_WXDY
                elif i_type == 6:
                    target_type = Onmyoji.foster_JJK_DY
                    target_card = Onmyoji.foster_JJK_SXDY1
                logger.debug("目标结界卡：{}", target_card)
                ImageService.touch(Onmyoji.foster_KJYBZ)
                logger.debug("确定上方好友坐标")
                coordinate_friend = ImageService.exists(Onmyoji.foster_SFHY)
                logger.debug("确定上方跨区坐标")
                coordinate_region = ImageService.exists(Onmyoji.foster_SFKQ)
                if coordinate_friend and coordinate_region:
                    logger.debug("计算位置1,测试系数0.95")
                    coordinate_difference = 0.95 * (coordinate_region[0] - coordinate_friend[0])
                    coordinate_start = (coordinate_region[0], coordinate_region[1] + coordinate_difference)
                    logger.debug("计算位置2")
                    coordinate_end = (coordinate_region[0], coordinate_region[1] + 2 * coordinate_difference)
                    for i_friends in range(100):
                        num_friend = num_friend + 1
                        logger.debug("当前第{}个好友", i_friends + 1)
                        logger.debug("点击位置1")
                        ImageService.touch_coordinate(coordinate_start)
                        logger.debug("判断结界卡是否是目标类型:{}", target_type)
                        is_type = ImageService.exists(target_type, threshold=0.8)
                        if is_type:
                            if target_type == Onmyoji.foster_JJK_TG:
                                logger.debug("如果是太鼓，重新判断一下")
                                is_type = ImageService.exists(Onmyoji.foster_JJK_GY)
                            if is_type:
                                logger.debug("判断结界卡是否是目标结界卡:{}", target_card)
                                is_target = ImageService.exists(target_card, threshold=0.8)
                                if is_target and is_target[0] > 1 / 2 * resolution[0]:
                                    # 截图,记录识别结果
                                    name = game_account.game_name
                                    name = name + "_" + target_card.replace("阴阳寮\\式神寄养\\结界卡\\", "")
                                    ImageService.snapshot(name, print_image=True)
                                    foster_result = target_card
                                    logger.debug("已找到目标结界卡,且X坐标在右侧,跳出一层循环,进入好友结界")
                                    break
                        else:
                            num_type_fail = num_type_fail + 1
                        if i_friends > 10 and num_type_fail % 3 == 0:
                            logger.debug("好友数大于10，且连续3次不是太鼓和斗鱼，判断是否是未放置")
                            is_place = ImageService.exists(Onmyoji.foster_JJK_WFZ)
                            if is_place:
                                logger.debug("未放置,跳出一层循环，更换目标结界卡或结束寄养查找")
                                ComplexService.get_reward(Onmyoji.foster_SFHY)
                                break
                        else:
                            is_place = False
                        if not is_target and not is_place:
                            logger.debug("没找到目标，且当前不是未放置，向下滑动")
                            ComplexService.refuse_reward()
                            ImageService.swipe(coordinate_end, coordinate_start)
                if is_target and foster_result:
                    logger.debug("已找到目标结界卡,跳出二层循环")
                    break
            if is_target and foster_result:
                logger.debug("进入好友结界")
                ImageService.touch(Onmyoji.foster_JRJJ)
                logger.debug("点击达摩")
                is_dharma = ImageService.touch(Onmyoji.foster_DMDJDM)
                if not is_dharma:
                    is_dharma = ImageService.touch(Onmyoji.foster_DMFWDM)
                    if not is_dharma:
                        ImageService.touch(Onmyoji.foster_DMZFDM)
                logger.debug("确定")
                ImageService.touch(Onmyoji.foster_QD)
            logger.debug("返回首页")
            ImageService.touch(Onmyoji.comm_FH_ZSJLDYXBSXYH)
            ImageService.touch(Onmyoji.comm_FH_ZSJLDYXBSXYH)
            ImageService.touch(Onmyoji.comm_FH_ZSJLDYXBSXYH)
            ImageService.touch(Onmyoji.comm_FH_ZSJHKZDHSXYH)
        else:
            logger.debug("返回首页")
            ImageService.touch(Onmyoji.comm_FH_ZSJLDYXBSXYH)
            ImageService.touch(Onmyoji.comm_FH_ZSJLDYXBSXYH)
            ImageService.touch(Onmyoji.comm_FH_ZSJLDYXBSXYH)
            ImageService.touch(Onmyoji.comm_FH_ZSJHKZDHSXYH)
        logger.debug("确认返回首页")
        impl_initialization.return_home(game_task)
        time_end = time.time()
        time_time = round(time_end - time_start, 3)
        time_avg_friend = 0
        if num_friend > 0:
            time_avg_friend = round(time_time / num_friend)
        if is_foster:
            logger.debug("寄养用时{}秒,检查好友{}轮{}个，平均用时{}秒，寄养结果{}", time_time, num_round, num_friend,
                         time_avg_friend, foster_result)
        else:
            logger.debug("已寄养，无需寄养,用时{}秒", time_time)


def foster_care1(game_task: []):
    """
    式神寄养
    :param game_task:
    :return:
    """
    # 寄养开始时间
    time_start = time.time()
    # 账号信息
    game_account = GameAccount(game_task[2])
    # 寄养结果
    foster_result = None
    # 默认未找到目标
    is_target = False
    logger.debug(game_account.game_name)
    logger.debug("式神寄养")
    for i_time in range(2):
        logger.debug("点击阴阳寮图标")
        ImageService.touch(Onmyoji.foster_YYLTB)
        logger.debug("点击寮首页结界")
        ImageService.touch(Onmyoji.foster_JJTB)
        logger.debug("点击结界-式神育成")
        is_growing = ImageService.touch(Onmyoji.foster_SSYC)
        if not is_growing:
            logger.debug("式神育成点击失败，重新点击")
            ImageService.touch(Onmyoji.foster_SSYC)
        logger.debug("判断是否可寄养")
        is_foster = ImageService.exists(Onmyoji.foster_KJYBZ)
        if is_foster:
            logger.debug("获取寄养列表结界卡数据，计算出最优结界卡,根据最优结界卡，重新滑动")
            faster_place = get_optimal_card()
            if is_target and faster_place:
                logger.debug("最优结界卡,进入好友结界")
                ImageService.touch(Onmyoji.foster_JRJJ)
                logger.debug("点击达摩,优先大吉达摩")
                is_dharma = ImageService.touch(Onmyoji.foster_DMDJDM)
                if not is_dharma:
                    is_dharma = ImageService.touch(Onmyoji.foster_DMFWDM)
                    if not is_dharma:
                        ImageService.touch(Onmyoji.foster_DMZFDM)
                logger.debug("确定")
                is_ture = ImageService.touch(Onmyoji.foster_QD)
                if is_ture:
                    foster_result = faster_place[1]
            logger.debug("返回首页")
            ImageService.touch(Onmyoji.comm_FH_ZSJLDYXBSXYH)
            ImageService.touch(Onmyoji.comm_FH_ZSJLDYXBSXYH)
            ImageService.touch(Onmyoji.comm_FH_ZSJLDYXBSXYH)
            ImageService.touch(Onmyoji.comm_FH_ZSJHKZDHSXYH)
        else:
            logger.debug("返回首页")
            ImageService.touch(Onmyoji.comm_FH_ZSJLDYXBSXYH)
            ImageService.touch(Onmyoji.comm_FH_ZSJLDYXBSXYH)
            ImageService.touch(Onmyoji.comm_FH_ZSJLDYXBSXYH)
            ImageService.touch(Onmyoji.comm_FH_ZSJHKZDHSXYH)
        logger.debug("确认返回首页")
        impl_initialization.return_home(game_task)
        time_end = time.time()
        time_time = round(time_end - time_start, 3)
        if is_foster:
            logger.debug("寄养用时{}秒,寄养结果{}", time_time, foster_result)
        else:
            logger.debug("已寄养，无需寄养,用时{}秒", time_time)


def get_optimal_card():
    """
    遍历寄养列表，计算出最优结界卡位置返回，六星太鼓直接寄养
    :return:
    """
    # 太鼓检查失败次数
    num_fail_money = 0
    # 寄养检查列表
    faster_list = []
    # 寄养检查结果
    faster_place = []
    logger.debug("判断是否可寄养")
    is_foster = ImageService.exists(Onmyoji.foster_KJYBZ)
    if is_foster:
        logger.debug("点击可寄养标志")
        ImageService.touch(Onmyoji.foster_KJYBZ)
        logger.debug("获取上方好友坐标")
        coordinate_friend = ImageService.exists(Onmyoji.foster_SFHY)
        logger.debug("获取上方跨区坐标")
        coordinate_region = ImageService.exists(Onmyoji.foster_SFKQ)
        logger.debug("计算起始位置1,测试系数0.8228571428571428")
        coordinate_difference = 0.8228571428571428 * (coordinate_region[0] - coordinate_friend[0])
        coordinate_start = (coordinate_region[0], coordinate_region[1])
        logger.debug("计算起始位置2")
        coordinate_end = (coordinate_region[0], coordinate_region[1] + coordinate_difference)
        if coordinate_start and coordinate_end and coordinate_end[1] - coordinate_start[1] > 0:
            for i_friends in range(100):
                logger.debug("当前第{}个好友，连续失败次数{}", i_friends + 1, num_fail_money)
                if len(faster_place) == 0:
                    logger.debug("点击位置2")
                    ImageService.touch_coordinate(coordinate_end)
                    if num_fail_money % 3 == 0 and num_fail_money != 0 and i_friends > 20:
                        logger.debug("判断目前结界卡，判断未放置")
                        target_card = get_card_type(1)
                    else:
                        logger.debug("判断目前结界卡，不判断未放置")
                        target_card = get_card_type(0)
                    faster_list.append([i_friends + 1, target_card[0], target_card[1]])
                    if not target_card[1]:
                        num_fail_money = num_fail_money + 1
                    if target_card[1] == Onmyoji.foster_JJK_LXTG:
                        logger.debug("六星太鼓，中断查找")
                        break
                    if target_card[1] == Onmyoji.foster_JJK_WFZ:
                        logger.debug("未放置，中断查找,退出重新进入")
                        ComplexService.get_reward(Onmyoji.foster_SFHY)
                        ImageService.touch(Onmyoji.foster_KJYBZ)
                        break
                logger.debug("向下滑动")
                ComplexService.refuse_reward()
                ImageService.swipe(coordinate_end, coordinate_start)
        if len(faster_list) > 0:
            min_value = min(item[1] for item in faster_list)
            min_index = min(i for i, item in enumerate(faster_list) if item[1] == min_value)
            faster_place = faster_list[min_index]
        faster_len = len(faster_place)
        logger.debug(faster_list)
        logger.debug("寄养查找的最优结果：{}", faster_place)
        # 根据最优结果寻找结界
        if len(faster_place) > 0:
            if faster_place[2] == Onmyoji.foster_JJK_LXTG:
                logger.debug("无需重新寻找")
                return faster_place
            else:
                logger.debug("重新滑动")
                for i_friends in range(faster_len):
                    if i_friends >= faster_len:
                        logger.debug("点击位置2")
                        ImageService.touch_coordinate(coordinate_end)
                        logger.debug("获取目标结界卡")
                        target_card = get_card_type(1)
                        logger.debug(target_card)
                        logger.debug(faster_place[i_friends])
                        if target_card == faster_place[i_friends][1]:
                            logger.debug("检查和寻找匹配成功")
                            return faster_place[i_friends]
                        else:
                            logger.debug("检查和寻找匹配失败")
                    logger.debug("向下滑动")
                    ImageService.swipe(coordinate_end, coordinate_start)
                    time.sleep(1)
                return None


def get_card_type(not_placed: int = 0):
    """
    获取当前结界卡,判断当前结界卡类型和等级，只要太鼓
    :return:
    """
    logger.debug("判断是否是太鼓")
    is_result = ImageService.exists(Onmyoji.foster_JJK_TG)
    if is_result:
        logger.debug("判断是否有勾玉")
        is_result = ImageService.exists(Onmyoji.foster_JJK_GY)
        if is_result:
            logger.debug("三星太鼓")
            is_result = ImageService.exists(Onmyoji.foster_JJK_SXTG)
            if is_result:
                return [4, Onmyoji.foster_JJK_SXTG]
            logger.debug("四星太鼓")
            is_result = ImageService.exists(Onmyoji.foster_JJK_SXTG1)
            if is_result:
                return [3, Onmyoji.foster_JJK_SXTG1]
            logger.debug("五星太鼓")
            is_result = ImageService.exists(Onmyoji.foster_JJK_WXTG)
            if is_result:
                return [2, Onmyoji.foster_JJK_WXTG]
            logger.debug("六星太鼓")
            is_result = ImageService.exists(Onmyoji.foster_JJK_LXTG)
            if is_result:
                return [1, Onmyoji.foster_JJK_LXTG]
    if not_placed == 1:
        logger.debug("判断是否是未放置")
        is_result = ImageService.exists(Onmyoji.foster_JJK_WFZ)
        if is_result:
            return [5, Onmyoji.foster_JJK_WFZ]
    logger.debug("其它情况")
    return [99, None]


def shack_house(game_task: []):
    """
    寮管理
    1.寮资金纸人
    2.寮体力纸人
    3.集体任务
    4.体力食盒和经验酒壶
    5.结界卡奖励领取
    6.寄养奖励领取
    7.结界卡放置
    8.式神育成
    :param game_task: 任务信息
    :return:
    """
    # 寮管理开始时间
    time_start = time.time()
    # 账号信息
    game_account = GameAccount(game_task[2])
    logger.debug(game_account.game_name)
    logger.debug("进入阴阳寮")
    ImageService.touch(Onmyoji.shack_YYLTB)
    logger.debug("1.寮资金纸人")
    is_capital = ImageService.touch(Onmyoji.shack_ZJLQ)
    if is_capital:
        logger.debug("领取按钮")
        ImageService.touch(Onmyoji.shack_LQAN)
        logger.debug("获得奖励")
        ComplexService.get_reward(Onmyoji.shack_HDJL)
    logger.debug("2.寮体力纸人")
    is_strength = ImageService.touch(Onmyoji.shack_TLXZR)
    if is_strength:
        logger.debug("获得奖励")
        ComplexService.get_reward(Onmyoji.shack_HDJL)
    logger.debug("3.集体任务")
    is_collective = ImageService.touch(Onmyoji.shack_ZCJTRW)
    if is_collective:
        logger.debug("集体任务，判断是否有觉醒任务")
        is_awaken = ImageService.exists(Onmyoji.shack_JXRW)
        if is_awaken:
            logger.debug("集体任务，判断是否提交")
            is_commit = ImageService.exists(Onmyoji.shack_TJ)
            if is_awaken and is_commit:
                coordinate_commit = (is_awaken[0], is_commit[1])
                logger.debug("确定有觉醒任务和提交,{}", coordinate_commit)
                ImageService.touch_coordinate(coordinate_commit)
                logger.debug("四种觉醒材料的坐标")
                is_fire = ImageService.exists(Onmyoji.shack_RWCLYHL)
                is_mine = ImageService.exists(Onmyoji.shack_RWCLTLG)
                is_water = ImageService.exists(Onmyoji.shack_RWCLSLL)
                is_wind = ImageService.exists(Onmyoji.shack_RWCLFZF)
                logger.debug("任务滚轮坐标")
                is_roller = ImageService.exists(Onmyoji.shack_RWGL)
                logger.debug("任务加号坐标")
                is_plus = ImageService.exists(Onmyoji.shack_RWJH)
                logger.debug("滑动四个滚轮，加满材料")
                if is_mine and is_roller and is_plus:
                    logger.debug("天雷鼓")
                    v1 = (is_roller[0], is_mine[1])
                    v2 = (is_plus[0], is_mine[1])
                    ImageService.swipe(v1, v2)
                if is_mine and is_roller and is_plus:
                    logger.debug("天雷鼓")
                    v1 = (is_roller[0], is_mine[1])
                    v2 = (is_plus[0], is_mine[1])
                    ImageService.swipe(v1, v2)
                if is_fire and is_roller and is_plus:
                    logger.debug("业火轮")
                    v1 = (is_roller[0], is_fire[1])
                    v2 = (is_plus[0], is_fire[1])
                    ImageService.swipe(v1, v2)
                if is_water and is_roller and is_plus:
                    logger.debug("水灵鲤")
                    v1 = (is_roller[0], is_water[1])
                    v2 = (is_plus[0], is_water[1])
                    ImageService.swipe(v1, v2)
                if is_wind and is_roller and is_plus:
                    logger.debug("风转符")
                    v1 = (is_roller[0], is_wind[1])
                    v2 = (is_plus[0], is_wind[1])
                    ImageService.swipe(v1, v2)
                logger.debug("判断是否获取到所需全部坐标")
                if is_roller and is_plus and is_mine and is_fire and is_water and is_wind:
                    logger.debug("提交")
                    ImageService.touch(Onmyoji.shack_TJCL)
                    logger.debug("获取奖励，2次")
                    ComplexService.get_reward(Onmyoji.shack_HDJL)
                    ComplexService.get_reward(Onmyoji.shack_HDJL)
                else:
                    logger.debug("全部坐标获取失败，尝试返回寮首页")
                    ComplexService.get_reward(is_fire)
        logger.debug("返回到寮首页")
        ImageService.touch(Onmyoji.comm_FH_YSJHDBSCH)
    logger.debug("4.体力食盒和经验酒壶")
    is_border = ImageService.touch(Onmyoji.shack_JJTB)
    if is_border:
        logger.debug("进入结界")
        logger.debug("判断是否有体力待领取")
        is_food_box = ImageService.touch(Onmyoji.shack_TLSH)
        if is_food_box:
            logger.debug("领取体力")
            ImageService.touch(Onmyoji.shack_QCTL)
            logger.debug("获得奖励")
            ComplexService.get_reward(Onmyoji.shack_HDJL)
            logger.debug("点击可能存在的返回")
            ImageService.touch(Onmyoji.comm_FH_YSJZDHBSCH, rgb=True)
        logger.debug("判断是否有经验待领取")
        is_wine_pot = ImageService.touch(Onmyoji.shack_JYJH)
        if is_wine_pot:
            logger.debug("领取经验")
            ImageService.touch(Onmyoji.shack_TQJY)
            logger.debug("点击可能存在的确定")
            ImageService.touch(Onmyoji.shack_QD)
            logger.debug("点击可能存在的返回")
            ImageService.touch(Onmyoji.comm_FH_YSJZDHBSCH, rgb=True)
    logger.debug("5.结界卡奖励领取")
    is_card_rewards = ImageService.touch(Onmyoji.shack_JJKJLLQ)
    if is_card_rewards:
        logger.debug("获得奖励")
        ComplexService.get_reward(Onmyoji.shack_HDJL)
    logger.debug("6.寄养奖励领取")
    is_faster_rewards = ImageService.touch(Onmyoji.shack_JYJLLQ)
    if is_faster_rewards:
        logger.debug("获得奖励")
        ComplexService.get_reward(Onmyoji.shack_HDJL)
    logger.debug("7.结界卡放置")
    is_border_card = ImageService.touch(Onmyoji.shack_JJK)
    if is_border_card:
        logger.debug("判断是否无结界卡")
        is_border_card = ImageService.touch(Onmyoji.shack_JJKDQ)
        if is_border_card:
            logger.debug("点击全部")
            ImageService.touch(Onmyoji.shack_JJKQBXL)
            logger.debug("点击下拉太鼓")
            ImageService.touch(Onmyoji.shack_XLTG)
            logger.debug("点击太鼓")
            is_place = ImageService.touch(Onmyoji.shack_TG)
            if is_place:
                logger.debug("激活")
                ImageService.touch(Onmyoji.shack_JH)
                logger.debug("返回")
                ImageService.touch(Onmyoji.comm_FH_YSJHDBSCH)
            else:
                logger.debug("点击全部")
                ImageService.touch(Onmyoji.shack_JJKQBXL)
                logger.debug("点击下拉斗鱼")
                ImageService.touch(Onmyoji.shack_XLDY)
                logger.debug("点击斗鱼")
                is_place = ImageService.touch(Onmyoji.shack_DY)
                if is_place:
                    logger.debug("激活")
                    ImageService.touch(Onmyoji.shack_JH)
                    logger.debug("返回")
                    ImageService.touch(Onmyoji.comm_FH_YSJHDBSCH)
                else:
                    logger.debug("点击全部")
                    ImageService.touch(Onmyoji.shack_JJKQBXL)
                    logger.debug("点击下拉太阴")
                    ImageService.touch(Onmyoji.shack_XLTY)
                    logger.debug("点击太阴")
                    ImageService.touch(Onmyoji.shack_TY)
                    logger.debug("返回")
                    ImageService.touch(Onmyoji.comm_FH_YSJHDBSCH)
        else:
            logger.debug("已有结界卡，返回")
            ImageService.touch(Onmyoji.comm_FH_YSJHDBSCH)
    logger.debug("8.式神育成")
    is_care = ImageService.touch(Onmyoji.shack_SSYC)
    if is_care:
        logger.debug("判断是否有满")
        is_full = ImageService.exists(Onmyoji.shack_MZ, cvstrategy=Cvstrategy.default)
        if is_full:
            for i_full in range(8):
                logger.debug("点击满字，去掉已满的式神")
                is_full = ImageService.touch(Onmyoji.shack_MZ, cvstrategy=Cvstrategy.default)
                if not is_full:
                    logger.debug("已无经验已满的式神")
                    break
        logger.debug("判断是否有放入式神")
        is_insert = ImageService.exists(Onmyoji.shack_FRSS)
        if is_insert:
            logger.debug("点击左下的全部")
            ImageService.touch(Onmyoji.shack_ZXQB)
            logger.debug("点击左下的素材")
            ImageService.touch(Onmyoji.shack_SCSC)
            is_white_egg = ImageService.exists(Onmyoji.shack_BD)
            if is_white_egg:
                logger.debug("点击白蛋，至少6次")
                for i_growing in range(8):
                    logger.debug("点击1级白蛋")
                    ImageService.touch_coordinate(is_white_egg)
                    if i_growing > 5:
                        logger.debug("判断是否还有放入式神")
                        is_insert = ImageService.exists(Onmyoji.shack_FRSS)
                        if not is_insert:
                            logger.debug("已全部寄养")
                            break
            else:
                logger.debug("没找1级白蛋")
    logger.debug("返回首页")
    ImageService.touch(Onmyoji.comm_FH_ZSJLDYXBSXYH)
    ImageService.touch(Onmyoji.comm_FH_ZSJLDYXBSXYH)
    ImageService.touch(Onmyoji.comm_FH_ZSJLDYXBSXYH)
    ImageService.touch(Onmyoji.comm_FH_ZSJHKZDHSXYH)
    logger.debug("确认返回首页")
    impl_initialization.return_home(game_task)
    time_end = time.time()
    time_time = round(time_end - time_start, 3)
    logger.debug("本次寮管理，用时{}秒", time_time)
