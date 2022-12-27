package clothes.shop.ventanas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class VentanaLOGGINN extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel principal = new JPanel(new GridLayout(1, 2));
	private JPanel logIn = new JPanel(new GridLayout(5, 1));
	private JPanel register = new JPanel();
	private JPanel credenciales = new JPanel();
	
	// Multiple lines in a label
	private JLabel logInText = new JLabel("<html> Login To Your Account <br> <br> Login using social networks </html>");
	private JLabel logInText1 = new JLabel("-------OR-------");
	
	private JToolBar toolbarRedesSociales = new JToolBar();
	private ImageIcon facebookIcon = new ImageIcon("foto/iconos/facebookIcon.png");
	private JButton facebookButton = new JButton(redimensionarIcono(facebookIcon, 50, 50));
	private ImageIcon googleIcon = new ImageIcon("foto/iconos/googleIcon.png");
	private JButton googleButton = new JButton(redimensionarIcono(googleIcon, 50, 50));
	private ImageIcon linkedinIcon = new ImageIcon("foto/iconos/linkedinIcon.png");
	private JButton linkedinButton = new JButton(redimensionarIcono(linkedinIcon, 50, 50));
	
	private JToolBar toolbarContraseña = new JToolBar();
	private JPasswordField contraseñaTexto = new JPasswordField();
	private ImageIcon ojoIcon = new ImageIcon("foto/iconos/ojoIcon.png");
	private JButton passwordButton = new JButton(redimensionarIcono(ojoIcon, 20, 20));
	
	private ImageIcon loginIcon = new ImageIcon("foto/iconos/loginIcon.png");
	private JButton loginButton = new JButton(redimensionarIcono(loginIcon, 200, 100));
	
	public VentanaLOGGINN() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setTitle("Ventana Login");
		setVisible(true);
		
		Container contentPane = getContentPane();
		contentPane.add(principal);
		
		logIn.setBackground(new Color(153,170,181));
		
		logIn.add(logInText);
		
		ajustesDeBoton(facebookButton);
		ajustesDeBoton(googleButton);
		ajustesDeBoton(linkedinButton);
		toolbarRedesSociales.setBorder(null);
		toolbarRedesSociales.setBackground(new Color(153,170,181));
		añadirSeparador(toolbarRedesSociales, 59, 50);
		toolbarRedesSociales.add(facebookButton);
		añadirSeparador(toolbarRedesSociales, 59, 50);
		toolbarRedesSociales.add(googleButton);
		añadirSeparador(toolbarRedesSociales, 59, 50);
		toolbarRedesSociales.add(linkedinButton);
		logIn.add(toolbarRedesSociales);
		logIn.add(logInText1);
		
		credenciales.setLayout(new BoxLayout(credenciales, BoxLayout.Y_AXIS));
		credenciales.setBackground(new Color(153,170,181));
	 	credenciales.add( new JLabel("Usuario") );
	 	JTextField Usuario = new JTextField();
	 	credenciales.add(Usuario);
	 	credenciales.add( new JLabel("Contraseña") );
	 	passwordButton.setBorder(null);
	 	toolbarContraseña.setBorder(null);
	 	toolbarContraseña.add(contraseñaTexto);
	 	toolbarContraseña.add(passwordButton);
	 	credenciales.add(toolbarContraseña);
	 	logIn.add(credenciales);
		
	 	loginButton.setBorder(null);
	 	loginButton.setContentAreaFilled(false);
	 	logIn.add(loginButton);
	 	
		register.setBackground(Color.PINK);
		
		principal.add(logIn);
		principal.add(register);
		
		//Renderers (ajustes de texto)
		logInText.setHorizontalAlignment(JLabel.CENTER);
		logInText1.setHorizontalAlignment(JLabel.CENTER);
		
		logInText.setFont(new Font("Arial", Font.BOLD, 25));
		logInText1.setFont(new Font("Arial", Font.BOLD, 25));
		
		
		//Eventos
		passwordButton.setToolTipText("Mostrar contraseña"); 
		
		passwordButton.addActionListener(e -> {
			char[] pf = contraseñaTexto.getPassword();
			String value = new String(pf);
			if(!value.isEmpty()) {
				System.out.println("Contraseña: " + value);
			} else {
				System.out.println("ERROR: No se ha introducido ninguna contraseña");
			}
		});
		
		loginButton.addActionListener(e -> {
			//TODO comprobar que esta en la bd
			System.out.println("Se ha iniciado sesión correctamente");
		});
		
		
	}
	
	/**  Redimensiona una imagen
	 * @param imageIcon icono de la imagen a modificar
	 * @param width ancho de la imagen
	 * @param height largo de la imagen
	 * @return Imagen modificada
	 */
	public ImageIcon redimensionarIcono(ImageIcon imageIcon, int width, int height) {
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // escalar la imagen
		
		imageIcon = new ImageIcon(newimg);
		
		return(imageIcon);
	}
	
	/** Elimina el borde y contenido del boton
	 * @param boton Nombre del boton a modificar
	 */
	public void ajustesDeBoton(JButton boton) {
		boton.setBorder(null);
		boton.setContentAreaFilled(false);
	}
	
	/** Añade un separador a la toolbar
	 * @param toolbar Nombre de la toolbar a modificar
	 * @param width ancho del separador
	 * @param height largo del separador
	 */
	public void añadirSeparador(JToolBar toolbar, int width, int height) {
		toolbar.addSeparator(new Dimension(width, height));
	}
}
