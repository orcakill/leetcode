import pytesseract
from PIL import Image
# 读取图片
im = Image.open("D://图片对比//123.jpg")
# 识别文字
string = pytesseract.image_to_string(im, lang="chi_sim")
print(string)