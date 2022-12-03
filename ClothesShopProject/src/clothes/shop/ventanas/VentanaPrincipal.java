package clothes.shop.ventanas;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clothes.shop.clases.BaseDatos;
import clothes.shop.clases.Ropa;

public class VentanaPrincipal extends JFrame {
	
//	private DefaultTableModel mRopa = new DefaultTableModel(
//			new Object[] {"Id", "Foto", "Nombre", "Talla", "Precio"}, 0);
//	private JTable tRopa = new JTable(mRopa);
	
	private DefaultListModel<Ropa> mlRopa = new DefaultListModel<>();
	private JList<Ropa> lRopa = new JList<>(mlRopa);
	
	
	
	public VentanaPrincipal () {
		
		BaseDatos.abrirConexion("prueba.bd", false);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 800);
		setTitle("Ventana de compra de productos");
		setVisible(true);
		// Cambiar el icono
		
		// Aqui va como se van a organizar todos los elementos por la mentana
		JPanel pPrincipal = new JPanel( new BorderLayout() ); // Panel central (tabla)
		pPrincipal.add( new JLabel( "Ropa:" ), BorderLayout.NORTH );
//		pPrincipal.add( new JScrollPane(tRopa), BorderLayout.CENTER );
		
		getContentPane().add( pPrincipal, BorderLayout.CENTER );
		
		// Añadir la ropa a la tabla
		List<Ropa> listaRopa = BaseDatos.getRopas();
		System.out.println(listaRopa);
		for (Ropa r : listaRopa) {
//			mRopa.addRow( new Object[] { r.getId(), r.getFotoUrl(), r.getNombre(), r.getTalla(), r.getPrecio()} );
			mlRopa.addElement(r);
		}
		lRopa.repaint();
		
		// Eventos
		
		
		
		
	}

}
