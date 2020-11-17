package Main;

import java.io.IOException;

import Quadtree.Quadtree;

public class ThreadCompression extends Thread implements Runnable {
	
	Quadtree qt;
	int typeCompression;
	int valCompression;
	
	public ThreadCompression(Quadtree quadtree, int typeCompression ,int valCompression) {
		this.qt = quadtree;
		this.typeCompression = typeCompression;
		this.valCompression = valCompression;
	}

	@Override
	public void run() {
		if(typeCompression==0) {
			
			try {
				System.out.println("Je fais la compression delta : "+valCompression);
				qt.compressDelta(valCompression);
				qt.exporterTexte("delta"+valCompression+".txt");
				qt.exporterImage("delta"+valCompression+".png");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if (typeCompression==1) {
			
			try {
				qt.compressPhi(valCompression);
				qt.exporterTexte("phi"+valCompression+".txt");
				qt.exporterImage("phi"+valCompression+".png");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}else {
			try {
				throw new Exception("Type de compression invalide.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
