package me.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/perfect-squares/
 */
public class Problem279 {

    public int numSquares(int n) {
        List<Integer> squares = getSquares(n);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        boolean[] marks = new boolean[n + 1];
        marks[n] = true;
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            while (size > 0) {
                int num = queue.poll();
                for (Integer square : squares) {
                    int next = num - square;
                    if (next < 0) {
                        break;
                    }
                    if (next == 0) {
                        return level;
                    }
                    if (marks[next]) {
                        continue;
                    }
                    queue.add(next);
                    marks[next] = true;
                }
                size--;
            }
        }
        return n;
    }

    public List<Integer> getSquares(int n) {
        List<Integer> squares = new ArrayList<>();
        int i = 1;
        int step = 3;
        while (i <= n) {
            squares.add(i);
            i += step;
            step += 2;
        }
        return squares;
    }

    public static void main(String[] args) {
        Problem279 problem = new Problem279();
        int result = problem.numSquares(13);
        System.out.println(result);
    }

}
