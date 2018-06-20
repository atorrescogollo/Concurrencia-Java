import java.util.concurrent.Semaphore;

public class Hilo implements Runnable {

	private int id;
	private Semaphore semaphore;

	public Hilo(int id, Semaphore semaphore) {
		this.id=id;
		this.semaphore=semaphore;
	}

	@Override
	public void run() {
		try {
			System.out.println("[Hilo-"+id+"]: id="+id);
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaphore.release();
		}
	}
}
