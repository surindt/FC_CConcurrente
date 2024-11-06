package unam.fc.concurrent.practica6;
/*
 * Programa que utiliza el programa de CountDownLatch para ejecutar varias rondas de consenso
 * El objeto de consenso es una instancia de ConsensusProtocol
 */
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class ExecConsenRounds {
    public static int[] winners;
    public static void task(CountDownLatch latch, CASConsensus<Integer> cas, int c, int round){
        Thread thread = Thread.currentThread();
        long me = thread.getId();
        int id = (int) (me % c);
        int winner = cas.decide(id, id);//Se ejecuta el protocolo de consenso, se devuelve al ganador
        winners[round] = winner;// se guarda al ganador en el arreglo
        System.out.println("Thread: "+ id + " says WIN: " + winner);
        latch.countDown(); // Una vez que terminas, disminuyes el contador de "latch"
    }
    public static void main(String[] args) {
        int c = 4, rounds = 10; // c es el no. de hilos, rounds es el no. de rondas
        winners = new int[rounds];
        ExecutorService executor = Executors.newFixedThreadPool(c);//El candado solo funciona para dos hilos
		
        
        for (int j = 0; j < rounds; j++) {//Iteras en el no. de rondas
            CountDownLatch latch =  new CountDownLatch(c);
            CASConsensus<Integer> protocolCAS = new CASConsensus<Integer>(c, -1);
            int currRound = j;
            System.out.println("Round: " + j);
            for (int i = 0; i < c; i++) { //creas "c" tareas, una por cada hilo
                executor.execute(() -> task(latch, protocolCAS, c, currRound)); 
            }
            try {
                latch.await(); // El hilo main espera a que los "c" hilos terminen
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }

        System.out.println("\n Winners: " + Arrays.toString(winners)); 

        executor.shutdown();
		
		//while(!executor.isTerminated()) {};
        
    }
}
