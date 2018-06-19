import java.util.concurrent.ArrayBlockingQueue;

public class Productor implements Runnable {

	private int id;
	private ArrayBlockingQueue<String> cola;

	public Productor(int id, ArrayBlockingQueue<String> cola) {
		this.id=id;
		this.cola=cola;
	}

	@Override
	public void run() {
		System.out.println("[Productor-"+id+"]: id="+id);

		for (int i = 0; i < 10; i++) {
			try {
				cola.put("HiloProductor-"+id+": Mensaje "+i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
