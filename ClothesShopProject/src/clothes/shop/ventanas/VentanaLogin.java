package clothes.shop.ventanas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import clothes.shop.clases.BaseDatos;
import clothes.shop.clases.Cliente;
import clothes.shop.clases.Ropa;
import clothes.shop.clases.Trabajador;

public class VentanaLogin extends JFrame{
	private static final long serialVersionUID = 1L;
	
public VentanaLogin () {
		
		// VISUAL
		JPanel Principal = new JPanel();
			Principal.setLayout(new GridLayout(7,1));
			Principal.add( new JLabel("") );
			Principal.add( new JLabel("") );
			Principal.add( new JLabel("Usuario") );
			JTextField Usuario = new JTextField();
			Principal.add(Usuario);
			Principal.add( new JLabel("") );
			Principal.add( new JLabel("") );
			Principal.add( new JLabel("contraseña") );
			JPasswordField Contrasenya = new JPasswordField();
			Principal.add(Contrasenya);
			Principal.add( new JLabel("") );
			Principal.add( new JLabel("") );
			Principal.add( new JLabel("") );
			Principal.add( new JLabel("") );
		JPanel Botones = new JPanel();
			JButton iBoton = new JButton("Iniciar Sesión");
			Botones.add(iBoton);
			Botones.add( new JLabel("") );
			Botones.add( new JLabel("") );
			JButton rBoton = new JButton("Registrarse");
			Botones.add(rBoton);
		JPanel FotoPerfil = new JPanel();
			JLabel imagen = new JLabel();
			imagen.setIcon(new ImageIcon("../foto/fotoPerfilPredeterminada.jpg"));
			imagen.setPreferredSize(new Dimension( 200, 200 ));
			FotoPerfil.add( imagen );
		
		Principal.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));
		Botones.setBorder(BorderFactory.createEmptyBorder(20,100,20,100));
		FotoPerfil.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));

		Container contentPane = getContentPane();
			contentPane.add(FotoPerfil, BorderLayout.NORTH);
			contentPane.add(Principal, BorderLayout.CENTER);
			contentPane.add(Botones, BorderLayout.AFTER_LAST_LINE);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 600);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
	    setLocation(x, y);
		setTitle("Login");
		setVisible(true);
		
		
		// FUNCIONALIDAD
		iBoton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				String CredencialUsuario = Usuario.getText();
				System.out.println(CredencialUsuario);
				char[] CredencialContrasenya = Contrasenya.getPassword();
				System.out.println(CredencialContrasenya);
				//TODO: Abrir base de datos y comprobar Usuario
				BaseDatos.abrirConexion("prueba.bd", false);
				for (int i = 0; i < BaseDatos.getTrabajadores().size(); i++) {
					Boolean InicioCorrecto = false;
					Boolean ContraCorrecta = false;
					if ((BaseDatos.getClientes().get(i).getNombre() + BaseDatos.getClientes().get(i).getApellido() + "@opendeusto.es").equals(CredencialUsuario)) {
						InicioCorrecto = true;
					}
					
					//TODO: Contraseña en cliente BD
//					if ((InicioCorrecto == true) && (BaseDatos.getClientes().get(i).getContraseña().equals(CredencialContrasenya))){
//						ContraCorrecta = true;
//					}
					//TODO: Comprobar ambos resultados
				}
				for (int i = 0; i < BaseDatos.getTrabajadores().size(); i++) {
					Boolean InicioCorrecto = false;
					Boolean ContraCorrecta = false;
					if ((BaseDatos.getTrabajadores().get(i).getNombre() + BaseDatos.getTrabajadores().get(i).getApellido() + "@opendeusto.es").equals(CredencialUsuario)) {
						InicioCorrecto = true;
					}
					if ((InicioCorrecto == true) && (BaseDatos.getTrabajadores().get(i).getContraseña().equals(CredencialContrasenya.toString()))){
						ContraCorrecta = true;
					}
					//TODO: Comprobar ambos resultados
				}
				
				BaseDatos.cerrarConexion();
			}
		});
		
		rBoton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		
	}

}
