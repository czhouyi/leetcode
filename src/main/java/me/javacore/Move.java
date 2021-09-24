package me.javacore;

public class Move {

    public static int neg(int a) {
        return ~(a-1);
    }

    public static void main(String[] args) {
        System.out.println((char)(1+'0'));
        System.out.println(5 >> 1);
        System.out.println(5 >>> 1);
        System.out.println(-5 >> 1);
        System.out.println(-5 >>> 1);
        System.out.println(5 << 1);


        System.out.println(Integer.toBinaryString(5));
        System.out.println(Integer.toBinaryString(-5));
        System.out.println(~5);
        System.out.println(~4);
    }
}
