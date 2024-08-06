package unam.fc.concurrent.practica1;
//Programa 5: Ejemplo de un contador que termina solo si el hilo principal lo detiene
//		El hilo principal es el que corre el programa en JVM.
//		El hilo A va a ejecutarse mientras done = true
//		

public class AlwaysDesignToStop {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String threadName = Thread.currentThread().getName();//Obtenemos el nombre del hilo
		StopCounter counter = new StopCounter();
		Thread threadA = new Thread(counter);
		threadA.start();
		
		try{
			Thread.sleep(1500);
			//threadA.join();
		}catch(InterruptedException e) {
			System.out.println(e);
		}
		System.out.println("Running Thread: " + threadName);
		counter.stopcount();
	}

}
class StopCounter  implements Runnable{
	int count=0;
	boolean done = true;
	int increment() {
		count++;
		return count;
	}
	void stopcount() {
		this.done = false;
	}
	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();//Obtenemos el nombre del hilo
		while(this.done) {
			try{
				Thread.sleep(500);
				System.out.println("Running Thread: " + threadName + " increment to: " + increment());
			}catch(InterruptedException e) {
				System.out.println(e);
			}
			
		}
		
	}
	
}

