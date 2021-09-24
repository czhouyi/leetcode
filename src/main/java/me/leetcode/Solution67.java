package me.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Solution67 {
    public String addBinary(String a, String b) {
        int lena = a.length(), lenb = b.length();
        int len = Math.max(lena, lenb);

        int carry = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= len - 1; i++) {
            int cai = 0;
            int cbi = 0;
            if (lena - i - 1 >= 0) {
                char c = a.charAt(lena - i - 1);
                cai = c - '0';
            }
            if (lenb - i - 1 >= 0) {
                char c = b.charAt(lenb - i - 1);
                cbi = c - '0';
            }
            list.add((carry+cai+cbi) % 2);
            carry = (carry+cai+cbi) / 2;
        }
        if (carry > 0) {
            list.add(carry);
        }

        int l = list.size();
        char[] array = new char[l];
        for(int i = l - 1; i >= 0; i--) {
            array[l-i-1] = (char)(list.get(i) + '0');
        }
        return new String(array);
    }

    public static void main(String[] args) {
        Solution67 solution67 = new Solution67();
        String result = solution67.addBinary("101", "1100");
        System.out.println(result);
    }
}