package unam.fc.concurrent.practica2;
//Programa 8: Scheduler -- Programa para completar utiliza la clase Tarea
import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import unam.fc.concurrent.practica2.Tarea;

public class Scheduler {
	
	static Semaphore smphre = new Semaphore(3);	// Solo tres a la vez
	

	public static void main(String[] args){
		// TODO Auto-generated method stub
				
		ExecutorService executorTarea = Executors.newFixedThreadPool(6);
		for(int i = 0; i < 26; i++) {
			executorTarea.execute(new Tarea(i));
		}
		executorTarea.shutdown();
	

	}

}
