import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {
	public static  final int NUM_HILOS=10;
	public static final int MAX_HILOS_PARALELO = 2;

	private static ArrayList<Thread> hilos;

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(MAX_HILOS_PARALELO);
		hilos=new ArrayList<>();
		for (int i = 0; i < NUM_HILOS; i++) {
			try {
				Thread h = new Thread(new Hilo(i+1, semaphore), "Hilo-"+(i+1));
				hilos.add(h);
				semaphore.acquire();
				h.start();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (Thread h : hilos) {
			try {
				h.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("[Hilo-principal]: Terminado!");
	}
}
