import java.util.*;

class Solution {
    public int solution(int[] A) {
        HashMap<Integer, int[]> candidates2 = new HashMap<>();
        boolean atLeastOneEquiLeader = false;
        int dominator = A[0];
        for (int i = 0; i < A.length; i++) {
            if (candidates2.containsKey(A[i])) {
                int[] increment = {candidates2.get(A[i])[0], candidates2.get(A[i])[1] + 1};
                candidates2.put(A[i], increment);
                if (candidates2.get(A[i])[1] > (double) A.length / 2) {
                    atLeastOneEquiLeader = true;
                    dominator = A[i];
                }
            } else {
                int[] initialiser = {i, 1};
                candidates2.put(A[i], initialiser);
            }
        }
        if (A.length == 1 || !atLeastOneEquiLeader) {
            return (0);
        }
        System.out.println(dominator);
        int dominatorCopies1 = 0;
        int dominatorCopies2 = candidates2.get(dominator)[1];
        int numberEquiLeaders = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == dominator) {
                dominatorCopies1++;
                dominatorCopies2--;
            }
            if (dominatorCopies1 > (double) (i + 1) / 2 && dominatorCopies2 > (double) (A.length - i - 1) / 2) {
                numberEquiLeaders++;
            }
        }
        return (numberEquiLeaders);
    }

    public static void main(String[] args) {
        Solution s = new
                Solution();
        System.out.println(s.solution(new int[]{4, 3, 4, 4, 4, 2}));
        System.out.println(s.solution(new int[]{4, 4}));
        System.out.println(s.solution(new int[]{1, 2}));
        System.out.println(s.solution(new int[]{4, 4, 2, 5, 3, 4, 4, 4}));
    }
}