package clothes.shop.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.PrinterInfo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import clothes.shop.clases.Cliente;
import clothes.shop.clases.Ropa;
import clothes.shop.clases.Talla;
import clothes.shop.clases.Tipo;

public class ClienteTest {
	private Cliente cliente;
	int id = 0;
	String nombre = "Iker";
	String apellido = "Cristobal";
	int edad = 19;
	boolean esSocio = false;
	List<Ropa> listaRopa = new ArrayList<Ropa>();
	Tipo tipo;
	

	@Before
	public void setUp() throws Exception {
		cliente = new Cliente(id, nombre, apellido, esSocio, edad, listaRopa);
	}

	@After
	public void tearDown() throws Exception {
		cliente = null;
	}

	@Test
	public void testToString() {
		String toString = String.format("%s %s %s" + " años," + " socio: %s, lista de la ropa: %s",nombre, apellido, edad, esSocio, listaRopa);
        assertEquals(toString, cliente.toString());
	}

	@Test
	public void testClienteIntStringStringBooleanIntListOfRopa() {
		assertNotNull(cliente);
		assertEquals(cliente.getId(), id);
		assertEquals(cliente.getNombre(), nombre);
		assertEquals(cliente.getApellido(), apellido);
		assertEquals(cliente.getEsSocio(), esSocio);
		assertEquals(cliente.getEdad(), edad);
		assertEquals(cliente.getListaRopa(), listaRopa);
	}

	@Test
	public void testCliente() {
		Cliente clienteVacio = new Cliente();
		assertNotNull(clienteVacio);
		assertEquals(clienteVacio.getId(), 0);
		assertEquals(clienteVacio.getNombre(), null);
		assertEquals(clienteVacio.getApellido(), null);
		assertEquals(clienteVacio.getEsSocio(), false);
		assertEquals(clienteVacio.getEdad(), 18);
		assertEquals(clienteVacio.getListaRopa(), new ArrayList<Ropa>());
	}

	@Test
	public void testGetEsSocio() {
		assertEquals(cliente.getEsSocio(), esSocio);
	}

	@Test
	public void testSetEsSocio() {
		boolean esSocioNuevo = false;
		cliente.setEsSocio(esSocioNuevo);
		assertEquals(esSocioNuevo, cliente.getEsSocio());
	}

	@Test
	public void testGetEdad() {
		assertEquals(cliente.getEdad(), edad);
	}

	@Test
	public void testSetEdad() {
		assertThrows(IllegalArgumentException.class, () -> {cliente.setEdad(-1);});
	}

	@Test
	public void testGetListaRopa() {
		assertEquals(cliente.getListaRopa(), listaRopa);
	}

	@Test
	public void testSetListaRopa() {
		ArrayList<Ropa> listaRopaNueva = new ArrayList<Ropa>();
		cliente.setListaRopa(listaRopaNueva);
		assertEquals(listaRopaNueva, cliente.getListaRopa());
	}

	@Test
	public void testCalculaPrecio() { //TODO
		listaRopa.add(new Ropa(0, "Prueba", Tipo.camiseta, 10, Talla.XL));
		cliente.setEsSocio(true);			
		assertEquals(cliente.calculaPrecio(), 8.5, 0);
	}

}
