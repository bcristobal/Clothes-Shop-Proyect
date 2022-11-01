package clothes.shop.clases;

public class Trabajador extends Persona {
	
	private int sueldo;
	private Puesto puesto;
	
	public Trabajador(int id, String nombre, String apellido, int sueldo, Puesto puesto) {
		super(id, nombre, apellido);
		setSueldo(sueldo);
		this.puesto = puesto;
	}
	
	public Trabajador() {
		super();
		setSueldo(1000);
		this.puesto = Puesto.EMPLEADO;
	}

	public int getSueldo() {
		return sueldo;
	}

	public void setSueldo(int sueldo) {
		if (sueldo > 0) {
			this.sueldo = sueldo;
		} else {
			System.err.println("ERROR: sueldo invalido");
		}
	}

	public Puesto getPuesto() {
		return puesto;
	}

	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s," +  " %s €," + " %s", 
				String.valueOf(getNombre()), getApellido(), sueldo, puesto);
	}
	
	
}
