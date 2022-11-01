package clothes.shop.clases;

public abstract class Ropa {
	
	private int id;
	private String nombre;
	private Tipo tipo;
	private float precio;
	
	public Ropa(int id, String nombre, Tipo tipo, float precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.precio = precio;
	}
	
	public Ropa() {
		super();
		this.nombre = null;
		this.tipo = null;
		this.precio = 0;
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

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		if (precio < 0) {
			throw new IllegalArgumentException(); //No acepta precios negativos
		} else {
			this.precio = precio;
		}
	}

	@Override
	public String toString() {
		return String.format("(%s)" +  " %s %s", id, nombre, precio);
	}
	
}
