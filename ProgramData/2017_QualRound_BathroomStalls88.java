import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            long n = in.nextLong();
            long k = in.nextLong();
            while (--k > 0) {
                n = (n - 1 + k % 2) / 2;
                k = k / 2 + k % 2;
            }
            System.out.println("Case #" + i + ": " + n / 2 + " " + (n - 1) / 2);
        }
    }

}
