package clothes.shop.clases;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		/** Base de datos con logger BIEN HECHA*/
		BaseDatos.abrirConexion("Nombre bd", true);
		
		Cliente cliente1 = new Cliente(1, "Paco", "Chocholatero", false, 30, null); //TODO mirar lo de la tabla
		Cliente cliente2 = new Cliente(2, "Jordi", "Armellini", true, 60, null); //TODO mirar lo de la tabla
		Trabajador trabajador1 = new Trabajador(1, "Pepe", "Palotes", 1000, Puesto.EMPLEADO, "contraseña");
		Trabajador trabajador2 = new Trabajador(2, "Paco", "Paquete", 1400, Puesto.ENCARGADO, "contraseña2");
		Ropa ropa1 = new Ropa(1, "god g", Tipo.calcetines, 3500, Talla.L, "foto/camisetaJurassicParkNegro");
		Ropa ropa2 = new Ropa(2, "gucci", Tipo.camiseta, 1500, Talla.M, "foto/camisetaJurassicParkAzulMarino");
		
		BaseDatos.insertarCliente(cliente1);
		BaseDatos.insertarCliente(cliente2);
		BaseDatos.insertarTrabajador(trabajador1);
		BaseDatos.insertarTrabajador(trabajador2);
		BaseDatos.insertarRopa(ropa1);
		BaseDatos.insertarRopa(ropa2);
		
		System.out.println(BaseDatos.getClientes());
		System.out.println(BaseDatos.getTrabajadores());
		System.out.println(BaseDatos.getRopas());
		
		BaseDatos.actualizarPassword(trabajador2, "HOLA");
		System.out.println(BaseDatos.getTrabajadores());
		
		try {
			BaseDatos.borrarCliente(cliente1);
			BaseDatos.borrarTrabajador(trabajador1);
			BaseDatos.borrarRopa(ropa1);
		} catch (SQLException e) {}
		
		//Mostrar para ver si se ha borrado bien
		System.out.println(BaseDatos.getClientes()); 
		System.out.println(BaseDatos.getTrabajadores());
		System.out.println(BaseDatos.getRopas());
		
		BaseDatos.cerrarConexion();
		
		
		/** Base de datos MAL HECHA*/
//		GestorBD bd = new GestorBD();
//		bd.crearBBDD();
//		
//		Cliente cliente = new Cliente(1, "Paco", "Chocholatero", false, 30, new ArrayList<Ropa>());
//		bd.insertarDatosCliente(cliente);
//
//		Ropa ropa = new Ropa(1, "god g", Tipo.calcetines, 3500, Talla.L, "foto/camisetaJurassicParkNegro");
//		bd.insertarDatosRopa(ropa);
//		
//		Trabajador trabajador = new Trabajador(1, "Pepe", "Palotes", 1000, Puesto.EMPLEADO, "contraseña");
//		bd.insertarDatosTrabajador(trabajador);
//		
//		bd.obtenerDatosClientes();
//		bd.obtenerDatosRopa();
//		bd.obtenerDatosTrabajador();
//		
//		bd.actualizarPassword(trabajador, "aaa");
//		
//		bd.borrarDatosCliente();
//		bd.borrarDatosTrabajador();
//		bd.borrarDatosRopa();
//		
//		bd.borrarBBDD();

	}

}
