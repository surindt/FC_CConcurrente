package unam.fc.concurrent.practica2;
//Programa 4: Ejemplo de ExecutorService
// El metodo execute solo toma un Runnable y lo ejecuta
// El metodo shutdown se encarga de que ya no existan mas tareas nuevas en la pool, aunque no las detiene


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExampleExecutor1 {


	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(4);
		for(int i = 0; i < 10; i++) {
			executor.execute(new MyRunnable(i));
		}
		executor.shutdown();
	}

}

class MyRunnable  implements Runnable{
	int nTask = 0;
	MyRunnable(int i){
		this.nTask = i;
	}
	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		System.out.println("Running " + threadName + " Task " + this.nTask);
	}
			
}
