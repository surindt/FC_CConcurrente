package unam.fc.concurrent.practica3;


public class Peterson {
	private volatile boolean[] flag = new boolean[2];
	private volatile int victim;
	public void Peterson() {
		flag[0] = false; flag[1] = false;
		victim = 3;
	}
	public void lock() {
		Thread currentThread = Thread.currentThread();
		long id = currentThread.getId();
		int i = (int) (id % 2);
		int j = 1-i;
		flag[i] = true;
		victim = i;
		while (flag[j] && victim == i) {
		};
	}
	public void unlock() {
		Thread currentThread = Thread.currentThread();
		long id = currentThread.getId();
		int i = (int) (id % 2);
		flag[i] = false;
	}
}
