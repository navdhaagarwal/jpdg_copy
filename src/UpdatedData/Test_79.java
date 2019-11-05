package UpdatedData;
import java.util.Scanner;

/**
 * Created by Daiki_Ikawa on 2017/04/08.
 */
public class Test_79 {


    public static void main(String[]args){
        ProblemC m = new Test_79();
        m.run();
    }

    void run(){
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for(int i = 0;i < t; i++){
            long n = sc.nextLong();
            long k = sc.nextLong();
            System.out.println("Case #"+(i+1)+": "+solve(n,k));
        }
    }

    String solve(long n, long k){

        long stage = Long.highestOneBit(k) - 1;

        long offsetFromStage = k - stage-1;

        long quotient = (n - stage)/(stage+1) ;
        long reminder = (n - stage)%(stage+1) ;

        long lastLsPlusRsPlus1;
        if(offsetFromStage<reminder){
           lastLsPlusRsPlus1 = quotient+1;
        }else{
            lastLsPlusRsPlus1 = quotient;
        }

        long ls = (lastLsPlusRsPlus1-1)/2;
        long rs = lastLsPlusRsPlus1/2;

        return rs+" "+ls;
    }

}

