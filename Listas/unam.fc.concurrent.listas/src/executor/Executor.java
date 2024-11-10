package executor;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import callables.*;

import lists.*;

import text.Set;

import java.util.Random;


public class Executor{

    public static void main(String[] args) throws ExecutionException {
        
        int numProcessors = Runtime.getRuntime().availableProcessors(); // El num de procesos que tiene tu compu 
        ExecutorService executor = Executors.newFixedThreadPool(numProcessors-1);//Creamos una pool de procesos de n hilos, intenta primero con tu num de procesos

        CoarseGrained coarselist = new CoarseGrained(); //instancia de la clase
        //LazyList lazylist = new LazyList();//instancia de la clase
        //NonBlocking locklist = new NonBlocking();//instancia de la clase


        

        Random rand = new Random(); //Para crear num randoms
        Set set = new Set("alphabet");
        System.out.println("Number of operations: "+ set.numOperations()); 
        
		List<String> alphabetList = set.get();

            try {
                
                List<Future<Boolean>> futures = new ArrayList<Future<Boolean>>();
                long startTime = System.nanoTime();
                for (int i = 0; i < alphabetList.size(); i++) {//Vamos a crear distintas instancias de Tareas en el tamaño de la lista
                    int numRand = rand.nextInt(100);//Genero un numero random
                    futures.add(executor.submit(new CoarseCallable(alphabetList.get(i), coarselist,numRand))); //Para ejecutar CoarseList
                    //futures.add(executor.submit(new LazyCallable(alphabetList.get(i), lazylist,numRand))); // Para ejecutar LazyList
                    //futures.add(executor.submit(new LockFreeCallable(alphabetList.get(i), locklist,numRand))); //Para ejecutar LockFree
                }
                //CompletableFuture.anyOf(futures).join();
                
                
                for (int i = 0; i < futures.size(); i++) {
                    while(!futures.get(i).isDone());
                    //Boolean result = futures.get(i).get();
                    //System.out.printf("\n Result: "+result);
                    
                }
                
                long endTime = System.nanoTime();
                System.out.println("Program took " +
                        (endTime - startTime)* 1.0E-6 + "ms") ;
                
                
            } catch (Exception e) {
                System.out.printf("Error %s\n", e.getMessage());
            }
          
        //Future<Boolean> result = executor.submit(new CoarseCallable("non", coarselist,110));//ejecuto la tarea con num 110 que corresponde a imprimir después de 100ms para CoarseList
        //Future<Boolean> result = executor.submit(new LazyCallable("non", lazylist,110));//ejecuto la tarea con num 110 que corresponde a imprimir después de 100ms para LazyList
        //Future<Boolean> result = executor.submit(new LockFreeCallable("non", locklist,110));//ejecuto la tarea con num 110 que corresponde a imprimir después de 100ms para LockFree

        executor.shutdown();//Detengo la pool de hilos
    }
    
}