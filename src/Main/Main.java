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
			System.out.println("avant compression" + qt.toString());
			qt.compressPhi(20);
			System.out.println("apres compression" + qt.toString());
			
			qt.exporterImage("test.png");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
