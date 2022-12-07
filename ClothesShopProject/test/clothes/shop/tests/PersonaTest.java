package clothes.shop.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import clothes.shop.clases.Persona;

public class PersonaTest {
	private Persona persona;
	private int id = 0;
	private String nombre = "Nombre";
	private String apellido = "Apellido";
	private String fotoPerfil = "foto/fotoPerfilPredeterminada.jpg";

	@Before
	public void setUp() throws Exception {
		persona = new Persona(id, nombre, apellido, fotoPerfil) {
		};
	}
	
	@After
	public void tearDown() throws Exception {
		persona = null;
	}

	@Test
	public void testPersonaIntStringString() {
		assertNotNull(persona);
		assertEquals(id, persona.getId());
		assertEquals(nombre, persona.getNombre());
		assertEquals(apellido, persona.getApellido());
		assertEquals(fotoPerfil, persona.getFotoPerfil());
	}

	@Test
	public void testPersona() {
		Persona personaVacia = new Persona() {};
		assertNotNull(personaVacia);
		assertEquals(personaVacia.getId(), 0);
		assertEquals(personaVacia.getNombre(), null);
		assertEquals(personaVacia.getApellido(), null);
		assertEquals(personaVacia.getFotoPerfil(), "foto/fotoPerfilPredeterminada.jpg");
		
	}

	@Test
	public void testGetId() {
		assertEquals(id, persona.getId());
	}
	
	@Test
	public void testSetId() {
		int newId = 0;
		persona.setId(newId);
		
		assertEquals(newId, persona.getId());
	}

	@Test
	public void testGetNombre() {
		assertEquals(nombre, persona.getNombre());
	}
	
	@Test
	public void testSetNombre() {
		String newName = "New name";
		persona.setNombre(newName);
		
		assertEquals(newName, persona.getNombre());
	}

	@Test
	public void testGetApellido() {
		assertEquals(apellido, persona.getApellido());
	}
	
	@Test
	public void testSetApellido() {
		String newSurname = "New Surname";
		persona.setApellido(newSurname);
		
		assertEquals(newSurname, persona.getApellido());
	}

	@Test
	public void testGetFotoPerfil() {
		assertEquals(persona.getFotoPerfil(), fotoPerfil);
	}

	@Test
	public void testSetFotoPerfil() {
		String fotoPerfilNueva = "New fotoPerfil";
		persona.setFotoPerfil(fotoPerfilNueva);
		assertEquals(fotoPerfilNueva, persona.getFotoPerfil());
	}
	
	@Test
	public void testToString() {
		String toString = String.format("(%s)" + " %s %s", id, nombre, apellido);

		assertEquals(toString, persona.toString());
	}

}
