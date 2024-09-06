package unam.fc.concurrent.practica3;
import java.util.concurrent.atomic.*;
//Programa 5: Lamport's algorithm Unbounded

import unam.fc.concurrent.practica3.Nodo;

public class Bakery {
	private Nodo head;
	private Nodo tail;
	
	public Bakery() {
		this.head  = new Nodo(false, 0);
	    this.tail  = new Nodo(false, 10000);
	    while (!this.head.next.compareAndSet(null, this.tail));
	}
	public void lock(Nodo newnode) {
		Boolean added = false;
		Nodo last = null;
		
		while (!added) {
			if (this.tail.next.get() == null) {
				added = this.head.next.compareAndSet(this.tail, newnode);
				if (added) {
					this.tail.next.compareAndSet(null, newnode);
					last = head;
				}
			}else {
				last =  this.tail.next.get();
				added = last.next.compareAndSet(null, newnode);
				tail.next.compareAndSet(last, newnode);
			}
		}
		while (last.flag == true) {};
	}
	public void unlock(Nodo newnode) {
		newnode.flag = false;
	}
	public void print() {
		Nodo pred = this.head;
        Nodo curr = pred.next.get();
        System.out.println(pred.item);
        while (curr != null) {
          pred = curr;
          curr = curr.next.get();
          System.out.println(pred.item);
        }
	}
	

}
