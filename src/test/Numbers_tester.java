package test;

import test.Numbers;
public class Numbers_tester extends Numbers{
	int num;
	Numbers_tester()
	{
		super();
	}
	static void show(int a)
	{
		Numbers n=new Numbers(a);
		n.print();
	}
	public static void main(String [] args) {

		int i = 0;
	
		while (i < 5)
		{
			show(i);
			i++;
		}
	}

}
