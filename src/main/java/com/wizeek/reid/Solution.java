package com.wizeek.reid;

public class Solution {
    public static String solution(int i) {
        // Your code here
        String s = buildPrimeString(10000 + 5);
        return s.substring(i, i + 5);
    }

    private static String buildPrimeString(int length) {
        StringBuilder sb = new StringBuilder();
        int n = 2;
        while (sb.length() < length) {
            while (!isPrime(n)) {
                n++;
            }
            sb.append(n++);
        }
        return sb.toString();
    }

    private static boolean isPrime(int n) {
        for (int d = 2; d < n; d++) {
            if (n % d == 0) {
                return false;
            }
        }
        return true;
    }
}