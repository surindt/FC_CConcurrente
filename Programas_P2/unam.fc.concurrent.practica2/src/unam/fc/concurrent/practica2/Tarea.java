package unam.fc.concurrent.practica2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tarea implements Runnable{
	int tiempoTarea;
	int task;
//	final Semaphore smphre;
//	Lock lock = new ReentrantLock();
	public Tarea(int i) {
		this.task = i;
	}
	
	@Override
	public void run() {
		Thread currentThread = Thread.currentThread();
		long id = currentThread.getId();
		
			int value = (int) (id % 6);
			System.out.println("Running Thread " + value + " task: " + this.task);
			switch(value) {
			  case 0, 2:
				  this.tiempoTarea = 500;
			    break;
			  case 1:
				  this.tiempoTarea = 2000;
			    break;
			  default:
				  this.tiempoTarea = 3000;
			}
			
			try{
				Thread.sleep(this.tiempoTarea);
			}catch(InterruptedException e) {
				System.out.println(e);
			}
			
			System.out.println("Running Thread " + value + " time: " + this.tiempoTarea);
		
	}
}
