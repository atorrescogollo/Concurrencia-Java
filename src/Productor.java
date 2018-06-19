import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

public class Productor implements Runnable {

	private int id;
	private ArrayBlockingQueue<String> cola;
	private BufferedReader br;

	public Productor(int id, ArrayBlockingQueue<String> cola, BufferedReader br) {
		this.id=id;
		this.cola=cola;
		this.br=br;
	}

	@Override
	public void run() {
		System.out.println("[Productor-"+id+"]: id="+id);
		String tarea = null;
		try {
			while ((tarea = br.readLine())!=null) {
				try {
					System.out.println("[Productor-"+id+"]: "+tarea);
					cola.put(tarea);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
