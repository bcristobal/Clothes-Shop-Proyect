package clothes.shop.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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

import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import clothes.shop.clases.BaseDatos;
import clothes.shop.clases.MySpinnerEditor;
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
	
	private DefaultTableModel mStock = new DefaultTableModel(new Object[] {"ID", "FOTO", "NOMBRE", "TIPO", "PRECIO", "TALLA", "ALMACEN", "PEDIDO"}, 0) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int rowIndex, int columnIndex){
			return columnIndex == 7; //Or whatever column index you want to be editable
			}
	};
	private JTable tStock = new JTable(mStock);
	private JScrollPane scrollStock = new JScrollPane(tStock);
	
	private JButton bPedirStock = new JButton("Pedir Stock");
	
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
        pStockSur.add(bPedirStock);
        pStock.add(pStockSur, BorderLayout.SOUTH);
		
		pestanas.add("Compra", pCompra);
		pestanas.add("Stock", pStock);
		getContentPane().add(pestanas);
		
		// Formatear componentes
		//sp.setPreferredSize(new Dimension(400, 400));
		labelFoto.setPreferredSize(new Dimension(400, 400));
		
		
		// Añadir la ropa a los modelos de lista y selecciona la opción por defencto del comboBox
		cargarModelosCompra();
		lRopa.setModel(mRopaId);
		lRopa.repaint();
		
		// Añadir la ropa al modelo de tabla
		tStock.setDefaultEditor(Object.class, null);

		cargarModeloStock();
		
		TableCellRenderer renderTabla = (table, value, isSelected, hasFocus, row, column) -> {
			JComponent resultado;
			
			// Añade la la imagen a la columna de foto
			if (column == 1) {
				resultado = new JLabel();
				ImageIcon imagen = new ImageIcon((String) value);
				((JLabel) resultado).setIcon(imagen);
				resultado.repaint();
				((JLabel) resultado).setHorizontalAlignment(JLabel.CENTER);
				
			} else if (column == 6 && (Integer) value < 10) {
				resultado = new JLabel(value.toString());
				resultado.setBackground(Color.RED);
				((JLabel) resultado).setHorizontalAlignment(JLabel.CENTER);
			      
			} /*else if (column == 7) {
				SpinnerModel modeloSpinner =
			            new SpinnerNumberModel(
			            	0, //initial value
			            	0, //minimum value
			            	100, //maximum value
			            	5); //step
			      resultado = new JSpinner(modeloSpinner);
			}*/ else {
				resultado = new JLabel(value.toString());
				((JLabel) resultado).setHorizontalAlignment(JLabel.CENTER);
			}
			
			return resultado;
		};
		
		//get the column model from JTable
        TableColumnModel model = tStock.getColumnModel();
        //get the 8nd column
        TableColumn col = model.getColumn(7);
      //set the editor
        col.setCellEditor(new MySpinnerEditor());
		
      //Se cambia la anchura de las columnas
      	this.tStock.getColumnModel().getColumn(0).setPreferredWidth(25);
      	this.tStock.getColumnModel().getColumn(1).setPreferredWidth(50);
      	this.tStock.getColumnModel().getColumn(2).setPreferredWidth(200);
      	this.tStock.getColumnModel().getColumn(3).setPreferredWidth(30);
      	this.tStock.getColumnModel().getColumn(4).setPreferredWidth(30);
      	this.tStock.getColumnModel().getColumn(5).setPreferredWidth(30);
      	this.tStock.getColumnModel().getColumn(6).setPreferredWidth(30);
      	this.tStock.getColumnModel().getColumn(7).setPreferredWidth(30);
      	
		this.tStock.setRowHeight(60);
		this.tStock.setDefaultRenderer(Object.class, renderTabla);
		
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
					refrescarFoto(seleccionado.getFotoUrl(), labelFoto);
				} else {
					labelFoto.setIcon(null);
					labelFoto.repaint();
				}
			}
		});
		
		// FALTA
		bPedirStock.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// primero añadira los pedidos al almacen y luego volvera a cargar la tabla para que se vea el almacen actualizado
			}
		});
		
	}
	
	private void refrescarFoto (String fotoUrl, JLabel label) {
		if (fotoUrl != null) {
			ImageIcon imagen = new ImageIcon(fotoUrl);
			label.setIcon(imagen);
			label.repaint();
		} else {
			label.setIcon(null);
			label.repaint();
		}
	}
	
	private void cargarModelosCompra () {
		List<Ropa> listaRopa = BaseDatos.getRopas();
		
		mRopaId.clear();
		Ropa.idCreciente(listaRopa);
		// System.out.println(listaRopa);
		for (Ropa r : listaRopa) {
			mRopaId.addElement(r);
		}
		mRopaAlabeticamente.clear();
		Ropa.alfabeticamente(listaRopa) ;
		// System.out.println(listaRopa);
		for (Ropa r : listaRopa) {
			mRopaAlabeticamente.addElement(r);
		}
		mRopaPrecioAscendente.clear();
		Ropa.precioCreciente(listaRopa) ;
		// System.out.println(listaRopa);
		for (Ropa r : listaRopa) {
			mRopaPrecioAscendente.addElement(r);
		}
		mRopaPrecioDescendente.clear();
		Ropa.precioDescendiente(listaRopa) ;
		// System.out.println(listaRopa);
		for (Ropa r : listaRopa) {
			mRopaPrecioDescendente.addElement(r);
		}
	}
	
	private void cargarModeloStock () {
		List<Ropa> listaRopa = BaseDatos.getRopas();
		while (mStock.getRowCount() > 0) {
			mStock.removeRow( 0 );
		}
		for (Ropa r : listaRopa) {
			mStock.addRow(new Object[] {r.getId(), r.getFotoUrl(), r.getNombre(), r.getTipo(), r.getPrecio() / 100, r.getTalla(), 0, 0});
			scrollStock.repaint();
		}
	}
	
	
	
	
	
	
	
	
	

}
