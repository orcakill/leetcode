package leetcode;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


public class leet841_1 {
    public static void main(String[] args) {
        int[][] a = {{1}, {2}, {3}, {}};
        int[][] b = {{1, 3}, {3, 0, 1}, {2}, {0}};
        int[] c = {1, 2, 3};
        int[][] d = {{1, 3}, {3, 0, 1}, {2}, {0}};
        int[][] e = {{4}, {3}, {}, {2, 5, 7}, {1}, {}, {8, 9}, {}, {}, {6}}; /* 0   1   2   3      4   5   6    7  8   9*/
        int[][] f = {{6, 7, 8}, {5, 4, 9}, {}, {8}, {4}, {}, {1, 9, 2, 3}, {7}, {6, 5}, {2, 3, 1}}; /* 0      1     2  3   4  5   6         7    8      9*/

    }

    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if(rooms.size()==1) {
            return true;
        }
        HashSet<Integer> set = new HashSet<>();
        LinkedList<Integer> list = new LinkedList<>();
        int length = rooms.size();
        list.add(0);
        set.add(0);
        while(!list.isEmpty()) {
            int room_id = list.poll();
            List<Integer> roomId_list = rooms.get(room_id);
            for(int i:roomId_list) {
                if(set.contains(i)) {
                    continue;
                }
                set.add(i);
                list.add(i);
            }
        }
        int flag = 0;
        for(int j=0;j<length;j++) {
            if(!set.contains(j)) {
                flag = 1;
                break;
            }
        }
        return flag==0;
    }
}
