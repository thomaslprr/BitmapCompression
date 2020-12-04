package Main;

import java.io.File;

import ImagePng.ImagePNG;
import Quadtree.Quadtree;

public class ThreadCompression extends Thread implements Runnable {
	
	Quadtree qt;
	int typeCompression;
	int valCompression;
	String nom;
	String adresseFichier;
	
	public ThreadCompression(ImagePNG im, int typeCompression ,int valCompression,String nom,String fichier) {
		try {
			this.qt = new Quadtree(im);
			this.typeCompression = typeCompression;
			this.valCompression = valCompression;
			this.nom = nom;
			this.adresseFichier = fichier;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		//si compression delta
		if(typeCompression==0) {
			
			try {
				System.out.println("----> LANCEMENT COMPRESSION DELTA ("+valCompression+")");
				qt.compressDelta(valCompression);
				System.out.println("	-- COMPRESSION REUSSIE");
				qt.exporterTexte(nom+"-delta"+valCompression+".txt");
				System.out.println("	-- EXPORT TEXTE REUSSI");
				qt.exporterImage(nom+"-delta"+valCompression+".png");
				System.out.println("	-- EXPORT PNG REUSSI");
				
				ImagePNG imageBase = new ImagePNG(adresseFichier);
				double EQMDelta = ImagePNG.computeEQM(imageBase, qt.toPNG());
				
				  // chargement des fichiers
	            File fic = new File(adresseFichier);
	            File ficDelta =  new File(nom+"-delta"+valCompression+".png");

	            // rapport des tailles
	            double wDelta = Math.ceil(10000.0*ficDelta.length() / fic.length())/100.0;

				System.out.println("Compression delta : taille = "+ wDelta+ "%  // qualitié = "+EQMDelta+"%");
				


			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		//si compression phi
		else if (typeCompression==1) {
			
			try {
				System.out.println("----> LANCEMENT COMPRESSION PHI ("+valCompression+")");
				qt.compressPhi(valCompression);
				System.out.println("	-- COMPRESSION REUSSI");
				qt.exporterTexte(nom+"-phi"+valCompression+".txt");
				System.out.println("	-- EXPORT TEXTE REUSSI");
				qt.exporterImage(nom+"-phi"+valCompression+".png");
				System.out.println("	-- EXPORT PNG REUSSI");
				
				ImagePNG imageBase = new ImagePNG(adresseFichier);
				double EQMPhi = ImagePNG.computeEQM(imageBase, qt.toPNG());
				
				  // chargement des fichiers
	            File fic = new File(adresseFichier);
	            File ficPhi =  new File(nom+"-phi"+valCompression+".png");

	            // rapport des tailles
	            double wPhi = Math.ceil(10000.0*ficPhi.length() / fic.length())/100.0;

				System.out.println("Compression phi : taille = "+ wPhi+ "%  // qualitié = "+EQMPhi+"%");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}else {
			try {
				throw new Exception("Type de compression invalide.");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
