package unam.fc.concurrent.practica1;

//Programa 3: Ejemplo de un contador implementando la interfaz Runnable
//   Acaban en distintos tiempos
//	 Se crea un solo objeto Runnable y se comparte, count es una variable compartida

public class ExampleMultipleRunnable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadRunnable1 myRunnable = new ThreadRunnable1();
		Thread threadA = new Thread(myRunnable);
		Thread threadB = new Thread(myRunnable);
		Thread threadC = new Thread(myRunnable);
		threadA.start();
		threadB.start();
		threadC.start();
	}

}

class ThreadRunnable1  implements Runnable{
	int count=0;
	int increment() {
		return count++;
	}
	int getCount() {
		return count;
	}
	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();//Obtenemos el nombre del hilo
		System.out.println("Running Thread Runnable " + threadName + " increment to: " + increment());
		
	}
	
}
