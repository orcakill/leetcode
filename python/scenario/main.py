import threading
import time

from resources.my_logger import MyLogger


class FirstThread:

    def first_thread(self):
        log.info("线程%s运行开始" % self)
        time.sleep(2)
        log.info("线程%s运行结束" % self)


class SecondThread:

    def second_thread(self):
        log.info("线程%s运行开始" % self)
        time.sleep(2)
        log.info("线程%s运行结束" % self)


# 按间距中的绿色按钮以运行脚本。
if __name__ == '__main__':
    log = MyLogger().get_logger()
    t1 = threading.Thread(target=FirstThread.first_thread, args=(1,))
    t2 = threading.Thread(target=SecondThread.second_thread, args=(2,))
    t1.start()
    t2.start()
