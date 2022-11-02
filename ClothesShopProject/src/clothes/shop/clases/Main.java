package clothes.shop.clases;

public class Main {

	public static void main(String[] args) {
		GestorBD bd = new GestorBD();
		bd.crearBBDD();
		
//		Cliente cliente = new Cliente(); //TODO - ERROR
//		bd.insertarDatos(cliente);

		Ropa ropa = new Ropa();
		bd.insertarDatos(ropa);
		
		Trabajador trabajador = new Trabajador();
		bd.insertarDatos(trabajador);
		
		bd.borrarBBDD();

	}

}
