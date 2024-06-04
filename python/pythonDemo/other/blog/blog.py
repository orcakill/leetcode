# -*- coding:utf-8 -*-
# @time: 2021/5/20 5:20
# @Author: 韩国麦当劳
# @Environment: Python 3.7
# @file: 有情人终成眷属.py
import requests
import csv
import time
import json


def get_html(url):
    headers = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36",
        "Referer": "https://weibo.com"
    }
    cookies = {
        "cookie": "UOR=www.baidu.com,open.weibo.com,www.baidu.com; SINAGLOBAL=9279525142779.576.1635697651627; XSRF-TOKEN=G0zz8IRxTqeJ3JRtuFKER4g-; login_sid_t=2b77533a51025876d2527b9a0bb2f2f9; cross_origin_proto=SSL; wb_view_log=1707*10671.5; _s_tentry=weibo.com; Apache=9966610533186.346.1696153507739; ULV=1696153507741:5:1:1:9966610533186.346.1696153507739:1672195354675; PC_TOKEN=d9f07fe403; SCF=Au6W77cnoE4bGJoPSn_ahZoZGS8QYegJbe3VaxICKFKd2HUQjOaVkukgqma7tg2WpcJePG4QmVWNT9RIR-KQ9q0.; SUB=_2A25IHTCCDeRhGeBO6VMQ8CrEzj-IHXVrayVKrDV8PUNbmtAGLWXwkW9NSjkoTyxZsq64LxZbqc5yvD3_HoHGAklA; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WhPgGD0IZpcQYupL7HKna9D5JpX5KzhUgL.Foq7eo2pehBRSKe2dJLoIEBLxK.L1h5L1hqLxKqL1hnL1-qLxKBLBo.L12zLxK-L1hnL1het; ALF=1727689811; SSOLoginState=1696153810; WBPSESS=VVInkE8AnJSp57XwmCQJ_utpPzQaaQdkJdN1ofwPMT47gGiZEF6ZC6Gf0ogfwCBY1vnPDVPfjsHJ8_0DYr3aKGfOWvWK8wq4--5Q4bZ0LBjvArIg7XryAb2CWqD07avCJrHzncDUdMcwZPY9uNl6Vw=="
    }
    response = requests.get(url, headers=headers, cookies=cookies)
    time.sleep(3)  # 加上3s 的延时防止被反爬
    return response.text


def save_data(data):
    title = ['text_raw', 'created_at', 'attitudes_count', 'comments_count', 'reposts_count']
    with open("data.csv", "a", encoding="utf-8", newline="") as fi:
        fi = csv.writer(fi)
        fi.writerow([data[i] for i in title])


if __name__ == '__main__':

    uid = 1240631574
    url = 'https://weibo.com/ajax/statuses/mymblog?uid={}&page={}&feature=0'
    page = 1
    while 1:
        print(page)
        url = url.format(uid, page)
        html = get_html(url)
        responses = json.loads(html)
        blogs = responses['data']['list']
        if len(blogs) == 0:
            break
        data = {}  # 新建个字典用来存数据
        for blog in blogs:
            data['attitudes_count'] = blog['attitudes_count']  # 点赞数量
            data['comments_count'] = blog['comments_count']  # 评论数量(超过100万的只会显示100万)
            data['created_at'] = blog['created_at']  # 发布时间
            data['reposts_count'] = blog['reposts_count']  # 转发数量(超过100万的只会显示100万)
            data['text_raw'] = blog['text_raw']  # 博文正文文字数据
            save_data(data)
        page += 1
