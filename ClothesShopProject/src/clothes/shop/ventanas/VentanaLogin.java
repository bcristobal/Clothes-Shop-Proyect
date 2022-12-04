package clothes.shop.ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import clothes.shop.clases.BaseDatos;

public class VentanaLogin extends JFrame{
	private static final long serialVersionUID = 1L;
	
public VentanaLogin () {
		
		JPanel Principal = new JPanel();
		Principal.setLayout(new GridLayout(15,1));
		Principal.add( new JLabel("") );
		Principal.add( new JLabel("") );
		Principal.add( new JLabel("Usuario") );
		Principal.add( new JTextField(15) );
		Principal.add( new JLabel("") );
		Principal.add( new JLabel("") );
		Principal.add( new JLabel("contraseña") );
		Principal.add( new JTextField(20) );
		Principal.add( new JLabel("") );
		Principal.add( new JLabel("") );
		Principal.add( new JLabel("") );
		Principal.add( new JLabel("") );
		JPanel Botones = new JPanel();
		Botones.add( new JButton("Iniciar Sesión") );
		Botones.add( new JLabel("") );
		Botones.add( new JLabel("") );
		Botones.add( new JButton("Registrarse") );
		JPanel FotoPerfil = new JPanel();
		JLabel imagen = new JLabel();
		imagen.setIcon(new ImageIcon("../foto/fotoPerfilPredeterminada.jpg"));
		Dimension tamanyo = imagen.getPreferredSize();
        imagen.setBounds(50, 30, tamanyo.width, tamanyo.height);
		FotoPerfil.add( imagen );
		Principal.setBorder(BorderFactory.createEmptyBorder(20,100,20,100));
		Botones.setBorder(BorderFactory.createEmptyBorder(20,100,20,100));
		FotoPerfil.setBorder(BorderFactory.createEmptyBorder(100,0,0,0));
		
		

		Container contentPane = getContentPane();
		contentPane.add(FotoPerfil, BorderLayout.NORTH);
		contentPane.add(Principal, BorderLayout.CENTER);
		contentPane.add(Botones, BorderLayout.AFTER_LAST_LINE);
//		add(Principal, BorderLayout.CENTER);

		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 600);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
	    setLocation(x, y);
		setTitle("Login");
		setVisible(true);
		
	}

}
