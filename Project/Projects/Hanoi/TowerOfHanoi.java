import java.util.Scanner;

public class TowerOfHanoi {
	public int n;
	public Stack A;
	public Stack B;
	public  Stack C;
	
	public TowerOfHanoi(int n)
	{
		A = new Stack(n);
		B = new Stack(n);
		C = new Stack(n);
		this.n = n;
	}
	
	public void display()
	{
		System.out.print("A: ");
		A.disp();
		System.out.println();
		
		System.out.print("B: ");
		B.disp();
		System.out.println();
		
		System.out.print("C: ");
		C.disp();
		System.out.println();
		System.out.println();
	}

	public int towerOfHanoi(Stack source, Stack helper, Stack destination, int n) 
	{
		if (n == 0) 
		{
			return 0 ;
		} 

		if (n == 1) 
		{
			destination.push(source.pop());
			display();
			return 1;
		}

		int count = 0;
		count += towerOfHanoi(source, destination, helper, n-1);
		
		count++;
		destination.push(source.pop());
		display();
		
		count += towerOfHanoi(helper, source, destination, n-1);
		
		return count;
	}
	
	public int towerOfHanoi2(String source, String helper, String destination, int n) 
	{
		if (n == 0) 
		{
			return 0 ;
		} 

		if (n == 1) 
		{
			System.out.println("Moving disc " + n + " from " + source + " to " + destination);
			return 1;
		}

		int count = 0;
		count += towerOfHanoi2(source, destination, helper, n-1);
		count++;
		System.out.println("Moving disc " + n + " from " + source + " to " + destination);
		count += towerOfHanoi2(helper, source, destination, n-1);
		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter n: ");
		TowerOfHanoi obj = new TowerOfHanoi(s.nextInt());
	
		for(int i = obj.n; i >= 1; i--)
			obj.A.push(i);

		obj.display();
		System.out.println(obj.towerOfHanoi(obj.A, obj.B, obj.C, obj.n));
		
//	obj.towerOfHanoi2("A", "B", "C", obj.n);

	}

}
