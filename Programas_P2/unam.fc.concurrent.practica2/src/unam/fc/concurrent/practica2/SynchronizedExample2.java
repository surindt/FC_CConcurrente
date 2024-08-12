package unam.fc.concurrent.practica2;
//
//Programa 2: Utilizacion de synchronized (no es tan trivial, ejemplo de porque no)
// 		Cuidado con como utilizar synchronized, por el scope podemos tener situaciones no previstas
//		Descomenta la linea 41 y comenta la linea 40 -> El programa no se detendra
//		A todo metodo al que se le aplique synchronized se convierte en una unica seccion critica compuesta por todos los metodos
public class SynchronizedExample2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ContadorSynchronized2 myRunnable = new ContadorSynchronized2();
		Thread threadA = new Thread(myRunnable);
		Thread threadB = new Thread(myRunnable);
		Thread threadC = new Thread(myRunnable);
		threadA.start();
		threadB.start();
		threadC.start();
		myRunnable.stopped();
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
class ContadorSynchronized2 implements Runnable{
	private int count=0;
	private boolean stop=false;
	public synchronized int increment() {
		return this.count++;
	}
	public synchronized int getValue() {
		return this.count;
	}
	public void stopped() {
//	public synchronized void stopped() {
		this.stop = true;
	}
	
	@Override
	public synchronized void run() {
		String threadName = Thread.currentThread().getName();//Obtenemos el nombre del hilo
		while(!this.stop) {
			increment();
			System.out.println("Running Thread " + threadName + " increment to: " + getValue());
			try{
				Thread.sleep(100);
			}catch(InterruptedException e) {
				System.out.println(e);
			}
			
			
		}
	}
}