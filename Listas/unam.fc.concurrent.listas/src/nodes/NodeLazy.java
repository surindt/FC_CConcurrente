package nodes;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NodeLazy {
      /**
     * actual item
     */
    public String item;
    /**
     * item's hash code
     */
    public int key;
    /**
     * next Node in list
     */
    public NodeLazy next;
    /**
     * If true, Node is logically deleted.
     */
    public boolean marked;
    /**
     * Synchronizes Node.
     */
    public Lock lock;
    /**
     * Constructor for usual Node
     * @param item element in list
     */
    public NodeLazy(String item) {      // usual constructor
      this.item = item;
      this.key = item.hashCode();
      this.next = null;
      this.marked = false;
      this.lock = new ReentrantLock();
    }
    /**
     * Constructor for sentinel Node
     * @param key should be min or max int value
     */
    public NodeLazy(int key) { // sentinel constructor
      this.item = null;
      this.key = key;
      this.next = null;
      this.marked = false;
      this.lock = new ReentrantLock();
    }

    public void setNext(NodeLazy ref){
      this.next = ref;
    }
    /**
     * Lock Node
     */
    public void lock() {lock.lock();}
    /**
     * Unlock Node
     */
    public void unlock() {lock.unlock();}
}

