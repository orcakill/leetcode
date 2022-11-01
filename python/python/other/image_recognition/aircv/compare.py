import aircv

if __name__ == '__main__':
    im_src = aircv.imread('./resources/a.jpg')
    im_obj = aircv.imread('./resources/b.png')

    aircv_ret = aircv.find_template(im_src, im_obj, 0.5)
    print('结果:{}'.format(aircv_ret))
    print('demo尺寸:{}'.format(im_src.shape))
    print('坐标：{}'.format(aircv_ret['result']))
    print('原图尺寸:{}'.format([im_src.shape[1], im_src.shape[0]]))
    print('坐标%:{}'.format([aircv_ret['result'][0] / im_src.shape[1],
                             aircv_ret['result'][1] / im_src.shape[0]
                             ]))
