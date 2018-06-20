import java.util.concurrent.ArrayBlockingQueue;

public class Productor implements Runnable {

	private int id;
	private ArrayBlockingQueue<String> cola;
	private Contador contador;

	public Productor(int id, ArrayBlockingQueue<String> cola, Contador contador) {
		this.id=id;
		this.cola=cola;
		this.contador=contador;
	}

	@Override
	public void run() {

		for (int i = 0; i < 4; i++) {
			try {
				cola.put("HiloProductor-"+id+": Mensaje "+i);
				System.out.println("[Productor-"+id+"]: "+"HiloProductor-"+id+": Mensaje "+i);
				contador.elementoIntroducido();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
