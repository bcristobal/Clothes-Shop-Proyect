package clothes.shop.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import clothes.shop.clases.BaseDatos;
import clothes.shop.clases.Cliente;
import clothes.shop.clases.MySpinnerEditor;
import clothes.shop.clases.Persona;
import clothes.shop.clases.Puesto;
import clothes.shop.clases.Ropa;
import clothes.shop.clases.Talla;
import clothes.shop.clases.Trabajador;

public class VentanaPrincipal extends JFrame {
	
	/** 
	 *  
	 */ 
	private static final long serialVersionUID = 1L;
	//TODO: Dependiendo de que usuario sea, se modificará la ventana. Para la ventana de perfil, que se accede desde esta ventana, pasará lo mismo.
	String cookieUsuario = VentanaLOGGINN.getCookieUsuario();
	
	private JPanel pRopaNorte = new JPanel( new FlowLayout() );
	
	private String[] filtro = {"Id creciente", "Orden alfabetico", "Precio descendente", "Precio creciente"};
	private JComboBox<String> comboRopa = new JComboBox<>(filtro);
	
	private JComboBox<Talla> comboTallas = new JComboBox<>(Talla.values());
	
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
	private JLabel infoCarrito = new JLabel("Carrito: " + carrito.size() + " productos");
	 
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
		setTitle("Ventana principal");
		setIconImage(new ImageIcon("foto/logo.png").getImage());
		setVisible(true);
		
		// Busca el trabajador en la BD
		Trabajador t = new Trabajador();
    	if (BaseDatos.existeTrabajador(cookieUsuario) == true) {
			ArrayList<Trabajador> trabajadores = BaseDatos.getTrabajadores();
			for (Trabajador trabajador : trabajadores) {
				if (trabajador.getNombre().equals(cookieUsuario)) {
					t.setNombre(trabajador.getNombre());
					t.setApellido(trabajador.getApellido());
					t.setId(trabajador.getId());
					t.setPuesto(trabajador.getPuesto());
				}
			}
		}
		
		// Aqui va como se van a organizar todos los elementos por la mentan
		pRopaNorte.add(new JLabel("Ropa:"));
		pRopaNorte.add(comboRopa);
		pRopaNorte.add(comboTallas);
		pRopa.add(pRopaNorte, BorderLayout.NORTH);
		pRopa.add(scrollRopa, BorderLayout.CENTER); 
		pRopa.add(bAñadir, BorderLayout.SOUTH); 
		pCarrito.add(infoCarrito, BorderLayout.NORTH);
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
		if (BaseDatos.existeTrabajador(cookieUsuario) == true) {
			pestanas.add("Stock", pStock);
		}
		getContentPane().add(pestanas);
		
		// Formatear componentes
		//sp.setPreferredSize(new Dimension(400, 400));
		labelFoto.setPreferredSize(new Dimension(400, 400));
		
		
		// Añadir la ropa a los modelos de lista y selecciona la opción por defencto del comboBox
		
		cargarModelosCompra((Talla) comboTallas.getSelectedItem());
		lRopa.setModel(mRopaId);
		lRopa.repaint();
		
		// Añadir la ropa al modelo de tabla
		tStock.setDefaultEditor(Object.class, null);

		cargarModeloStock();
		
		TableCellRenderer renderTabla = (table, value, isSelected, hasFocus, row, column) -> {
			JComponent resultado;
			
			resultado = new JLabel();
			
			
			// FOTO -> Añade la imagen de la url de value
			if (column == 1) {
				refrescarFoto((String) value, (JLabel) resultado, 60, 60);
			}
			
			// ID, NOMBRE, TIPO, PRECIO, TALLA, ALMACEN y PEDIDO -> Añade el value
			if (column == 0 || column == 2 || column == 3 || column == 4 || column == 5 || column == 6 || column == 7) {
				resultado = new JLabel(value.toString());
			}
			
			// Pinta la fila de un color dependiendo del stock
			int almacen = (int) table.getModel().getValueAt(row, 6);
			if (almacen < 5) {
				resultado.setBackground(new Color(236, 112, 99));
				//resultado.setForeground(Color.WHITE);
			} else if (almacen < 10) {
				resultado.setBackground(new Color(245, 176, 65));
			} else if (almacen < 20) {
				resultado.setBackground(new Color(247, 220, 111));
			} else {
				resultado.setBackground(Color.WHITE);
			}
			
			((JLabel) resultado).setHorizontalAlignment(JLabel.CENTER);
			resultado.setOpaque(true);
			table.repaint();
			return resultado;
		};
		
