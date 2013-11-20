package towerDefense;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Enemy_Speed extends Enemy{
	
	private BufferedImage bild2;

	public Enemy_Speed(int posy, int width, int height, int leben, Color color) {
		super(posy, width, height, leben, color);
		this.bild2 = BilderLaden.getGegner2();
		setSpeed(getSpeed()*1.5f);
	}

	public static void spawn(){
		spawnAll();
		
		lebenSpawn *= 0.5;
		
		for(int i=0; i<8; i++){
			enemysCounter++;
			Spielablauf.enemys.add(new Enemy_Speed(i*3+2, Spielablauf.quadratGröße, Spielablauf.quadratGröße, lebenSpawn, Color.PINK));
		}
		Spielablauf.LebenGegner = lebenSpawn;
	}
	
	public BufferedImage getBild(){
		System.out.println("bild2");
		return bild2;
	}
}
