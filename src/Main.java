import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
	private static final int CAPACIDAD = 3;
	private static final int PRODUCTORES = 5;
	private static final int CONSUMIDORES = 5;
	private static final String F_ORIGEN = "resources/src.txt";
	private static final String F_RESULTADOS = "resources/FicheroDeResultados.txt";

	private static ArrayList<Thread> hilos;
	private static ArrayBlockingQueue<String> cola;


	public static void main(String[] args) {
		hilos = new ArrayList<>();
		cola = new ArrayBlockingQueue<>(CAPACIDAD);
		File f_origen = new File(F_ORIGEN);
		File f_resultados = new File(F_RESULTADOS);
		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			FileReader fr=new FileReader(f_origen);
			br=new BufferedReader(fr);

			FileWriter fw=new FileWriter(f_resultados);
			pw=new PrintWriter(fw);

			for (int i = 0; i < PRODUCTORES; i++) {
				Thread h = new Thread(new Productor(i+1, cola, br), "Productor-"+(i+1));
				hilos.add(h);
			}

			for (int i = 0; i < CONSUMIDORES; i++) {
				Thread h = new Thread(new Consumidor(i+1, cola, pw), "Consumidor-"+(i+1));
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(br!=null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(pw!=null)
				pw.close();
		}

	}

}
