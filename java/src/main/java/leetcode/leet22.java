package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName lee22.java
 * @Description 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
                有效括号组合需满足：左括号必须以正确的顺序闭合
 * @createTime 2021年11月05日 09:08:00
 */
public class leet22 {
	public static void main(String[] args) {
		int n=3;
		System.out.println (generateParenthesis(n));
	}
	
	static List<String> generateParenthesis(int n) {
		List<String> combinations = new ArrayList<String> ();
		generateAll(new char[2 * n], 0, combinations);
		return combinations;
	}
	
	static  void generateAll(char[] current, int pos, List<String> result) {
		if (pos == current.length) {
			if (valid(current)) {
				result.add(new String(current));
			}
		} else {
			current[pos] = '(';
			generateAll(current, pos + 1, result);
			current[pos] = ')';
			generateAll(current, pos + 1, result);
		}
	}
	
	static boolean valid(char[] current) {
		int balance = 0;
		for (char c: current) {
			if (c == '(') {
				++balance;
			} else {
				--balance;
			}
			if (balance < 0) {
				return false;
			}
		}
		return balance == 0;
	}
	

}
