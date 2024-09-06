package unam.fc.concurrent.practica3;
//Programa 2: Programa que ejemplifica el problema de  Reordenamiento en el JMM
// Si no se utiliza volatile, entonces las lineas 15 a 18 se reordenan y el resultado de a y b es distinto
// Implica que volatile forza al compilador a no reordenar las instrucciones

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VolatileExample2 {
	static Boolean flag = false;
	volatile static int a = 0, b = 0;
	
	private static void read() {
		
		while(!flag) {
			a = 0; 
			b += 1;
			b = a; 
			a += 1;}
		
		System.out.println("La bandera esta en: " + flag + " a: " +a +" b: " + b);
	}
	private static void write() {
		try {
            Thread.sleep(1000);	
			
			a = b;
			b = a;
			
			flag = true;
			b += 1;
			a += 1;
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
		try {
			Thread.sleep(1500);
			System.out.println("main: " + flag + " a: " +a +" b: " + b);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
