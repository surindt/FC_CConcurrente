package unam.fc.concurrent.practica1;

//Programa 2: Ejemplo de un contador extendiendo la clase Thread
//		Acaban en distintos tiempos
//		Cada hilo crea su propia variable count porque son 3 objetos ThreadExtend1 distintos

public class ExampleMultipleExtends {

	public static void main(String[] args) {
		ThreadExtend1 threadA = new ThreadExtend1();
		ThreadExtend1 threadB = new ThreadExtend1();
		ThreadExtend1 threadC = new ThreadExtend1();
		threadA.start();
		threadB.start();
		threadC.start();
	}

}

class ThreadExtend1  extends Thread{
	int count=0;
	int increment() {
		count++;
		return count;
	}
	public void run() {
		String threadName = Thread.currentThread().getName();//Obtenemos el nombre del hilo
		System.out.println("Running Thread " + threadName + " increment to: " + increment());
	}
}

