package leetcode;

import java.util.ArrayList;
import java.util.List;

public class leet802 {
    public static void main(String[] args) {
        int[][] a1 = {{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}};
        System.out.println(eventualSafeNodes(a1));
    }

    public static  List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (safe(graph, color, i)) {
                ans.add(i);
            }
        }
        return ans;
    }

    public  static boolean safe(int[][] graph, int[] color, int x) {
        if (color[x] > 0) {
            return color[x] == 2;
        }
        color[x] = 1;
        for (int y : graph[x]) {
            if (!safe(graph, color, y)) {
                return false;
            }
        }
        color[x] = 2;
        return true;
    }


}
