package Main;



import ImagePng.ImagePNG;
import Quadtree.Quadtree;

public class Main {

	public static void main(String[] args) throws Exception {
		
		/**
		ImagePNG image= new ImagePNG("images/1024-cube.png");
		
		Quadtree qt = new Quadtree(image);
		
		qt.compressPhi(1);
		
		qt.exporterImage("test.png");
		*/
		
		
		if(args.length!=3) {
			throw new Exception("Paramètres incorrects.");
		}
		String imageACompresser = args[0],
				typeCompression=null, 
				valeurCompressionPhi=args[2], 
				valeurCompressionDelta=args[1];
		
		int delta = -1;
		int phi = -1;
		
		
		
		
		boolean aTousLesArguments = true;
		
		
		if(args[0]==null || args[1]==null || args[2]==null) {
			aTousLesArguments = false;

		}else {
			try {
				delta = Integer.parseInt(valeurCompressionDelta);
				phi = Integer.parseInt(valeurCompressionPhi);
			}catch(NumberFormatException e) {
				throw new Exception("Les valeurs de compression ne sont pas conformes");
			}
		}
		
		

			if(aTousLesArguments) {
				
				ImagePNG image = new ImagePNG(imageACompresser);
				
				
				
				Quadtree qt = new Quadtree(image);
				Quadtree qtDelta = qt;
				Quadtree qtPhi = qt;
				
				
				ThreadCompression threadDelta = new ThreadCompression(qtDelta,0,delta);
				ThreadCompression threadPhi = new ThreadCompression(qtPhi,1,phi);
				
				threadDelta.run();
				threadPhi.run();
				

					
				


				
			}else {
				throw new Exception("Les paramètres ne sont pas corrects.");
			}
			
			
			
		
		
		
	}

}
