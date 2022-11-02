package clothes.shop.clases;

public class Trabajador extends Persona {
	
	private int sueldo;
	private Puesto puesto;
	private String contraseña;
	
	public Trabajador(int id, String nombre, String apellido, int sueldo, Puesto puesto, String contraseña) {
		super(id, nombre, apellido);
		setSueldo(sueldo);
		setPuesto(puesto);
		setContraseña(contraseña);
	}
	
	public Trabajador() {
		super();
		setSueldo(1000);
		setPuesto(Puesto.EMPLEADO);
		setContraseña(null);
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
	
	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	@Override
	public String toString() {
		return String.format("%s %s," +  " %s €," + " %s", 
				String.valueOf(getNombre()), getApellido(), sueldo, puesto);
	}
	
	
}
