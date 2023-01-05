package clothes.shop.ventanas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import clothes.shop.clases.BaseDatos;
import clothes.shop.clases.Cliente;

public class VentanaLOGGINN extends JFrame {
	//TODO:Cuando el usuario finalmente ,y con todas las comprobaciones hechas, inicie sesión se guardará aquí su credencial de usuario
	protected static String cookieUsuario;
	
	private static final long serialVersionUID = 1L;
	
	private JPanel principal = new JPanel(new GridLayout(1, 2));
	private JPanel logIn = new JPanel(new GridLayout(5, 1));
	private JPanel register = new JPanel(new GridLayout(3, 1));
	private JPanel credencialesLogin = new JPanel();
	private JPanel credencialesRegister = new JPanel();
	
	// Multiple lines in a label
	private JLabel logInText = new JLabel("<html> <center> <br> <h1> Login To Your Account </h1> <h2> Login using social networks </h2> </center> </html>");
	private JLabel logInText1 = new JLabel("------- OR -------");
	private JLabel usuarioLoginText = new JLabel("Usuario");
	private JLabel contrasenaLoginText = new JLabel("Contraseña");
	
	private JTextField usuarioLoginTextField = new JTextField();
	private JTextField usuarioRegisterTextField = new JTextField();
	
	private JToolBar toolbarRedesSociales = new JToolBar();
	private ImageIcon facebookIcon = new ImageIcon("foto/iconos/facebookIcon.png");
	private JButton facebookButton = new JButton(redimensionarIcono(facebookIcon, 50, 50));
	private ImageIcon googleIcon = new ImageIcon("foto/iconos/googleIcon.png");
	private JButton googleButton = new JButton(redimensionarIcono(googleIcon, 50, 50));
	private ImageIcon linkedinIcon = new ImageIcon("foto/iconos/linkedinIcon.png");
	private JButton linkedinButton = new JButton(redimensionarIcono(linkedinIcon, 50, 50));
	
	private JToolBar toolbarLoginContraseña = new JToolBar();
	private JPasswordField contraseñaLoginTextField = new JPasswordField();
	private ImageIcon ojoIconLogin = new ImageIcon("foto/iconos/ojoIcon.png");
	private JButton passwordLoginButton = new JButton(redimensionarIcono(ojoIconLogin, 20, 20));
	private ImageIcon ojoTachadoIconLogin = new ImageIcon("foto/iconos/ojoTachadoIcon.png");
	
	private ImageIcon loginIcon = new ImageIcon("foto/iconos/loginIcon.png");
	private JButton loginButton = new JButton(redimensionarIcono(loginIcon, 120, 50));
	
	private JLabel registerText = new JLabel("<html> <center> <br> <h1> New here? </h1> "
			+ "<h2> Sign up and discover a great amount of new opportunities! </h2> </center> </html>");
	private JLabel usuarioRegisterText = new JLabel("Usuario");
	private JLabel contrasenaRegisterText = new JLabel("Contraseña");
	
	private JToolBar toolbarRegisterContraseña = new JToolBar();
	private JPasswordField contraseñaRegisterTextField = new JPasswordField();
	private ImageIcon ojoIconRegister = new ImageIcon("foto/iconos/ojoIcon.png");
	private JButton passwordRegisterButton = new JButton(redimensionarIcono(ojoIconRegister, 20, 20));
	private ImageIcon ojoTachadoIconRegister = new ImageIcon("foto/iconos/ojoTachadoIcon.png");
	
	
	private ImageIcon registerIcon = new ImageIcon("foto/iconos/registerIcon.png");
	private JButton registerButton = new JButton(redimensionarIcono(registerIcon, 120, 50));
	
	public VentanaLOGGINN() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(800, 600);
		setLocation(
				(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),  
				(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2) 
		); //Muestra la ventana en el centro de la pantalla
		setTitle("Welcome");
		setIconImage(new ImageIcon("foto/logo.png").getImage());
		setVisible(true);
		
		Container contentPane = getContentPane();
		contentPane.add(principal);
		
		initLogin();
	 	
		initRegister();
		
		principal.add(logIn);
		principal.add(register);
		
