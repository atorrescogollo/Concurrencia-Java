
public class Mensaje {
	public final int id;
	public final String texto;

	public Mensaje(int id, String texto) {
		this.id=id;
		this.texto=texto;
	}

	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", texto=" + texto + "]";
	}


}
