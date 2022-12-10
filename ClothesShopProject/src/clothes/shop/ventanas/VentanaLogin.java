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
   	 JPanel Principal = new JPanel();
   		 Principal.setLayout(new GridLayout(6,2));
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
   		 JButton iBoton = new JButton("Iniciar Sesión");
   		 Principal.add(iBoton);
   		 JButton rBoton = new JButton("Registrarse");
   		 Principal.add(rBoton);
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
   		 
   	 
   	 Principal.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));
   	 FotoPerfil.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
   	 Tipo.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

   	 Container contentPane = getContentPane();
   		 contentPane.add(FotoPerfil, BorderLayout.CENTER);
   		 contentPane.add(Principal, BorderLayout.SOUTH);
   		 contentPane.add(Tipo, BorderLayout.NORTH);
   	 
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
   					 //TODO: Contraseña en cliente BD
//   					 if ((InicioCorrecto == true) && (BaseDatos.getClientes().get(i).getContraseña().equals(CredencialContrasenya))){
//   						 ContraCorrecta = true;
//   					 }
   					 
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
