package test;

public class Numbers {
	static int x;
	Numbers () {}
	Numbers (int t)
	{
		x=t;
	}
	static void print()
	{
		while (x>0)
		{
			System.out.println(x);
			x--;
		}
	}
}
