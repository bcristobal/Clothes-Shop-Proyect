package clothes.shop.tests;

import static org.junit.Assert.*;

import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import clothes.shop.clases.Ropa;
import clothes.shop.clases.Talla;
import clothes.shop.clases.Tipo;

public class RopaTest {
	
	private Ropa ropa;
	private int id = 0;
	private String nombre = "nombre";
	private Tipo tipo = Tipo.camiseta;
	private float precio = 10;
	private Talla talla = Talla.L;

	@Before
	public void setUp() throws Exception {
		ropa = new Ropa(id, nombre, tipo, precio, talla);
	}

	@After
	public void tearDown() throws Exception {
		ropa = null;
	}

	@Test
	public void testRopaIntStringTipoFloatTalla() {
		assertNotNull(ropa);
		assertEquals(ropa.getId(), id);
		assertEquals(ropa.getNombre(), nombre);
		assertEquals(ropa.getTipo(), tipo);
		assertEquals(ropa.getPrecio(), precio, 0);
		assertEquals(ropa.getTalla(), talla);
		
	}

	@Test
	public void testRopa() {
		Ropa ropaVacio = new Ropa();
		assertNotNull(ropaVacio);
		assertEquals(ropaVacio.getId(), 0);
		assertEquals(ropaVacio.getNombre(), null);
		assertEquals(ropaVacio.getTipo(), null);
		assertEquals(ropaVacio.getPrecio(), 0, 0);
		assertEquals(ropaVacio.getTalla(), null);
	}

	@Test
	public void testGetId() {
		assertEquals(ropa.getId(), id);
	}

	@Test
	public void testSetId() {
		int idNuevo = 1;
		ropa.setId(idNuevo);
		assertEquals(ropa.getId(), idNuevo);
	}

	@Test
	public void testGetNombre() {
		assertEquals(ropa.getNombre(), nombre);
	}

	@Test
	public void testSetNombre() {
		String nombreNuevo = "nombreNuevo";
		ropa.setNombre(nombreNuevo);
		assertEquals(ropa.getNombre(), nombreNuevo);
	}

	@Test
	public void testGetTipo() {
		assertEquals(ropa.getTipo(), tipo);
	}

	@Test
	public void testSetTipo() {
		Tipo tipoNuevo = Tipo.zapatillas;
		ropa.setTipo(tipoNuevo);
		assertEquals(ropa.getTipo(), tipoNuevo);
	}

	@Test
	public void testGetPrecio() {
		assertEquals(ropa.getPrecio(), precio, 0);
	}

	@Test
	public void testSetPrecio() {
		assertThrows(IllegalArgumentException.class, () -> {ropa.setPrecio(1);});
	}

	@Test
	public void testGetTalla() {
		assertEquals(ropa.getTalla(), talla);
	}

	@Test
	public void testSetTalla() {
		Talla tallaNueva = Talla.XL;
		ropa.setTalla(tallaNueva);
		assertEquals(ropa.getTalla(), tallaNueva);
	}

	@Test
	public void testToString() {
		String toString = "(" + id + ") "+ nombre + " " + precio + " " + talla;
		assertEquals(ropa.toString(), toString);
	}

}
