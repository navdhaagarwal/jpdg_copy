package UpdatedData;
import java.io.*;
import java.util.*;

public class Test_20 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t=scan.nextInt();
		for(int i=1; i<=t; i++){
			long n=scan.nextLong();
			long k=scan.nextLong();
			long spacing[] = getStallSpace(n, k);
			System.out.println("Case #"+i+": "+spacing[0]+" "+spacing[1]);
		}
		scan.close();
	}
	
	public static long[] getStallSpace(long n, long k){
		long allSpaces[][] = new long[][]{{n,1},{-1,-1}};
		long newSpaces[][] = new long[2][2];
		long res = k;
		while(res>0){
			newSpaces = new long[][]{{-1,-1},{-1,-1}};
			for(long[] space: allSpaces){
				if(space[0]==-1){
					break;
				}
				res-=space[1];
				if(res<=0){
					return new long[]{(space[0]-1)/2+(space[0]-1)%2, (space[0]-1)/2};
				}
				if(space[0]%2==0){
					if(newSpaces[0][0]==-1){
						newSpaces[0][0]=space[0]/2;
						newSpaces[0][1]=space[1];
					} else if(newSpaces[0][0]==space[0]/2){
						newSpaces[0][1]+=space[1];
					} else {
						newSpaces[1][0]=newSpaces[0][0];
						newSpaces[1][1]=newSpaces[0][1];
						newSpaces[0][0]=space[0]/2;
						newSpaces[0][1]=space[1];
					}
					if(newSpaces[1][0]==-1){
						newSpaces[1][0]=space[0]/2-1;
						newSpaces[1][1]=space[1];
					} else {
						newSpaces[1][1]+=space[1];
					}
				}
				if(space[0]%2==1){
					if(newSpaces[0][0]==-1){
						newSpaces[0][0]=space[0]/2;
						newSpaces[0][1]=space[1]*2;
					} else if(newSpaces[0][0]==space[0]/2){
						newSpaces[0][1] += space[1]*2;
					} else {
						newSpaces[1][1] += space[1]*2;
					}
				}
			}
			for(int i=0;i<2;i++){
				allSpaces[i] = newSpaces[i].clone();
			}
		}
		return new long[] {-1,-1};
	}

}

