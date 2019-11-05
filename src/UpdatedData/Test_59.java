package UpdatedData;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Test_59 {
	public static String Solve(long stalls, long people){
		String answer = "";
		List<Long> minmax = new ArrayList<Long>();
		minmax = firstPerson(stalls);
		if (people == 1){
			return reverseList(minmax.toString());
		}else{
			if ((people-1)%2==0){
				//System.out.println(minmax.get(0) + "," + (people-1)/2);
				answer = Solve(minmax.get(0),(people-1)/2);
			}else{
				//System.out.println(minmax.get(1) + "," + (((people-1)/2)+1));
				answer = Solve(minmax.get(1),(((people-1)/2)+1));
			}
		}
		return answer;
	}
	public static List<Long> firstPerson(long stalls){
		List<Long> answer = new ArrayList<Long>();
		if (stalls%2==0){
			long max = stalls/2;
			long min = max-1;
			answer.add(min);
			answer.add(max);
		}else{
			long max = stalls/2;
			answer.add(max);
			answer.add(max);
		}
		return answer;
	}
	public static long middleStall(long num1, long num2){
		long answer = (num2-num1)/2;
		answer += num1;
		return answer;
	}
	public static String reverseList(String arr){
		String answer = "";
		List<String> temp = new ArrayList<String>();
		temp.add(arr.substring(1,arr.indexOf(",")));
		arr = arr.substring(arr.indexOf(",")+2);
		temp.add(arr.substring(0,arr.indexOf("]")));
		answer = temp.get(1) + " " + temp.get(0);
		return answer;
	}
	
	public static List<Long> unpackInput(String str){
		String stalls = str.substring(0,str.indexOf(" "));
		str = str.substring(str.indexOf(" ")+1);
		String people = str;
		List<Long> answer = new ArrayList<Long>();
		answer.add(Long.parseLong(stalls));
		answer.add(Long.parseLong(people));
		return answer;
	}

public static void main(String args[]){
	List<String> file = Findfile();
	List<String> answer = new ArrayList<String>();
	List<Long> components = new ArrayList<Long>();
	for(int i = 0; i < file.size(); i++){
		components.clear();
		components = unpackInput(file.get(i));
		answer.add(Solve(components.get(0),components.get(1)));
		System.out.println(i);
	}
	outputFile(answer);
}

public static void outputFile(List<String> toAdd){
	createFile();
	addRecords(toAdd);
	closeFile();
}
private static Formatter x;
public static void createFile(){
	try{
		x = new Formatter("C:\\Users\\Kareem\\Desktop\\Bathroom_Stalls_Large.txt");
	}catch(Exception e){
		System.out.println("You have an error!");
	}
}

public static void addRecords(List<String> toAdd){
	String finalanswer = "";
	String newline = System.lineSeparator();
	int num = 0;
	for (int i = 0; i < toAdd.size(); i++){
		num = i+1;
		finalanswer = finalanswer+"Case #"+num+":"+" " + toAdd.get(i) + newline;
	}
	System.out.println(finalanswer.toString());
	x.format(finalanswer);
}

public static void closeFile(){
	x.close();
}

public static List<String> Findfile(){
List<String> answer = new ArrayList<String>();
File testFile = new File("C:\\Users\\Kareem\\Desktop\\CodeJam2017\\C-large.in");

Scanner scan = null;

try{
	scan = new Scanner(testFile);
	scan.nextLine();
	
	while(scan.hasNextLine()){
		answer.add(scan.nextLine());
	}
	scan.close();
	return answer;
}catch (FileNotFoundException e) {
	return answer;
}
}
}

