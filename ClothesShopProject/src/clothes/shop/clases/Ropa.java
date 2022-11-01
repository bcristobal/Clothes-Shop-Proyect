package clothes.shop.clases;

public class Ropa {
	
	private int id;
	private String nombre;
	private Tipo tipo;
	private float precio;
	private Talla talla;
	
	public Ropa(int id, String nombre, Tipo tipo, float precio, Talla talla) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		setPrecio(precio);
		this.talla = talla;
	}
	
	public Ropa() {
		super();
		this.nombre = null;
		this.tipo = null;
		setPrecio(0);
		this.talla = null;
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
			System.err.println("ERROR: precio invalido");
			throw new IllegalArgumentException(); //No acepta precios negativos
		} else {
			this.precio = precio;
		}
	}

	public Talla getTalla() {
		return talla;
	}

	public void setTalla(Talla talla) {
		this.talla = talla;
	}

	@Override
	public String toString() {
		return String.format("(%s)" +  " %s %s %s", id, nombre, precio, talla);
	}
	
}
