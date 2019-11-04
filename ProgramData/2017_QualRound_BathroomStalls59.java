package gcj2017;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * @author Vincent Cantin
 */
public class C {

  public static void main(String[] args) throws Exception {
    File dataFolder = new File("data");
    File inputFile  = new File(dataFolder, "C-large.in");
    File outputFile = new File(dataFolder, "C-large.out");
    
    try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
      int nbTestCases = Integer.parseInt(reader.readLine());
      for (int t = 1; t <= nbTestCases; t++) {
        Scanner scanner = new Scanner(reader.readLine());
        long n = scanner.nextLong();
        long k = scanner.nextLong();
        writer.write("Case #" + t + ": " + solve(n, k) + "\n");
      }
    }
  }
  
  /**
   * Complexity O(log(k))
   * Terminal recursion
   */
  private static String solve(long n, long k) {
    if (k == 1) {
      long bigHalf = n / 2;
      long smallHalf = (n - 1) - bigHalf;
      
      return "" + bigHalf + " " + smallHalf;
    }
    
    boolean k_even = ((k & 1) == 1);
    boolean n_even = ((n & 1) == 1);
    
    if (n_even) {
      if (k_even) {
        return solve((n - 1) / 2, (k - 1) / 2);
      }
      else {
        return solve((n - 1) / 2, k / 2);
      }
    }
    else {
      if (k_even) {
        return solve((n / 2) - 1, (k - 1) / 2);
      }
      else {
        return solve(n / 2, k / 2);
      }
    }
  }
}
