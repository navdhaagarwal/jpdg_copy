package UpdatedData;

import java.io.*;
import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

public class Test_25 {

    public static class SegTree implements Comparable<SegTree>{
        public int first;
        public int last;

        public SegTree(int first, int last){
            this.first = first;
            this.last = last;
        }

        @Override
        public int compareTo(SegTree o) {
            int len = this.last - this.first;
            int len2 = o.last - o.first;

            if(len != len2){
                return Integer.compare(len2, len);
            }
            return Integer.compare(this.first, o.first);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());

        for(int i = 1 ; i <= T ; i++){
            String[] input = in.readLine().split(" ");
            int N = Integer.parseInt(input[0]);
            int K = Integer.parseInt(input[1]);

            int[] res = solve(N , K);
            System.out.println("Case #" + i + ": " + res[0] + " " + res[1] );
        }

    }

    public static int[] solve(int N, int K){
        SortedSet<SegTree> seg = new TreeSet<>();

        seg.add(new SegTree(0, N + 1));
        int min = 0, max = 0;
        int middle = 0;

        int left, right;

        for(int i = 0 ; i < K; i++){
            SegTree f = null;
            for(SegTree s : seg){
                f = s;
                break;
            }
            middle = (f.last + f.first) / 2;
            left  = middle - f.first -1;
            right = f.last - middle - 1;

            max = Math.max(left,right);
            min = Math.min(left, right);

            if(middle > f.first + 1){
                seg.add(new SegTree(f.first , middle));
            }

            if(f.last > middle + 1){
                seg.add(new SegTree(middle, f.last));
            }

            seg.remove(f);
        }

        return new int[]{max , min};
    }
}

