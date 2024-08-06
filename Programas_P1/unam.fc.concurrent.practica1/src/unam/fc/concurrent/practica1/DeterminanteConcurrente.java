package unam.fc.concurrent.practica1;
/*	Programa 10: Programa para obtener el determinante de una matriz de 3x3
 * 	Es más paralelizable, pero nos benefician en el tiempo de ejecucon utilizar hilos?
*/

public class DeterminanteConcurrente extends Thread{
    static int determinante;
    static int n_prueba = 3;
    static int matriz_prueba[][] = { { 1, 2, 2 }, { 1, 0, -2 }, { 3, -1, 1 }};
    int num1, num2, num3, partial;
    
    public DeterminanteConcurrente(int num1, int num2, int num3) {
		this.num1 = num1;
		this.num2 = num2;
		this.num3 = num3;
	}
    
    public static int determinanteMatriz3x3(int matriz[][], int n_prueba) {
        int result = 0;
        DeterminanteConcurrente thr1 = new DeterminanteConcurrente(matriz[0][0], matriz[1][1], matriz[2][2]);
        DeterminanteConcurrente thr2 = new DeterminanteConcurrente(matriz[1][0], matriz[2][1], matriz[0][2]);
        DeterminanteConcurrente thr3 = new DeterminanteConcurrente(matriz[2][0], matriz[0][1], matriz[1][2]);
        DeterminanteConcurrente thr4 = new DeterminanteConcurrente(matriz[2][0], matriz[1][1], matriz[0][2]);
        DeterminanteConcurrente thr5 = new DeterminanteConcurrente(matriz[1][0], matriz[0][1], matriz[2][2]);
        DeterminanteConcurrente thr6 = new DeterminanteConcurrente(matriz[0][0], matriz[2][1], matriz[1][2]);
        thr1.start();
		thr2.start();
		thr3.start();
		thr4.start();
		thr5.start();
		thr6.start();
        try{
			thr1.join();
			thr2.join();
			thr3.join();
			thr4.join();
			thr5.join();
			thr6.join();
		}catch(InterruptedException e) {}
        result = thr1.partial + thr2.partial + thr3.partial - thr4.partial - thr5.partial - thr6.partial;
       
        return result;
    }
    
    public void run(){
    	this.partial = this.num1 * this.num2 * this.num3;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime = System.nanoTime();
		determinante = determinanteMatriz3x3(matriz_prueba, n_prueba);
		long endTime = System.nanoTime();
        System.out.println("Program took " +
                (endTime - startTime) + "ns, result: " + determinante) ;

	}

}
