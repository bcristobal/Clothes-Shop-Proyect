package clothes.shop.ventanas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaCarga extends JFrame {

	private static final long serialVersionUID = 1L;

	public VentanaCarga() {
		this.setTitle("Cloth shop");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setUndecorated(true); // hide title bar
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));		
		
		Container container = this.getContentPane();
		container.setBackground(Color.white);

		JLabel label = new JLabel();
		
		label.setIcon(resizeIconImage());
		
		container.add(label);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public ImageIcon resizeIconImage() {
		ImageIcon imageIcon = new ImageIcon("foto/logo.png");
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(400, 400,  java.awt.Image.SCALE_SMOOTH); // escalar la imagen
		
		imageIcon = new ImageIcon(newimg);
		
		return(imageIcon);
	}
}
