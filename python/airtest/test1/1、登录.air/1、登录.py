# -*- encoding=utf8 -*-
from airtest.core.api import *
from airtest.core.settings import Settings as ST
import datetime



auto_setup(__file__)
now=datetime.datetime.now()

touch(Template(r"tpl1720065276467.png", record_pos=(0.37, 0.907), resolution=(445, 993)))



now1=datetime.datetime.now()
print(now1-now)







