# -*- encoding=utf8 -*-
from airtest.core.api import *
import datetime

auto_setup(__file__)
now=datetime.datetime.now()


screen = G.DEVICE.snapshot()

now1=datetime.datetime.now()
print(1)
print(now1-now)




