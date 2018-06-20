import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {
	public static final int NUM_HILOS = 8;
	private static ArrayList<Thread> hilos;

	public static void main(String[] args) {
		hilos = new ArrayList<>();
		ArrayList<String> list = new ArrayList<>();

		Semaphore semaphore = new Semaphore(1);
		for (int i = 0; i < NUM_HILOS; i++) {
			Thread h = new Thread(new Hilo(i+1, list, semaphore), "Hilo-"+(i+1));
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

		System.out.println("[Hilo-principal]: La lista contiene "+list.size()+" elementos.");
	}

}
