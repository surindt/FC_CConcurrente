package unam.fc.concurrent.practica1;
public class ThreadExtends  extends Thread{
	public void run() {
		String threadName = Thread.currentThread().getName();//Obtenemos el nombre del hilo
		System.out.println("Running Thread that extends Thread class: " + threadName);
	}
	
}
