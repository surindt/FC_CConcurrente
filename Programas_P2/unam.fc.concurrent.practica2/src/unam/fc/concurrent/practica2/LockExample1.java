package unam.fc.concurrent.practica2;
import java.util.concurrent.locks.*;
//Programa 3: Contador con Lock (Utilizar candados tampoco es trivial)
//		En este caso nuestra seccion critica se encuentra solo en this.count, el candado se deja y se toma en cada ciclo del while 
//		En la Linea 22 se detiene el contador, si esa linea se pone al final del metodo main, el programa no acaba		
//		Si comentas la linea 22 y descomentas la 26 no se detiene porque espera por todos (por Join())


public class LockExample1 {

	public static void main(String[] args) {
		ContadorLock myRunnable = new ContadorLock();
		Thread threadA = new Thread(myRunnable);
		Thread threadB = new Thread(myRunnable);
		Thread threadC = new Thread(myRunnable);
		threadA.start();
		threadB.start();
		threadC.start();
		
		try{
			Thread.sleep(500);
			myRunnable.stopped();
			threadA.join();
			threadB.join();
			threadC.join();
//			myRunnable.stopped();
		}catch(InterruptedException e) {
			System.out.println(e);
		}
		
		System.out.println("Total " + myRunnable.getValue());
	}
	

}


class ContadorLock implements Runnable{
	private int count=0;
	private boolean stop=false;
	private Lock lock = new ReentrantLock();
	
	public int increment() {
		try {
			lock.lock();
			return this.count++;
		}finally {
			lock.unlock();		
			}
	}
	public int getValue() {
		try {
			lock.lock();
			return this.count;
		}finally {
			lock.unlock();
		}
	}
	public void stopped() {
		try {
			lock.lock();
			this.stop = true;
		}finally {
			lock.unlock();
		}
		
	}
	
	@Override
	public void run() {
		String threadName = Thread.currentThread().getName(); //Obtenemos el nombre del hilo
		while(!this.stop) {
			increment();
			System.out.println("Running Thread " + threadName + " increment to: " + getValue());
			try{
				Thread.sleep(50);
			}catch(InterruptedException e) {
				System.out.println(e);
			}
			
			
		}
	}
}