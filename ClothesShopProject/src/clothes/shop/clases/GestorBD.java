package clothes.shop.clases;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class GestorBD {
	
	protected static final String DRIVER_NAME = "org.sqlite.JDBC";
	protected static final String DATABASE_FILE = "db/database.db";
	protected static final String CONNECTION_STRING = "jdbc:sqlite:" + DATABASE_FILE;

	public GestorBD() {		
		try {
			//Cargar el diver SQLite
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException ex) {
			System.err.println(String.format("* Error al cargar el driver de BBDD: %s", ex.getMessage()));
			ex.printStackTrace();
		}
	}
	
	public void crearBBDD() {
		//Se abre la conexión y se obtiene el Statement
		//Al abrir la conexión, si no existía el fichero, se crea la base de datos
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			
	        String sql1 = "CREATE TABLE IF NOT EXISTS CLIENTE (\n"
	                   + " ID_CLIENTE INTEGER PRIMARY KEY AUTOINCREMENT,\n"
	                   + " NOMBRE_CLIENTE TEXT NOT NULL,\n"
	                   + " APELLIDO_CLIENTE TEXT NOT NULL,\n"
	                   + " ES_SOCIO BOOLEAN NOT NULL CHECK (ES_SOCIO IN (0,1)),\n"
	                   + " EDAD INTEGER NOT NULL\n"
	                   + ");";
	        
	       String sql2 = "CREATE TABLE IF NOT EXISTS TRABAJADOR (\n"
                   + " ID_TRABAJADOR INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                   + " NOMBRE_TRABAJADOR TEXT NOT NULL,\n"
                   + " APELLIDO_TRABAJADOR TEXT NOT NULL,\n"
                   + " SUELDO INTEGER NOT NULL, \n"
                   + " PUESTO TEXT NOT NULL\n" // El enum de Puesto lo he puesto como texto y no como enum
                   + ");";
	       
	       String sql3 = "CREATE TABLE IF NOT EXISTS ROPA (\n"
                   + " ID_ROPA INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                   + " NOMBRE_ROPA TEXT NOT NULL,\n"
                   + " TIPO TEXT NOT NULL,\n" // El enum esta puesto como texto
                   + " PRECIO INTEGER NOT NULL, \n" // El precio se guarda en centimos
                   + " TALLA TEXT NOT NULL\n" // El enum esta puesto como texto
                   + ");";
	       
	       String sql4 = "CREATE TABLE IF NOT EXISTS CLIENTE_ROPA (\n"
                   + " ID_CLIENTE_F INTEGER PRIMARY KEY NOT NULL ,\n"
                   + " ID_ROPA_F INTEGER PRIMARY KEY NOT NULL, "
                   + " FOREIGN KEY(ID_CLIENTE_F) REFERENCES CLIENTE(ID_CLIENTE), \n"
                   + " FOREIGN KEY(ID_ROPA_F) REFERENCES ROPA(ID_ROPA) \\n"
                   + ");";

	        	        
	       /*
	        * 
	        * FALTA LA TABLA DE CLIENTE_ROPA
	        * 
	        */
	       
	        if (!stmt.execute(sql1)) {
	        	System.out.println("- Se ha creado la tabla Cliente");
	        }
	        if (!stmt.execute(sql2)) {
	        	System.out.println("- Se ha creado la tabla Trabajador");
	        }
	        if (!stmt.execute(sql3)) {
	        	System.out.println("- Se ha creado la tabla Ropa");
	        }
	        if (!stmt.execute(sql4)) {
	        	System.out.println("- Se ha creado la tabla Ropa");
	        }
		} catch (Exception ex) {
			System.err.println(String.format("* Error al crear la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
	}
	
	public void borrarBBDD() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			
	        String sql1 = "DROP TABLE IF EXISTS CLIENTE";
	        String sql2 = "DROP TABLE IF EXISTS TRABAJADOR";
	        String sql3 = "DROP TABLE IF EXISTS ROPA";
	        String sql4 = "DROP TABLE IF EXISTS CLIENTE_ROPA";
			
	        //Se ejecuta la sentencia de creación de la tabla Estudiantes
	        if (!stmt.execute(sql1)) {
	        	System.out.println("- Se ha borrado la tabla Cliente");
	        }
	        if (!stmt.execute(sql2)) {
	        	System.out.println("- Se ha borrado la tabla Trabajador");
	        }
	        if (!stmt.execute(sql3)) {
	        	System.out.println("- Se ha borrado la tabla Ropa");
	        }
	        if (!stmt.execute(sql4)) {
	        	System.out.println("- Se ha borrado la tabla Cliente_Ropa");
	        }
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
		try {
			//Se borra el fichero de la BBDD
			Files.delete(Paths.get(DATABASE_FILE));
			System.out.println("- Se ha borrado el fichero de la BBDD");
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar el archivo de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}
		
	}
	
	
	
	
	
	
	
}