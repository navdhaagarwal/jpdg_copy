package UpdatedData;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import static java.util.Arrays.deepToString;

public class Test_58 {
    static PrintWriter out = null;
    static FileInputStream in = null;
    Scanner sc;

    Long N;
    Long K;

    void read() {
        N = sc.nextLong();
        K = sc.nextLong();
    }

    void solve() {
        Long sizeOfThisPass = 1L;
        Long numPasses = 0L;
        Long i = 0L;
        do {
            if (i + sizeOfThisPass >= K) {
                // N-i slots left, devided into i+1 continuous slots
                long partitions = sizeOfThisPass;
                Long smallSlotLength = (N - i) / partitions;
                Long numberOfLargeSlots = (N - i) - (smallSlotLength * partitions);
                Long choosenSlotSize;
                if (numberOfLargeSlots >= K - i) {
                    choosenSlotSize = smallSlotLength + 1;
                } else {
                    choosenSlotSize = smallSlotLength;
                }

                Long left = (choosenSlotSize - 1) / 2;
                Long right = choosenSlotSize - 1 - left;

                print(Math.max(left, right) + " " + Math.min(left, right) + "\n");
                return;
            }

            i += sizeOfThisPass;
            sizeOfThisPass *= 2;
            numPasses++;
        } while (i < K);
    }

    void run() {
        try {
            in = new FileInputStream("src/com/resources/input.txt");
            out = new PrintWriter("src/com/resources/output.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        sc = new Scanner(in);
        int caseN = sc.nextInt();
        for (int caseID = 1; caseID <= caseN; caseID++) {
            read();
            print("Case #" + caseID + ": ");
            solve();
        }
        out.close();
    }

    private void print(String s) {
        out.printf(s);
        System.out.printf(s);
    }

    void debug(Object... os) {
        System.err.println(deepToString(os));
    }

    public static void main(String[] args) {
        try {
            System.setIn(new BufferedInputStream(new FileInputStream(args.length > 0 ? args[0] : (codejam2017QCL.class.getName() + ".in"))));
        } catch (Exception e) {
        }
        new codejam2017QCL().run();
    }
}

