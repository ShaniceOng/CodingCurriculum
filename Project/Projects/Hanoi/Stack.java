package Hanoi;

public class Stack {
	private int data[];
	private int nextIndex;
	
	public Stack(int n) {
		data = new int[n];
		nextIndex = 0;
	}
	
	public int size() {
		return nextIndex;
	}
	
	public boolean isEmpty() {
		if (size() == 0)
			return true;
		return false;
	}
	
	public int top() {
		if (isEmpty()) {
			System.out.println("Stack is empty");
		}
		return data[nextIndex -1];
	}
	
	public void push(int next){
		if (nextIndex > data.length - 1) {
			System.out.println("Stack already full");
		}
			
		data[nextIndex] = next;
		nextIndex++;
	}
	
	public int pop() {
		if (isEmpty()) {
			System.out.println("Stack already empty");
			return -1;
		}
		
		nextIndex--;
		return data[nextIndex];
	}
	
	public void disp() {
		if (isEmpty()) {
			System.out.print(" ");
		}
		int temp = 0;
		while(temp < nextIndex)
			System.out.print(data[temp++] + " ");
	}
}
