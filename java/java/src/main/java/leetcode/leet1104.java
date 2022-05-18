package leetcode;

import leetclass.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class leet1104 {
    public static void main(String[] args) {
        int a1=14;
        System.out.println(pathInZigZagTree(a1));
    }
    public  static List<Integer> pathInZigZagTree(int label) {
        int row = 1, rowStart = 1;
        while (rowStart * 2 <= label) {
            row++;
            rowStart *= 2;
        }
        if (row % 2 == 0) {
            label = getReverse(label, row);
        }
        List<Integer> path = new ArrayList<>();
        while (row > 0) {
            if (row % 2 == 0) {
                path.add(getReverse(label, row));
            } else {
                path.add(label);
            }
            row--;
            label >>= 1;
        }
        Collections.reverse(path);
        return path;
    }

    public  static int getReverse(int label, int row) {
        return (1 << row - 1) + (1 << row) - 1 - label;
    }



}
