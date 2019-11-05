package UpdatedData;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

public class Test_35 {
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
//
//        for (int i = 1; i < 1026; i++) {
//            System.out.println(i + ": " + log2(i));
//        }

        int numCases = sc.nextInt();
        for (int q = 1; q <= numCases; q++) {
            System.out.printf("Case #%d: ", q);

            long N = sc.nextLong();
            long K = sc.nextLong();

            long log2K = log2(K);

            TreeMap<Long, Long> freq = new TreeMap<>(Comparator.reverseOrder());
            freq.put(N, 1L);

            for (int depth = 0; depth < log2K; depth++) {
                TreeMap<Long, Long> next = new TreeMap<>(Comparator.reverseOrder());
                for (Map.Entry<Long, Long> entry : freq.entrySet()) {
                    long width = entry.getKey();
                    long count = entry.getValue();

                    {
                        long min = (width - 1) / 2;
                        Long minCount = next.get(min);
                        if (minCount == null) {
                            next.put(min, count);
                        } else {
                            next.put(min, minCount + count);
                        }
                    }

                    {
                        long max = width / 2;
                        Long maxCount = next.get(max);
                        if (maxCount == null) {
                            next.put(max, count);
                        } else {
                            next.put(max, maxCount + count);
                        }
                    }
                }
                freq = next;
            }

            long idx = (K - (1L << log2K)) + 1;
            long size = 0;
            for (Map.Entry<Long, Long> entry : freq.entrySet()) {
                long width = entry.getKey();
                long count = entry.getValue();
                size += count;
                if (idx <= size) {
                    long min = (width - 1) / 2;
                    long max = width / 2;
                    System.out.println(max + " " + min);
                    break;
                }
            }
        }
    }

    static long log2(long num) {
        return 63 - Long.numberOfLeadingZeros(num);
    }

    static void sort(int[] arr) {
        Random rng = new Random();
        int length = arr.length;
        for (int idx = 0; idx < arr.length; idx++) {
            int toSwap = idx + rng.nextInt(length-idx);
            int tmp = arr[idx];
            arr[idx] = arr[toSwap];
            arr[toSwap] = tmp;
        }
        Arrays.sort(arr);
    }

    public static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner(Reader in) {
            br = new BufferedReader(in);
        }

        public FastScanner() {
            this(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String readNextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        int[] readIntArray(int n) {
            int[] a = new int[n];
            for (int idx = 0; idx < n; idx++) {
                a[idx] = nextInt();
            }
            return a;
        }
        long[] readLongArray(int n) {
            long[] a = new long[n];
            for (int idx = 0; idx < n; idx++) {
                a[idx] = nextLong();
            }
            return a;
        }
    }
}

