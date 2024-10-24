package unam.fc.concurrent.practica5;

public class WFSnapshotH<T> implements Snapshot<T> {
	  private StampedSnapH<T>[] a_table; // array of MRSW atomic registers
	  public WFSnapshotH(int capacity, T init) {
	    a_table = (StampedSnapH<T>[]) new StampedSnapH[capacity];
	    for (int i = 0; i < a_table.length; i++) {
	      a_table[i] = new StampedSnapH<T>(init);
	    }
	  }
	  public void update(T value) {
	    int me = ThreadID.get();
	    T[] snap1 = this.scan();
	    StampedSnapH<T> oldValue = a_table[me];
	    oldValue.snap.add(snap1);
	    oldValue.values.add(value);
	    StampedSnapH<T> newValue =
	      new StampedSnapH<T>(oldValue.stamp+1, value, oldValue.snap, oldValue.values);
	    a_table[me] = newValue;
	  }
	  public StampedSnapH<T>[] collect() {
	    StampedSnapH<T>[] copy = (StampedSnapH<T>[]) new StampedSnapH[a_table.length];
	    for (int j = 0; j < a_table.length; j++)
	      copy[j] = a_table[j];
	    return copy;
	  }
	  public T[] scan() {
	    StampedSnapH<T>[] oldCopy;
	    StampedSnapH<T>[] newCopy;
	    boolean[] moved = new boolean[a_table.length];
	    oldCopy = collect();
	    collect: while (true) {
	      newCopy = collect();
	      for (int j = 0; j < a_table.length; j++) {
	        // did any thread move?
	        if (oldCopy[j].stamp != newCopy[j].stamp) {
	          if (moved[j]) {       // second move
	            return oldCopy[j].snap.getLast();
	          } else {
	            moved[j] = true;
	            oldCopy = newCopy;
	            continue collect;
	          }
	        }
	      }
	      // clean collect
	      T[] result = (T[]) new Object[a_table.length];
	      for (int j = 0; j < a_table.length; j++)
	        result[j] = (T) newCopy[j].values;
	      return result;
	    }
	  }
	}
