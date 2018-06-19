import java.util.ArrayList;

public class Main {
	public static final int NUM_HILOS = 8;
	private static ArrayList<Thread> hilos;
	
	
	public static void main(String[] args) {
		hilos = new ArrayList<>();
		
		for (int i = 0; i < NUM_HILOS; i++) {
			Thread h = new Thread(new Hilo(i+1), "Hilo-"+(i+1));
			hilos.add(h);
			h.start();
		}

		for (Thread h : hilos) {
			try {
				h.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
