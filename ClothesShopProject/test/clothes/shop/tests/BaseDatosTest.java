package clothes.shop.tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import clothes.shop.clases.BaseDatos;
import clothes.shop.clases.Cliente;
import clothes.shop.clases.Puesto;
import clothes.shop.clases.Ropa;
import clothes.shop.clases.Talla;
import clothes.shop.clases.Tipo;
import clothes.shop.clases.Trabajador;

public class BaseDatosTest {
	
	private BaseDatos baseDatos;
	
	@Before
	public void setUp() throws ClassNotFoundException, SQLException {
		baseDatos = new BaseDatos();
		BaseDatos.abrirConexion("pruebaTest.bd");
	}

	@After
	public void tearDown() throws SQLException {
		//TODO 
	}

	@Test
	public void testGetClientes() {
		assertEquals(10, BaseDatos.getClientes().size());
		assertEquals("Joel", BaseDatos.getClientes().get(0).getNombre());
		assertEquals("Rigoberto", BaseDatos.getClientes().get(BaseDatos.getClientes().size()-1).getNombre());
	}

	@Test
	public void testGetTrabajadores() {
		assertEquals(10, BaseDatos.getTrabajadores().size());
		assertEquals("Miguel", BaseDatos.getTrabajadores().get(0).getNombre());
		assertEquals("Ane", BaseDatos.getTrabajadores().get(BaseDatos.getTrabajadores().size()-1).getNombre());
	}

	@Test
	public void testGetRopas() {
		assertEquals(10, BaseDatos.getRopas().size());
		assertEquals(1, BaseDatos.getRopas().get(0).getId());
		assertEquals(10, BaseDatos.getRopas().get( BaseDatos.getRopas().size()-1).getId());
	}

	@Test
	public void testInsertarCliente() {
		Cliente nuevoCliente = new Cliente(12, "Raul", "Pedrerol", "SinDefinir", "1234", 
				false, 18 , new ArrayList<Ropa>());
		
		if(nuevoCliente != null) {
			BaseDatos.insertarCliente(nuevoCliente);
		}
		
		assertEquals(nuevoCliente.getNombre(), 
				BaseDatos.getClientes().get(BaseDatos.getClientes().size()-1).getNombre());
	}

	@Test
	public void testInsertarTrabajador() {
		Trabajador nuevoTrabajador = new Trabajador(14, "Pedro", "Ramirez", "SinDefinir", 
				"contrasenyaaa", 1000, Puesto.EMPLEADO);
		
		if(nuevoTrabajador != null) {
			BaseDatos.insertarTrabajador(nuevoTrabajador);
		}
		
		assertEquals(nuevoTrabajador.getNombre(),
				BaseDatos.getTrabajadores().get(BaseDatos.getTrabajadores().size()-1).getNombre());
	}

	@Test
	public void testInsertarRopa() {
		Ropa nuevaRopa = new Ropa(11, "Air fox new", Tipo.zapatillas, 10, 
				Talla.L, "SinDefinir");
		
		if(nuevaRopa != null) {
			BaseDatos.insertarRopa(nuevaRopa);
		}
		
		assertEquals(nuevaRopa.getId(),
				BaseDatos.getRopas().get(BaseDatos.getRopas().size()-1).getId());
	}

	@Test
	public void testBorrarCliente() throws SQLException {
		Cliente clienteABorrar = BaseDatos.getClientes().get(0);
		BaseDatos.borrarCliente(clienteABorrar);
		assertEquals(9, BaseDatos.getClientes().size()); //10 - 1 (el borrado)
	}

	@Test
	public void testBorrarTrabajador() throws SQLException {
		Trabajador trabajadorABorrar = BaseDatos.getTrabajadores().get(0);
		BaseDatos.borrarTrabajador(trabajadorABorrar);
		assertEquals(9, BaseDatos.getTrabajadores().size());
	}

	@Test
	public void testBorrarRopa() throws SQLException {
		Ropa ropaABorrar = BaseDatos.getRopas().get(0);
		BaseDatos.borrarRopa(ropaABorrar);
		assertEquals(9, BaseDatos.getRopas().size());
	}

	@Test
	public void testActualizarPasswordTrabajador() {
		String contraseñaNueva = "newPassword";
		BaseDatos.actualizarPasswordTrabajador(BaseDatos.getTrabajadores().get(0), contraseñaNueva);
		assertEquals(contraseñaNueva, BaseDatos.getTrabajadores().get(0).getContraseña());
	}
	
	@Test
	public void testActualizarPasswordCliente() {
		String contraseñaNueva = "newPassword";
		BaseDatos.actualizarPasswordCliente(BaseDatos.getClientes().get(0), contraseñaNueva);
		assertEquals(contraseñaNueva, BaseDatos.getClientes().get(0).getContraseña());
	}

	@Test
	public void testGetRopasEntrePrecio() {
		int precioInicio = 1500;
		int precioFin = 3000;
		List<Ropa> ropasEntrePrecios = BaseDatos.getRopasEntrePrecio(precioInicio, precioFin);
		assertEquals(2, ropasEntrePrecios.size());
	}
	
	@Test
	public void testExisteCliente() {
		String nombreCliente = "Joel";
		assertTrue(BaseDatos.existeCliente(nombreCliente));
	}
	
	@Test
	public void testNoExisteCliente() {
		String nombreCliente = "Cloe";
		assertFalse(BaseDatos.existeCliente(nombreCliente));
	}
	
	@Test
	public void testExisteTrabajador() {
		String nombreTrabajador = "Miguel";
		assertTrue(BaseDatos.existeTrabajador(nombreTrabajador));
	}
	
	@Test
	public void testNoExisteTrabajador() {
		String nombreTrabajador = "Roberto";
		assertFalse(BaseDatos.existeTrabajador(nombreTrabajador));
	}
	
	@Test
	public void testCerrarConexion() {
		BaseDatos.cerrarConexion();
	}

}
