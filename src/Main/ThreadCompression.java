package Main;

import java.io.IOException;

import Quadtree.Quadtree;

public class ThreadCompression extends Thread implements Runnable {
	
	Quadtree qt;
	int typeCompression;
	int valCompression;
	String nom;
	
	public ThreadCompression(Quadtree quadtree, int typeCompression ,int valCompression,String nom) {
		this.qt = quadtree;
		this.typeCompression = typeCompression;
		this.valCompression = valCompression;
		this.nom = nom;
	}

	@Override
	public void run() {
		if(typeCompression==0) {
			
			try {
				System.out.println("----> LANCEMENT COMPRESSION DELTA ("+valCompression+")");
				qt.compressDelta(valCompression);
				System.out.println("	-- COMPRESSION REUSSIE");
				qt.exporterTexte(nom+"-delta"+valCompression+".txt");
				System.out.println("	-- EXPORT TEXTE REUSSI");
				qt.exporterImage(nom+"-delta"+valCompression+".png");
				System.out.println("	-- EXPORT PNG REUSSI");


			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if (typeCompression==1) {
			
			try {
				System.out.println("----> LANCEMENT COMPRESSION PHI ("+valCompression+")");
				qt.compressPhi(valCompression);
				System.out.println("	-- COMPRESSION REUSSI");
				qt.exporterTexte(nom+"-phi"+valCompression+".txt");
				System.out.println("	-- EXPORT TEXTE REUSSI");
				qt.exporterImage(nom+"-phi"+valCompression+".png");
				System.out.println("	-- EXPORT PNG REUSSI");
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
