import java.util.concurrent.ArrayBlockingQueue;

public class Consumidor implements Runnable {

	private int id;
	private ArrayBlockingQueue<String> cola;
	private Contador contador;

	public Consumidor(int id, ArrayBlockingQueue<String> cola, Contador contador) {
		this.id=id;
		this.cola=cola;
		this.contador=contador;
	}

	@Override
	public void run() {

		while(true) {
			try {
				String msg = cola.take();
				System.out.println("[Consumidor-"+id+"]: "+msg);
				contador.elementoConsumido();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
