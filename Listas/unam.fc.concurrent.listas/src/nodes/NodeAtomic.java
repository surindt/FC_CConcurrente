package nodes;


import java.util.concurrent.atomic.AtomicMarkableReference;

public class NodeAtomic {
    /**
     * actual item
     */
    public String item;
    /**
     * item's hash code
     */
    public int key;
    /**
     * next node in list
     */
    public AtomicMarkableReference<NodeAtomic> next;
    /**
     * Constructor for usual node
     * @param item element in list
     */
    public NodeAtomic(String item) {      // usual constructor
      this.item = item;
      this.key = item.hashCode();
      this.next = new AtomicMarkableReference<NodeAtomic>(null, false);
    }
    /**
     * Constructor for sentinel node
     * @param key should be min or max int value
     */
    public NodeAtomic(int key) { // sentinel constructor
      this.item = null;
      this.key = key;
      this.next = new AtomicMarkableReference<NodeAtomic>(null, false);
    }
}

