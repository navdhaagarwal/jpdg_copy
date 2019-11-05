package UpdatedData;

import javax.swing.tree.TreeNode;
import java.io.*;
import java.util.*;

public class Test_46 {
    BufferedWriter bw;

    public int[] calc(int n, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());

        pq.add(n);
        int le = 0, ri = 0;
        while (k > 0) {
            k --;

            int top = pq.poll();
            int half = (top + 1) / 2;
            le = half - 1;
            ri = top - half;
            if (le > 0) {
                pq.add(le);
            }
            if (ri > 0) {
                pq.add(ri);
            }
        }

        int[] res = {Math.max(le, ri), Math.min(le, ri)};
        return res;
    }

    public int[] calcSlow(int n, int k) {
        int[] arr = new int [n + 2];
        arr[0] = arr[n + 1] = 1;
        int big = -1, small = -1;
        while (k > 0) {
            k --;

            int idx = -1;
            big = -1;
            small = -1;
            for (int i = 1; i <= n; i ++) {
                if (arr[i] == 1) continue;

                int l = i - 1, r = i + 1;
                int le = 0, ri = 0;
                while (l > 0 && arr[l] == 0) {
                    l --;
                    le ++;
                }
                while (r <= n && arr[r] == 0) {
                    r ++;
                    ri ++;
                }

                int b = Math.max(le, ri);
                int s = Math.min(le, ri);

                if (s > small || (s == small && b > big)) {
                    idx = i;
                    small = s;
                    big = b;
                }
            }

            arr[idx] = 1;
        }

        int[] res = {big, small};
        return res;
    }

    public long[] calcFast(long n, long k) {
        Map<Long, Long> map = new TreeMap<>(Collections.reverseOrder());
        map.put(n, 1L);

        long big = -1, small = -1;
        while (k > 0) {
            Map<Long, Long> next = new TreeMap<>(Collections.reverseOrder());
            for (Map.Entry<Long, Long> en : map.entrySet()) {
                long val = en.getKey();
                long cnt = en.getValue();

                if (cnt >= k) {
                    long half = (val + 1) / 2;
                    long le = half - 1;
                    long ri = val - half;
                    big = Math.max(le, ri);
                    small = Math.min(le, ri);
                    k = 0;
                    break;
                } else {
                    k -= cnt;
                }

                if (val % 2 == 0) {
                    long a = val / 2;
                    long b = val / 2 - 1;
                    addToMap(next, a, cnt);
                    addToMap(next, b, cnt);
                } else {
                    long a = val / 2;
                    addToMap(next, a, cnt << 1);
                }
            }
            map = next;
        }

        long[] res = {big, small};
        return res;
    }

    void addToMap(Map<Long, Long> map, long val, long cnt) {
        if (val <= 0) return;
        long pc = map.getOrDefault(val, 0L);
        pc += cnt;
        map.put(val, pc);
    }
    void go() throws IOException {
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("file/output.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Scanner scan = new Scanner(System.in);
        Scanner scan = new Scanner(new File("file/input.txt"));
        int t = scan.nextInt();
        for (int i = 1; i <= t; i ++) {
            System.out.print("Case #" + i + ": ");
            bw.write("Case #" + i + ": ");

            long n = scan.nextLong();
            long k = scan.nextLong();

            long[] res = calcFast(n, k);

            System.out.println(res[0] + " " + res[1]);
            bw.write(res[0] + " " + res[1] + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static void main(String[] args) {
        try {
            new Test_46().go();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

