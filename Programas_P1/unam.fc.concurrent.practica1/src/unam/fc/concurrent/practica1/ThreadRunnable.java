package unam.fc.concurrent.practica1;
//Clase que implementa la interfaz Runnable
public class ThreadRunnable implements Runnable{
	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();//Obtenemos el nombre del hilo
		System.out.println("Running Thread that implements Runnable interface: " + threadName);
	}
}
