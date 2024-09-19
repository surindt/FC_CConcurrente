package unam.fc.concurrent.practica3;
//Programa 1: Programa que ejemplifica el problema de Visibilidad en el JMM 
// Si la variable flag no es volatile, entonces el hilo que ejecuta read() puede nunca leer que el 
//  hilo que ejecuta write() modifico flag
// Implica que volatile hace flush! a las modificaciones de flag

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VolatileExample1 {
	static Boolean flag = false;
	
	private static void read() {
		
		while(!flag) {}
		
		System.out.println("La bandera esta en: " + flag);
	}
	private static void write() {
		try {
            Thread.sleep(1000);	
            flag = true;
            System.out.println("La bandera fue cambiada a true: " + flag);
		}
		catch(InterruptedException e) {
            e.printStackTrace();
        }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService executor = Executors.newFixedThreadPool(2);
		 
		executor.execute(() -> write()); 
		executor.execute(() -> read());
		executor.shutdown();
		
	}

}
