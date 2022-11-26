package clothes.shop.clases;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		GestorBD bd = new GestorBD();
		bd.crearBBDD();
		
		Cliente cliente = new Cliente(1, "Paco", "Chocholatero", false, 30, new ArrayList<Ropa>());
		bd.insertarDatosCliente(cliente);

		Ropa ropa = new Ropa(1, "god g", Tipo.calcetines, 3500, Talla.L, null);
		bd.insertarDatosRopa(ropa);
		
		Trabajador trabajador = new Trabajador(1, "Pepe", "Palotes", 1000, Puesto.EMPLEADO, "contraseña");
		bd.insertarDatosTrabajador(trabajador);
		
		bd.obtenerDatosClientes();
		bd.obtenerDatosRopa();
		bd.obtenerDatosTrabajador();
		
		bd.actualizarPassword(trabajador, "aaa");
		
		bd.borrarDatosCliente();
		bd.borrarDatosTrabajador();
		bd.borrarDatosRopa();
		
		bd.borrarBBDD();

	}

}
