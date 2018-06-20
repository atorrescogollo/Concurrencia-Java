import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
	public static final int NUM_HILOS = 8;
	private static final int CAPACIDAD = 3;
	private static final int PRODUCTORES = 2;
	private static final int CONSUMIDORES = 2;

	private static ArrayList<Thread> hilos;
	private static ArrayBlockingQueue<String> cola;


	public static void main(String[] args) {
		hilos = new ArrayList<>();
		cola = new ArrayBlockingQueue<>(CAPACIDAD);

		Contador contador= new Contador();
		for (int i = 0; i < PRODUCTORES; i++) {
			Thread h = new Thread(new Productor(i+1, cola, contador), "Productor-"+(i+1));
			hilos.add(h);
		}

		for (int i = 0; i < CONSUMIDORES; i++) {
			Thread h = new Thread(new Consumidor(i+1, cola, contador), "Consumidor-"+(i+1));
			hilos.add(h);
		}

		for (Thread h : hilos) {
			h.start();
		}

		while (true) {
			System.out.println("[Hilo-principal]: La cola contiene "+cola.size()+" elementos.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
