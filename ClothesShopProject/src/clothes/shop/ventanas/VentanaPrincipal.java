package clothes.shop.ventanas;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
	
	private JButton bAñadir = new JButton("Añadir"); 
	 private JButton bBorrar = new JButton("Borrar"); 
	
	public VentanaPrincipal () {
		
		BaseDatos.abrirConexion("prueba.bd", false);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 800);
		setLocation( 
				(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),  
				(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2) 
		);
		setTitle("Ventana de compra de productos");
		setVisible(true);
		// Cambiar el icono
		
		// Aqui va como se van a organizar todos los elementos por la mentana
		pRopa.add(new JLabel("Ropa:"), BorderLayout.NORTH); 
		pRopa.add(scrollRopa, BorderLayout.CENTER); 
		pRopa.add(bAñadir, BorderLayout.SOUTH); 
		pCarrito.add(new JLabel("Carrito:"), BorderLayout.NORTH); 
		pCarrito.add(scrollCarrito, BorderLayout.CENTER); 
		pCarrito.add(bBorrar, BorderLayout.SOUTH); 
		getContentPane().add(sp, BorderLayout.WEST);		
		
		
		// Añadir la ropa a la tabla
		List<Ropa> listaRopa = BaseDatos.getRopas();
		System.out.println(listaRopa);
		for (Ropa r : listaRopa) {
//			mRopa.addRow( new Object[] { r.getId(), r.getFotoUrl(), r.getNombre(), r.getTalla(), r.getPrecio()} );
			mRopa.addElement(r);
		}
		lRopa.repaint();
		
		// Eventos
		bAñadir.setToolTipText("Añade la prenda seleccionada"); 
		bAñadir.addActionListener(new ActionListener() { 
			@Override 
			public void actionPerformed(ActionEvent e) { 
				Ropa seleccionado = lRopa.getSelectedValue(); 
				if (seleccionado != null) { 
					mCarrito.addElement(seleccionado); 
				} 
			} 
		}); 
		 
		bBorrar.setToolTipText("Borra la prenda seleccionada"); 
		bBorrar.addActionListener(new ActionListener() { 
			@Override 
			public void actionPerformed(ActionEvent e) { 
				Ropa seleccionado = lCarrito.getSelectedValue(); 
				if (seleccionado != null) { 
					mCarrito.removeElement(seleccionado); 
				} 
			} 
		}); 
		
		
		
	}

}
