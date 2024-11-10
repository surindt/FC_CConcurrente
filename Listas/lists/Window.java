package lists;

import nodes.NodeAtomic;


public class Window {
    public NodeAtomic pred;
    public NodeAtomic curr;

    public Window(NodeAtomic pred, NodeAtomic curr) {
      this.pred = pred; this.curr = curr;
    }
}
  
