package clothes.shop.ventanas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import clothes.shop.clases.BaseDatos;
import clothes.shop.clases.Ropa;

public class VentanaPrincipal extends JFrame {
	
	/** 
	 *  
	 */ 
	private static final long serialVersionUID = 1L; 
	
	private JPanel pRopaNorte = new JPanel( new GridLayout(1, 2) );
	
	private String[] filtro = {"Id creciente", "Orden alfabetico", "Precio descendente", "Precio creciente"};
	private JComboBox<String> comboRopa = new JComboBox<>(filtro);
	
	private DefaultListModel<Ropa> mRopaId = new DefaultListModel<>(); 
	private DefaultListModel<Ropa> mRopaAlabeticamente = new DefaultListModel<>();
	private DefaultListModel<Ropa> mRopaPrecioDescendente = new DefaultListModel<>();
	private DefaultListModel<Ropa> mRopaPrecioAscendente = new DefaultListModel<>();
	private JList<Ropa> lRopa = new JList<>(mRopaId); 
	private JScrollPane scrollRopa = new JScrollPane(lRopa); 
	private JPanel pRopa = new JPanel( new BorderLayout() ); 
	 
	private DefaultListModel<Ropa> mCarrito = new DefaultListModel<>(); 
	private JList<Ropa> lCarrito = new JList<>(mCarrito); 
	private List<Ropa> carrito = new ArrayList<>();
	private JScrollPane scrollCarrito = new JScrollPane(lCarrito); 
	private JPanel pCarrito = new JPanel( new BorderLayout() ); 
	 
