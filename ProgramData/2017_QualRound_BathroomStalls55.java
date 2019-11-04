
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kehxd
 */
public class C3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int q = 0; q < t; q++) {
            long n = sc.nextLong();
            long k = sc.nextLong();

            long k2 = k;
            long r = 1;
            long c = 0;
            while (k2 > 0) {
                //n/=2;
                //n2=(n2-1)/2;
                k2 /= 2;
                r *= 2;
                c++;
            }
            r /= 2;
            long l = (long) Math.pow(2, c);
            long li, ri;

            r = k - r;

            long n2 = n;
            n2 -= (l - 1);
            long r2 = n2 % l;
            //System.out.println(r + " " + r2 + " " + l);
            if (n2 < 0) {
                li = 0;
                ri = 0;
            } else if (r2 > l / 2) {
                if (r < r2-l/2) {
                    li = n2 / l + 1;
                    ri = n2 / l + 1;
                } else {
                    li = n2 / l + 1;
                    ri = n2 / l;
                }
            } else if (r < r2) {
                li = n2 / l + 1;
                ri = n2 / l;
            } else {
                li = n2 / l;
                ri = n2 / l;
            }
            System.out.println("Case #" + (q + 1) + ": " + Math.max(li, ri) + " " + Math.min(li, ri));
        }
    }

}
