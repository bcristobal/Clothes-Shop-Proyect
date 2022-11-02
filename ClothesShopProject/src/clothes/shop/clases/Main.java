package clothes.shop.clases;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		GestorBD bd = new GestorBD();
		bd.crearBBDD();
		
//		Cliente cliente = new Cliente(); //TODO - ERROR
//		bd.insertarDatosCliente(cliente);

		Ropa ropa = new Ropa();
		bd.insertarDatosRopa(ropa);
		
		Trabajador trabajador = new Trabajador();
		bd.insertarDatosTrabajador(trabajador);
		
		bd.obtenerDatosClientes();
//		bd.obtenerDatosRopa();     //TODO
//		bd.obtenerDatosTrabajador; //TODO
		
		bd.actualizarPassword(trabajador, "aaa");
		
		bd.borrarDatosCliente();
		bd.borrarDatosTrabajador();
		bd.borrarDatosRopa();
		
		bd.borrarBBDD();

	}

}
