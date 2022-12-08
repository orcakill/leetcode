class Solution:
    def num_different_integers(self, word: str) -> int:
        setList = set()
        s1 = ""
        for i in range(0, len(word)):
            s = word[i:i + 1:1]
            if Solution.is_number(0, s):
                s1 = s1 + s
            else:
                if Solution.is_number(0, s1):
                    setList.add(float(s1))
                    s1 = ""
            if Solution.is_number(0, s1) and i == len(word) - 1:
                setList.add(float(s1))
        return len(setList)

    def is_number(self, s: str) -> bool:
        """判断字符串是否为数字"""
        try:
            float(s)
            return True
        except ValueError:
            return False


if __name__ == '__main__':
    # print(Solution.num_different_integers(0, 'a123bc34d8ef34'))
    # print(Solution.num_different_integers(0, 'leet1234code234'))
    # print(Solution.num_different_integers(0, 'a1b01c001'))
    print(Solution.num_different_integers(0, '239370688023611040705962469696782876275265198273011522169043782150822941'
                                             '9410771541532394006597463715513741725852432559057224478815116557380260390'
                                             '43221122757966357104684584228170428174957111007697426497198989360713714045'
                                             '62543469556334554460578237387573231498568581545291053011973881772425836586'
                                             '41529908583934918768953462557716z9743802042995294464628808417333470104757'
                                             '41889362013248451491101767161302670416744382376080387344315194398281913442'
                                             '3860956753039918931684635976625650737124053062069710286423879235028997845'
                                             '050916269706894860472264673917459053033651047506152109450385059845353670'
                                             '6982695212493902968251702853203929616930291257062173c794872819006623438306'
                                             '48295410'))
