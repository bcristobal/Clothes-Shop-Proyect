package clothes.shop.clases;

public class Trabajador extends Persona {
	
	private int sueldo;
	private Puesto puesto;
	
	public Trabajador(int id, String nombre, String apellido, int sueldo, Puesto puesto) {
		super(id, nombre, apellido);
		this.sueldo = sueldo;
		this.puesto = puesto;
	}
	
	public Trabajador() {
		super();
		this.sueldo = 1000;
		this.puesto = Puesto.EMPLEADO;
	}

	public int getSueldo() {
		return sueldo;
	}

	public void setSueldo(int sueldo) {
		if (sueldo >= 900) {
			this.sueldo = sueldo;
		}
		else {
			System.err.println("Error: el sueldo no puede ser menor que 900€");
		}
	}

	public Puesto getPuesto() {
		return puesto;
	}

	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}
	
	
	
	
	
	
	

}
