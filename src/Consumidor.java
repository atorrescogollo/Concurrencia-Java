import java.util.concurrent.ArrayBlockingQueue;

public class Consumidor implements Runnable {

	private int id;
	private ArrayBlockingQueue<String> cola;

	public Consumidor(int id, ArrayBlockingQueue<String> cola) {
		this.id=id;
		this.cola=cola;
	}

	@Override
	public void run() {
		System.out.println("[Consumidor-"+id+"]: id="+id);

		while(true) {
			try {
				String msg = cola.take();
				System.out.println("[Consumidor-"+id+"]: "+msg);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
