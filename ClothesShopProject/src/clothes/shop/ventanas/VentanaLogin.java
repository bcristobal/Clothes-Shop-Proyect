package clothes.shop.ventanas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaLogin extends JFrame{
	private static final long serialVersionUID = 1L;
	
public VentanaLogin () {
		
		// VISUAL
		JPanel Principal = new JPanel();
			Principal.setLayout(new GridLayout(7,1));
			Principal.add( new JLabel("") );
			Principal.add( new JLabel("") );
			Principal.add( new JLabel("Usuario") );
			Principal.add( new JTextField() );
			Principal.add( new JLabel("") );
			Principal.add( new JLabel("") );
			Principal.add( new JLabel("contraseña") );
			Principal.add( new JPasswordField() );
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
				//TODO: Funcionalidad Boton Iniciar Sesión
			}
		});
		
		rBoton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO: Funcionalidad Boton Registrarse
			}
		});
		
		
	}

}
