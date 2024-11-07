package unam.fc.concurrent.practica6;

/*
 * Programa que ejecuta FifoReadWriteLock
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


public class ExecReadersWriters {
    int counter = 0;
    static AtomicInteger counterWriters = new AtomicInteger(0);
    static AtomicInteger counterReaders = new AtomicInteger(0);
    private static void taskR(FifoReadWriteLock rw) {//Runnable para el lock Read
        String threadName = Thread.currentThread().getName();//Obtenemos el nombre del hilo
		try {
			rw.readLock().lock();
            System.out.println("Running Reader Thread En: " + threadName);
            System.out.println(counterReaders.incrementAndGet());
		}finally {
            rw.readLock().unlock();	
            System.out.println(counterReaders.decrementAndGet());
            System.out.println("Running Reader Thread - Salio: " + threadName);
			
            
		}
	}

    private static void taskW(FifoReadWriteLock rw) {//Runnable para el lock Write
        String threadName = Thread.currentThread().getName();//Obtenemos el nombre del hilo
		try {
			rw.writeLock().lock();	
            System.out.println("Running Writer Thread En: " + threadName);
            //System.out.println(counterWriters.incrementAndGet());// Mas de un writer en la SC?
		}finally {
            rw.writeLock().unlock();	
            //System.out.println(counterWriters.decrementAndGet());
            System.out.println("Running Writer Thread - Salio: " + threadName);          
		}
	}
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub

		FifoReadWriteLock rwLock = new FifoReadWriteLock(); 
		ExecutorService executor = Executors.newFixedThreadPool(6);//El candado solo funciona para dos hilos
		
		
        executor.execute(() -> taskR(rwLock)); 

        executor.execute(() -> taskW(rwLock)); 
        
        executor.execute(() -> taskR(rwLock)); 
        executor.execute(() -> taskR(rwLock)); 
        executor.execute(() -> taskR(rwLock)); 
        
        executor.execute(() -> taskW(rwLock)); 
        executor.execute(() -> taskW(rwLock));
        executor.execute(() -> taskW(rwLock));

        executor.execute(() -> taskR(rwLock)); 
        executor.execute(() -> taskR(rwLock)); 
        executor.execute(() -> taskR(rwLock)); 
        executor.execute(() -> taskR(rwLock)); 

		executor.shutdown();
		
		//while(!executor.isTerminated()) {};

	
	}
}
