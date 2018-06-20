
public class Contador {
	private int contador;

	public Contador() {
		contador=0;
	}

	public synchronized void elementoIntroducido() {
		contador++;
		System.out.println("Contador: IN ("+contador+")");
		notifyAll();
	}

	public synchronized void elementoConsumido() throws InterruptedException {
		contador--;
		System.out.println("Contador: OUT ("+contador+")");
		while(contador==0) wait();
	}

	public synchronized int cuantosElementos() {
		return contador;
	}
}
