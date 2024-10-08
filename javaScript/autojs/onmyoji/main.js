// 检查登录状态，已登录账号不参与脚本执行
// 每一个时间段为一个事件，每一个流程为一个任务
// 执行任务前，检查该账号是否有可执行该任务的权限
// 每次事件清理完成，且未到下一事件，且周活动模式为开启状态，则执行活动战斗，如距离下一次事件有10分钟，则中断活动战斗，准备进入下一事件
// 1 小号日常（体服小号1个->正式服小号3个，云手机）   0点--清理个人结界-御魂20次-业原火-日轮之陨-永生之海-探索5次-活动-领取每日奖励-获取周奖励-周竞速-清理御魂
//                                                12点-周斗技+寮结界+活动
//                                                17点-逢魔--地域鬼王+活动
//                                                19点-麒麟-退治-道馆-狩猎战-买首领御魂+活动（优先级最高的小号处理，其他小号不上）                                
//                                                21点-寮结界-活动                                              
// 2  大号日常（体服大号1个->正式服大号，本机）       0点--清理个人结界-御魂20次-业原火-日轮之陨-永生之海-探索5次-活动-领取每日奖励-获取周奖励-周竞速-清理御魂
//                                                7点-寮结界-活动（每半小时清理一次，上一事件未执行则执行上一事件） 
//                                                12点-周斗技+寮结界-活动
//                                                17点-逢魔--地域鬼王-活动
//                                                19点-麒麟-退治-道馆-狩猎战-买首领御魂-活动（根据录入的任务时间进行，换寮会出现变化）
//                                                21点-宴会-活动（根据录入的任务时间进行，换寮会出现变化）
// 3  御魂      账号选择，默认1轮，默认30次，可选60,120，均开加成，小号魂十，大号魂十一，小号优先使用协战式神，计算各个账号的攻打次数，战斗时长，胜率
// 4  个人结界  账号选择，默认1轮，默认30次，全清理提前结束，计算各个账号的攻打次数，战斗时长，胜率 
// 5  寮结界    账号选择，默认1轮，默认8次，全清理提前结束，计算各个账号的攻打次数，战斗时长，胜率
// 6  探索      账号选择，默认1轮，默认3次，可选10,30
// 7  业原火    账号选择，默认1轮，默认100次，全清理提前结束
// 8  日轮之陨  账号选择，默认1轮，默认50次，全清理提前结束
// 9  永生之海  账号选择，默认1轮，默认30次，全清理提前结束，均开加成
// 10 御灵      账号选择，默认1轮，默认60次，可选120
// 11 斗技      账号选择，默认1轮，默认10次，计算各个账号的攻打次数，战斗时长，胜率
// 12 清理御魂  账号选择，默认1轮，(1)贪吃鬼手动执行，清理4星御魂
//                               (2)二号位速度强化
//                               (3)其他位置速度强化
//                               (4)极限副属性强化
//                               (5)不足4条副属性弃置
//                               (6)5星御魂弃置
//                               (7)弃置御魂清理
// 访问服务端，获取全部账号信息，显示默认挑战类型，默认轮次，默认次数
toast('Hello,Auto.js');