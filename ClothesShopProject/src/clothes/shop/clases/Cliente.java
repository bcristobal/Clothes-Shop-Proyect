package clothes.shop.clases;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {

	private static final double DESCUENTO = 0.15;

	private Boolean esSocio;
	private int edad;
	private List<Ropa> listaRopa;

	public Cliente(int id, String nombre, String apellido, Boolean esSocio, int edad, List<Ropa> listaRopa) {
		super(id, nombre, apellido);
		this.esSocio = false;
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
		if (edad > 0){
			this.edad = edad;
		} else {
			this.edad = 18;
		}
		
	}

	public List<Ropa> getListaRopa() {
		return listaRopa;
	}

	public void setListaRopa(List<Ropa> listaRopa) {
		this.listaRopa = listaRopa;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s  %s" + " años," + " socio: %s, lista de la ropa: %s", 
				String.valueOf(getNombre()), getApellido(), edad, esSocio, listaRopa);
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
