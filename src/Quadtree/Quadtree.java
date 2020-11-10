package Quadtree;

import java.awt.Color;
import java.util.ArrayList;

import ImagePng.ImagePNG;

public class Quadtree {
	
	Carre carrePrincipal;
	
	ImagePNG image;
	
	int largeur;
	int hauteur;
	

	
	public Quadtree(ImagePNG image) throws Exception {
		this.image = image;
		this.largeur=image.width();
		this.hauteur= image.height();
		
		if(largeur!=hauteur) {
			//lever une exception
			throw new Exception("La largeur et la hauteur ne sont pas similaires");
		}
		
		carrePrincipal = new Carre(null,);
		
		//generation();
		
		
	}
	
	public void generation(int xDepart, int xArrive, int yDepart, int yArrive){
		ArrayList<Color> listeCouleurs = new ArrayList<>();
		
		if(!zoneEstDeLaMemeCouleur(xDepart,xArrive,yDepart,yArrive)) {
			
			//carre en haut a gauche
			generation(xDepart,xArrive/2,yDepart,yArrive/2);
			//carre en haut a droite
			generation(xArrive/2,xArrive,yDepart,yArrive/2);
			//carre en bas a droite
			generation(xArrive/2,xArrive,yArrive/2,yArrive);
			//carre en bas a gauche
			generation(xDepart,xArrive/2,yArrive/2,yArrive);
			
			
		}
		
		
		
	}
	
	public boolean zoneEstDeLaMemeCouleur(int xDepart, int xArrive, int yDepart, int yArrive) {
		
		ArrayList<Color> listeCouleurs = new ArrayList<>();
		
		for(int x = xDepart; x<xArrive;x++) {
			for(int y = yDepart; y<yArrive; y++) {
				Color c = image.getPixel(x, y);
				if(!listeCouleurs.contains(c)) {
					
					listeCouleurs.add(c);
					
				}else {
					
					return false;
					
				}
				
			}
		}
		
		
		return true;
	}
	
	
	
	
	public void compressDelta(int delta) {
		
		if(delta<0 || delta>255) {
			//lever une exeption 
		
		}
		
		
	}
	
	public void compressPhi(int phi) {
		
		if(phi<0) {
			//lever une exeption 
		
		}
		
	}
	
	public String toString() {
		
		return null;
	}
	
	public ImagePNG toPNG() {
		
		//devra retourner une imagePNG
		return null;
	}
	
	
}
