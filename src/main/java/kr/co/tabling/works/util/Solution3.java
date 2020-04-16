package kr.co.tabling.works.util;// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

class Solution3 {

    public int solution(int[] A) {

        Map<Integer, AtomicInteger> result = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            int k = Math.abs(A[i]);
            if (!result.containsKey(k)) {
                result.put(k, new AtomicInteger(A[i]));
            } else {
                result.get(k).addAndGet(A[i]);
            }
        }

        int K = 0;

        for (Map.Entry<Integer, AtomicInteger> entry : result.entrySet()) {
            int value = entry.getValue().get();
            if (value == 0) {
                K = Math.max(K, entry.getKey());
            }
        }
        return K;
    }
}