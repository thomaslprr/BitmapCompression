package Quadtree;

import java.awt.Color;
import java.util.ArrayList;

import ImagePng.ImagePNG;

public class Carre {
	
	Color couleur;
	Position position;
	ArrayList<Carre> listeCarres;
	
	
	
	
	public Carre(Color couleur, Position position) throws Exception {

		if(position.getTailleCarre()>=1) {
		this.couleur = couleur;
		this.position = position;
		listeCarres= new ArrayList<>();
		}else {
			throw new Exception("Taille du carré plus petite que 1");
		}
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
	
	
	
	
	

}
