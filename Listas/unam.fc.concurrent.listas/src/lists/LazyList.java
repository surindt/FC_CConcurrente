package lists;


import nodes.NodeLazy;


public class LazyList {
    /**
   * First list Node
   */
  private NodeLazy head;
  /**
   * Constructor
   */
  public LazyList() {
    // Add sentinels to start and end
    this.head  = new NodeLazy(Integer.MIN_VALUE);
    this.head.next = new NodeLazy(Integer.MAX_VALUE);
    //System.out.println("Here___init");

  }

  /**
   * Check that prev and curr are still in list and adjacent
   */
  private boolean validate(NodeLazy pred, NodeLazy curr) {
    return  !pred.marked && !curr.marked && pred.next == curr;
  }
  /**
   * Add an element.
   * @param item element to add
   * @return true iff element was not there already
   */
  public boolean add(String item) {
    System.out.println("Here___ add");

    int key = item.hashCode();
    while (true) {
      NodeLazy pred = this.head;
      NodeLazy curr = head.next;
      while (curr.key < key) {
        pred = curr; curr = curr.next;
      }
      pred.lock();
      try {
        curr.lock();
        try {
          if (validate(pred, curr)) {
            if (curr.key == key) { // present
              return false;
            } else {               // not present
              NodeLazy Node = new NodeLazy(item);
              Node.setNext(curr);
              pred.next = Node;
              return true;
            }
          }
        } finally { // always unlock
          curr.unlock();
        }
      } finally { // always unlock
        pred.unlock();
      }
    }
  }
  /**
   * Remove an element.
   * @param item element to remove
   * @return true iff element was present
   */
  public boolean remove(String item) {
    int key = item.hashCode();
    while (true) {
      NodeLazy pred = this.head;
      NodeLazy curr = head.next;
      while (curr.key < key) {
        pred = curr; curr = curr.next;
      }
      pred.lock();
      try {
        curr.lock();
        try {
          if (validate(pred, curr)) {
            if (curr.key != key) {    // present
              return false;
            } else {                  // absent
              curr.marked = true;     // logically remove
              pred.next = curr.next;  // physically remove
              return true;
            }
          }
        } finally {                   // always unlock curr
          curr.unlock();
        }
      } finally {                     // always unlock pred
        pred.unlock();
      }
    }
  }
  /**
   * Test whether element is present
   * @param item element to test
   * @return true iff element is present
   */
  public boolean contains(String item) {
    System.out.println("Here___ contains");

    int key = item.hashCode();
    NodeLazy curr = this.head;
    while (curr.key < key)
      curr = curr.next;
    return curr.key == key && !curr.marked;
  }

  public void printList(){
    System.out.println("Here___");
    NodeLazy pred, curr;
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

