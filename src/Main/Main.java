package Main;



import java.awt.Color;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ImagePng.ImagePNG;
import Quadtree.Carre;
import Quadtree.Position;
import Quadtree.Quadtree;

public class Main {
	
	public static boolean aDejaCompresse = false;

	public static void main(String[] args) throws Exception {
		
		
		if(args.length==0) {
			ImagePNG imageInitiale = null;
			ImagePNG image = null;
			Quadtree original = null,delta = null,phi = null;
			
			System.out.println("Bienvenue sur le menu textuel de la compression Bitmap ! ");
			afficherLesOptions(imageInitiale,image,original,delta,phi);
			
			
		}else if(args.length==3) {
			

			String imageACompresser = args[0],
					typeCompression=null, 
					valeurCompressionPhi=args[2], 
					valeurCompressionDelta=args[1];
			
			int delta = -1;
			int phi = -1;
			
			
			
			
			boolean aTousLesArguments = true;
			
			
			if(args[0]==null || args[1]==null || args[2]==null) {
				aTousLesArguments = false;

			}else {
				try {
					delta = Integer.parseInt(valeurCompressionDelta);
					phi = Integer.parseInt(valeurCompressionPhi);
				}catch(NumberFormatException e) {
					throw new Exception("Les valeurs de compression ne sont pas conformes");
				}
			}
			
			String nom = "";
			if(imageACompresser.length()>4) {
				if(!imageACompresser.substring(imageACompresser.length() - 4).equals(".png")) {
					throw new Exception("Le fichier source à compresser doit être écrit sous la forme \"nomdufichier.png\" ou \"dossiersource/.../nomdufichier.png\"");
				}else {
					nom=imageACompresser.substring(0,imageACompresser.length() - 4);
					String val[] = nom.split("/");
					nom = val[val.length-1];
				}
			}
			
			
			
			System.out.println("////// LANCEMENT COMPRESSION \\\\\\");


				if(aTousLesArguments) {
					
					
					ImagePNG image = new ImagePNG(imageACompresser);
					System.out.println("----> IMAGE TROUVEE AVEC SUCCES");

					
					
					Quadtree qt = new Quadtree(image);
					Quadtree qtDelta =  new Quadtree(image);
					Quadtree qtPhi =  new Quadtree(image);
					
					
					ThreadCompression threadDelta = new ThreadCompression(qtDelta,0,delta,nom,imageACompresser);
					ThreadCompression threadPhi = new ThreadCompression(qtPhi,1,phi,nom,imageACompresser);
					
					threadDelta.run();
					threadPhi.run();
					
					
						
					


					
				}else {
					throw new Exception("Les paramètres ne sont pas corrects.");
				}
				
				
				System.out.println("////// FIN DU PROGRAMME \\\\\\");
			
			
			
		}else if(args.length == 1 && args[0].equals("-a")){
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						InterfaceGraphique window = new InterfaceGraphique();
						window.getFrame().setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
			
		}else {
			
			throw new Exception("Paramètres incorrects.");

			
		}
			
			
		
		
		
		
		
		
		
		
	}

	private static void afficherLesOptions(ImagePNG imageInitiale, ImagePNG image, Quadtree original, Quadtree delta, Quadtree phi) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n ///////////////////////////////////////// \n");
		System.out.println("1: Charger une image PNG en mémoire dans un quadtree");
		System.out.println("2: Effectuer une compression Delta");
		System.out.println("3: Effectuer une compression Phi");
		System.out.println("4: Sauvegarder le quadtree dans un fichier PNG");
		System.out.println("5: Sauvegarder le quadtree dans un fichier TXT");
		System.out.println("6: Donner les mesures comparatives de deux images PNG");
		System.out.println("7: Quitter l'application");
		
		int numAction = -1;
		
		do {
			numAction = sc.nextInt();
			
			
		}while(numAction<1 || numAction>7 );
			