		//Renderers (ajustes de texto)
		logInText.setHorizontalAlignment(JLabel.CENTER);
		logInText1.setHorizontalAlignment(JLabel.CENTER);
		usuarioLoginText.setHorizontalAlignment(JLabel.LEFT);
		contrasenaLoginText.setHorizontalAlignment(JLabel.LEFT);
		
		logIn.setBorder(new EmptyBorder(5, 20, 5, 20));
		register.setBorder(new EmptyBorder(5, 20, 5, 20));
		
		logInText.setFont(new Font("Arial", Font.BOLD, 25));
		logInText1.setFont(new Font("Arial", Font.BOLD, 15));
		
		registerText.setFont(new Font("Arial", Font.BOLD, 25));
		
		//Eventos
		//TODO Cliente o Trabajador
		
		facebookButton.setToolTipText("Coming soon...");
		googleButton.setToolTipText("Coming soon...");
		linkedinButton.setToolTipText("Coming soon...");
		passwordLoginButton.setToolTipText("Mostrar contraseña"); 
		passwordRegisterButton.setToolTipText("Mostrar contraseña"); 
	
		passwordLoginButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				char[] pf = contraseñaLoginTextField.getPassword();
				String value = new String(pf);
				if(!value.isEmpty()) {
					passwordLoginButton.setIcon(redimensionarIcono(ojoTachadoIconLogin, 20, 20));
					contraseñaLoginTextField.setEchoChar((char)0); //Muestra la contraseña
				} else {
					 JOptionPane.showMessageDialog(null, "No se ha introducido ninguna contraseña", "Aviso",JOptionPane.WARNING_MESSAGE);
				}
			}
			public void mouseReleased(MouseEvent e) {
				passwordLoginButton.setIcon(redimensionarIcono(ojoIconLogin, 20, 20));
				contraseñaLoginTextField.setEchoChar('•');
			}
		});
		
		passwordRegisterButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				char[] pf = contraseñaRegisterTextField.getPassword();
				String value = new String(pf);
				if(!value.isEmpty()) {
					passwordRegisterButton.setIcon(redimensionarIcono(ojoTachadoIconRegister, 20, 20));
					contraseñaRegisterTextField.setEchoChar((char)0); //Muestra la contraseña
				} else {
					JOptionPane.showMessageDialog(null, "No se ha introducido ninguna contraseña", "Aviso",JOptionPane.WARNING_MESSAGE);
				}
			}
			public void mouseReleased(MouseEvent e) {
				passwordRegisterButton.setIcon(redimensionarIcono(ojoIconRegister, 20, 20));
				contraseñaRegisterTextField.setEchoChar('•');
			}
		});
		
		loginButton.addActionListener(e -> {
			List<Cliente> clientes = new ArrayList<>(BaseDatos.getClientes());
			String nombreUsuario = usuarioLoginTextField.getText();
			String contraseña = String.valueOf(contraseñaLoginTextField.getPassword());
			
			if( nombreUsuario.isEmpty() || contraseña.isEmpty() ){
				loginButton.setEnabled(false); //Bloquea el botón
				JOptionPane.showMessageDialog(null, "Falta de datos al iniciar sesión", "Aviso",JOptionPane.WARNING_MESSAGE);
				loginButton.setEnabled(true);
			}
			
			//TODO Sino existe ese cliente
			for (Cliente cliente : clientes) {
				if(nombreUsuario.equals(cliente.getNombre()) && contraseña.equals(cliente.getContraseña())) {
					JOptionPane.showMessageDialog(null, "Se ha iniciado sesión correctamente");
					//Seguimiento de cookie
					cookieUsuario = usuarioLoginTextField.getText();
					//Cerrar esta ventana y abrir la siguiente ventana
					VentanaLOGGINN.this.dispose();
					VentanaPrincipal p = new VentanaPrincipal(); //TODO no debe ser new, tiene que ser la creada
				}
			}
		});
		
		registerButton.addActionListener(e -> {
			String nombreUsuario = usuarioRegisterTextField.getText();
			String contraseña = String.valueOf(contraseñaRegisterTextField.getPassword());
			
			if( BaseDatos.existeCliente(nombreUsuario) ) {
				JOptionPane.showMessageDialog(null, "Usuario Existente", "Aviso",JOptionPane.WARNING_MESSAGE);
			} else {
				 if ( !nombreUsuario.isEmpty() && !contraseña.isEmpty() ) {
					 BaseDatos.insertarCliente(new Cliente(0, nombreUsuario, null, null, contraseña, false, 0, null));
					JOptionPane.showMessageDialog(null, "Se ha registrado correctamente"); 
					//Seguimiento de cookie
					cookieUsuario = usuarioRegisterTextField.getText();
					//Cerrar esta ventana y abrir la siguiente ventana
					VentanaLOGGINN.this.dispose();
					VentanaPrincipal p = new VentanaPrincipal(); //TODO no debe ser new, tiene que ser la creada
				 }
			}
			
			if( nombreUsuario.isEmpty() || contraseña.isEmpty() ){
				registerButton.setEnabled(false); //Bloquea el botón
				JOptionPane.showMessageDialog(null, "Falta de datos al registrarse", "Aviso",JOptionPane.WARNING_MESSAGE);
				registerButton.setEnabled(true);
			}
			
		});
		
		
	}
	
	/**  Inicializa el panel login
	*/
	public void initLogin() {
		logIn.setBackground(Color.WHITE);
		
		credencialesLogin.setLayout(new GridLayout(5, 1));
		credencialesLogin.setBackground(Color.WHITE);
		
		credencialesLogin.add(usuarioLoginText);
	 	credencialesLogin.add(usuarioLoginTextField);
	 	credencialesLogin.add(new JLabel()); //Espacio en blanco
	 	credencialesLogin.add(contrasenaLoginText);
	 	credencialesLogin.add(toolbarLoginContraseña);
		
		ajustesDeBoton(facebookButton);
		ajustesDeBoton(googleButton);
		ajustesDeBoton(linkedinButton);
		ajustesDeBoton(loginButton);
		passwordLoginButton.setBorder(null);
		
		toolbarRedesSociales.setBorder(null);
		toolbarRedesSociales.setBackground(Color.WHITE);
		toolbarLoginContraseña.setBorder(null);
		
		añadirSeparador(toolbarRedesSociales, 50, 50);
		toolbarRedesSociales.add(facebookButton);
		añadirSeparador(toolbarRedesSociales, 55, 50);
		toolbarRedesSociales.add(googleButton);
		añadirSeparador(toolbarRedesSociales, 55, 50);
		toolbarRedesSociales.add(linkedinButton);
	 	
	 	toolbarLoginContraseña.add(contraseñaLoginTextField);
	 	toolbarLoginContraseña.add(passwordLoginButton);
	 	
	 	logIn.add(logInText);
		logIn.add(toolbarRedesSociales);
		logIn.add(logInText1);
		logIn.add(credencialesLogin);
		logIn.add(loginButton);
	}
	
	/**  Inicializa el panel register
	*/
	public void initRegister() {
		register.setBackground(new Color(133, 196, 65));
		
		credencialesRegister.setLayout(new GridLayout(5, 1));
		credencialesRegister.setBackground(new Color(133, 196, 65));
		
		passwordRegisterButton.setBorder(null);
	 	toolbarRegisterContraseña.setBorder(null);
	 	
	 	ajustesDeBoton(registerButton);
		
		credencialesRegister.add(usuarioRegisterText);
		credencialesRegister.add(usuarioRegisterTextField);
		credencialesRegister.add(new JLabel()); //Espacio en blanco
		credencialesRegister.add(contrasenaRegisterText);
		credencialesRegister.add(toolbarRegisterContraseña);
		
		toolbarRegisterContraseña.add(contraseñaRegisterTextField);
		toolbarRegisterContraseña.add(passwordRegisterButton);
		
		register.add(registerText);
		register.add(credencialesRegister);
		register.add(registerButton);
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

	public static String getCookieUsuario() {
		return cookieUsuario;
	}

	public static void setCookieUsuario(String cookieUsuario) {
		VentanaLOGGINN.cookieUsuario = cookieUsuario;
	}
	
	
}
