package UpdatedData;
import java.util.*;
import java.io.*;
public class Test_86 {
    private class Pair {
        long x1; /* Bigger number of the pair */
        long x0; /* Smaller number of the pair */
        public void setPair(long x1, long x0) {
            this.x1 = x1;
            this.x0 = x0;
        }
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            long n = in.nextLong();
            long k = in.nextLong();
            Pair res = (new Test_86()).solve(n, k);
            System.out.println("Case #" + i + ": " + res.x1 + " " + res.x0);
        }
    }
    
    public Pair solve(long n, long k) {
        Pair res = new Pair();
        /*
        if (k >= n * 2 / 3) {
            return res;
        }
        */
        PriorityQueue<Long> pq = new PriorityQueue<>(1000, Collections.reverseOrder());
        pq.offer(n);
        for (int i = 1; i <= k; i++) {
            if (pq.isEmpty())
                break;
            long tmp = pq.poll();
            long x0 = (tmp - 1) / 2;
            long x1 = tmp - 1 - x0;
            //System.out.print(tmp + " " + x1 + " " + x0 + ". ");
            if (i == k) {
                res.setPair(x1, x0);
                break;
            }
            if (x1 != 0) {
                pq.offer(x1);
                if (x0 != 0)
                    pq.offer(x0);
            }
            /*
            for (long a : list)
                System.out.print(a + " ");
            System.out.println();
            */
        }
        //System.out.println();
        return res;
    }
}

