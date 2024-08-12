package unam.fc.concurrent.practica2;
//Programa 5: Contador con ExecutorService
// Sin synchronized en increment el resultado no es siempre el mismo
//

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CounterPool {
	static int counter = 0;
	Integer arrayRes[];
	
	private static void increment(int i) {
		 counter++;
	}
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(4);
		for(int i = 0; i < 300; i++) {
			final int ntask=i;
			executor.execute(() -> increment(ntask)); //Runnable al estilo lambda, esta notacion se usa en interfaces
		}
		executor.shutdown();
		
		try{
			Thread.sleep(1800);// Delay para esperar que todas las tareas terminen
		}catch(InterruptedException e) {
			System.out.println(e);
		}
		System.out.println("Cuenta final: " + counter);
	}
	
}