		//get the column model from JTable
        TableColumnModel model = tStock.getColumnModel();
        //get the 8nd column
        TableColumn col = model.getColumn(7);
      //set the editor
        col.setCellEditor(new MySpinnerEditor());
		
      //Se cambia la anchura de las columnas
      	this.tStock.getColumnModel().getColumn(0).setPreferredWidth(20);
      	this.tStock.getColumnModel().getColumn(1).setPreferredWidth(50);
      	this.tStock.getColumnModel().getColumn(2).setPreferredWidth(220);
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
		Map<Integer, List<Ropa>> mapAux = new HashMap<>();
		
		bAñadir.setToolTipText("Añade la prenda seleccionada"); 
		bAñadir.addActionListener(new ActionListener() { 
			@Override 
			public void actionPerformed(ActionEvent e) { 
				Ropa seleccionado = lRopa.getSelectedValue(); 
				if (seleccionado != null) {
					mapAux.putIfAbsent(seleccionado.getId(), new ArrayList<>());
					if (mapAux.get(seleccionado.getId()).size() < seleccionado.getCantidad()) {
						mapAux.get(seleccionado.getId()).add(seleccionado);
						mCarrito.addElement(seleccionado); 
						carrito.add(seleccionado);
						infoCarrito.setText("Carrito: " + carrito.size() + " productos");
					} else {
						JOptionPane.showMessageDialog(null, "No queda más stock de " + seleccionado.getNombre() + "(" + seleccionado.getTalla().toString() +  ") en el almacen");
					}	
				}
			} 
		}); 
		 
		bBorrar.setToolTipText("Borra la prenda seleccionada"); 
		bBorrar.addActionListener(new ActionListener() { 
			@Override 
			public void actionPerformed(ActionEvent e) { 
				Ropa seleccionado = lCarrito.getSelectedValue(); 
				if (seleccionado != null) {
					mapAux.get(seleccionado.getId()).remove(0);
					mCarrito.removeElement(seleccionado); 
					carrito.remove(seleccionado);
					infoCarrito.setText("Carrito: " + carrito.size() + " productos");
				} 
			} 
		}); 
		
