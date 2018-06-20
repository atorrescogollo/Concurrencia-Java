
public class Paquete {
	public final int id;
	public String texto1,texto2;

	public Paquete(int id, String texto1, String texto2) {
		this.id = id;
		this.texto1 = texto1;
		this.texto2 = texto2;
	}

	@Override
	public String toString() {
		return "Paquete [id=" + id + ", texto1=" + texto1 + ", texto2=" + texto2 + "]";
	}


}
