# -*- encoding=utf8 -*-
from airtest.core.api import *
from airtest.core.settings import Settings as ST
import datetime



auto_setup(__file__)
now=datetime.datetime.now()
snapshot(msg="测试")



now1=datetime.datetime.now()
print(now1-now)







