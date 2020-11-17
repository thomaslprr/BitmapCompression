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
			
			
			
			Quadtree qt = new Quadtree(image);
			qt.compressPhi(15000);
			qt.exporterTexte("phi15.txt");
			qt.exporterImage("test.png");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
