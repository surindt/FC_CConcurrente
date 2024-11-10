package lists;


import nodes.NodeAtomic;
import lists.Window;

import java.util.concurrent.atomic.AtomicMarkableReference;

public class NonBlocking {
    NodeAtomic head;
    public NonBlocking(){
        this.head  = new NodeAtomic(Integer.MIN_VALUE);
        NodeAtomic tail = new NodeAtomic(Integer.MAX_VALUE);
        while (!head.next.compareAndSet(null, tail, false, false));
    }

    public boolean add(String item) {
    int key = item.hashCode();
    boolean splice;
    while (true) {
      // find predecessor and curren entries
      Window window = find(head, key);
      NodeAtomic pred = window.pred, curr = window.curr;
      // is the key present?
      if (curr.key == key) {
        return false;
      } else {
        // splice in new node
        NodeAtomic node = new NodeAtomic(item);
        node.next = new AtomicMarkableReference(curr, false);
        if (pred.next.compareAndSet(curr, node, false, false)) {
          return true;
        }
      }
    }
  }


  public boolean remove(String item) {
    int key = item.hashCode();
    boolean snip;
    while (true) {
      // find predecessor and curren entries
      Window window = find(head, key);
      NodeAtomic pred = window.pred, curr = window.curr;
      // is the key present?
      if (curr.key != key) {
        return false;
      } else {
        // snip out matching node
        NodeAtomic succ = curr.next.getReference();
        snip = curr.next.attemptMark(succ, true);
        if (!snip)
          continue;
        pred.next.compareAndSet(curr, succ, false, false);
        return true;
      }
    }
  }

  public boolean contains(String item) {
    int key = item.hashCode();
    // find predecessor and curren entries
    Window window = find(head, key);
    NodeAtomic pred = window.pred, curr = window.curr;
    return (curr.key == key);
  }

   /**
   * If element is present, returns node and predecessor. If absent, returns
   * node with least larger key.
   * @param head start of list
   * @param key key to search for
   * @return If element is present, returns node and predecessor. If absent, returns
   * node with least larger key.
   */
  public Window find(NodeAtomic head, int key) {
    NodeAtomic pred = null, curr = null, succ = null;
    boolean[] marked = {false}; // is curr marked?
    boolean snip;
    retry: while (true) {
      pred = head;
      curr = pred.next.getReference();
      while (true) {
        succ = curr.next.get(marked); 
        while (marked[0]) {           // replace curr if marked
          snip = pred.next.compareAndSet(curr, succ, false, false);
          if (!snip) continue retry;
          curr = pred.next.getReference();
          succ = curr.next.get(marked);
        }
        if (curr.key >= key)
          return new Window(pred, curr);
        pred = curr;
        curr = succ;
      }
    }
  }

  public void printlist() {
    NodeAtomic pred, curr;
    pred = this.head;
    curr = pred.next.getReference();
    System.out.println("\n ->head:"+pred.item);
    //System.out.println("->"+curr.item+" key:"+curr.key);
    while (curr.next.getReference() != null) {
        curr = pred.next.getReference();
        //succ = curr.next.get(marked);
        System.out.println("->"+curr.item+" key:"+curr.key);
        
        pred = curr;
        //curr = succ;
    }
    //}
  }

 
}
