package kr.co.tabling.works.util;

import java.util.Arrays;

public class Solution4 {

    int solution(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        ;
        Arrays.sort(A);
        Arrays.sort(B);
        int i = 0;
        for (int k = 0; k < n; k = A[k] > B[i] && i < m - 1 ? k : k + 1) {
            if (i < m - 1 && B[i] < A[k])
                i += 1;
            if (A[k] == B[i])
                return A[k];
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution4 s = new Solution4();
        System.out.println(s.solution(new int[]{1, 3, 2, 1}, new int[]{4, 2, 5, 3, 2}));
        System.out.println(s.solution(new int[]{4, 2, 5, 3, 2}, new int[]{1, 3, 2, 1}));

        System.out.println(s.solution(new int[]{4, 2, 5, 3, 2}, new int[]{1, 1, 2, 1}));

        System.out.println(s.solution(new int[]{2, 1}, new int[]{3, 3}));
        System.out.println(s.solution(new int[]{3, 3}, new int[]{2, 1}));
    }
}