		// FALTA BUSCARLE UNA UTILIDAD A LAS COMPRAS
		bTerminarCompra.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!mCarrito.isEmpty()) {
					for (Integer id : mapAux.keySet()) {
						// SE AÑADE A LA BASE DE DATOS EL NUEVO STOCK
						if (mapAux.get(id).size() > 0) {
							BaseDatos.actualizarCantidadRopa(id, mapAux.get(id).get(0).getCantidad() - mapAux.get(id).size());
						}
					}
					recivoTXT(mapAux);
					mapAux.clear();
					carrito.clear();
					mCarrito.clear();
					// Carga el modelo de Stock
					cargarModeloStock();
					cargarModelosCompra((Talla) comboTallas.getSelectedItem());
					infoCarrito.setText("Carrito: " + carrito.size() + " productos");
				}
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
		
		comboTallas.addItemListener(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent e) {
				Talla seleccionado = (Talla) comboTallas.getSelectedItem();
				cargarModelosCompra(seleccionado);
			}
		});
		
		lRopa.addListSelectionListener(new ListSelectionListener() {	
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Ropa seleccionado = lRopa.getSelectedValue();
				if (seleccionado != null) {
					refrescarFoto(seleccionado.getFotoUrl(), labelFoto, labelFoto.getWidth(), labelFoto.getHeight());
				} else {
					labelFoto.setIcon(null);
					labelFoto.repaint();
				}
			} 
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				BaseDatos.cerrarConexion();
				
			}
		});
		
		// FALTA
		bPedirStock.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// primero añadira los pedidos al almacen y luego volvera a cargar la tabla para que se vea el almacen actualizado
				if (t.getPuesto().equals(Puesto.ENCARGADO) || t.getPuesto().equals(Puesto.JEFE)) {
					for (int i = 0; i < tStock.getRowCount(); i++) {
						int pedido = (int) tStock.getValueAt(i, 7);
						if (pedido != 0) {
							int cant = (int) tStock.getValueAt(i, 6);
							BaseDatos.actualizarCantidadRopa( (int)tStock.getValueAt(i, 0) , cant + pedido);
							tStock.setValueAt(cant + pedido, i, 6);
							tStock.setValueAt(0, i, 7);
						}
					}
					mapAux.clear();
					carrito.clear();
					mCarrito.clear();
					cargarModelosCompra((Talla) comboTallas.getSelectedItem());
				} else {
					JOptionPane.showMessageDialog(null, "No tienes el rango suficiente para pedir stock");
				}		
			}
		});
		
	}
	
	/**
	 * Carga una imagen utilizando su dirección y la muestra en un JLabel
	 * @param fotoUrl URL de la foto
	 * @param label lugar donde se carga la foto
	 * @param width ancho de la imagen
	 * @param height largo de la imagen
	 */
	private void refrescarFoto (String fotoUrl, JLabel label, int width, int height) {
		if (fotoUrl != null) {
			ImageIcon imagenIcon = new ImageIcon(fotoUrl);
			Image image = imagenIcon.getImage();
			Image nueva = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
			
			label.setIcon(new ImageIcon(nueva));
			label.repaint();
		} else {
			label.setIcon(null);
			label.repaint();
		}
	}
	
	private void cargarModelosCompra (Talla talla) {
		//List<Ropa> listaRopa = BaseDatos.getRopas();
		List<Ropa> listaRopa = Ropa.getRopaPorTalla(BaseDatos.getRopas(), talla);
		
		mRopaId.clear();
		Ropa.idCreciente(listaRopa);
		// System.out.println(listaRopa);
		for (Ropa r : listaRopa) {
			if (r.getCantidad() > 0) {
				mRopaId.addElement(r);
			}
		}
		mRopaAlabeticamente.clear();
		Ropa.alfabeticamente(listaRopa) ;
		// System.out.println(listaRopa);
		for (Ropa r : listaRopa) {
			if (r.getCantidad() > 0) {
				mRopaAlabeticamente.addElement(r);
			}
		}
		mRopaPrecioAscendente.clear();
		Ropa.precioCreciente(listaRopa) ;
		// System.out.println(listaRopa);
		for (Ropa r : listaRopa) {
			if (r.getCantidad() > 0) {
				mRopaPrecioAscendente.addElement(r);
			}
		}
		mRopaPrecioDescendente.clear();
		Ropa.precioDescendiente(listaRopa) ;
		// System.out.println(listaRopa);
		for (Ropa r : listaRopa) {
			if (r.getCantidad() > 0) {
				mRopaPrecioDescendente.addElement(r);
			}
		}
	}
	
	private void cargarModeloStock () {
		List<Ropa> listaRopa = BaseDatos.getRopas();
		while (mStock.getRowCount() > 0) {
			mStock.removeRow( 0 );
		}
		for (Ropa r : listaRopa) {
			mStock.addRow(new Object[] {
					r.getId(), 
					r.getFotoUrl(), 
					r.getNombre(), 
					r.getTipo(), 
					(r.getPrecio() / 100) + "," + (r.getPrecio() - (r.getPrecio() / 100) * 100) + "€",
					r.getTalla(), 
					r.getCantidad(), 
					0
					});
			scrollStock.repaint();
		}
	}
	
	private Map<String, List<Ropa>> getMapaRopaPorTallas() {
		Map<String, List<Ropa>> map = new HashMap<>();
		List<Ropa> list = BaseDatos.getRopas();
		
		for (Ropa r : list) {
			map.putIfAbsent(r.getNombre(), new ArrayList<>());
			map.get(r.getNombre()).add(r);
		}
		
		return map;
	}
	
	private List<Talla> getTallas (List<Ropa> ropas) {
		List<Talla> resultado = new ArrayList<>();
		for (Ropa r : ropas) {
			resultado.add(r.getTalla());
		}
		return resultado;
	}
	
	private void recivoTXT (Map<Integer, List<Ropa>> map) {
		try {
			 int total = 0;
	         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
			
			String home = System.getProperty("user.home");
            String downloads = home + "/Downloads/recivo(" + formatter.format(ZonedDateTime.now()) + ").txt";
            FileWriter file = new FileWriter(downloads);
            PrintWriter printer = new PrintWriter(file);          
            
			printer.println("TIENDA DEUSTO");
			printer.println("producto:");
			for (Integer id : map.keySet()) {
				printer.println("- " + map.get(id).get(0).getNombre() + " " + map.get(id).size() + " x " 
				+ (map.get(id).get(0).getPrecio() / 100) + "," + (map.get(id).get(0).getPrecio() - (map.get(id).get(0).getPrecio() / 100) * 100) + "€");
				total = total + map.get(id).size()*map.get(id).get(0).getPrecio();
			}
			printer.println("_____________________________________");
			printer.println("Total: " + (total / 100) + "," + (total - (total / 100) * 100) + "€"); // 
			
			printer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	

}
