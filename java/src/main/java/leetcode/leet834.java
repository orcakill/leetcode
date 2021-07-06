package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leet834 {
    public static void main(String[] args) {
        int N = 6;
        int[][] edges = {{0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}};
        System.out.println(Arrays.toString(sumOfDistancesInTree(N, edges)));
    }

    static int[] ans;
    static int[] sz;
    static int[] dp;
    static List<List<Integer>> graph;

    public static int[] sumOfDistancesInTree(int N, int[][] edges) {
        ans = new int[N];
        sz = new int[N];
        dp = new int[N];
        graph = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        dfs(0, -1);
        dfs2(0, -1);
        return ans;
    }

    public static void dfs(int u, int f) {
        sz[u] = 1;
        dp[u] = 0;
        for (int v : graph.get(u)) {
            if (v == f) {
                continue;
            }
            dfs(v, u);
            dp[u] += dp[v] + sz[v];
            sz[u] += sz[v];
        }
    }

    public static void dfs2(int u, int f) {
        ans[u] = dp[u];
        for (int v : graph.get(u)) {
            if (v == f) {
                continue;
            }
            int pu = dp[u], pv = dp[v];
            int su = sz[u], sv = sz[v];

            dp[u] -= dp[v] + sz[v];
            sz[u] -= sz[v];
            dp[v] += dp[u] + sz[u];
            sz[v] += sz[u];

            dfs2(v, u);

            dp[u] = pu;
            dp[v] = pv;
            sz[u] = su;
            sz[v] = sv;
        }
    }
}





