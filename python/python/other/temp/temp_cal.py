# @Time: 2024年03月12日 16:57
# @Author: orcakill
# @File: temp_cal.py
# @Description: TODO
from workalendar.asia import China

if __name__ == '__main__':


    cal = China()
    holidays = cal.holidays(2023)
    print(holidays)