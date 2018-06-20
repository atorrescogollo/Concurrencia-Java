import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Hilo implements Runnable {

	private int id;
	private ArrayList<String> list;
	private Semaphore semaphore;

	public Hilo(int id, ArrayList<String> list, Semaphore semaphore) {
		this.id=id;
		this.list=list;
		this.semaphore=semaphore;
	}

	@Override
	public void run() {
		System.out.println("[Hilo-"+id+"]: id="+id);

		for (int i = 0; i < 10000; i++) {
			try {
				semaphore.acquire();
				list.add("Texto");
				semaphore.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("[Hilo-"+id+"]: numElementos="+list.size());

	}

}
