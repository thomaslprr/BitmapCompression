package Main;

import java.io.IOException;
import java.util.ArrayList;

import ImagePng.ImagePNG;
import Quadtree.Carre;
import Quadtree.Quadtree;

public class Main {

	public static void main(String[] args) {

		try {
			ImagePNG image = new ImagePNG("images/1024-cube.png");
			System.out.println(image.height()+"    "+image.width());
			Quadtree qt = new Quadtree(image);
			System.out.println("Voici le quadtree en texte : "+qt+" taille : "+qt.toString().length());
			qt.compressDelta(128);
			System.out.println("Voici le quadtree en texte : "+qt+" après compression !  Taille : "+qt.toString().length());
			
			
			Quadtree qt2 = new Quadtree(image);
			qt2.compressPhi(4);
			System.out.println("Voici le quadtree en texte : "+qt2+" après compression !  Taille : "+qt.toString().length());



			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
