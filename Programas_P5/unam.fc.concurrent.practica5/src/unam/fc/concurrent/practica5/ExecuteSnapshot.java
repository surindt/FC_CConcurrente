package unam.fc.concurrent.practica5;
// Programa 1: Programa que ejecuta el WFSnapshot, imprime el contenido de los snaps actualizados por update()
//	Utiliza WFSnapshot<T>, el cual utiliza StampedSnap y StampedValue
//
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteSnapshot {
	public static void main(String[] args) {
		int capacity = 4;
		int init = -1;
		WFSnapshot<Integer> snapshot = new WFSnapshot<Integer>(capacity,init);
			ExecutorService executor = Executors.newFixedThreadPool(4);
			for(int i = 0; i < 20; i++) {
				final int ntask=i;
				executor.execute(() -> snapshot.update(ntask)); 
			}
			executor.shutdown();
			
			while(!executor.isTerminated()) {};
			
			System.out.println("Snapshot Final");
			StampedSnap<Integer>[] copy = (StampedSnap<Integer>[]) new StampedSnap[capacity];
			copy = snapshot.collect();
			for (int j = 0; j < capacity; j++) {
				System.out.println(" Value: " + copy[j].value + " Owner: " + 
			copy[j].owner + " Stamp: " + copy[j].stamp + " Snap: ");
				
				for (int k = 0; k < capacity; k++) {
					System.out.println("Thread " + k + " has value: " + copy[j].getSnap(k));
				}
			}
		}
	

}
