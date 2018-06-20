import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
	private static final int CAPACIDAD = 3;
	private static final int PRODUCTORES = 1;
	private static final int CONSUMIDORES = 2;

	private static ArrayList<Thread> hilos;
	private static ArrayBlockingQueue<Mensaje> cola;
	private static ConcurrentHashMap<Integer, Paquete> mapa;

	public static void main(String[] args) {
		hilos = new ArrayList<>();
		cola = new ArrayBlockingQueue<>(CAPACIDAD);
		mapa = new ConcurrentHashMap<>();

		for (int i = 0; i < PRODUCTORES; i++) {
			Thread h = new Thread(new Productor(i+1, cola, mapa), "Productor-"+(i+1));
			hilos.add(h);
		}

		for (int i = 0; i < CONSUMIDORES; i++) {
			Thread h = new Thread(new Consumidor(i+1, cola, mapa), "Consumidor-"+(i+1));
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
