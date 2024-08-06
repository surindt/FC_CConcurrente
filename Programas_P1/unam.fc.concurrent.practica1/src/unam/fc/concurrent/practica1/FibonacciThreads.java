package unam.fc.concurrent.practica1;
/*	Programa 9: Fibonacci
 * Programa no muy paralelizable porque es recursivo, ademas de que crear muchos hilos hace un "overhead" en la memoria
*/
public class FibonacciThreads extends Thread{
	int n;
	int result;
	public FibonacciThreads(int n) {
		this.n = n;
	}
	
	public void run() {
		if((n==0)||(n==1)) result = 1;
		else {
			FibonacciThreads thr1 = new FibonacciThreads(n-1); 
			FibonacciThreads thr2 = new FibonacciThreads(n-2); 
			thr1.start();
			thr2.start();
			
			try{
				thr1.join();
				thr2.join();
			}catch(InterruptedException e) {}
			
			this.result = thr1.result + thr2.result;
			//System.out.println(this.result);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = 10;
		FibonacciThreads thr0 = new FibonacciThreads(num);
		thr0.start();
		try{
			//threadA.join();
			thr0.join();
		}catch(InterruptedException e) {}
		System.out.println("The "+ num + "th number of the fibonacci series is: "+ thr0.result);
	}
}
