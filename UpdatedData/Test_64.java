package UpdatedData;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Test_64 {

    void processInput() throws IOException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int T = in.nextInt();

        for (int testCase = 1; testCase <= T; testCase++) {
            long N = in.nextLong();
            long K = in.nextLong();

            solve(testCase, N, K);
        }

        in.close();
    }

    void solve(int testCase, long n, long k) {
        TreeMap<Long, Long> q = new TreeMap<>();
        q.put(n, 1l);

        long l = 0;
        long r = 0;
        long i = 0;
        while (i <= k - 1) {
            Map.Entry<Long, Long> curr = q.pollLastEntry();
            l = (curr.getKey() - 1) / 2;
            r = l;
            if (curr.getKey() % 2 == 0) {
                r++;
            }

            long v = curr.getKey();
            long qty = curr.getValue();
            long v1 = (v - 1) / 2;
            long v2 = v1;
            if (v % 2 == 0) {
                v2++;
            }

            put(q, v1, qty);
            put(q, v2, qty);
            i += qty;
        }

        System.out.printf(Locale.ENGLISH, "Case #%d: %d %d\n", testCase, r, l);
    }

    void put(TreeMap<Long, Long> q, long val, long qty) {
        if (val <= 0) {
            return;
        }
        if (!q.containsKey(val)) {
            q.put(val, 0l);
        }
        q.put(val, q.get(val) + qty);
    }

    public static void main(String[] args) throws Exception {
        BathroomStalls m = new Test_64();
        m.processInput();
    }
}

