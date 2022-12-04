package clothes.shop.ventanas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import clothes.shop.clases.BaseDatos;
import clothes.shop.clases.Ropa;

public class VentanaPrincipal extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DefaultListModel<Ropa> mRopa = new DefaultListModel<>();
	private JList<Ropa> lRopa = new JList<>(mRopa);
	private JScrollPane scrollRopa = new JScrollPane(lRopa);
	private JPanel pRopa = new JPanel( new BorderLayout() );
	
	private DefaultListModel<Ropa> mCarrito = new DefaultListModel<>();
	private JList<Ropa> lCarrito = new JList<>(mCarrito);
	private JScrollPane scrollCarrito = new JScrollPane(lCarrito);
	private JPanel pCarrito = new JPanel( new BorderLayout() );
	
	private JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pRopa, pCarrito);
	
	 
	
	
	
	public VentanaPrincipal () {
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 800);
		setLocation(
				(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2), 
				(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2)
		);
		setTitle("Ventana de compra de productos");
		setVisible(true);
		// Cambiar icono
		
		// Aqui va como se van a organizar todos los elementos por la mentana
		pRopa.add(new JLabel("Ropa:"), BorderLayout.NORTH);
		pRopa.add(scrollRopa, BorderLayout.CENTER);
		pCarrito.add(new JLabel("Carrito:"), BorderLayout.NORTH);
		pCarrito.add(scrollCarrito, BorderLayout.CENTER);
		getContentPane().add(sp, BorderLayout.WEST);
		
//		JSplitPane spOeste = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
//		
//		JPanel pRopa = new JPanel( new BorderLayout() ); 
//		pRopa.add(new JLabel("Ropa:"), BorderLayout.NORTH);
//		pRopa.add(scrollRopa, BorderLayout.CENTER);
//		spOeste.setTopComponent(pRopa);
//		
//		JPanel pCarrito = new JPanel( new BorderLayout() );
//		pCarrito.add(new JLabel("Carrito:"), BorderLayout.NORTH);
//		pCarrito.add(scrollCarrito, BorderLayout.CENTER);
//		spOeste.setBottomComponent(pCarrito);
//		
//		getContentPane().add(spOeste);
		
		
		// Añadir la ropa a la tabla
		List<Ropa> listaRopa = BaseDatos.getRopas();
		System.out.println(listaRopa);
		for (Ropa r : listaRopa) {
			mRopa.addElement(r);
		}
		lRopa.repaint();
		
		// Eventos
		
		
		
		
	}

}
