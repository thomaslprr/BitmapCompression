package Quadtree;

import java.awt.Color;
import java.util.ArrayList;

import ImagePng.ImagePNG;

public class Carre implements Comparable<Carre>  {
	
	Color couleur;
	Position position;
	ArrayList<Carre> listeCarres;
	Color couleurMoyenne;
	double ecartColorimetrique;
	
	Carre carrePere;
	
	
	
	
	public Carre(Color couleur, Position position, Carre carrePere) throws Exception {

		if(position.getTailleCarre()>=1) {
		this.carrePere = carrePere;
		this.couleur = couleur;
		this.position = position;
		listeCarres= new ArrayList<>();
		}else {
			throw new Exception("Taille du carré plus petite que 1");
		}
	}
	
	
	
	
	public Carre getCarrePere() {
		return carrePere;
	}



	public void setCarrePere(Carre carrePere) {
		this.carrePere = carrePere;
	}
	
	public boolean estFeuille() {
		if((this.getListeCarres().size() == 0 || this.getListeCarres() ==null) && this.getCouleur()!=null) {
			return true;
		}
		return false;
	}
	
	public boolean estPereDeFeuille() {
		if(listeCarres.size()==4) {
			
			for(Carre c : listeCarres) {
				
				if(c.getCouleur()==null) {
					return false;
				}
				
			}
			
			return true;
			
		}
		
		return false;
	}
	
	



	public Color getCouleurMoyenne() {
		
		if(estFeuille()) {
			int rougeMoyen = 0 ;
			int vertMoyen = 0;
			int bleuMoyen = 0;
			if(carrePere!=null) {
				for(Carre c : carrePere.listeCarres) {
					rougeMoyen+=c.getCouleur().getRed();
					vertMoyen+= c.getCouleur().getGreen();
					bleuMoyen+=c.getCouleur().getBlue();
					
					
				}
			}
			
			rougeMoyen /= 4;
			vertMoyen /=4;
			bleuMoyen /=4;
			
			Color cou = new Color(rougeMoyen,vertMoyen,bleuMoyen);
			setCouleurMoyenne(cou);
			return cou;
			
		}else if(estPereDeFeuille()) {
			
			return getListeCarres().get(0).getCouleurMoyenne();
			
			
		}
		
		
		return null;
	}
	
	
	public void setCouleurMoyenne(Color couleurMoyenne) {
		this.couleurMoyenne = couleurMoyenne;
	}



	public void setEcartColorimetrique(double ecartColorimetrique) {
		this.ecartColorimetrique = ecartColorimetrique;
	}



	public double getEcartColorimetrique() {
		
		
		if(estFeuille()) {
			
			double ecartColorimetrique = 0;
			
			int rouge = 0;
			int vert =0;
			int bleu = 0;
			
			Color cMoyen = getCouleurMoyenne();
			
			
			rouge = (int) Math.pow(getCouleur().getRed() - cMoyen.getRed(),2);
			vert = (int) Math.pow(getCouleur().getGreen() - cMoyen.getGreen(),2);
			bleu = (int) Math.pow(getCouleur().getBlue() - cMoyen.getBlue(),2);
			
			
			ecartColorimetrique = Math.sqrt((rouge+vert+bleu)/3);
			return ecartColorimetrique;
			
			
		}else if(estPereDeFeuille()) {
			
			
			double ecartColorimetriqueMax = getListeCarres().get(0).getEcartColorimetrique();
			
			for(int i=1; i<4;i++) {
				double ecartTmp = getListeCarres().get(i).getEcartColorimetrique();
				if(ecartTmp>ecartColorimetriqueMax) {
					ecartColorimetriqueMax = ecartTmp;
				}
			}
			

			//System.out.println("Voici l'écart colorimétrique : "+ecartColorimetriqueMax);

			return ecartColorimetriqueMax;
				
			
		}
		
		return -999999;
		
		
		
	}
	
	
	public Color getCouleur() {
		return couleur;
	}
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}


	public ArrayList<Carre> getListeCarres() {
		return listeCarres;
	}


	public void setListeCarres(ArrayList<Carre> listeCarres) {
		this.listeCarres = listeCarres;
	}
	
	public String toString() {
		
		if(this.getCouleur()!=null) {
			return ""+ImagePNG.colorToHex(getCouleur());
		}
		
		return "";
		
	}



	public void supprimerFeuilles() throws Exception {
		if(estPereDeFeuille()) {
			
			for(Carre c : getListeCarres()) {
				
				if(!c.estFeuille()) {
					
					throw new Exception("Impossible de supprimer un noeud qui n'est pas une feuille.");
					
				}
				
				
			}
			
			setListeCarres(new ArrayList<Carre>());
			
			
		}
		
		
	}




	@Override
	public int compareTo(Carre c) {
		
		
		if(this.getEcartColorimetrique()>c.getEcartColorimetrique()) {
			return 1;
		}else if(this.getEcartColorimetrique() < c.getEcartColorimetrique()) {
			return -1;
		}else if(this==c){
			return 0;
		}
		
		return -1;
		
	}
	
	
	
	
	

}
