package Main;

import java.io.IOException;

import ImagePng.ImagePNG;
import Quadtree.Quadtree;

public class Main {

	public static void main(String[] args) {

		try {
			ImagePNG image = new ImagePNG("images/4.png");
			System.out.println(image.height()+"    "+image.width());
			Quadtree qt = new Quadtree(image);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
