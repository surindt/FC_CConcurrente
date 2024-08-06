package unam.fc.concurrent.practica1;

//Programa 1: Creacion de un hilo
//   Dos formas: Forma de creacion del Hilo A o forma de creacion del hilo B
//   Utiliza las clases: ThreadExtends y ThreadRunnable

public class ExampleThreads{
	public static void main(String[] args) {
		//threadA es un hilo que extiende la clase Thread
		ThreadExtends threadA= new ThreadExtends(); 
		//threadB es un hilo Thread al cual se le pasa una instancia de Runnable
		Thread threadB= new Thread(new ThreadRunnable()); 
		threadA.start();
		threadB.start();
	}
}
