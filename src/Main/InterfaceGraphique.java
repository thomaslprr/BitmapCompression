package Main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import java.awt.Canvas;
import javax.swing.border.LineBorder;

public class InterfaceGraphique {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceGraphique window = new InterfaceGraphique();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfaceGraphique() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		
		
		JLabel lblTitreApplication = new JLabel("BITMAP COMPRESSION");
		lblTitreApplication.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitreApplication.setBounds(6, 6, 438, 28);
		lblTitreApplication.setForeground(new Color(255, 255, 255));
		lblTitreApplication.setFont(new Font("Krungthep", Font.PLAIN, 21));
		frame.getContentPane().add(lblTitreApplication);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setBounds(6, 68, 147, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Compresser Phi");
		btnNewButton_1.setBounds(6, 150, 147, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Compresser Delta");
		btnNewButton_1_1.setBounds(6, 109, 147, 29);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Exporter PNG");
		btnNewButton_1_2.setBounds(6, 187, 147, 29);
		frame.getContentPane().add(btnNewButton_1_2);
		
		JButton btnNewButton_1_2_1 = new JButton("Exporter texte");
		btnNewButton_1_2_1.setBounds(6, 225, 147, 29);
		frame.getContentPane().add(btnNewButton_1_2_1);
		
		JLabel lblNewLabel = new JLabel("image/1024-cubes.png");
		lblNewLabel.setForeground(new Color(0, 255, 0));
		lblNewLabel.setBounds(229, 217, 158, 17);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBorder(null);
		panel.setBounds(245, 57, 114, 148);
		frame.getContentPane().add(panel);
		try {
			BufferedImage myPicture;
			myPicture = ImageIO.read(new File("images/1024-cube.png"));
			
			JLabel picLabel = new JLabel(new ImageIcon(resize(myPicture,114,137)));
			panel.add(picLabel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  
}
