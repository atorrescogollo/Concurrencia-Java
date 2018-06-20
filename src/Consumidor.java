import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class Consumidor implements Runnable {

	private int id;
	private ArrayBlockingQueue<Mensaje> cola;
	private ConcurrentHashMap<Integer, Paquete> mapa;

	public Consumidor(int id, ArrayBlockingQueue<Mensaje> cola, ConcurrentHashMap<Integer, Paquete> mapa) {
		this.id=id;
		this.cola=cola;
		this.mapa=mapa;
	}

	@Override
	public void run() {
		System.out.println("[Consumidor-"+id+"]: id="+id);

		while(true) {
			try {
				Mensaje msg = cola.take();
				Paquete paquete = mapa.get(msg.id);
				if (paquete.texto1!=null&&paquete.texto1.equals(msg.texto)) {
					paquete.texto1=null;
					System.out.println("[Consumidor-"+id+"]: Paquete actualizado: "+paquete);
				}
				else if (paquete.texto2!=null&&paquete.texto2.equals(msg.texto)) {
					paquete.texto2=null;
					System.out.println("[Consumidor-"+id+"]: Paquete actualizado: "+paquete);
				}

				if (paquete.texto1==null && paquete.texto2==null) {
					mapa.remove(msg.id);
					System.out.println("[Consumidor-"+id+"]: Grupo "+msg.id+" completado");
				}


			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
