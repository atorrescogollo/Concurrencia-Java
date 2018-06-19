import java.io.PrintWriter;
import java.util.concurrent.ArrayBlockingQueue;

public class Consumidor implements Runnable {

	private int id;
	private ArrayBlockingQueue<String> cola;
	private PrintWriter pw;

	public Consumidor(int id, ArrayBlockingQueue<String> cola, PrintWriter pw) {
		this.id=id;
		this.cola=cola;
		this.pw=pw;
	}

	@Override
	public void run() {
		System.out.println("[Consumidor-"+id+"]: id="+id);

		while(true) {
			try {
				String tarea = cola.take();
				System.out.println("[Consumidor-"+id+"]: "+tarea);

				synchronized (pw) {
					for (int i = 0; i < tarea.length(); i++) {
						pw.write(tarea, i, 1); // Escritura independiente de caracteres
					}
					pw.println();
					pw.flush(); // Refresco
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


	}

}
