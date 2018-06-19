import java.io.PrintWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Lock;

public class Consumidor implements Runnable {

	private int id;
	private ArrayBlockingQueue<String> cola;
	private PrintWriter pw;
	private Lock lock;

	public Consumidor(int id, ArrayBlockingQueue<String> cola, PrintWriter pw, Lock lock) {
		this.id=id;
		this.cola=cola;
		this.pw=pw;
		this.lock=lock;
	}

	@Override
	public void run() {
		System.out.println("[Consumidor-"+id+"]: id="+id);

		while(true) {
			try {
				String tarea = cola.take();
				System.out.println("[Consumidor-"+id+"]: "+tarea);

				lock.lock();
				for (int i = 0; i < tarea.length(); i++) {
					pw.write(tarea, i, 1); // Escritura independiente de caracteres
				}
				pw.println();
				pw.flush(); // Refresco
				lock.unlock();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


	}

}
