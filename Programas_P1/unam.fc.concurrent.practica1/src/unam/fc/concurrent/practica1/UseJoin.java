package unam.fc.concurrent.practica1;

//Programa 6: Uso de join()
//		Si comentamos la lineas 34-49 el resultado es 0
//		Si solo comentamos la linea 35 el resultado es 01
//		Si solo comentamos la linea 36 el resultado es 11
//		El metodo A.join() permite que todo se congele hasta que termine el hilo A

public class UseJoin extends Thread{
	int rounds=0;
	int result=0;
	public UseJoin(int rounds) {
		this.rounds = rounds; 
	}
	
	public void run() {
		String threadName = Thread.currentThread().getName();//Obtenemos el nombre del hilo
		for(int i=0; i<this.rounds; i++) {
			try{
				Thread.sleep(200);
				System.out.println("Running round: "+i+" Thread "+ threadName);
				//threadA.join();
			}catch(InterruptedException e) {
				System.out.println(e);
			}
		}
		this.result=1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UseJoin threadA = new UseJoin(4);
		UseJoin threadB = new UseJoin(2);
		threadA.start();
		threadB.start();
		try{
			//threadA.join();
			threadB.join();
		}catch(InterruptedException e) {
			System.out.println(e);
		}
		System.out.println("Result: "+ threadA.result + threadB.result);
	}

}
