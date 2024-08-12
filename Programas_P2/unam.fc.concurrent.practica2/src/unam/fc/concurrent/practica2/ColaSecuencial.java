package unam.fc.concurrent.practica2;
//Programa 7: Cola Secuencial, utiliza la clase Nodo
public class ColaSecuencial {
	private Nodo head;
	private Nodo tail;
	public ColaSecuencial() {
		this.head  = new Nodo("hnull");
	    this.tail  = new Nodo("tnull");
	    this.head.next = this.tail;
	}
	public Boolean enq(String x) {
		Nodo newnode = new Nodo(x);
		if(this.head.next == this.tail) {
			newnode.next = this.tail;
			this.head.next = newnode;	
		}else {
			Nodo last =  this.tail.next;
			newnode.next = tail;
			last.next = newnode;
		}
		tail.next = newnode;
		return true;
	}
	public String deq() {
		if(this.head.next == this.tail) {
			return "empty";
		}
		Nodo first = this.head.next;
		this.head.next = first.next;		
		return first.item;
	}
	public void print() {
		System.out.println("Print ");
        Nodo pred = this.head;
        Nodo curr = pred.next;
        System.out.println(pred.item);
        while (curr.item != "tnull") {
          pred = curr;
          curr = curr.next;
          System.out.println(pred.item);
        }
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ColaSecuencial queue = new ColaSecuencial();
		queue.deq();
		queue.enq("x");
		queue.enq("a");
		queue.deq();
		queue.enq("b");
		queue.enq("c");
		queue.deq();
		queue.deq();
		queue.deq();
		queue.deq();
		queue.enq("x");
		queue.print();
		}

}
