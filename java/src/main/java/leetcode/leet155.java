package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author orcakill
 * @date 2021/4/6  11:21
 **/
public class leet155 {
    static class MinStack {
        final Deque<Integer> xStack;
        final Deque<Integer> minStack;

        public MinStack() {
            xStack = new LinkedList<>();
            minStack = new LinkedList<>();
            minStack.push(Integer.MAX_VALUE);
        }

        public void push(int x) {
            xStack.push(x);
            minStack.push(Math.min(minStack.peek(), x));
        }

        public void pop() {
            xStack.pop();
            minStack.pop();
        }

        public int top() {
            return xStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

}
