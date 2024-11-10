package lists;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import nodes.Node;

public class CoarseGrained {
    private Node head; //First node
    private Node tail; //Last node
  
    private Lock lock = new ReentrantLock();
  
    public CoarseGrained(){
      // Add sentinels to start and end
      head  = new Node(Integer.MIN_VALUE);
      tail  = new Node(Integer.MAX_VALUE);
      head.next = this.tail;
    }
  
    public boolean add(String item) {
      Node pred, curr;
      int key = item.hashCode();
      lock.lock();
      try {
        pred = head;
        curr = pred.next;
        while (curr.key < key) {
          pred = curr;
          curr = curr.next;
        }
        if (key == curr.key) {
          return false;
        } else {
          Node node = new Node(item);
          node.next = curr;
          pred.next = node;
          return true;
        }
      } finally {
        lock.unlock();
      }
    }

    public boolean remove(String item) {
      Node pred, curr;
      int key = item.hashCode();
      lock.lock();
      try {
        pred = this.head;
        curr = pred.next;
        while (curr.key < key) {
          pred = curr;
          curr = curr.next;
        }
        if (key == curr.key) {  // present
          pred.next = curr.next;
          return true;
        } else {
          return false;         // not present
        }
      } finally {               // always unlock
        lock.unlock();
      }
    }
    /**
     * Test whether element is present
     * @param item element to test
     * @return true iff element is present
     */
    public boolean contains(String item) {
      Node pred, curr;
      int key = item.hashCode();
      lock.lock();
      try {
        pred = head;
        curr = pred.next;
        while (curr.key < key) {
          pred = curr;
          curr = curr.next;
        }
        return (key == curr.key);
      } finally {               // always unlock
        lock.unlock();
      }
    }
  
    public void printList(){
      Node pred, curr;
      pred = this.head;
      curr = pred.next;
      System.out.println("\n ->head:"+pred.item);
      System.out.println("->"+curr.item+" key:"+curr.key);
      while (curr.next != null) {
          pred = curr;
          curr = curr.next;
          System.out.println("->"+curr.item+" key:"+curr.key);
        }
    }
    
}
