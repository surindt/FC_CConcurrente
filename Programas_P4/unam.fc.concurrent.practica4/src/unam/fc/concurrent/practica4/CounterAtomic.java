package unam.fc.concurrent.practica4;

import java.util.concurrent.atomic.AtomicInteger;

/*
	Programa 1: Contador linealizable no bloqueante, utiliza
	primitivas: getAndIncrement y get
*/
public class CounterAtomic {
	private AtomicInteger count;
	public CounterAtomic() {
		this.count = new AtomicInteger(0);
	}
	public int increment() { //return the last value
		return count.getAndIncrement();
	}
	public int getValue() { //return the value
		return count.get();
	}
	

}
