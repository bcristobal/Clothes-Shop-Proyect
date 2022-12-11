package clothes.shop.ventanas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import clothes.shop.clases.BaseDatos;

public class VentanaLogin extends JFrame{
    private static final long serialVersionUID = 1L;
    
public VentanaLogin () {
   	 
   	 // VISUAL
   	 JPanel Principal = new JPanel(new BorderLayout());
//   	 Principal.setLayout(new BoxLayout(Principal, BoxLayout.Y_AXIS));
   	 
   	 JPanel Credenciales = new JPanel();
   	 	    Credenciales.setLayout(new BoxLayout(Credenciales, BoxLayout.Y_AXIS));
   	 	 	Credenciales.add( new JLabel("Usuario") );
   	 	 	JTextField Usuario = new JTextField();
   	 	 	Credenciales.add(Usuario);
   	 	 	Credenciales.add( new JLabel("Contraseña") );
   	 	 	JPasswordField Contrasenya = new JPasswordField();
   	 	 	Credenciales.add(Contrasenya);
   	 
   	 JPanel Botones = new JPanel();
   	 		Botones.setLayout(new BoxLayout(Botones, BoxLayout.X_AXIS));
   	 	 	JButton iBoton = new JButton("Iniciar Sesión");
   	 	 	Botones.add(iBoton);
   	 	 	JButton rBoton = new JButton("Registrarse");
   	 	 	Botones.add(rBoton);
   	 
   	 Principal.add(Credenciales, BorderLayout.NORTH);
   	 Principal.add(Botones, BorderLayout.CENTER);
   	 
   	 JPanel FotoPerfil = new JPanel();
   		 JLabel imagen = new JLabel();
   		 imagen.setIcon(new ImageIcon("foto/fotoPerfilPredeterminada.jpg"));
   		 imagen.setPreferredSize(new Dimension( 200, 200 ));
   		 FotoPerfil.add( imagen );
   	 JPanel Tipo = new JPanel();
   		 JComboBox<String> TipoPersona = new JComboBox<>();
   		 TipoPersona.addItem("Cliente");
   		 TipoPersona.addItem("Trabajador");
   		 Tipo.add(TipoPersona);
   		 
   	 Botones.setBorder(BorderFactory.createEmptyBorder(0, 75, 0, 10));
   	 Principal.setBorder(BorderFactory.createEmptyBorder(20,50,50,50));
   	 FotoPerfil.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
   	 Tipo.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

   	 Container contentPane = getContentPane();
   		 contentPane.add(FotoPerfil, BorderLayout.NORTH);
   		 contentPane.add(Principal, BorderLayout.CENTER);
   		 contentPane.add(Tipo, BorderLayout.SOUTH);
   	 
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
   			 
   			 if (TipoPersona.getSelectedIndex() == 0) {
   				 String CredencialUsuario = Usuario.getText();
   				 System.out.println(CredencialUsuario);
   				 char[] CredencialContrasenya = Contrasenya.getPassword();
   				 System.out.println(CredencialContrasenya);
   				 Boolean CredencialesClienteCorrectas = false;
   				 
   				 
   				 for (int i = 0; i < BaseDatos.getTrabajadores().size(); i++) {
   					 Boolean InicioCorrecto = false;
   					 Boolean ContraCorrecta = false;
   					 if ((BaseDatos.getClientes().get(i).getNombre() + BaseDatos.getClientes().get(i).getApellido() + "@opendeusto.es").equals(CredencialUsuario)) {
   						 InicioCorrecto = true;
   					 }
   					 if ((InicioCorrecto == true) && (BaseDatos.getClientes().get(i).getContraseña().equals(CredencialContrasenya))){
   						 ContraCorrecta = true;
   					 }
   					 
   					 if (InicioCorrecto && ContraCorrecta) {
   						 CredencialesClienteCorrectas = true;
   						 //TODO: Mirar los permisos del cliente
   					 }
   					 
   				 }
   			 
   			 
   			 } else if (TipoPersona.getSelectedIndex() == 1) {
   				 String CredencialUsuario = Usuario.getText();
   				 System.out.println(CredencialUsuario);
   				 char[] CredencialContrasenya = Contrasenya.getPassword();
   				 System.out.println(CredencialContrasenya);
   				 Boolean CredencialesTrabajadorCorrectas = false;
   				 
   				 
   				 for (int i = 0; i < BaseDatos.getTrabajadores().size(); i++) {
   					 Boolean InicioCorrecto = false;
   					 Boolean ContraCorrecta = false;
   						 if ((BaseDatos.getTrabajadores().get(i).getNombre() + BaseDatos.getTrabajadores().get(i).getApellido() + "@opendeusto.es").equals(CredencialUsuario)) {
   							 InicioCorrecto = true;
   						 }
   						 if ((InicioCorrecto == true) && (BaseDatos.getTrabajadores().get(i).getContraseña().equals(CredencialContrasenya.toString()))){
   							 ContraCorrecta = true;
   						 }
   						 
   						 if (InicioCorrecto && ContraCorrecta) {
   							 CredencialesTrabajadorCorrectas = true;
   							 //TODO: Mirar los permisos del trabajador
   						 }
   				 }
   					 
   			 }
   		 }
   	 });
   	 
   	 rBoton.addActionListener(new ActionListener() {
   		 @Override
   		 public void actionPerformed(ActionEvent e) {
   			 
   			 
   		 }
   	 });
   	 
   	 
    }

}
