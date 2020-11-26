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

import ImagePng.ImagePNG;
import Quadtree.Quadtree;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JSplitPane;
import java.awt.Canvas;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		
		
		JLabel lblTitreApplication = new JLabel("BITMAP COMPRESSION");
		lblTitreApplication.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitreApplication.setBounds(6, 6, 438, 28);
		lblTitreApplication.setForeground(new Color(255, 255, 255));
		lblTitreApplication.setFont(new Font("Krungthep", Font.PLAIN, 21));
		frame.getContentPane().add(lblTitreApplication);
		
		JButton btnNewButton = new JButton("Importer image");
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
		
		final JLabel lblSourceFichier = new JLabel("");
		lblSourceFichier.setHorizontalAlignment(SwingConstants.CENTER);
		lblSourceFichier.setForeground(Color.RED);
		lblSourceFichier.setBounds(193, 225, 198, 17);
		frame.getContentPane().add(lblSourceFichier);
		
		final JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBorder(null);
		panel.setBounds(220, 64, 147, 148);
		frame.getContentPane().add(panel);
		
		
		

		
		JLabel lblQualit = new JLabel("Qualité : 90%");
		lblQualit.setForeground(Color.WHITE);
		lblQualit.setBounds(379, 89, 96, 16);
		frame.getContentPane().add(lblQualit);
		
		JLabel lblPoid = new JLabel("Poids : 37%");
		lblPoid.setForeground(Color.WHITE);
		lblPoid.setBounds(379, 122, 96, 16);
		frame.getContentPane().add(lblPoid);
		
		final JLabel lblTailleFichier = new JLabel("");
		lblTailleFichier.setForeground(Color.WHITE);
		lblTailleFichier.setHorizontalAlignment(SwingConstants.CENTER);
		lblTailleFichier.setBounds(220, 242, 147, 16);
		frame.getContentPane().add(lblTailleFichier);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Choisir une image");
				fc.setAcceptAllFileFilterUsed(false);
				fc.setFileFilter(new FileNameExtensionFilter("PNG", "png"));
				fc.showOpenDialog(new JPanel());
				try {
					System.out.println(fc.getSelectedFile().getAbsolutePath());
					if(panel.getComponentCount()>0) {
						panel.removeAll();
					}
					BufferedImage myPicture;
					lblSourceFichier.setForeground(Color.GREEN);
					lblSourceFichier.setText("Image chargée");
					File fichier = new File(fc.getSelectedFile().getAbsolutePath());
					lblTailleFichier.setText(""+fichier.length()/ 1024 + "  kb");
					myPicture = ImageIO.read(fichier);
					JLabel picLabel = new JLabel(new ImageIcon(resize(myPicture,114,137)));
					panel.add(picLabel);
					panel.revalidate();
						
					
				} catch (Exception e1) {
					lblSourceFichier.setText("Impossible de charger le fichier");
					lblSourceFichier.setForeground(Color.RED);
					e1.printStackTrace();
				}
			}
		});
		
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
