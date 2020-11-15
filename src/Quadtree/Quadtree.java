package Quadtree;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ImagePng.ImagePNG;

public class Quadtree {
	
	Carre carrePrincipal;
	
	ImagePNG image;
	
	int largeur;
	int hauteur;
	

	
	public Carre getCarrePrincipal() {
		return carrePrincipal;
	}

	public Quadtree(ImagePNG image) throws Exception {
		this.image = image;
		this.largeur=image.width();
		this.hauteur= image.height();
		
		
		if(largeur!=hauteur || largeur%2 !=0) {
			//lever une exception
			throw new Exception("L'image n'est pas carré.");
		}
		
		carrePrincipal = new Carre(null,new Position(0,this.largeur,0,this.hauteur),null);
		
		generation(carrePrincipal.getPosition(),carrePrincipal);
		
		
	}
	
	private void generation(Position p,Carre c) throws Exception{
		ArrayList<Carre> listeCarres = new ArrayList<>();
		

		
		if(!zoneEstDeLaMemeCouleur(p)) {
			
			if(p.getTailleCarre()>=2) {

			
			
			
			//carre en haut a gauche
			Position p1 = new Position(p.getxDepart(),(p.getxArrive()+p.getxDepart())/2,p.getyDepart(),p.getyArrive()/2);
			Carre carre1 = new Carre(null,p1,c);
			listeCarres.add(carre1);
			
			
			//carre en haut a droite
			Position p2 = new Position((p.getxArrive()+p.getxDepart())/2,p.getxArrive(),p.getyDepart(),p.getyArrive()/2);
			Carre carre2 = new Carre(null,p2,c);
			listeCarres.add(carre2);
			
			
			//carre en bas a droite
			Position p3 = new Position((p.getxArrive()+p.getxDepart())/2,p.getxArrive(),(p.getyArrive()+p.getyDepart())/2,p.getyArrive());
			Carre carre3 = new Carre(null,p3,c);
			listeCarres.add(carre3);
			
			
			//carre en bas a gauche
			Position p4 = new Position(p.getxDepart(),(p.getxArrive()+p.getxDepart())/2,(p.getyArrive()+p.getyDepart())/2,p.getyArrive());
			Carre carre4 = new Carre(null,p4,c);
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
	
	
	
	
	public void compressDelta(int delta) throws Exception {
		
		if(delta<0 || delta>255) {
			throw new Exception("Le delta doit être compris entre 0 et 255");
		
		}else {
			
			ArrayList<Carre> listePereDeFeuille = new ArrayList<>();
			
			getPereDeFeuille(listePereDeFeuille,getCarrePrincipal());
			
			for(Carre c : listePereDeFeuille) {
				if(c.getEcartColorimetrique()<=delta) {
					

					//on attribue la couleur moyenne au noeud pere
					c.setCouleur(c.getCouleurMoyenne());
					
					//on supprime ses feuilles
					c.supprimerFeuilles();
					
					
				}
				
			}	
			
			
		}
		
		
	}
	
	
	public ArrayList<Carre> getFeuilles(ArrayList<Carre> listeFeuille, Carre c){
		
		ArrayList<Carre> listeFeuilles = listeFeuille;
		
		
		
		if(c.estFeuille()) {
			listeFeuilles.add(c);
		}else {
			
			for(Carre ca : c.getListeCarres()) {
				getFeuilles(listeFeuilles,ca);
			}
			
		}
		
		
		return listeFeuilles;
		
	}
	
	
	public ArrayList<Carre> getPereDeFeuille(ArrayList<Carre> carrePere, Carre c) {
		
		ArrayList<Carre> listeCarrePere = carrePere;
		
		if(c.estPereDeFeuille()) {
			
			if(!carrePere.contains(c)) {
				carrePere.add(c);
			}
			
			
		}else {
			if(c.getListeCarres().size()==4) {
				getPereDeFeuille(carrePere,c.getListeCarres().get(0));
				getPereDeFeuille(carrePere,c.getListeCarres().get(1));
				getPereDeFeuille(carrePere,c.getListeCarres().get(2));
				getPereDeFeuille(carrePere,c.getListeCarres().get(3));
			}
		}
		
		return listeCarrePere;
		
		
	}
	
	
	
	public void compressPhi(int phi) throws Exception {
		
		if(phi<=0) {
			throw new Exception("L'indice de compression phi doit être plus grand que 0");
		
		}else {
			
			//on récupère toutes les feuilles de l'arbre
			ArrayList<Carre> listeFeuilles = new ArrayList<>();
			getFeuilles(listeFeuilles,carrePrincipal);
			
			int nbFeuilles = listeFeuilles.size();
			
			
			//création d'un comparateur d'écart colorimétrique
			Comparator<Carre> comparerParEcartColorimetrique = new Comparator<Carre>(){

				@Override
				public int compare(Carre c1, Carre c2) {
					
					return c1.compareTo(c2);
				}
				
			};
			
			
			
			ArrayList<Carre> pereDeFeuille= new ArrayList<>();
			
			getPereDeFeuille(pereDeFeuille,carrePrincipal);
			
			//on trie les pères de feuille par valeur croissante d'écart colorimétrique
			Collections.sort(pereDeFeuille, comparerParEcartColorimetrique);
			
			

			//il faut supprimer les feuilles en trop tant qu'il y en a en trop
			//on supprime les feuilles par 4 jusqu'à ce que le nombre de feuille du quadtree passe en dessous de phi.
			
			while(nbFeuilles>phi && pereDeFeuille.size()>0 ) {
				
				
				Carre pereQuiDevientFeuille = pereDeFeuille.get(0);
				
				//la couleur du père prend la couleur moyenne de ses feuilles
				pereQuiDevientFeuille.setCouleur(pereQuiDevientFeuille.getCouleurMoyenne());

				
				//on supprime les 4 feuilles, le noeud devient ainsi une feuille
				pereQuiDevientFeuille.supprimerFeuilles();

				
				//on supprime le père devenu feuille de la liste des pères
				pereDeFeuille.remove(pereQuiDevientFeuille);
				
				
				//on décrémente de 3 le nombre de feuille de l'arbre
				nbFeuilles -=3 ;
				
				
			
			}
				
				
				
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
