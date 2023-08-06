# -*- encoding=utf8 -*-
from airtest.core.api import *
from airtest.core.settings import Settings as ST
import datetime



auto_setup(__file__)
now=datetime.datetime.now()

touch(Template(r"tpl1691080454624.png", record_pos=(0.029, -0.165), resolution=(1280, 720)))


now1=datetime.datetime.now()
print(now1-now)







