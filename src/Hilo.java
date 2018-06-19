import java.util.ArrayList;

public class Hilo implements Runnable {

	private int id;
	private ArrayList<String> list;
	
	public Hilo(int id) {
		this.id=id;
	}

	@Override
	public void run() {
		System.out.println("[Hilo-"+id+"]: id="+id);
		list = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			list.add("Texto");
		}
		
		System.out.println("[Hilo-"+id+"]: numElementos="+list.size());
		
	}

}