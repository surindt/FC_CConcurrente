package unam.fc.concurrent.practica1;

//Programa 4: Ejemplo de un contador que no termina
//	El hilo principal es el que corre el programa en JVM.
//	El metodo run() no tiene fin, el hilo A nunca termina, entonces el programa no termina
//	El programa puede terminar si descomentas la linea 14, porque el hilo A se vuelve daemon

public class MainThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String threadName = Thread.currentThread().getName();//Obtenemos el nombre del hilo
		Thread threadA = new Thread(new InfiniteCounter());
		//threadA.setDaemon(true);
		threadA.start();
		
		try{
			Thread.sleep(1500);
			//threadA.join();
		}catch(InterruptedException e) {
			System.out.println(e);
		}
		System.out.println("Running Thread: " + threadName);
	}

}
class InfiniteCounter  implements Runnable{
	int count=0;
	int increment() {
		count++;
		return count;
	}
	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();//Obtenemos el nombre del hilo
		while(true) {
			try{
				Thread.sleep(500);
				System.out.println("Running Thread: " + threadName + " increment to: " + increment());
			}catch(InterruptedException e) {
				System.out.println(e);
			}
			
		}
		
	}
	
}

