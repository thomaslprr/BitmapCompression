package Quadtree;

import java.awt.Color;
import java.util.ArrayList;

public class Carre {
	
	Color couleur;
	int position;
	ArrayList<Carre> listeCarres;
	
	
	
	
	public Carre(Color couleur, int position) {
		super();
		this.couleur = couleur;
		this.position = position;
	}
	
	
	public Color getCouleur() {
		return couleur;
	}
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}


	public ArrayList<Carre> getListeCarres() {
		return listeCarres;
	}


	public void setListeCarres(ArrayList<Carre> listeCarres) {
		this.listeCarres = listeCarres;
	}
	
	
	
	
	

}
