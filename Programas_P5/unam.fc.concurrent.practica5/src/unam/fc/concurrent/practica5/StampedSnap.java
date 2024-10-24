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
public class StampedSnap<T> extends StampedValue<T> {
	
  public T[] snap; 
  public StampedSnap(T value) {
    super(value);
    snap  = null;
  }
  /**
   * Constructor.
   * @param stamp timestamp
   * @param value object value
   * @param snap 
   */
  public StampedSnap(long stamp, T value, T[] snap) {
    super(stamp, value);
    this.snap = snap;
  }
  public T getSnap(int i) {
	  return this.snap[i];
  }
}