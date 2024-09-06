package unam.fc.concurrent.practica3;

public class CounterNaive {
	private int count=0;
	public int increment() {
		return this.count++;
	}
	public int getValue() {
		return this.count;
	}
	
}