package Main;

import java.awt.EventQueue;
import java.awt.FileDialog;

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
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
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
	
	private ImagePNG imageDeBaseSauvegarde;
	
	private ImagePNG image;
	
	private Quadtree qt;
	
	private static boolean estCompresse = false;
	
	
	

	

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
		
		
		final JLabel lblCompression = new JLabel("");
		lblCompression.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompression.setForeground(Color.WHITE);
		lblCompression.setBounds(144, 46, 296, 16);
		frame.getContentPane().add(lblCompression);
		
		
		
		JLabel lblTitreApplication = new JLabel("BITMAP COMPRESSION");
		lblTitreApplication.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitreApplication.setBounds(6, 6, 536, 28);
		lblTitreApplication.setForeground(new Color(255, 255, 255));
		lblTitreApplication.setFont(new Font("Krungthep", Font.PLAIN, 21));
		frame.getContentPane().add(lblTitreApplication);
		
		JButton btnNewButton = new JButton("Importer image");
		btnNewButton.setBounds(6, 68, 147, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Compresser Phi");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!estCompresse) {
					if(image!=null && qt !=null) {
						try{
							int valCom = -1; 
							String input = JOptionPane.showInputDialog("Valeur compression phi",JOptionPane.QUESTION_MESSAGE);
							valCom = Integer.parseInt(input);
							if(valCom>0) {
								try {
									lblCompression.setText("Compression en cours... Veuillez patienter.");
									qt.compressPhi(valCom);	
									lblCompression.setText("Image compressée (Phi : "+valCom+")");
									estCompresse=true;
									erreurSaisie(4);
								} catch (Exception e1) {
									lblCompression.setText("");
									erreurSaisie(3);
								}
								
								
								
							}else {
								erreurSaisie(1);
							}
							
							
							
						}catch(NumberFormatException ex) {
						}
						
					}else {
						erreurSaisie(2);
					}
					}else {
						erreurSaisie(5);
					}
				
			}
		});
		btnNewButton_1.setBounds(6, 150, 147, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Compresser Delta");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!estCompresse) {
				if(image!=null && qt !=null) {
					try{
						int valCom = -1; 
						String input = JOptionPane.showInputDialog("Valeur compression delta",JOptionPane.QUESTION_MESSAGE);
						valCom = Integer.parseInt(input);
						if(valCom>=0 && valCom<=255 ) {
							try {
								lblCompression.setText("Compression en cours... Veuillez patienter.");
								qt.compressDelta(valCom);
								lblCompression.setText("Image compressée (Delta : "+valCom+")");
								estCompresse=true;
								erreurSaisie(4);
							} catch (Exception e1) {
								lblCompression.setText("");
								erreurSaisie(3);
							}
							
							
							
						}else {
							erreurSaisie(0);
						}
						
						
						
					}catch(NumberFormatException ex) {
					}
					
				}else {
					erreurSaisie(2);
				}
				}else {
					erreurSaisie(5);
				}
					
					
						
			}

			
		});
				
		
		btnNewButton_1_1.setBounds(6, 109, 147, 29);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Exporter PNG");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(image!=null && qt!=null) {
					
					
					
					 FileDialog fDialog = new FileDialog(frame, "Enregistrer sous", FileDialog.SAVE);
				     fDialog.setVisible(true);
				     String path = fDialog.getDirectory() + fDialog.getFile();
				    	 
				     if(!path.equals("nullnull")) {

				    	 if(path.length()>4) {
								if(!path.substring(path.length() - 4).equals(".png")) {
									path+=".png";
								}
						}
					     try {
							qt.exporterImage(path);
							erreurSaisie(7);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					     
				     }
				     

				     
		
					
					
					
				}else {
					erreurSaisie(6);
				}
				
			}
		});
		btnNewButton_1_2.setBounds(6, 187, 147, 29);
		frame.getContentPane().add(btnNewButton_1_2);
		
		JButton btnNewButton_1_2_1 = new JButton("Exporter texte");
		btnNewButton_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(image!=null && qt!=null) {
					
					

					
					 FileDialog fDialog = new FileDialog(frame, "Enregistrer sous", FileDialog.SAVE);
				     fDialog.setVisible(true);
				    
				     String path = fDialog.getDirectory() + fDialog.getFile();
				     
				     if(!path.equals("nullnull")) {
				    	 
					     if(path.length()>4) {
								if(!path.substring(path.length() - 4).equals(".txt")) {
									path+=".txt";
								}
						}
					     try {
							qt.exporterTexte(path);
							erreurSaisie(7);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
				    
				    	 
				     }
				     
				     	

				     
		
					
					
					
				}else {
					erreurSaisie(6);
				}
				
			}
		});
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
		panel.setBounds(220, 64, 148, 148);
		frame.getContentPane().add(panel);
		
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
					if(panel.getComponentCount()>0) {
						panel.removeAll();
					}
					BufferedImage myPicture;
					lblSourceFichier.setForeground(Color.GREEN);
					lblSourceFichier.setText("Image chargée");
					File fichier = new File(fc.getSelectedFile().getAbsolutePath());
					lblTailleFichier.setText(""+fichier.length()/ 1024 + "  kb");
					myPicture = ImageIO.read(fichier);
					JLabel picLabel = new JLabel(new ImageIcon(resize(myPicture,145,145)));
					panel.add(picLabel);
					panel.revalidate();
					
					//on sauvegarde en memoire l'image
					imageDeBaseSauvegarde = new ImagePNG(fc.getSelectedFile().getAbsolutePath());
					image = new ImagePNG(fc.getSelectedFile().getAbsolutePath());
					qt = new Quadtree(image);
					
				} catch (Exception e1) {
					lblSourceFichier.setText("Impossible de charger le fichier");
					lblSourceFichier.setForeground(Color.RED);
					e1.printStackTrace();
				}
			}
		});
		
	}
	
	private void erreurSaisie(int i) {
		//delta
		if(i==0) {
			
			JOptionPane.showMessageDialog(frame,
				    "La valeur doit être comprise entre 0 et 255.",
				    "Mauvaise saisie",
				    JOptionPane.ERROR_MESSAGE);
			
		}
		//phi
		else if(i==1) {
			
			JOptionPane.showMessageDialog(frame,
				    "La valeur doit être plus grande que 0.",
				    "Mauvaise saisie",
				    JOptionPane.ERROR_MESSAGE);
			
		}
		//aucune image n'est chargée
		else if(i==2) {
			JOptionPane.showMessageDialog(frame,
				    "Vous devez charger une image avant d'effectuer une compression.",
				    "Aucune image",
				    JOptionPane.ERROR_MESSAGE);
		}else if(i==3) {
			JOptionPane.showMessageDialog(frame,
				    "Impossible de compresser l'image choisie",
				    "Problème de compression",
				    JOptionPane.ERROR_MESSAGE);
			
		}else if(i==4) {
			JOptionPane.showMessageDialog(frame,
				    "La compression s'est réalisée avec succès.",
				    "Compression réussie",
				    JOptionPane.INFORMATION_MESSAGE);
			
		}else if(i==5) {
			JOptionPane.showMessageDialog(frame,
				    "Impossible de compresser une image déjà compressée. \n Pour effectuer une compression, veuillez importer une image qui n'a jamais été compressé. \n Pensez à exporter l'image actuelle pour ne pas perdre les modifications.",
				    "Erreur",
				    JOptionPane.ERROR_MESSAGE);
		}else if(i==6) {
			JOptionPane.showMessageDialog(frame,
				    "Vous devez charger une image avant d'effectuer un export.",
				    "Aucune image",
				    JOptionPane.ERROR_MESSAGE);
			
		}else if(i==7) {
			
			JOptionPane.showMessageDialog(frame,
				    "Export réalisé avec succès !",
				    "Export réussi",
				    JOptionPane.INFORMATION_MESSAGE);
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
	
	public Frame getFrame() {
		return this.frame;
	}
}