	private JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pRopa, pCarrito);
	
	private JButton bAñadir = new JButton("Añadir"); 
	private JButton bBorrar = new JButton("Borrar");  
	
	private JLabel labelFoto = new JLabel();
	private JButton bTerminarCompra = new JButton("Terminar compra");
	private JPanel pCentroCompra = new JPanel( new BorderLayout() );
	
	private JPanel pCompra = new JPanel( new BorderLayout() );
	
	private DefaultTableModel mStock = new DefaultTableModel(new Object[] {"ID", "NOMBRE", "TIPO", "PRECIO", "TALLA"}, 0);
	private JTable tStock = new JTable(mStock);
	private JScrollPane scrollStock = new JScrollPane(tStock);
	
	private JButton bPedirStock = new JButton("Pedir Stock");
    private SpinnerModel mSpinner =
            new SpinnerNumberModel(5, //initial value
               1, //minimum value
               100, //maximum value
               5); //step
    private JSpinner spinnerNumStock = new JSpinner(mSpinner); 

    private JPanel pStockSur = new JPanel(new FlowLayout());
	
	private JPanel pStock = new JPanel( new BorderLayout() );
	
	private JTabbedPane pestanas = new JTabbedPane();
	
	
	public VentanaPrincipal () {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setLocation( 
				(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2),  
				(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2) 
		);
		setTitle("Ventana de compra de productos");
		setVisible(true);
		// Cambiar el icono
		
		initPanelStock();
		
		// Aqui va como se van a organizar todos los elementos por la mentan

		pRopaNorte.add(new JLabel("Ropa:"));
		pRopaNorte.add(comboRopa);
		pRopa.add(pRopaNorte, BorderLayout.NORTH);
		pRopa.add(scrollRopa, BorderLayout.CENTER); 
		pRopa.add(bAñadir, BorderLayout.SOUTH); 
		pCarrito.add(new JLabel("Carrito:"), BorderLayout.NORTH); 
		pCarrito.add(scrollCarrito, BorderLayout.CENTER); 
		pCarrito.add(bBorrar, BorderLayout.SOUTH); 
		pCompra.add(sp, BorderLayout.WEST);	
		pCentroCompra.add(bTerminarCompra, BorderLayout.SOUTH);
		pCentroCompra.add(labelFoto, BorderLayout.CENTER);
		pCompra.add(pCentroCompra, BorderLayout.CENTER);
		
		pStock.add(scrollStock, BorderLayout.CENTER);
		
		pStock.add(scrollStock, BorderLayout.CENTER);
        pStockSur.add(bPedirStock);
        pStockSur.add(spinnerNumStock);
        pStock.add(pStockSur, BorderLayout.SOUTH);
		
		pestanas.add("Compra", pCompra);
		pestanas.add("Stock", pStock);
		getContentPane().add(pestanas);
		
		// Formatear componentes
		//sp.setPreferredSize(new Dimension(400, 400));
		labelFoto.setPreferredSize(new Dimension(400, 400));
		
		
		// Añadir la ropa a los modelos de lista
		cargarModelosCompra();
		
		List<Ropa> listaRopa = BaseDatos.getRopas();
		while (mStock.getRowCount() > 0) {
			mStock.removeRow( 0 );
		}
		for (Ropa r : listaRopa) {
			mStock.addRow(new Object[] {r.getId(), r.getNombre(), r.getTipo(), r.getPrecio(), r.getTalla()});
			scrollStock.repaint();
		}
		
		//Selecciona la opción por defencto del comboBox
		lRopa.setModel(mRopaId);
		lRopa.repaint();
		
		// Eventos
		bAñadir.setToolTipText("Añade la prenda seleccionada"); 
		bAñadir.addActionListener(new ActionListener() { 
			@Override 
			public void actionPerformed(ActionEvent e) { 
				Ropa seleccionado = lRopa.getSelectedValue(); 
				if (seleccionado != null) { 
					mCarrito.addElement(seleccionado); 
					carrito.add(seleccionado);
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
					carrito.remove(seleccionado);
				} 
			} 
		}); 
		
		// FALTA BUSCARLE UNA UTILIDAD A LAS COMPRAS
		bTerminarCompra.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		comboRopa.addItemListener(new ItemListener() {	
			@Override
			public void itemStateChanged(ItemEvent e) {
				String seleccionado = (String) comboRopa.getSelectedItem();
				if (seleccionado.equals(filtro[0])) {
					lRopa.setModel(mRopaId);
				} else if (seleccionado.equals(filtro[1])) {
					lRopa.setModel(mRopaAlabeticamente);
				} else if (seleccionado.equals(filtro[2])) {
					lRopa.setModel(mRopaPrecioDescendente);
				} else {
					lRopa.setModel(mRopaPrecioAscendente);
				}
			}
		});
		
		lRopa.addListSelectionListener(new ListSelectionListener() {	
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Ropa seleccionado = lRopa.getSelectedValue();
				if (seleccionado != null) {
					refrescarFoto(seleccionado);
				} else {
					labelFoto.setIcon(null);
					labelFoto.repaint();
				}
			}
		});
		
		
		
		
	}
	
	private void refrescarFoto (Ropa ropa) {
		if (ropa.getFotoUrl() != null) {
			ImageIcon imagen = new ImageIcon(ropa.getFotoUrl());
			labelFoto.setIcon(imagen);
			labelFoto.repaint();
		} else {
			labelFoto.setIcon(null);
			labelFoto.repaint();
		}
	}
	
	// FALTA LA CABECERA DE LOS PRODUCTOS EN EL ALMACEN
	private void initPanelStock () {
		Vector<String> cabeceraTabla = new Vector<String>(Arrays.asList("ID", "NOMBRE", "TIPO", "PRECIO", "TALLA"));
		this.mStock = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraTabla);
		this.tStock = new JTable(this.mStock);
	}
	
	private void cargarModelosCompra () {
		List<Ropa> listaRopa = BaseDatos.getRopas();
		
		Ropa.idCreciente(listaRopa) ;
		System.out.println(listaRopa);
		for (Ropa r : listaRopa) {
			mRopaId.addElement(r);
		}
		Ropa.alfabeticamente(listaRopa) ;
		System.out.println(listaRopa);
		for (Ropa r : listaRopa) {
			mRopaAlabeticamente.addElement(r);
		}
		Ropa.precioCreciente(listaRopa) ;
		System.out.println(listaRopa);
		for (Ropa r : listaRopa) {
			mRopaPrecioAscendente.addElement(r);
		}
		Ropa.precioDescendiente(listaRopa) ;
		System.out.println(listaRopa);
		for (Ropa r : listaRopa) {
			mRopaPrecioDescendente.addElement(r);
		}
	}
	
	private void cargarModeloStock () {
		List<Ropa> listaRopa = BaseDatos.getRopas();
		// mStock.setRowCount(0);
		for (Ropa r : listaRopa) {
			mStock.addRow(new Object[] {r.getId(), r.getNombre(), r.getTipo(), r.getPrecio(), r.getTalla()});
			scrollStock.repaint();
		}
	}
	
	
	
	
	
	
	
	
	

}
