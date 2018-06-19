import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
	public static final int NUM_HILOS = 8;
	private static final int CAPACIDAD = 3;
	private static final int PRODUCTORES = 1;
	private static final int CONSUMIDORES = 2;
	private static final String F_ORIGEN = "resources/src.txt";

	private static ArrayList<Thread> hilos;
	private static ArrayBlockingQueue<String> cola;


	public static void main(String[] args) {
		hilos = new ArrayList<>();
		cola = new ArrayBlockingQueue<>(CAPACIDAD);
		File f_origen = new File(F_ORIGEN);
		BufferedReader br = null;
		try {
			FileReader fr=new FileReader(f_origen);
			br=new BufferedReader(fr);

			for (int i = 0; i < PRODUCTORES; i++) {
				Thread h = new Thread(new Productor(i+1, cola, br), "Productor-"+(i+1));
				hilos.add(h);
			}

			for (int i = 0; i < CONSUMIDORES; i++) {
				Thread h = new Thread(new Consumidor(i+1, cola), "Consumidor-"+(i+1));
				hilos.add(h);
			}

			for (Thread h : hilos) {
				h.start();
			}

			while (true) {
				System.out.println("[Hilo-principal]: La cola contiene "+cola.size()+" elementos.");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("[!] No se ha encontrado el fichero '"+f_origen.getAbsolutePath()+"'");
		}
		finally {
			if(br!=null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}

}
