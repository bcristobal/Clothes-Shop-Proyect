package clothes.shop.clases;

public class Main {

	public static void main(String[] args) {
		GestorBD bd = new GestorBD();
		bd.crearBBDD();
		bd.borrarBBDD();

	}

}
