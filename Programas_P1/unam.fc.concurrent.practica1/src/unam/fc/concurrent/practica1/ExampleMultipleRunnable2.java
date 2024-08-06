package unam.fc.concurrent.practica1;
//Programa 8: Ejemplo de un contador implementando Runnable
//Se crea un solo contador y se comparte a ThreadExtend2, asi que el contador es un objeto compartido
//Los hilos se ejecutan en desorden y el resultado no es siempre: 1, 2, 3
public class ExampleMultipleRunnable2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CounterNaive counter = new CounterNaive();
		ThreadRunnable2 myRunnable = new ThreadRunnable2(counter);
		Thread threadA = new Thread(myRunnable);
		Thread threadB = new Thread(myRunnable);
		Thread threadC = new Thread(myRunnable);
		threadA.start();
		threadB.start();
		threadC.start();
	}

}

class ThreadRunnable2  implements Runnable{
	CounterNaive counter=null;
	ThreadRunnable2(){}
	ThreadRunnable2(CounterNaive counter){
		this.counter = counter;
	}
	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();//Obtenemos el nombre del hilo
		System.out.println("Running Thread Runnable " + threadName + " increment to: " + this.counter.increment());
	}
	
}
