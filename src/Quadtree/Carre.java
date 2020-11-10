package Quadtree;

import java.util.ArrayList;

public class Carre {
	
	String couleur;
	int position;
	ArrayList<Carre> listeCarres;
	
	
	
	
	public Carre(String couleur, int position) {
		super();
		this.couleur = couleur;
		this.position = position;
	}
	
	
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
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
