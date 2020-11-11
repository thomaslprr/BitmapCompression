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
			throw new Exception("L'image n'est pas carré.");
		}
		
		carrePrincipal = new Carre(null,new Position(0,this.largeur,0,this.hauteur));
		
		generation(carrePrincipal.getPosition(),carrePrincipal);
		
		
	}
	
	private void generation(Position p,Carre c) throws Exception{
		ArrayList<Carre> listeCarres = new ArrayList<>();
		

		
		if(!zoneEstDeLaMemeCouleur(p)) {
			
			if(p.getTailleCarre()>=2) {

			
			
			
			//carre en haut a gauche
			Position p1 = new Position(p.getxDepart(),(p.getxArrive()+p.getxDepart())/2,p.getyDepart(),p.getyArrive()/2);
			Carre carre1 = new Carre(null,p1);
			listeCarres.add(carre1);
			
			
			//carre en haut a droite
			Position p2 = new Position((p.getxArrive()+p.getxDepart())/2,p.getxArrive(),p.getyDepart(),p.getyArrive()/2);
			Carre carre2 = new Carre(null,p2);
			listeCarres.add(carre2);
			
			
			//carre en bas a droite
			Position p3 = new Position((p.getxArrive()+p.getxDepart())/2,p.getxArrive(),(p.getyArrive()+p.getyDepart())/2,p.getyArrive());
			Carre carre3 = new Carre(null,p3);
			listeCarres.add(carre3);
			
			
			//carre en bas a gauche
			Position p4 = new Position(p.getxDepart(),(p.getxArrive()+p.getxDepart())/2,(p.getyArrive()+p.getyDepart())/2,p.getyArrive());
			Carre carre4 = new Carre(null,p4);
			listeCarres.add(carre4);
			
			c.setListeCarres(listeCarres);

			generation(p1,carre1);
			generation(p2,carre2);
			generation(p3,carre3);
			generation(p4,carre4);




			
			}
			
		
			
		}else {
			c.setCouleur(image.getPixel(p.getxDepart(), p.getyDepart()));

		}
		
		
		
	}
	
	public boolean zoneEstDeLaMemeCouleur(Position p) {
		
		Color c = image.getPixel(p.getxDepart(), p.getyDepart()) ;
		
		for(int x = p.getxDepart(); x<p.getxArrive();x++) {
			for(int y = p.getyDepart(); y< p.getyArrive(); y++) {
				Color c1 = image.getPixel(x, y);
				if(!c.equals(c1)) {
					
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
		
		String s = recupererChaine(this.carrePrincipal);
		return s.replace("( ", "(");
	}
	
	private String recupererChaine(Carre c) {
		
		String s = "";
		
		
		if(c.getListeCarres().size()>0) {
			 s += "";

			
			s+="(";
			
			for(Carre carre : c.getListeCarres()) {
				
				s+=" "+carre + recupererChaine(carre);
				
				
			}
			s+=")";
			
			
			
		}
		
		
		return s;
		
		
	}
	
	public ImagePNG toPNG() {
		
		//devra retourner une imagePNG
		return null;
	}
	
	
}
