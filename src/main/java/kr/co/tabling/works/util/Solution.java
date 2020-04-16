package kr.co.tabling.works.util;

public class Solution {

    public final static String IMPOSSIBLE = "IMPOSSIBLE";

    public String solution(int U, int L, int[] C) {
        if (isInRange(U, 0, 100000)) {
            return Solution.IMPOSSIBLE;
        }

        if (isInRange(L, 0, 100000)) {
            return Solution.IMPOSSIBLE;
        }

        if (C == null || isInRange(C.length, 1, 100000)) {
            return Solution.IMPOSSIBLE;
        }

        int upperRemaining = U;
        int lowerRemaining = L;

        char[] upperRow = new char[C.length];
        char[] lowerRow = new char[C.length];

        for (int i = 0; i < C.length; i++) {
            if (C[i] == 2) {
                upperRow[i] = '1';
                lowerRow[i] = '1';
                upperRemaining--;
                lowerRemaining--;
            } else if (C[i] == 0) {
                upperRow[i] = '0';
                lowerRow[i] = '0';
            } else if (C[i] == 1) {
                if (upperRemaining > 0) {
                    upperRow[i] = '1';
                    lowerRow[i] = '0';
                    upperRemaining--;
                } else if (lowerRemaining > 0) {
                    lowerRow[i] = '1';
                    upperRow[i] = '0';
                    lowerRemaining--;
                } else {
                    return Solution.IMPOSSIBLE;
                }
            } else {
                return Solution.IMPOSSIBLE;
            }

        }

        if (upperRemaining != 0 || lowerRemaining != 0) {
            return Solution.IMPOSSIBLE;
        }
        return String.valueOf(upperRow) + ',' + String.valueOf(lowerRow);
    }

    private boolean isInRange(int i, int lowerBound, int upperBound) {
        return i < lowerBound || i > upperBound;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(2, 3, new int[]{2, 1, 2}));

    }


}
