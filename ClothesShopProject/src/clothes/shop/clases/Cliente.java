package clothes.shop.clases;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {

	private static final double DESCUENTO = 0.15;

	private Boolean esSocio;
	private int edad;
	private List<Ropa> listaRopa;

	public Cliente(String nombre, String apellido, Boolean esSocio, int edad, List<Ropa> listaRopa) {
		super(nombre, apellido);
		this.esSocio = esSocio;
		this.edad = edad;
		this.listaRopa = listaRopa;
	}

	public Cliente() {
		super();
		this.esSocio = false;
		this.edad = 0;
		this.listaRopa = new ArrayList<Ropa>();
	}

	public Boolean getEsSocio() {
		return esSocio;
	}

	public void setEsSocio(Boolean esSocio) {
		this.esSocio = esSocio;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public List<Ropa> getListaRopa() {
		return listaRopa;
	}

	public void setListaRopa(List<Ropa> listaRopa) {
		this.listaRopa = listaRopa;
	}

	public double calculaPrecio() {
		double totalPrecioCompra = 0.0;

		for (Ropa ropa : listaRopa) {
			totalPrecioCompra += ropa.getPrecio();
		}

		if (esSocio) {
			totalPrecioCompra = totalPrecioCompra - (DESCUENTO * totalPrecioCompra);
		}

		return totalPrecioCompra;
	}

}
