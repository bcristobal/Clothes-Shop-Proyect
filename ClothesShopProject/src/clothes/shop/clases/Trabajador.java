package clothes.shop.clases;

public class Trabajador extends Persona {
	
	private int sueldo;
	private Puesto puesto;
	
	public Trabajador(int id, String nombre, String apellido, String fotoPerfil, String contraseña, int sueldo, Puesto puesto) {
		super(id, nombre, apellido, fotoPerfil, contraseña);
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
		if (sueldo < 0) {
			throw new IllegalArgumentException("ERROR: sueldo invalido");
		} else {
			this.sueldo = sueldo;
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
		return String.format("%s %s," +  " %d €," + " %s, %s", 
				String.valueOf(getNombre()), getApellido(), sueldo, puesto, getContraseña());
	}
	
	
}
