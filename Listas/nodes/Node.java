package nodes;

public class Node {
    public String item;
    /**
     * item's hash code
     */
    public int key;
    /**
     * next Node in list
     */
    public Node next;
    /**
     * Constructor for usual Node
     * @param item element in list
     */
    public Node(String item) {
      this.item = item;
      this.key = item.hashCode();
    }
    /**
     * Constructor for sentinel Node
     * @param key should be min or max int value
     */
    public Node(int key) {
      this.item = null;
      this.key = key;
    }
}
