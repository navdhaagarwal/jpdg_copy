package codejam2017.round0;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by sreinck on 08.04.17.
 */
public class ProblemC_BathroomStalls {

    private static Object solve(Scanner sc) {
        String line = sc.nextLine();
        String[] split = line.split(" ");
        long N = Long.parseLong(split[0]);
        long K = Long.parseLong(split[1]);

        StringBuilder way = new StringBuilder(Long.toBinaryString(K).substring(1));
        way.append('1');
        way.reverse();
        long min = N;
        long max = 0;
        for (int i = 0; i < way.length(); i++) {
            N = (way.charAt(i) == '1') ? min : max;
            N--;
            min = N / 2;
            max = N - min;
        }

        return max + " " + min;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc;
        if (args.length > 0) {
            sc = new Scanner(Paths.get(args[0]));
        } else {
            sc = new Scanner(System.in);
        }

        int T = Integer.parseInt(sc.nextLine());

        for (int i = 1; i <= T; i++) {
            Object solution = solve(sc);
            System.out.println("Case #" + i + ": " + solution);
        }
    }

}
