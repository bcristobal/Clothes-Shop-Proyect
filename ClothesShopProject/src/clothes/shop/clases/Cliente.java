package clothes.shop.clases;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {

	private static final double DESCUENTO = 0.15;

	private Boolean esSocio;
	private int edad;
	private List<Ropa> listaRopa;

	public Cliente(int id, String nombre, String apellido, String fotoPerfil, String contraseña, Boolean esSocio, int edad, List<Ropa> listaRopa) {
		super(id, nombre, apellido, fotoPerfil, contraseña);
		this.esSocio = false;
		setEdad(edad);
		this.listaRopa = listaRopa;
	}

	public Cliente() {
		super();
		this.esSocio = false;
		setEdad(18);
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
		if (edad < 0){
			throw new IllegalArgumentException("ERROR: edad invalida"); //No acepta precios negativos
		} else {
			this.edad = edad;
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
		return String.format("%s %s %s %d" + " años," + " socio: %s, lista de la ropa: %s", 
				String.valueOf(getNombre()), getApellido(), getContraseña(), edad, esSocio, listaRopa);
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
