package unam.fc.concurrent.practica2;

// Programa 1: Utilizacion de synchronized
// 		El metodo run es una seccion critica, cada hilo lo ejecuta, y hasta que no termina 
//		de ejecutarlo lo deja. 

public class SynchronizedExample1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ContadorSynchronized myRunnable = new ContadorSynchronized();
		Thread threadA = new Thread(myRunnable);
		Thread threadB = new Thread(myRunnable);
		Thread threadC = new Thread(myRunnable);
		threadA.start();
		threadB.start();
		threadC.start();
		try{
			threadA.join();
			threadB.join();
			threadC.join();
		}catch(InterruptedException e) {
			System.out.println(e);
		}
		System.out.println("Total " + myRunnable.getValue());
	}

}
class ContadorSynchronized implements Runnable{
	private int count=0;
	public int increment() {
		return this.count++;
	}
	public int getValue() {
		return this.count;
	}
	
	@Override
	public synchronized void run() {
		String threadName = Thread.currentThread().getName();//Obtenemos el nombre del hilo
		for(int i = 0; i < 10; i++) {
			//increment();
			System.out.println("Running Thread " + threadName + " increment to: " + increment());
			try{
				Thread.sleep(100);
			}catch(InterruptedException e) {
				System.out.println(e);
			}
			
			
		}
	}
}