		effectuerAction(numAction,imageInitiale,image,original,delta,phi);

		
		
		
	}
	
	private static void effectuerAction(int numeroAction, ImagePNG imageInitiale, ImagePNG image, Quadtree original, Quadtree delta, Quadtree phi) {
		
		Scanner sc = new Scanner(System.in);
		
		switch(numeroAction) {
		
			case 1:
				
			try {
				System.out.println("Veuillez saisir l'adresse de l'image");
				String adresseFichier = sc.nextLine();
				imageInitiale = new ImagePNG(adresseFichier);
				image = new ImagePNG(adresseFichier);
				try {
					original = new Quadtree(image);
					aDejaCompresse=false;
					System.out.println("Image PNG chargée en mémoire");
					afficherLesOptions(imageInitiale, image,original,delta,phi);
					
				} catch (Exception e) {

					System.out.println("Impossible de convertir l'image en quadtree");
				}
			} catch (IOException e) {
				System.out.println("Impossible de trouver le fichier");
				afficherLesOptions(imageInitiale, image,original,delta,phi);
			}
				
				break;
			case 2:
				if(original!=null) {
					
					if(aDejaCompresse) {
						System.out.println("Vous avez déjà compressé l'image. Veuillez importer une autre image. "
								+ "\n Pensez à exporter l'image déjà compressé pour ne pas la perdre.");
						afficherLesOptions(imageInitiale, image,original,delta,phi);
					}else {
					System.out.println("Quelle est la valeur de compression Delta que vous voulez appliquer sur l'image chargée ?");
					try {
						int valCompress = sc.nextInt();
						System.out.println("Compression en cours...");
						original.compressDelta(valCompress);
						aDejaCompresse = true;
						System.out.println("Compression effectuée.");
						afficherLesOptions(imageInitiale, image,original,delta,phi);

					} catch (Exception e) {
						System.out.println(e.getMessage().toString());
						afficherLesOptions(imageInitiale, image,original,delta,phi);
						
					}
					}
				}else {
					System.out.println("Aucune image n'est chargée, veuillez en charger une.");
					afficherLesOptions(imageInitiale, image,original,delta,phi);
				}
				break;
			case 3:
				if(original!=null) {
					if(aDejaCompresse) {
						System.out.println("Vous avez déjà compressé l'image. Veuillez importer une autre image. "
								+ "\n Pensez à exporter l'image déjà compressé pour ne pas la perdre.");
						afficherLesOptions(imageInitiale, image,original,delta,phi);
					}else {
					System.out.println("Quelle est la valeur de compression Phi que vous voulez appliquer sur l'image chargée ?");
					try {
						int valCompress = sc.nextInt();
						System.out.println("Compression en cours...");
						original.compressPhi(valCompress);
						aDejaCompresse = true;
						System.out.println("Compression effectuée.");
						afficherLesOptions(imageInitiale, image,original,delta,phi);

						
					} catch (Exception e) {
						System.out.println(e.getMessage().toString());
						afficherLesOptions(imageInitiale, image,original,delta,phi);
						
					}
					}
				}else {
					System.out.println("Aucune image n'est chargée, veuillez en charger une.");
					afficherLesOptions(imageInitiale, image,original,delta,phi);
				}
				break;
			case 4:
				if(original!=null) {
					if(!aDejaCompresse) {
						System.out.println("Vous n'avez apporté aucune modification sur l'image d'origine.");
					}
					try {
						System.out.println("Veuillez saisir le nom que vous voulez donner au fichier (sans l'extension .png)");
						original.exporterImage(sc.nextLine()+".png");
						System.out.println("Image PNG exportée.");
						afficherLesOptions(imageInitiale, image,original,delta,phi);


						
					} catch (Exception e) {
						System.out.println("Impossible d'exporter le quadtree en PNG");
					}
				}
				else {
					System.out.println("Aucune image n'est chargée, veuillez en charger une.");
					afficherLesOptions(imageInitiale, image,original,delta,phi);
				
				}
				break;
			case 5:
				if(original!=null) {
					try {
						System.out.println("Veuillez saisir le nom que vous voulez donner au fichier (sans l'extension .txt)");
						original.exporterImage(sc.nextLine()+".txt");
						System.out.println("Fichier texte exporté.");
						afficherLesOptions(imageInitiale, image,original,delta,phi);
					} catch (Exception e) {
						System.out.println("Impossible d'exporter le quadtree en TXT");
					}
				}
				else {
					System.out.println("Aucune image n'est chargée, veuillez en charger une.");
					afficherLesOptions(imageInitiale, image,original,delta,phi);
				
				}
				break;
			case 6:
				
				
					
					File fichier1 = null;
					File fichier2 = null;
					ImagePNG image1 = null;
					ImagePNG image2 = null;

					
					
					
					
					try {
						System.out.println("Saisissez l'adresse de la première image.");
						String adresse = sc.nextLine();
						image1 = new ImagePNG(adresse);
						fichier1= new File(adresse);
						
						
						System.out.println("Saisissez l'adresse de la deuxième image.");
						adresse = sc.nextLine();
						image2 = new ImagePNG(adresse);
						fichier2 = new File(adresse);
						
						
					} catch (IOException e) {
						System.out.println("Impossible de trouver le fichier");
						afficherLesOptions(imageInitiale, image,original,delta,phi);
						
					}
					
					if(image1!=null && image2 !=null && fichier1 !=null && fichier2 !=null) {
			            double wRapport = Math.ceil(10000.0*fichier2.length() / fichier1.length())/100.0;
						System.out.println("RAPPORT TAILLE : "+wRapport+"%  / EQM : "+ImagePNG.computeEQM(image1, image2)+"%" );
					}
					
					afficherLesOptions(imageInitiale, image,original,delta,phi);

					
				
				
				break;
				
			case 7:
				System.out.println("Application quittée.");
				System.exit(1);
		
		
		}
		
		
	}

}
