package clothes.shop.clases;

public class Ropa {
	private int id;
	private String nombre;
	private Tipo tipo;
	
	public Ropa(int id, String nombre, Tipo tipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
	}
	
	public Ropa() {
		super();
		this.id = 0;
		this.nombre = null;
		this.tipo = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

}
