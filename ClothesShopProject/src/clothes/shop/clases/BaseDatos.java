package clothes.shop.clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Clase de gestión de base de datos del sistema de compras
 * Incluye un logger que registra todas las operaciones de base de datos
 */

public class BaseDatos {
	private static Connection conexion;
	private static Logger logger = Logger.getLogger( "BaseDatos" );
	
	/** Abre conexión con la base de datos
	 * @param nombreBD	Nombre del fichero de base de datos
	 * @param reiniciaBD	true si se quiere reiniciar la base de datos (se borran sus contenidos si los tuviera y se crean datos por defecto)
	 * @return	true si la conexión ha sido correcta, false en caso contrario
	 */
	public static boolean abrirConexion( String nombreBD, boolean reiniciaBD ) {
		try {
			logger.log( Level.INFO, "Carga de librería org.sqlite.JDBC" );
			Class.forName("org.sqlite.JDBC");  // Carga la clase de BD para sqlite
			logger.log( Level.INFO, "Abriendo conexión con " + nombreBD );
			conexion = DriverManager.getConnection("jdbc:sqlite:data/" + nombreBD );
			if (reiniciaBD){
				Statement statement = conexion.createStatement();
				// CLIENTE
				String sent = "DROP TABLE IF EXISTS CLIENTE";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE IF NOT EXISTS CLIENTE (\n"
		                   + " ID_CLIENTE INTEGER PRIMARY KEY AUTOINCREMENT,\n"
		                   + " NOMBRE_CLIENTE TEXT NOT NULL,\n"
		                   + " APELLIDO_CLIENTE TEXT NOT NULL,\n"
		                   + " ES_SOCIO BOOLEAN NOT NULL CHECK (ES_SOCIO IN (0,1)),\n"
		                   + " EDAD INTEGER NOT NULL\n"
		                   + ");";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				// TRABAJADOR
				sent = "DROP TABLE IF EXISTS TRABAJADOR";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE IF NOT EXISTS TRABAJADOR (\n"
		                   + " ID_TRABAJADOR INTEGER PRIMARY KEY AUTOINCREMENT,\n"
		                   + " NOMBRE_TRABAJADOR TEXT NOT NULL,\n"
		                   + " APELLIDO_TRABAJADOR TEXT NOT NULL,\n"
		                   + " SUELDO INTEGER NOT NULL, \n"
		                   + " PUESTO TEXT  NOT NULL, \n"
		                   + " PASSWORD TEXT NOT NULL" // El enum de Puesto lo he puesto como texto y no como enum
		                   + ");";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				// ROPA
				sent = "DROP TABLE IF EXISTS ROPA";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE IF NOT EXISTS ROPA (\n"
		                   + " ID_ROPA INTEGER PRIMARY KEY AUTOINCREMENT,\n"
		                   + " NOMBRE_ROPA TEXT NOT NULL,\n"
		                   + " TIPO TEXT NOT NULL,\n" // El enum esta puesto como texto
		                   + " PRECIO INTEGER NOT NULL, \n" // El precio se guarda en centimos
		                   + " TALLA TEXT NOT NULL,\n" // El enum esta puesto como texto
		                   + " FOTO_URL TEXT NOT NULL" 
		                   + ");";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				// CLIENTE_ROPA
				sent = "DROP TABLE IF EXISTS CLIENTE_ROPA";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE IF NOT EXISTS CLIENTE_ROPA (\n"
		                   + " ID_CLIENTE_F INTEGER NOT NULL ,\n"
		                   + " ID_ROPA_F INTEGER NOT NULL, \n"
		                   + " PRIMARY KEY (ID_CLIENTE_F, ID_ROPA_F), \n"
		                   + " FOREIGN KEY(ID_CLIENTE_F) REFERENCES CLIENTE(ID_CLIENTE), \n"
		                   + " FOREIGN KEY(ID_ROPA_F) REFERENCES ROPA(ID_ROPA) \n"
		                   + ");";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				//
				try {
					//TODO falta crear la ropa-init.txt
				} catch (Exception e) {
					logger.log( Level.SEVERE, "Excepción", e );
				}
			}
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	/** Cierra la conexión abierta de base de datos ({@link #abrirConexion(String)})
	 */
	public static void cerrarConexion() {
		try {
			logger.log( Level.INFO, "Cerrando conexión" );
			conexion.close();
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Excepción", e );
		}
	}	
	
	/** Lee los clientes de la conexión de base de datos abierta (debe abrirse previamente con {@link #abrirConexion(String, boolean)}
	 * @return	Lista completa de clientes, null si hay algún error
	 */
	public static ArrayList<Cliente> getClientes(){
		try (Statement statement = conexion.createStatement()) {
			ArrayList<Cliente> ret = new ArrayList<>();
			String sent = "SELECT * FROM CLIENTE WHERE ID_CLIENTE >= 0;";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while( rs.next() ) { // Leer el resultset
				int id = rs.getInt("ID_CLIENTE");
				String nombre = rs.getString("NOMBRE_CLIENTE");
				String apellido = rs.getString("APELLIDO_CLIENTE");;
				Boolean esSocio = rs.getBoolean("ES_SOCIO");
				int edad = rs.getInt("EDAD");
				//TODO NO ESTA CREADO EN EL CREATE TABLE ESTA LISTA -> MIRAR PROBLEMA A LA HORA DE BORRAR EL CLIENTE SI SE BORRA SU LISTA ASOCIADA
				//List<Ropa> listaRopa = (List<Ropa>) rs.getArray("listaRopa"); //Casteo de array a list
				List<Ropa> listaRopa = new ArrayList<>();
				ret.add( new Cliente(id, nombre, apellido, esSocio, edad, listaRopa) );
			}
			return ret;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return null;
		}
	}
	
	/** Lee los trabajadores de la conexión de base de datos abierta (debe abrirse previamente con {@link #abrirConexion(String, boolean)}
	 * @return	Lista completa de trabajadores, null si hay algún error
	 */
	public static ArrayList<Trabajador> getTrabajadores(){
		try (Statement statement = conexion.createStatement()) {
			ArrayList<Trabajador> ret = new ArrayList<>();
			String sent = "SELECT * FROM TRABAJADOR WHERE ID_TRABAJADOR >= 0;";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while( rs.next() ) { // Leer el resultset
				int id = rs.getInt("ID_TRABAJADOR");
				String nombre = rs.getString("NOMBRE_TRABAJADOR");
				String apellido = rs.getString("APELLIDO_TRABAJADOR");
				int sueldo = rs.getInt("SUELDO");
				Puesto puesto = Puesto.valueOf(rs.getString("PUESTO"));;
				String contraseña = rs.getString("PASSWORD");
				ret.add( new Trabajador(id, nombre, apellido, sueldo, puesto, contraseña) );
			}
			return ret;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return null;
		}
	}
	
	/** Lee las ropas de la conexión de base de datos abierta (debe abrirse previamente con {@link #abrirConexion(String, boolean)}
	 * @return	Lista completa de ropa, null si hay algún error
	 */
	public static ArrayList<Ropa> getRopas() {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<Ropa> ret = new ArrayList<>();
			String sent = "SELECT * FROM ROPA WHERE ID_ROPA >= 0;";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while( rs.next() ) { // Leer el resultset
				int id = rs.getInt("ID_ROPA");
				String nombre = rs.getString("NOMBRE_ROPA");
				Tipo tipo = Tipo.valueOf(rs.getString("TIPO"));
				int precio = rs.getInt("PRECIO");
				Talla talla = Talla.valueOf(rs.getString("TALLA"));
				String fotoUrl = rs.getString("FOTO_URL");
				ret.add( new Ropa(id, nombre, tipo, precio, talla, fotoUrl) );
			}
			return ret;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return null;
		}
	}
	
	/** Inserta un cliente en la base de datos abierta (debe abrirse previamente con {@link #abrirConexion(String, boolean)}
	 * Actualiza el id del cliente insertado
	 * @param cliente	Cliente a insertar
	 * @return	true si la inserción es correcta, false en caso contrario
	 */
	public static boolean insertarCliente( Cliente cliente ) {
		try (Statement statement = conexion.createStatement()) {
			String sent = "INSERT INTO CLIENTE (ID_CLIENTE, NOMBRE_CLIENTE, APELLIDO_CLIENTE, ES_SOCIO, EDAD) VALUES (" + cliente.getId() + ",'" + cliente.getNombre() + "','" + cliente.getApellido() + "'," + cliente.getEsSocio() + "," + cliente.getEdad() + ");";
			logger.log( Level.INFO, "Statement: " + sent );
			int insertados = statement.executeUpdate( sent );
			if (insertados!=1) return false;  // Error en inserción
			// Búsqueda de la fila insertada - para ello hay que recuperar la clave autogenerada. Hay varias maneras, vemos dos diferentes:
			// Se hace utilizando método del propio objeto statement
			ResultSet rrss = statement.getGeneratedKeys();  // Genera un resultset ficticio con las claves generadas del último comando
			rrss.next();  // Avanza a la única fila 
			int pk = rrss.getInt( 1 );  // Coge la única columna (la primary key autogenerada)
			cliente.setId( pk );
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	/** Inserta un trabajador en la base de datos abierta (debe abrirse previamente con {@link #abrirConexion(String, boolean)}
	 * Actualiza el id del trabajador insertado
	 * @param trabajador	Trabajador a insertar
	 * @return	true si la inserción es correcta, false en caso contrario
	 */
	public static boolean insertarTrabajador( Trabajador trabajador ) {
		try (Statement statement = conexion.createStatement()) {
			String sent ="INSERT INTO TRABAJADOR (ID_TRABAJADOR, NOMBRE_TRABAJADOR, APELLIDO_TRABAJADOR, SUELDO, PUESTO, PASSWORD) VALUES (" + trabajador.getId() + ",'" + trabajador.getNombre() + "','" + trabajador.getApellido() + "'," + trabajador.getSueldo() + ",'" + trabajador.getPuesto() + "','" + trabajador.getContraseña() + "');";
			logger.log( Level.INFO, "Statement: " + sent );
			int insertados = statement.executeUpdate( sent );
			if (insertados!=1) return false;  // Error en inserción
			// Búsqueda de la fila insertada - para ello hay que recuperar la clave autogenerada. Hay varias maneras, vemos dos diferentes:
			// Se hace utilizando método del propio objeto statement
			ResultSet rrss = statement.getGeneratedKeys();  // Genera un resultset ficticio con las claves generadas del último comando
			rrss.next();  // Avanza a la única fila 
			int pk = rrss.getInt( 1 );  // Coge la única columna (la primary key autogenerada)
			trabajador.setId( pk );
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	/** Inserta una ropa en la base de datos abierta (debe abrirse previamente con {@link #abrirConexion(String, boolean)}
	 * Actualiza el id de la ropa insertada
	 * @param ropa	Ropa a insertar
	 * @return	true si la inserción es correcta, false en caso contrario
	 */
	public static boolean insertarRopa( Ropa ropa ) {
		try (Statement statement = conexion.createStatement()) {
			String sent ="INSERT INTO ROPA (ID_ROPA, NOMBRE_ROPA, TIPO, PRECIO, TALLA, FOTO_URL) VALUES (" + ropa.getId() + ",'" + ropa.getNombre() + "','" + ropa.getTipo() + "'," + ropa.getPrecio() + ",'" + ropa.getTalla() + "','" + ropa.getFotoUrl() + "');";
			logger.log( Level.INFO, "Statement: " + sent );
			int insertados = statement.executeUpdate( sent );
			if (insertados!=1) return false;  // Error en inserción
			// Búsqueda de la fila insertada - para ello hay que recuperar la clave autogenerada. Hay varias maneras, vemos dos diferentes:
			// Se hace utilizando método del propio objeto statement
			ResultSet rrss = statement.getGeneratedKeys();  // Genera un resultset ficticio con las claves generadas del último comando
			rrss.next();  // Avanza a la única fila 
			int pk = rrss.getInt( 1 );  // Coge la única columna (la primary key autogenerada)
			ropa.setId( pk );
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	
	/** Borra un cliente en la base de datos abierta (debe abrirse previamente con {@link #abrirConexion(String, boolean)}
	 * @param cliente	Cliente a borrar
	 * @throws SQLException	Lanzada si no existe el cliente a borrar, o se produce cualquier otro error de base de datos
	 */
	public static void borrarCliente( Cliente cliente ) throws SQLException {
		Statement statement = conexion.createStatement();
		String sent = "DELETE FROM CLIENTE WHERE ID_CLIENTE=" + cliente.getId() + ";";
		logger.log( Level.INFO, "Statement: " + sent );
		int borrados = statement.executeUpdate( sent );
		if (borrados==0) throw new SQLException( "No se ha borrado ningun cliente con id=" + cliente.getId() );
		statement.close();
	}
	
	/** Borra un trabajador en la base de datos abierta (debe abrirse previamente con {@link #abrirConexion(String, boolean)}
	 * @param trabajador	Trabajador a borrar
	 * @throws SQLException	Lanzada si no existe el trabajador a borrar, o se produce cualquier otro error de base de datos
	 */
	public static void borrarTrabajador( Trabajador trabajador ) throws SQLException {
		Statement statement = conexion.createStatement();
		String sent = "DELETE FROM TRABAJADOR WHERE ID_TRABAJADOR=" + trabajador.getId() + ";";
		logger.log( Level.INFO, "Statement: " + sent );
		int borrados = statement.executeUpdate( sent );
		if (borrados==0) throw new SQLException( "No se ha borrado ningun trabajador con id=" + trabajador.getId() );
		statement.close();
	}
	
	/** Borra una ropa en la base de datos abierta (debe abrirse previamente con {@link #abrirConexion(String, boolean)}
	 * @param ropa	Ropa a borrar
	 * @throws SQLException	Lanzada si no existe la ropa a borrar, o se produce cualquier otro error de base de datos
	 */
	public static void borrarRopa( Ropa ropa ) throws SQLException {
		Statement statement = conexion.createStatement();
		String sent = "DELETE FROM ROPA WHERE ID_ROPA=" + ropa.getId() + ";";
		logger.log( Level.INFO, "Statement: " + sent );
		int borrados = statement.executeUpdate( sent );
		if (borrados==0) throw new SQLException( "No se ha borrado ninguna ropa con id=" + ropa.getId() );
		statement.close();
	}
	
	//TODO ACTUALIZARPASSWORD
	
	/** Definir consulta de ropa entre precios (en centimos -> int)
	 * @param precioInicio	Precio inicial de la ropa
	 * @param precioFin	    Precio final de la ropa
	 * @return Lista de la ropa entre dicho intervalo, null si hay algún error
	 */
	public ArrayList<Ropa> getRopasEntrePrecio(int precioInicio, int precioFin) {
		try (Statement statement = conexion.createStatement()) {
			ArrayList<Ropa> ret = new ArrayList<>();
			String sent = "SELECT * FROM ROPA WHERE PRECIO >=" + precioInicio + " AND PRECIO <= " + precioFin + " ;";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			ArrayList<Ropa> ropas = BaseDatos.getRopas();
			while( rs.next() ) { // Leer el resultset
				int id = rs.getInt("ID_ROPA");
				String nombre = rs.getString("NOMBRE_ROPA");
				Tipo tipo = Tipo.valueOf(rs.getString("TIPO"));
				int precio = rs.getInt("PRECIO");
				Talla talla = Talla.valueOf(rs.getString("TALLA"));
				String fotoUrl = rs.getString("FOTO_URL");
				
				for (Ropa r : ropas) {
					if (r.getId() == rs.getInt("ID_ROPA")) {
						ret.add( new Ropa(id, nombre, tipo, precio, talla, fotoUrl) );
						break;
					}
				}
			}
			return ret;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return null;
		}
	}
}
