/**
 * Created by qixinzhu on 4/8/16.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

import static jdk.nashorn.internal.objects.NativeMath.min;

public class C {


    public static void genLargeP() throws FileNotFoundException {
        File inputFile = new File("C-small-2-attempt0.in");
        PrintWriter out = new PrintWriter("C-large-practice.txt");
        Scanner in = new Scanner(inputFile);
        int T = in.nextInt();
        out.println(T);
        for (int i = 0; i < T; i++) {
            String N = in.next();
            String K = in.next();
            String repeatedN = new String(new char[18 / N.length()]).replace("\0", N);
            String repeatedK = new String(new char[14 / K.length()]).replace("\0", K);
            out.printf("%s %s\n", repeatedN, repeatedK);
        }
        in.close();
        out.close();
    }

    public static class Stall implements Comparable {
        public long distance;
        public long count;

        public Stall(long dist, long c) {
            distance = dist;
            count = c;
        }

        public String toString() {
            return String.format("(%d, %d)", distance, count);
        }

        public int compareTo(Object obj) {
            Stall other = (Stall) obj;
            if (this.distance - other.distance > 0) {
                return 1;
            } else if (this.distance - other.distance < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        // test case
//        genLargeP();
//        File inputFile = new File("test.txt");
//        PrintWriter out = new PrintWriter("test_out.txt");

//        File inputFile = new File("C-small-2-attempt0.in");
//        PrintWriter out = new PrintWriter("C-small-2-out.txt");

//        File inputFile = new File("C-large-practice.txt");
//        PrintWriter out = new PrintWriter("C-large-out.txt");

        File inputFile = new File("C-large.in");
        PrintWriter out = new PrintWriter("C-large-out.txt");

        Scanner in = new Scanner(inputFile);

        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            long time0 = System.currentTimeMillis();

            long N = in.nextLong();
            long K = in.nextLong();
            Queue<Stall> q = new PriorityQueue<>(Comparator.reverseOrder());
            q.add(new Stall(N, 1));
            long k = 0;
            long left, right;
            while (true) {
                Stall s1 = q.poll();
                long n1 = s1.distance - 1;
                long count1 = s1.count;
                long left1 = n1 / 2;
                long right1 = n1 / 2 + n1 % 2;
                k += count1;
                if (k >= K) {
                    left = left1;
                    right = right1;
                    break;
                }
                if (q.isEmpty()) {
                    Stall sL = new Stall(left1, count1);
                    Stall sS = new Stall(right1, count1);
                    q.add(sL);
                    q.add(sS);
                    continue;
                }
                Stall s2 = q.poll();
                long n2 = s2.distance - 1;
                long count2 = s2.count;
                long left2 = n2 / 2;
                long right2 = n2 / 2 + n2 % 2;
                k += count2;
                if (k >= K) {
                    left = left2;
                    right = right2;
                    break;
                }
                long distL = Math.max(Math.max(left1, right1), Math.max(left2,right2));
                long distS = Math.min(Math.min(left1, right1), Math.min(left2,right2));

                Stall sL = new Stall(distL, 0);
                Stall sS = new Stall(distS, 0);
                if (right1 == sL.distance) {
                    sL.count += count1;
                } else {
                    sS.count += count1;
                }
                if (right2 == sL.distance) {
                    sL.count += count2;
                } else {
                    sS.count += count2;
                }
                if (left1 == sL.distance) {
                    sL.count += count1;
                } else {
                    sS.count += count1;
                }
                if (left2 == sL.distance) {
                    sL.count += count2;
                } else {
                    sS.count += count2;
                }
                q.add(sL);
                q.add(sS);
            }


            out.printf("Case #%d: %d %d\n", t + 1, right, left);


            long time1 = System.currentTimeMillis();
            System.out.printf("[%dms] Test case %d.\n", time1 - time0, t + 1);
        }


        in.close();
        out.close();
    }

}
