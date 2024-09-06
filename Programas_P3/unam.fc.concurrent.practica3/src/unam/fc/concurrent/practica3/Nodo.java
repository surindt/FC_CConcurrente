package unam.fc.concurrent.practica3;

import java.util.concurrent.atomic.AtomicReference;

public class Nodo {
	public volatile Boolean flag; // Bandera que es true si quiero pasar y false si ya pase
	public AtomicReference<Nodo> next;
	public int item;
	public Nodo(Boolean flag, int item) {
		this.flag = flag;
		this.item = item;
		this.next = new AtomicReference<Nodo>(null);
	}
}
