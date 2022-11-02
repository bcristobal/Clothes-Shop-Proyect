package clothes.shop.clases;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class GestorBD {
	
	protected static final String DRIVER_NAME = "org.sqlite.JDBC";
	protected static final String DATABASE_FILE = "data/database.db";
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
                   + " PUESTO ENUM ('EMPLEADO', 'ENCARGADO', 'JEFE')  NOT NULL, \n"
                   + " PASSWORD TEXT NOT NULL" // El enum de Puesto lo he puesto como texto y no como enum
                   + ");";
	       
	       String sql3 = "CREATE TABLE IF NOT EXISTS ROPA (\n"
                   + " ID_ROPA INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                   + " NOMBRE_ROPA TEXT NOT NULL,\n"
                   + " TIPO ENUM ('camiseta', 'pantalon', 'sudadera', 'calcetines', 'zapatillas') NOT NULL,\n" // El enum esta puesto como texto
                   + " PRECIO INTEGER NOT NULL, \n" // El precio se guarda en centimos
                   + " TALLA ENUM ('XS', 'S', 'M', 'M_L', 'L', 'L_XL', 'XL', 'XL_XXL', 'XXL') NOT NULL\n" // El enum esta puesto como texto
                   + ");";
	       
	       String sql4 = "CREATE TABLE IF NOT EXISTS CLIENTE_ROPA (\n"
                   + " ID_CLIENTE_F INTEGER NOT NULL ,\n"
                   + " ID_ROPA_F INTEGER NOT NULL, \n"
                   + " PRIMARY KEY (ID_CLIENTE_F, ID_ROPA_F), \n"
                   + " FOREIGN KEY(ID_CLIENTE_F) REFERENCES CLIENTE(ID_CLIENTE), \n"
                   + " FOREIGN KEY(ID_ROPA_F) REFERENCES ROPA(ID_ROPA) \n"
                   + ");";
           
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
	        	System.out.println("- Se ha creado la tabla Cliente_Ropa");
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
	
	public void insertarDatos(Cliente... clientes ) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO CLIENTE (ID_CLIENTE, NOMBRE_CLIENTE, APELLIDO_CLIENTE, ES_SOCIO, EDAD) VALUES ('%s', '%s', '%s', '%s', '%s');";
			
			System.out.println("- Insertando clientes...");
			
			//Se recorren los clientes y se insertan uno a uno
			for (Cliente c : clientes) {
				if (1 == stmt.executeUpdate(String.format(sql, c.getId(), c.getNombre(), c.getApellido(), c.getEsSocio(), c.getEdad()))) {					
					System.out.println(String.format(" - Cliente insertado: %s", c.toString()));
				} else {
					System.out.println(String.format(" - No se ha insertado el cliente: %s", c.toString()));
				}
			}			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}				
	}
	
	public void insertarDatos(Trabajador... trabajador ) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO CLIENTE (ID_TRABAJADOR, NOMBRE_TRABAJADOR, APELLIDO_TRABAJADOR, SUELDO, PUESTO) VALUES ('%s', '%s', '%s', '%s', '%s');";
			
			System.out.println("- Insertando trabajadores...");
			
			//Se recorren los clientes y se insertan uno a uno
			for (Trabajador t : trabajador) {
				if (1 == stmt.executeUpdate(String.format(sql, t.getId(), t.getNombre(), t.getApellido(), t.getSueldo(), t.getPuesto()))) {					
					System.out.println(String.format(" - Trabajador insertado: %s", t.toString()));
				} else {
					System.out.println(String.format(" - No se ha insertado el trabajador: %s", t.toString()));
				}
			}			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}				
	}
	
	public void insertarDatos(Ropa... ropa ) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO ROPA (ID_ROPA, NOMBRE_ROPA, TIPO, PRECIO, TALLA) VALUES ('%s', '%s', '%s', '%s', '%s');";
			
			System.out.println("- Insertando trabajadores...");
			
			//Se recorren los clientes y se insertan uno a uno
			for (Ropa r : ropa) {
				if (1 == stmt.executeUpdate(String.format(sql, r.getId(), r.getNombre(), r.getTipo(), r.getPrecio(), r.getTalla()))) {					
					System.out.println(String.format(" - Ropa insertado: %s", r.toString()));
				} else {
					System.out.println(String.format(" - No se ha insertado el Ropa: %s", r.toString()));
				}
			}			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}				
	}
	
	
	
	
	
}