import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class Productor implements Runnable {

	private int id;
	private ArrayBlockingQueue<Mensaje> cola;
	private ConcurrentHashMap<Integer, Paquete> mapa;

	public Productor(int id, ArrayBlockingQueue<Mensaje> cola, ConcurrentHashMap<Integer,Paquete> mapa) {
		this.id=id;
		this.cola=cola;
		this.mapa=mapa;
	}

	@Override
	public void run() {
		System.out.println("[Productor-"+id+"]: id="+id);


		for (int i = 0; i < 10; i++) {
			String texto1="(id="+(i+1)+")T1Paquete";
			String texto2="(id="+(i+1)+")T2Paquete";
			Paquete p = new Paquete(i+1, texto1, texto2);
			mapa.put(i+1, p);
			System.out.println("[Productor-"+id+"]: Nuevo paquete en el mapa: "+p);
			try {
				Mensaje m = new Mensaje(i+1, texto1);
				cola.put(m);
				System.out.println("[Productor-"+id+"]: Nuevo mensaje encolado: "+m);
				m=new Mensaje(i+1, texto2);
				cola.put(m);
				System.out.println("[Productor-"+id+"]: Nuevo mensaje encolado: "+m);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
