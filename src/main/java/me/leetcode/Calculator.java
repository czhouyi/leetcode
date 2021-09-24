package me.leetcode;

public class Calculator {

    public int plus(int a, int b) {
        if (a == 0) {
            return b;
        }
        return plus((a & b) << 1, a ^ b);
    }

    public int subtract(int a, int b) {
        return plus(a, ~b + 1);
    }

    public int multiply(int a, int b) {
        int ans = 0, i = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
               ans += a << i;
            }
            b = b >>> 1;
            i++;
        }
        return ans;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException(" / zero");
        }
        if (a == 0) {
            return 0;
        }
        int negative = (a > 0 && b < 0) || (a < 0 && b > 0) ? -1 : 1;
        a = Math.abs(a);
        b = Math.abs(b);
        int ans = 0;
        while (a > b) {
            a = subtract(a, b);
            ans ++;
        }
        return ans * negative;
    }

    public void testPlus(int a, int b) {
        System.out.printf("%d+%d=%d\n", a, b, plus(a, b));
    }
    public void testSubtract(int a, int b) {
        System.out.printf("%d-%d=%d\n", a, b, subtract(a, b));
    }
    public void testMultiply(int a, int b) {
        System.out.printf("%d*%d=%d\n", a, b, multiply(a, b));
    }
    public void testDivide(int a, int b) {
        System.out.printf("%d/%d=%d\n", a, b, divide(a, b));
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.testPlus(2, 2);
        calculator.testPlus(3, 102);
        calculator.testPlus(88888, 999999);
        calculator.testPlus(-1, -2);
        calculator.testPlus(10, -2);
        calculator.testPlus(0, 2);
        calculator.testPlus(0, -42);
        calculator.testMultiply(3, -4444);
        calculator.testSubtract(3, 100);
        calculator.testSubtract(332, 100);
        calculator.testSubtract(332, -88);
        calculator.testDivide(32222, 232);
        calculator.testDivide(4234223, -4444);
    }
}
