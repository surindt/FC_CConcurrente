package unam.fc.concurrent.practica6;

/*
 * FIFOReadWriteLock.java
 *
 * Created on January 9, 2006, 7:39 PM
 *
 * From "Multiprocessor Synchronization and Concurrent Data Structures",
 * by Maurice Herlihy and Nir Shavit.
 * Copyright 2006 Elsevier Inc. All rights reserved.
 * 
 * Modified 3nov24 Gilde Valeria R.
 */


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * First-in First-out readers/writers lock.
 * @author Maurice Herlihy
 */
public class FifoReadWriteLock implements ReadWriteLock {
  int readAcquires; // read acquires since start
  int readReleases; // read releses since start
  boolean writer;   // writer present?
  Lock metaLock;    // short-term synchronization
  Condition condition;
  Lock readLock;    // readers apply here
  Lock writeLock;   // writers apply here
  
  public FifoReadWriteLock() {
    readAcquires = readReleases = 0;
    writer    = false;
    metaLock  = new ReentrantLock();
    condition = metaLock.newCondition();
    readLock  = new ReadLock();
    writeLock = new WriteLock();
  }
  
  public Lock readLock() {
    return readLock;
  }
  
  public Lock writeLock() {
    return writeLock;
  }
  private class ReadLock implements Lock {
    public void lock() {
      metaLock.lock();
      try {
        
        //System.out.println("Lock ReadAcquires: " + readAcquires + " ReadReleases: " + readReleases);
        
        while (writer) {
          try {
            condition.await();
          } catch (InterruptedException ex) {
            // do something application-specific
          }
          
        }
        //System.out.println("Writer: " + writer);
        readAcquires++;
      } finally {
        metaLock.unlock();
      }
    }
    public void unlock() {
      metaLock.lock();
      try {
        readReleases++;
        //System.out.println("Unlock ReadAcquires: " + readAcquires + " ReadReleases: " + readReleases);

        if (readAcquires == readReleases)
          condition.signalAll();
      } finally {
        metaLock.unlock();
      }
    }
    public void lockInterruptibly() throws InterruptedException {
      throw new UnsupportedOperationException();
    }
    
    public boolean tryLock() {
      throw new UnsupportedOperationException();
    }
    
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
      throw new UnsupportedOperationException();
    }
    
    public Condition newCondition() {
      throw new UnsupportedOperationException();
    }
  }
  private class WriteLock implements Lock {
    public void lock() {
      metaLock.lock();
      try {
        while (writer)
          try {
            condition.await();
          } catch (InterruptedException e) {}
        
        writer = true;

        while (readAcquires != readReleases)
          try {
            condition.await();
          } catch (InterruptedException e) {}
        
        //System.out.println("Writer: " + writer);

      } finally {
        metaLock.unlock();
      }
    }
    public void unlock() {
      
      writer = false;
      //System.out.println("Writer: " + writer);
    }
    public void lockInterruptibly() throws InterruptedException {
      throw new UnsupportedOperationException();
    }
    
    public boolean tryLock() {
      throw new UnsupportedOperationException();
    }
    
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
      throw new UnsupportedOperationException();
    }
    
    public Condition newCondition() {
      throw new UnsupportedOperationException();
    }
  }
}