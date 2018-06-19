import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

		File f = new File("resources/consumidor-"+id+".txt");
		PrintWriter pw = null;
		try {
			if (!f.exists()) f.createNewFile();
			pw = new PrintWriter(new FileWriter(f));

			while(true) {
				try {
					String tarea = cola.take();
					System.out.println("[Consumidor-"+id+"]: "+tarea);
					pw.println(tarea);
					pw.flush(); // Refresco
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (pw!=null) {
				pw.close();
			}
		}

	}

}
