import easyocr

if __name__ == '__main__':
    reader=easyocr.Reader(['ch_sim','en'])
    result=reader.readtext('./resources/a.jpg')
