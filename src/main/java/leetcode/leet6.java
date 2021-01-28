package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author orcakill
 * @date 2021/1/21  13:30
 **/
public class leet6 {
    public static void main(String[] args) throws Exception {
        String  str="PAYPALISHIRING";

        System.out.println(convert(str,3));
    }


    public static String convert(String s, int numRows) {
        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();

    }
}
