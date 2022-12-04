package clothes.shop.clases;

import java.sql.SQLException;

import clothes.shop.ventanas.VentanaLogin;
import clothes.shop.ventanas.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		/** Base de datos con logger BIEN HECHA*/
		BaseDatos.abrirConexion("prueba.bd", false);
		
		
		
		
		
		Cliente cliente1 = new Cliente(11, "Paco", "Chocholatero", false, 30, null); //TODO mirar lo de la tabla
		Cliente cliente2 = new Cliente(12, "Jordi", "Armellini", true, 60, null); //TODO mirar lo de la tabla
		Trabajador trabajador1 = new Trabajador(11, "Pepe", "Palotes", 1000, Puesto.EMPLEADO, "contraseña");
		Trabajador trabajador2 = new Trabajador(12, "Paco", "Paquete", 1400, Puesto.ENCARGADO, "contraseña2");
		Ropa ropa1 = new Ropa(11, "god g", Tipo.calcetines, 3500, Talla.L, "foto/camisetaJurassicParkNegro");
		Ropa ropa2 = new Ropa(12, "gucci", Tipo.camiseta, 1500, Talla.M, "foto/camisetaJurassicParkAzulMarino");
		
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
		
//		VentanaPrincipal v = new VentanaPrincipal();
		VentanaLogin l = new VentanaLogin();
		
//		try {
//			BaseDatos.borrarCliente(cliente1);
//			BaseDatos.borrarTrabajador(trabajador1);
//			BaseDatos.borrarRopa(ropa1);
//		} catch (SQLException e) {}
//		
//		//Mostrar para ver si se ha borrado bien
//		System.out.println(BaseDatos.getClientes()); 
//		System.out.println(BaseDatos.getTrabajadores());
//		System.out.println(BaseDatos.getRopas());
		
		BaseDatos.cerrarConexion();
		

	}

}
