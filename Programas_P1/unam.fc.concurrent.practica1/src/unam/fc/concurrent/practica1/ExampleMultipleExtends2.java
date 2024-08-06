package unam.fc.concurrent.practica1;

//Programa 7: Ejemplo de un contador extendiendo la clase Thread
// Se crea un solo contador y se comparte a ThreadExtend2, asi que el contador es un objeto compartido
// Utilizamos join() para que se devuelva la cuenta final en la linea 23. 
// 
// Practica 2: Existe una condicion de carrera (race condition)
// Tiene una consistencia baja el contador

public class ExampleMultipleExtends2 {

	public static void main(String[] args) {
		CounterNaive counter = new CounterNaive();
		ThreadExtend2 threadA = new ThreadExtend2(counter);
		ThreadExtend2 threadB = new ThreadExtend2(counter);
		ThreadExtend2 threadC = new ThreadExtend2(counter);
		ThreadExtend2 threadD = new ThreadExtend2(counter);
		threadA.start();
		threadB.start();
		threadC.start();
		threadD.start();
		try{
			threadA.join();
			threadB.join();
			threadC.join();
			threadD.join();
		}catch(InterruptedException e) {
			System.out.println(e);
		}
		System.out.println("Total " + counter.getValue());
	}

}
class ThreadExtend2  extends Thread{
	private CounterNaive counter = null;
	
	ThreadExtend2(){}
	ThreadExtend2(CounterNaive counter){
		this.counter = counter;
	}
	public void run() {
//		String threadName = Thread.currentThread().getName();//Obtenemos el nombre del hilo
		//while(this.counter.getValue() < 10) {
		for(int i = 0; i < 10; i++) {
			this.counter.increment();
			try{
				Thread.sleep(100);
			}catch(InterruptedException e) {
				System.out.println(e);
			}
			
			//System.out.println("Running Thread " + threadName + " increment to: " + this.counter.increment());
		}
		//System.out.println("Running Thread " + threadName + " increment to: " + this.counter.increment());
	}
}
