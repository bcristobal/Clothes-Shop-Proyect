package clothes.shop.ventanas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clothes.shop.clases.BaseDatos;
import clothes.shop.clases.Cliente;
import clothes.shop.clases.Trabajador;

public class VentanaPerfil extends JFrame{
	
	String cookieUsuario = VentanaLOGGINN.getCookieUsuario();
    private static final long serialVersionUID = 1L;
    public VentanaPerfil() {
    	//SEGUIMIENTO DE LA BD
    	Boolean tipo = true;
    	//TODO: Crear diferentes VentanaPerfiles
    	Trabajador t = new Trabajador();
    	Cliente c = new Cliente();
    	if (BaseDatos.existeTrabajador(cookieUsuario) == true) {
    		tipo = true;
			ArrayList<Trabajador> trabajadores = BaseDatos.getTrabajadores();
			for (Trabajador trabajador : trabajadores) {
				if (trabajador.getNombre().equals(cookieUsuario)) {
					t.setNombre(trabajador.getNombre());
					t.setApellido(trabajador.getApellido());
					t.setId(trabajador.getId());
					t.setPuesto(trabajador.getPuesto());
				}
			}
		} else if (BaseDatos.existeCliente(cookieUsuario) == true) {
			tipo = false;
			ArrayList<Cliente> clientes = BaseDatos.getClientes();
			for (Cliente cliente : clientes) {
				if (cliente.getNombre().equals(cookieUsuario)) {
					c.setNombre(cliente.getNombre());
					c.setApellido(cliente.getApellido());
					c.setId(cliente.getId());
					c.setEsSocio(cliente.getEsSocio());
				}
			}
		}
    	
    	//VISUAL
    JPanel Datos = new JPanel();
        
    if (tipo == true) {
    	JLabel perfilNombre = new JLabel(String.format("<html> <center> <br> <h4> Nombre y Apellido: %s %s </h4> </center> </html>", t.getNombre(), t.getApellido()));
    	JLabel perfilId = new JLabel(String.format("<html> <center> <br> <h4> Id: %s </h4> </center> </html>", t.getId()));
    	JLabel perfilPuesto = new JLabel(String.format("<html> <center> <br> <h4> Puesto: %s </h4> </center> </html>", t.getPuesto()));
        Datos.add(perfilNombre);
        Datos.add(perfilId);
        Datos.add(perfilPuesto);
	} else if (tipo == false) {
		JLabel perfilNombre = new JLabel(String.format("<html> <center> <br> <h4> Nombre y Apellido: %s %s </h4> </center> </html>", c.getNombre(), c.getApellido()));
		JLabel perfilId = new JLabel(String.format("<html> <center> <br> <h4> Id: %s </h4> </center> </html>", c.getId()));
		JLabel perfilEsSocio = new JLabel(String.format("<html> <center> <br> <h4> Socio: %s </h4> </center> </html>", c.getEsSocio()));
	    Datos.add(perfilNombre);
	    Datos.add(perfilId);
	    Datos.add(perfilEsSocio);
	}
    	
    JPanel Header = new JPanel();
    JLabel perfilHeader = new JLabel(String.format("<html> <center> <br> <h1> Welcome %s </h1> <h2> Personal Information </h2> </center> </html>", cookieUsuario));
    Header.add(perfilHeader);
    
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    
    Container contentPane = getContentPane();
    contentPane.add(Header, BorderLayout.NORTH);
    contentPane.add(Datos, BorderLayout.CENTER);
    
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(1000, 800);
    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
    setLocation(x, y);
    setTitle("Perfil");
    setVisible(true);
   	 
   	 //FUNCIONALIDAD
   	 
    }
}
