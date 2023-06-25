# -*- encoding=utf8 -*-
from airtest.core.api import *
from airtest.core.settings import Settings as ST
import datetime

# 指定仅使用mstpl算法
ST.CVSTRATEGY = ["sift"]

auto_setup(__file__)
print(1)
now=datetime.datetime.now()

exists(Template(r"tpl1687653092891_0.369_-0.105_1920_1080.png"))

now1=datetime.datetime.now()
print(1)
print(now1-now)
now2=datetime.datetime.now()

exists(Template(r"tpl1687653092891_0.369_-0.105_1920_1080.png"))

now3=datetime.datetime.now()
print(1)
print(now3-now2)
print(now3-now)




