package Main;

import java.io.IOException;
import java.util.ArrayList;

import ImagePng.ImagePNG;
import Quadtree.Carre;
import Quadtree.Quadtree;

public class Main {

	public static void main(String[] args) {

		try {
			ImagePNG image = new ImagePNG("images/4.png");
			System.out.println(image.height()+"    "+image.width());
			Quadtree qt = new Quadtree(image);
			System.out.println("Voici le quadtree en texte : "+qt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
