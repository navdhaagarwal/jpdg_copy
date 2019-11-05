package codejampractice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author boeingtuan
 */
public class BathStall {

    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        long t = sc.nextLong();
        long cnt = 0;
        while (t-- > 0) {
            cnt++;
            long n = sc.nextLong();
            long k = sc.nextLong();
            MinMax res = f(k, n);
            System.out.println("Case #" + cnt + ": " + res.max + " " + res.min);
        }
    }

    public static MinMax f(long k, long N) {
//        if (k > (N / 2 + N % 2)) {
//            return new MinMax(0, 0);
//        }
        if (k == 1) {
            if (N % 2 == 0) {
                return new MinMax(N / 2, (N / 2) - 1);
            } else {
                return new MinMax((N - 1) / 2, (N - 1) / 2);
            }
        } else {
            long par = k % 2 == 0 ? k / 2 : (k - 1) / 2;
            if (N % 2 == 0) {
                if (k % 2 == 0) {
                    return f(par, N / 2);
                } else {
                    return f(par, (N / 2) - 1);
                }
            } else {
                return f(par, (N - 1) / 2);
            }
        }
    }

    public static class MinMax {

        long min;
        long max;

        public MinMax(long max, long min) {
            this.min = min;
            this.max = max;
        }

    }
}
