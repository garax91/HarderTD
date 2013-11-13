package towerDefense;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BilderLaden {
	
	private static BufferedImage turmBild;
	private static BufferedImage failBild;
	private static BufferedImage schussBild;
	private static BufferedImage schussExploBild;
	private static BufferedImage gegner1Bild;
	
	public BilderLaden() {
		laden();
	}
	
	public  void laden(){
		try {
			turmBild = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Bilder/Turm_test.png"));
			failBild = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Bilder/Failbildschirm.png"));
			schussBild = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Bilder/Schuss.png"));
			schussExploBild = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Bilder/Schuss_Explosion_test.png"));
			gegner1Bild = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Bilder/Gegner1_test.png"));
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public static BufferedImage getTurmBild(){
		return turmBild;
	}
	public static BufferedImage getFailBild(){
		return failBild;
	}
	public static BufferedImage getSchuss(){
		return schussBild;
	}
	public static BufferedImage getSchussExplo(){
		return schussExploBild;
	}
	public static BufferedImage getGegner1(){
		return gegner1Bild;
	}
}
