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
		
		carrePrincipal = new Carre(null,new Position(0,this.largeur,0,this.hauteur));
		
		generation(carrePrincipal.getPosition(),carrePrincipal);
		
		
	}
	
	public void generation(Position p,Carre c){
		ArrayList<Carre> listeCarres = new ArrayList<>();
		
		
		if(!zoneEstDeLaMemeCouleur(p)) {
			
			//carre en haut a gauche
			Position p1 = new Position(p.getxDepart(),p.getxArrive()/2,p.getyDepart(),p.getyArrive()/2);
			Carre carre1 = new Carre(null,p1);
			generation(p1,carre1);
			//carre en haut a droite
			Position p2 = new Position(p.getxArrive()/2,p.getxArrive(),p.getyDepart(),p.getyArrive()/2);
			Carre carre2 = new Carre(null,p2);
			generation(p2,carre2);
			//carre en bas a droite
			Position p3 = new Position(p.getxArrive()/2,p.getxArrive(),p.getyArrive()/2,p.getyArrive());
			Carre carre3 = new Carre(null,p3);
			generation(p3,carre3);
			//carre en bas a gauche
			Position p4 = new Position(p.getxDepart(),p.getxArrive()/2,p.getyArrive()/2,p.getyArrive());
			Carre carre4 = new Carre(null,p4);
			generation(p4,carre4);
			
			
		}
		
		c.setListeCarres(listeCarres);
		
		
	}
	
	public boolean zoneEstDeLaMemeCouleur(Position p) {
		
		ArrayList<Color> listeCouleurs = new ArrayList<>();
		
		for(int x = p.getxDepart(); x<p.getxArrive();x++) {
			for(int y = p.getyDepart(); y< p.getyArrive(); y++) {
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
