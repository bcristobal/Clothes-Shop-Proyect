package clothes.shop.clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ropa implements Pagable{
	
	private int id;
	private String nombre;
	private Tipo tipo;
	private int precio; // Esta en centimos
	private Talla talla;
	private String fotoUrl;
	private int cantidad;
	
	public Ropa(int id, String nombre, Tipo tipo, int precio, Talla talla, String fotoUrl, int cantidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		setPrecio(precio);
		this.talla = talla;
		this.fotoUrl = fotoUrl;
		setCantidad(cantidad);
	}
	
	public Ropa() {
		super();
		this.nombre = null;
		this.tipo = null;
		setPrecio(0);
		this.talla = null;
		this.fotoUrl = null;
		setCantidad(0);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
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
	
	@Override
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
	
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad (int cantidad) {
		if (cantidad < 0) {
			throw new IllegalArgumentException("ERROR: cantidad invalida"); // No se aceptan cantidades negativas
		} else {
			this.cantidad = cantidad;
		}
	}

	@Override
	public String toString() {
		return String.format("%s -  %.2f €", nombre, (float) getPrecio() / 100);
		//Precio está en euros
	}
	
	/**
	 * Ordena una lista de prendas por valor de id creciente
	 * @param List<Ropa> con la lista de prendas
	 */
	public static void idCreciente (List<Ropa> lista) {
		Comparator<Ropa> comp = (r1, r2) -> {
			return Integer.compare(r1.getId(),r2.getId());
		};
		Collections.sort(lista, comp);
	}
	
	/**
	 * Ordena una lista de prendas alfabéticamente por nombre de ropa
	 * @param List<Ropa> con la lista de prendas
	 */
	public static void alfabeticamente (List<Ropa> lista) {
		Comparator<Ropa> comp =(r1, r2) -> {
			return r1.getNombre().compareTo(r2.getNombre());
		};
		Collections.sort(lista, comp);
	}
	
	/**
	 * Ordena una lista de prendas por precio de ropa creciente
	 * @param List<Ropa> con la lista de prendas
	 */
	public static void precioCreciente (List<Ropa> lista) {
		Comparator<Ropa> comp =(r1, r2) -> {
			return Double.compare(r1.getPrecio(), r2.getPrecio());
		};
		Collections.sort(lista, comp);
	}
	
	/**
	 * Ordena una lista de prendas por precio de ropa descendiente
	 * @param List<Ropa> con la lista de prendas
	 */
	public static void precioDescendiente (List<Ropa> lista) {
		Comparator<Ropa> comp =(r1, r2) -> {
			return Double.compare(r1.getPrecio(), r2.getPrecio());
		};
		Collections.sort(lista, comp.reversed());
	}
	
	/**
	 * 
	 */
	public static List<Ropa> getRopaPorTalla (List<Ropa> ropas, Talla talla) {
		List<Ropa> resultado = new ArrayList<>();
		for (Ropa r : ropas) {
			if (r.getTalla().equals(talla)) {
				resultado.add(r);
			}
		}
		return resultado;
	}
}
