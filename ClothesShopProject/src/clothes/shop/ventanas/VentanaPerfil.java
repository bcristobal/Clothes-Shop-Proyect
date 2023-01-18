package clothes.shop.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import clothes.shop.clases.BaseDatos;
import clothes.shop.clases.Cliente;
import clothes.shop.clases.Trabajador;

public class VentanaPerfil extends JFrame{
	
	String cookieUsuario = VentanaLOGGINN.getCookieUsuario();
    private static final long serialVersionUID = 1L;
    int ColumnaRaton = -1;
    
    JTable tabla;
    JPanel Foto;
    JLabel foto = new JLabel();
    
    
    
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
					t.setFotoPerfil(trabajador.getFotoPerfil());
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
					c.setFotoPerfil(cliente.getFotoPerfil());
				}
			}
		}
    	
    //VISUAL
    Foto = new JPanel();
    JPanel Datos = new JPanel();
    JLabel perfilHeader = new JLabel(String.format("<html> <center> <br> <h1> Welcome %s! </h1> <h3> Personal Information </h2> </center> </html>", cookieUsuario));
    Datos.add(perfilHeader);
    
        
    if (tipo == true) {
    	String data[][]={{t.getId()+"", t.getNombre() + " " + t.getApellido(), t.getPuesto()+""}};
		String column[]={"ID","NOMBRE","PUESTO"};
		tabla = new JTable(data, column);
		JScrollPane tablaSP = new JScrollPane(tabla);
		tabla.setRowHeight(40);
		tablaSP.setPreferredSize(new Dimension(300, 63));
		Datos.add(tablaSP);
		refrescarFoto(t.getFotoPerfil(), foto, 100, 100);
		Foto.add(foto);
	} else if (tipo == false) {
		String data[][]={{c.getId()+"", c.getNombre() + " " + c.getApellido(), c.getEsSocio()+""}};
		String column[]={"ID","NOMBRE","SOCIO"};
		tabla = new JTable(data, column);
		JScrollPane tablaSP = new JScrollPane(tabla);
		tabla.setRowHeight(40);
		tablaSP.setPreferredSize(new Dimension(350, 63));
		Datos.add(tablaSP);
		refrescarFoto(c.getFotoPerfil(), foto, 100, 100);
		Foto.add(foto);
	}
    
    
    
    Container contentPane = getContentPane();
    contentPane.add(Datos, BorderLayout.CENTER);
    contentPane.add(Foto, BorderLayout.NORTH);
    
    setIconImage(new ImageIcon("foto/logo.png").getImage());
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(450, 400);
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
    setLocation(x, y);
    setTitle("Perfil");
    setVisible(true);
   	 
   	 //FUNCIONALIDAD
	
	TableCellRenderer renderMouseOver = (table, value, isSelected, hasFocus, row, column) -> {
		JLabel label = new JLabel(value.toString());
		
		//Si la fila/columna de la celda se corresponden con la fila/columna
		//sobre la que está el ratón, el fondo es CYAN.
		if (column == this.ColumnaRaton) {
			label.setBackground(Color.CYAN);
		} else {
			label.setBackground(table.getBackground());
		}
		
		//Si la celda está seleccionada, se usan los colores por defecto
		if (isSelected) {
			label.setBackground(table.getSelectionBackground());
			label.setForeground(table.getSelectionForeground());
		}
		
		//Se fuerza el pintado del color de fondo del label
		label.setOpaque(true);
		
		return label;
	};
	
	this.tabla.setDefaultRenderer(Object.class, renderMouseOver);
	
	this.tabla.addMouseMotionListener((MouseMotionListener) new MouseMotionAdapter() {
		public void mouseMoved(MouseEvent e) {
			//Se obtiene la fila/columna sobre la que está el ratón mientras se mueve
			ColumnaRaton = tabla.columnAtPoint(e.getPoint());

			//Se repinta la tabla para forzar el renderizado de las celdas
			if (ColumnaRaton >-1) {
				tabla.repaint();
			}
		}
	});
   	 
	
    }
    private void refrescarFoto (String fotoUrl, JLabel label, int width, int height) {
		if (fotoUrl != null) {
			ImageIcon imagenIcon = new ImageIcon(fotoUrl);
			Image image = imagenIcon.getImage();
			Image nueva = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
			
			label.setIcon(new ImageIcon(nueva));
			label.repaint();
		} else {
			label.setIcon(null);
			label.repaint();
		}
	}
}
