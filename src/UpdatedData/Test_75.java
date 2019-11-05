package UpdatedData;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by jml90 on 4/8/2017.
 */
public class Test_75 {

    Scanner             sc       = new Scanner(getClass().getResourceAsStream(IN));
    static final String FILENAME = "c-large";
    static final String IN       = FILENAME + ".in";
    static final String OUT      = FILENAME + ".out";
    PrintStream out      = System.out;

    private void solve(long n, long k) {
        long ans0 = 0;
        long ans1 = 0;
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>((a, b) -> (Long.compare(b, a)));
        HashMap<Long, Long> map = new HashMap<>();
        priorityQueue.offer(n);
        map.put(n, 1l);
        long count = 0;
        while (!priorityQueue.isEmpty()) {
            long top = priorityQueue.poll();
            if (count + map.get(top) >= k) {
                ans0 = getMax(top);
                ans1 = getMin(top);
                break;
            }
            if (top % 2 == 0) {
                if (!map.containsKey(top / 2)) {
                    priorityQueue.offer(top / 2);
                    map.put(top / 2 , 0l);
                }
                if (!map.containsKey(top / 2 + 1)) {
                    priorityQueue.offer(top / 2 + 1);
                    map.put(top/ 2 + 1, 0l);
                }
                map.put(top / 2, map.get(top / 2) + map.get(top));
                map.put(top / 2 + 1, map.get(top / 2 + 1) + map.get(top));

            } else {
                if (!map.containsKey(top / 2 + 1)) {
                    priorityQueue.offer(top / 2 + 1);
                    map.put(top/2 + 1, 0l);
                }
                map.put(top / 2 + 1, map.get(top / 2 + 1) + map.get(top) * 2);
            }
            count += map.get(top);
        }

        out.println(ans0 + " " + ans1);
    }
    private long getMin(long n) {
        return (n + 1) / 2 - 2;
    }

    private long getMax(long n) {
        return n - (n + 1)/ 2 - 1;
    }

    private void run() throws Exception {
        out = new PrintStream(new FileOutputStream(OUT));
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            long n = sc.nextLong() + 2;
            long k = sc.nextLong();
            out.print("Case #" + i + ": ");
            solve(n, k);
        }
        sc.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        new  BathRoomStalls().run();
    }
}

