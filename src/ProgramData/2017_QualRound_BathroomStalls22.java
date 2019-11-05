package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

public class ProbC {
    private Scanner scanner = new Scanner(System.in);

    private void reDirect() {
        try {
            FileInputStream fileInputStream = new FileInputStream("C-large.in");
            //System.setIn(fileInputStream);
            scanner = new Scanner(fileInputStream);
            ///*
            PrintStream ps = new PrintStream(new FileOutputStream("c-out-3.txt"));
            System.setOut(ps);
            //*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void main() {
        reDirect();
        int T = scanner.nextInt();
        for (int cas = 1; cas <= T; cas++) {
            long[] ans = run();
            System.out.println("Case #" + cas + ": " + ans[0] + " " + ans[1]);
        }
    }

    private long[] run() {
        long n = scanner.nextLong();
        long k = scanner.nextLong();

        Queue<Long> q = new PriorityQueue<Long>(11, (a,b) -> Long.compare(b, a));
        Map<Long, Long> count = new HashMap<>();

        q.offer(n);
        count.put(n, 1L);

        while (!q.isEmpty()) {
            long num = q.poll();
            long cc = count.get(num);
            long left = (num - 1) / 2;
            long right = num / 2;
            if (k <= cc) {
                return new long[] {right, left};
            }
            k -= cc;
            if (count.containsKey(left)) {
                count.put(left, count.get(left) + cc);
            }
            else {
                count.put(left, cc);
                q.offer(left);
            }

            if (count.containsKey(right)) {
                count.put(right, count.get(right) + cc);
            }
            else {
                count.put(right, cc);
                q.offer(right);
            }
        }
        return null;
    }
}
