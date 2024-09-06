package unam.fc.concurrent.practica3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//Programa 4: Programa que ejecuta la clase Lamport's Bakery
// Este programa utiliza a su vez la clase Nodo

public class ExecuteBakery {
	
	public static void take(Bakery lock, CounterNaive counter) {
		Thread currentThread = Thread.currentThread();
		long id = currentThread.getId();
		Nodo node = new Nodo(true, (int) (id));
		try {
			lock.lock(node);
			Thread.sleep(5);
			counter.increment();
			//System.out.println("in ");
		}catch(InterruptedException e) {
			System.out.println(e);
		}finally {
			lock.unlock(node);		
		}
	}
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Bakery lock = new Bakery();
		CounterNaive counter= new CounterNaive(); 
		
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for(int i = 0; i < 400; i++) {
			executor.execute(() -> take(lock, counter)); //Runnable al estilo lambda, esta notacion se usa en interfaces
		}
		try {
			Thread.sleep(5000);
			lock.print();
			System.out.println(counter.getValue());
		}catch(InterruptedException e) {
			System.out.println(e);
		}
	}
	
}
