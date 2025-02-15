package Quadtree;

import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

import ImagePng.ImagePNG;

public class Quadtree {
	
	Carre carrePrincipal;
	
	ImagePNG image;
	
	int largeur;
	int hauteur;
	
	private TreeSet<Carre> listePereDeFeuilles; 
	private int cptFeuille;
	

	
	public Carre getCarrePrincipal() {
		return carrePrincipal;
	}

	public Quadtree(ImagePNG image) throws Exception {
		this.image = image;
		this.largeur=image.width();
		this.hauteur= image.height();
				
		
		listePereDeFeuilles = new TreeSet<Carre>(comparerParEcartColorimetrique);
		
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
			Position p1 = new Position(p.getxDepart(),(p.getxArrive()+p.getxDepart())/2,p.getyDepart(),(p.getyArrive()+p.getyDepart())/2);
			Carre carre1 = new Carre(null,p1,c);
			listeCarres.add(carre1);
			
			
			//carre en haut a droite
			Position p2 = new Position((p.getxArrive()+p.getxDepart())/2,p.getxArrive(),p.getyDepart(),(p.getyArrive()+p.getyDepart())/2);
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
			
			cptFeuille++;
			
			if(c.getCarrePere().estPereDeFeuille()) {
				listePereDeFeuilles.add(c.getCarrePere());
			}

			
			

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
			
	
			while(listePereDeFeuilles.size()>0  && listePereDeFeuilles.first().getEcartColorimetrique()<=delta) {
				
				Carre c = listePereDeFeuilles.first();
				//on le supprime de la liste
				listePereDeFeuilles.remove(c);
				
					

				//on attribue la couleur moyenne au noeud pere
				c.setCouleur(c.getCouleurMoyenne());
					
				//on supprime ses feuilles
				c.supprimerFeuilles();
						
						
				if(c.getCarrePere()!=null && c.getCarrePere().estPereDeFeuille()) {
					listePereDeFeuilles.add(c.getCarrePere());
				}
						
				
			}
					
			
		}
		
		
	}
	
	



	
	public void compressPhi(int phi) throws Exception {
		
		if(phi<=0) {
			throw new Exception("L'indice de compression phi doit être plus grand que 0");
		
		}else {
			
			
			
			int nbFeuilles = cptFeuille;


			//il faut supprimer les feuilles en trop tant qu'il y en a en trop
			//on supprime les feuilles par 4 jusqu'à ce que le nombre de feuille du quadtree passe en dessous de phi.
			
			while(nbFeuilles>phi && listePereDeFeuilles.size()>0 ) {
				

				
				
				Carre pereQuiDevientFeuille = listePereDeFeuilles.first();
				
				//on supprime le père devenu feuille de la liste des pères
				listePereDeFeuilles.remove(pereQuiDevientFeuille);
				
				
				//la couleur du père prend la couleur moyenne de ses feuilles
				pereQuiDevientFeuille.setCouleur(pereQuiDevientFeuille.getCouleurMoyenne());

				
				
				//on supprime les 4 feuilles, le noeud devient ainsi une feuille
				pereQuiDevientFeuille.supprimerFeuilles();

				
				
				Carre pereNouvelleFeuille = pereQuiDevientFeuille.getCarrePere();
				
				if(nbFeuilles-3<4) {
					listePereDeFeuilles.add(this.getCarrePrincipal());
					
				}else if(pereNouvelleFeuille.estPereDeFeuille()) {
					listePereDeFeuilles.add(pereNouvelleFeuille);	
					
				}
				
				
				
				
				//on décrémente de 3 le nombre de feuille de l'arbre
				nbFeuilles -=3 ;
				

			
			}
				
				
				
			}
			
			
			
			
			
		}
		

public TreeSet<Carre> getFeuilles(TreeSet<Carre> listeFeuille, Carre c){
		
		TreeSet<Carre> listeFeuilles = listeFeuille;
		
		
		
		if(c.estFeuille()) {
			listeFeuilles.add(c);
		}else {
			
			for(Carre ca : c.getListeCarres()) {
				getFeuilles(listeFeuilles,ca);
			}
			
		}
		
		
		return listeFeuilles;
		
	}
	
	

	public String toString() {
		
		if(listePereDeFeuilles.size()==1) {
			return "("+ImagePNG.colorToHex(carrePrincipal.getCouleur())+")";
		}
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
		
		ImagePNG img = image;
		
		TreeSet<Carre> feuilles = new TreeSet<Carre>(comparerParEcartObjet);
		
		getFeuilles(feuilles,carrePrincipal);
		
		for(Carre c : feuilles) {
			
			
			Position p = c.getPosition();
			
			for(int i = p.getxDepart(); i<p.getxArrive();i++) {
				for(int j = p.getyDepart() ; j<p.getyArrive();j++) {
					
					img.setPixel(i,j,c.getCouleur());

				}
				
			}
			
			
			
		}
				
		return img;
	}
	
	public void exporterTexte(String nomDuFichier) throws Exception {
		if(nomDuFichier.equals(".txt")) {
			throw new Exception("Le nom du fichier ne peut pas être vide.");
		}
		try {
			FileWriter fw;
			fw = new FileWriter(nomDuFichier);
			fw.write(this.toString());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}
	
	
	public void exporterImage(String nomDuFichier) throws Exception {
		if(nomDuFichier.equals(".png")) {
			throw new Exception("Le nom du fichier ne peut pas être vide.");
		}
		this.toPNG().save(nomDuFichier);
	}
	
	//création d'un comparateur d'écart colorimétrique
	Comparator<Carre> comparerParEcartColorimetrique = new Comparator<Carre>(){

		@Override
		public int compare(Carre c1, Carre c2) {
			
			return c1.compareTo(c2);
		}
		
	};
	
	//création d'un comparateur d'objet
		Comparator<Carre> comparerParEcartObjet = new Comparator<Carre>(){

			@Override
			public int compare(Carre c1, Carre c2) {
				
				if(c1.equals(c2)) {
					return 0;
				}
				return -1;
			}
			
		};
	
	
}
