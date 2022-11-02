package clothes.shop.clases;

public class Main {

	public static void main(String[] args) {
		GestorBD bd = new GestorBD();
		bd.crearBBDD();
		
		Cliente cliente = new Cliente();
		bd.insertarDatosCliente(cliente);

		Ropa ropa = new Ropa(1, "god g", Tipo.calcetines, 3.5f, Talla.L);
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
