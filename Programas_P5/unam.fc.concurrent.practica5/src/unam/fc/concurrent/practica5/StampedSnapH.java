package unam.fc.concurrent.practica5;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/*
 * StampedSnap.java
 *
 * Created on January 19, 2006, 9:03 PM
 *
 * From "Multiprocessor Synchronization and Concurrent Data Structures",
 * by Maurice Herlihy and Nir Shavit.
 * Copyright 2006 Elsevier Inc. All rights reserved.
 */


/**
 * Labeled snapshot value.
 * @param T object type
 * @author Maurice Herlihy
 */
public class StampedSnapH<T> extends StampedValue<T> {
	
  //public T[] snap; 
	public List<T[]> snap = new ArrayList<T[]>();
	public List<T> values = new ArrayList<T>();
  public StampedSnapH(T value) {
    super(value);
    //snap  = null;
  }
  /**
   * Constructor.
   * @param stamp timestamp
   * @param value object value
   * @param snap 
   */
  public StampedSnapH(long stamp, T value, List<T[]> s, List<T> v) {
    super(stamp, value);
    snap = s;
    values = v;
  }
  public StampedSnapH(long stamp, T value) {
	    super(stamp, value);
	    
	  }
  public T getSnap(int i, int j) {
	  return this.snap.get(j)[i];
  }
}