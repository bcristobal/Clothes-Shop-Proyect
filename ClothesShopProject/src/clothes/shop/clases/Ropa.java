package clothes.shop.clases;

public class Ropa {
	
	private int id;
	private String nombre;
	private Tipo tipo;
	private int precio; // Esta en centimos
	private Talla talla;
	private String fotoUrl;
	
	public Ropa(int id, String nombre, Tipo tipo, int precio, Talla talla, String fotoUrl) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		setPrecio(precio);
		this.talla = talla;
		this.fotoUrl = fotoUrl;
	}
	
	public Ropa() {
		super();
		this.nombre = null;
		this.tipo = null;
		setPrecio(0);
		this.talla = null;
		this.fotoUrl = null;
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

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		if (precio < 0) {
			throw new IllegalArgumentException("ERROR: precio invalido"); //No acepta precios negativos
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
	
	public String getFotoUrl() {
		return fotoUrl;
	}
	
	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}

	@Override
	public String toString() {
		return String.format("(%s)" +  " %s %s %s", id, nombre, precio, talla);
	}
	
}
