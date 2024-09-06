package unam.fc.concurrent.practica3;
//Programa 3: Programa que ejecuta la clase Peterson, todas las variables utilizan volatile
// Utiliza la clase CounterNaive
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LockPeterson{
	
	private static void task(Peterson lock, CounterNaive counter) {
		try {
			lock.lock();
			counter.increment();
//			System.out.println(counter.getValue());
		}finally {
			lock.unlock();		
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Peterson lock = new Peterson();
		CounterNaive counter = new CounterNaive();
		ExecutorService executor = Executors.newFixedThreadPool(2);//El candado solo funciona para dos hilos
		
		for(int i = 0; i < 1000; i++) {
			executor.execute(() -> task(lock,counter)); 
		}
		executor.shutdown();
		
		try{
			Thread.sleep(500);
			System.out.println(counter.getValue());
		}catch(InterruptedException e) {
			System.out.println(e);
		}
	
	}

}
