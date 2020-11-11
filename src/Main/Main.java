package Main;

import java.io.IOException;
import java.util.ArrayList;

import ImagePng.ImagePNG;
import Quadtree.Carre;
import Quadtree.Quadtree;

public class Main {

	public static void main(String[] args) {

		try {
			ImagePNG image = new ImagePNG("images/i.png");
			System.out.println(image.height()+"    "+image.width());
			Quadtree qt = new Quadtree(image);
			System.out.println("Voici le quadtree en texte : "+qt+" taille : "+qt.toString().length());
			qt.compressDelta(128);
			System.out.println("Voici le quadtree en texte : "+qt+" après compression !  Taille : "+qt.toString().length());

			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
