package UpdatedData;

import java.io.*;
import java.util.*;

/**
 * Created by Sergey on 4/7/2017.
 *
 * https://code.google.com/codejam/contest/3264486/dashboard#s=p2
 */
public class Test_71 extends C_GoogleCodeJamSolution {

    @Override
    public void solveCase(int caseNumber, Scanner scanner) {
        long n = scanner.nextLong();
        long k = scanner.nextLong();

        debugf("[DEBUG] Case #%d: %d %d\n", caseNumber, n, k);

        List<String> lrs = new ArrayList<>();
        List<Long> cnts = new ArrayList<>();

        TreeMap<Long, Long> queue = new TreeMap<>();
        queue.put(n, 1L);
        while (!queue.isEmpty()) {
            Long top = queue.lastKey();
            if (top == 1) {
                break;
            }
            Long occurrence = queue.get(top);
            queue.remove(top);
            long left = (top - 1) / 2;
            long right = top - 1 - left;
            lrs.add(right + " " +  left);
            cnts.add(occurrence);
            if (queue.containsKey(left)) {
                queue.put(left, queue.get(left) + occurrence);
            } else {
                queue.put(left, occurrence);
            }
            if (queue.containsKey(right)) {
                queue.put(right, queue.get(right) + occurrence);
            } else {
                queue.put(right, occurrence);
            }
        }

        String answer = "0 0";
        for (int i = 0; i < lrs.size(); i++) {
            if (k <= cnts.get(i)) {
                answer = lrs.get(i);
                break;
            }
            k = k - cnts.get(i);
        }

        printf("Case #%d: %s\n", caseNumber, answer);
    }

    public static void main(String[] args) {
        C_GoogleCodeJamSolution.main(C_BathroomStalls.class, "C-large");
    }
}

// =================================================================
// NOTE:
// This is an utility class used in all my GoogleCodeJam challenges as
// a base class to provide basic functionality of file reading as well
// as some useful utility methods for debugging.
// =================================================================
class C_GoogleCodeJamSolution {

    private PrintStream mainPs;
    private PrintStream debugPs;
    private boolean debugEnabled = true;

    public C_GoogleCodeJamSolution() {
        // empty
    }

    private void setMainPs(PrintStream mainPs) {
        this.mainPs = mainPs;
    }

    private void setDebugPs(PrintStream debugPs) {
        this.debugPs = debugPs;
    }

    private void setDebugEnabled(boolean debugEnabled) {
        this.debugEnabled = debugEnabled;
    }

    protected void init() {
        // some initialization once the STDOUT/STDERR streams are set
    }

    protected void printf(String s, Object ... args) {
        debugPs.printf(s, args);
        debugPs.flush();
        mainPs.printf(s, args);
        mainPs.flush();
    }

    protected void debugf(String s, Object ... args) {
        if (debugEnabled) {
            debugPs.printf(s, args);
        }
    }

    protected void debugMatrix(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                debugf("\t%d", m[i][j]);
            }
            debugf("\n");
        }
        debugf("\n");
    }

    protected long findMax(long[] arr) {
        long imax = Long.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > imax) {
                imax = arr[i];
            }
        }
        return imax;
    }

    protected int findMaxIndex(List<Integer> arr) {
        int imax = -1;
        for (int i = 0; i < arr.size(); i++) {
            if (imax < 0 || arr.get(imax) < arr.get(i)) {
                imax = i;
            }
        }
        return imax;
    }

    protected void fillMatrix(int[][] matrix, int val) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = val;
            }
        }
    }

    protected long findLcm(long d1, long d2) {
        long left = d1;
        long right = d2;
        while (left > 0 && right > 0) {
            if (left == right) {
                return left;
            }
            if (left < right) {
                left = (right / d1) * d1 + (right % d1 != 0 ? d1 : 0);
            }
            if (right < left) {
                right = (left / d2) * d2 + (left % d2 != 0 ? d2 : 0);
            }
        }
        throw new RuntimeException("Numeric overflow");
    }

    long findLcm(long[] arr) {
        long lcm = 1;
        for (int i = 0; i < arr.length; i++) {
            lcm = findLcm(lcm, arr[i]);
        }
        return lcm;
    }

    public void solveCase(int caseNumber, Scanner scanner) {
        int value = scanner.nextInt();
        printf("Case #%d: %d\n", caseNumber, value);
        debugf("[DEBUG] Case #%d: %d\n", caseNumber, value);
    }

    public void test() {
        // put your test code here
    }

    public static void main(Class<? extends C_GoogleCodeJamSolution> clazz, String inputFile) {
        long time = System.currentTimeMillis();
        main(clazz, inputFile, true);
        System.out.println("Execution time: " + (System.currentTimeMillis() - time) / 1000 + " seconds");
    }

    public static void main(Class<? extends C_GoogleCodeJamSolution> clazz, String inputFile, boolean debugEnabled) {
        try {
            String basePath = "./GoogleCodeJam/src/" + clazz.getPackage().getName().replace(".", "/");
            InputStream is = new FileInputStream(basePath + "/" + inputFile + ".in");
            PrintStream fos = new PrintStream(new FileOutputStream(basePath + "/" + inputFile + ".out"));
            Scanner scanner = new Scanner(is);
            int cases = scanner.nextInt();
            for (int caseNumber = 1; caseNumber <= cases; caseNumber++) {
                C_GoogleCodeJamSolution solution = clazz.newInstance();
                solution.setDebugPs(System.out);
                solution.setMainPs(fos);
                solution.setDebugEnabled(debugEnabled);
                solution.init();
                solution.solveCase(caseNumber, scanner);
            }
            is.close();
            fos.close();
        } catch (IOException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        main(C_GoogleCodeJamSolution.class, "small-practice");
    }

    public static void mainTest(Class<? extends C_GoogleCodeJamSolution> clazz) {
        try {
            C_GoogleCodeJamSolution solution = clazz.newInstance();
            solution.setDebugPs(System.out);
            solution.setMainPs(System.out);
            solution.setDebugEnabled(true);
            solution.init();
            solution.test();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

}
