package clothes.shop.tests;

import static org.junit.Assert.*;

import java.util.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import clothes.shop.clases.Puesto;
import clothes.shop.clases.Trabajador;

public class TrabajadorTest {
	
	private Trabajador trabajador;
	private int id = 0;
	private String nombre = "Nombre";
	private String apellido = "Apellido";
	private int sueldo = 1000;
	private Puesto puesto = Puesto.EMPLEADO;
	private String contraseña = "P@ssw0rd123!";

	@Before
	public void setUp() throws Exception {
		trabajador = new Trabajador(id, nombre, apellido, sueldo, puesto, contraseña);
		
	}

	@After
	public void tearDown() throws Exception {
		trabajador = null;
	}

	@Test
	public void testToString() {
		String toString = nombre + " " + apellido + ", " + sueldo + " €, " + puesto;
		assertEquals(trabajador.toString(), toString);
	}

	@Test
	public void testTrabajadorIntStringStringIntPuesto() {
		assertNotNull(trabajador);
		assertEquals(trabajador.getId(), id);
		assertEquals(trabajador.getNombre(), nombre);
		assertEquals(trabajador.getApellido(), apellido);
		assertEquals(trabajador.getSueldo(), sueldo);
		assertEquals(trabajador.getPuesto(), puesto);
		assertEquals(trabajador.getContraseña(), contraseña);
	}

	@Test
	public void testTrabajador() {
		Trabajador trabajadorVacio = new Trabajador();
		assertNotNull(trabajadorVacio);
		assertEquals(trabajadorVacio.getId(), 0);
		assertEquals(trabajadorVacio.getNombre(), null);
		assertEquals(trabajadorVacio.getApellido(), null);
		assertEquals(trabajadorVacio.getSueldo(), 1000);
		assertEquals(trabajadorVacio.getPuesto(), Puesto.EMPLEADO);
		assertEquals(trabajadorVacio.getContraseña(), null);
	}

	@Test
	public void testGetSueldo() {
		assertEquals(trabajador.getSueldo(), sueldo);
	}

	@Test
	public void testSetSueldo() { //TODO
		if (sueldo > 0) { 
			int sueldoNuevo = 2000;
			trabajador.setSueldo(sueldoNuevo);
			assertEquals(sueldoNuevo, trabajador.getSueldo());
		}
	}

	@Test
	public void testGetPuesto() {
		assertEquals(trabajador.getPuesto(), puesto);
	}

	@Test
	public void testSetPuesto() {
		Puesto puestoNuevo = Puesto.JEFE;
		trabajador.setPuesto(puestoNuevo);
		assertEquals(trabajador.getPuesto(), puestoNuevo);
	}
	
	@Test
	public void testGetContraseña() {
		assertEquals(trabajador.getContraseña(), contraseña);
	}

	@Test
	public void testSetContraseña() {
		String contraseñaNueva = "newP@ssw0rd123!";
		trabajador.setContraseña(contraseñaNueva);
		assertEquals(trabajador.getContraseña(), contraseñaNueva);
	}

}
