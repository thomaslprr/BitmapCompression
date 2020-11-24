package Main;



import ImagePng.ImagePNG;
import Quadtree.Quadtree;

public class Main {

	public static void main(String[] args) throws Exception {
		
		
		
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
		
		String nom = "";
		if(imageACompresser.length()>4) {
			if(!imageACompresser.substring(imageACompresser.length() - 4).equals(".png")) {
				throw new Exception("Le fichier source à compresser doit être écrit sous la forme \"nomdufichier.png\" ou \"dossiersource/.../nomdufichier.png\"");
			}else {
				nom=imageACompresser.substring(0,imageACompresser.length() - 4);
				String val[] = nom.split("/");
				nom = val[val.length-1];
			}
		}
		
		
		
		System.out.println("////// LANCEMENT COMPRESSION \\\\\\");


			if(aTousLesArguments) {
				
				
				ImagePNG image = new ImagePNG(imageACompresser);
				System.out.println("----> IMAGE TROUVEE AVEC SUCCES");

				
				
				Quadtree qt = new Quadtree(image);
				Quadtree qtDelta = qt;
				Quadtree qtPhi = qt;
				
				
				ThreadCompression threadDelta = new ThreadCompression(qtDelta,0,delta,nom,imageACompresser);
				ThreadCompression threadPhi = new ThreadCompression(qtPhi,1,phi,nom,imageACompresser);
				
				threadDelta.run();
				threadPhi.run();
				
				
					
				


				
			}else {
				throw new Exception("Les paramètres ne sont pas corrects.");
			}
			
			
			System.out.println("////// FIN DU PROGRAMME \\\\\\");

		
		
		
	}

}
