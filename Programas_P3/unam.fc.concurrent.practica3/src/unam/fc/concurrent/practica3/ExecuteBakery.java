package unam.fc.concurrent.practica3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteBakery {
	
	public static void take(Bakery lock) {
		Thread currentThread = Thread.currentThread();
		long id = currentThread.getId();
		Nodo node = new Nodo(true, (int) (id));
		try {
			lock.lock(node);
			Thread.sleep(500);
			System.out.println("in ");
		}catch(InterruptedException e) {
			System.out.println(e);
		}finally {
			lock.unlock(node);		
		}
	}
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Bakery lock = new Bakery();
		
		
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for(int i = 0; i < 10; i++) {
			executor.execute(() -> take(lock)); //Runnable al estilo lambda, esta notacion se usa en interfaces
		}
		lock.print();
	}
	
}