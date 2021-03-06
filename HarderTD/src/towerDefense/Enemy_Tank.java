package towerDefense;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Enemy_Tank extends Enemy{
	
	private BufferedImage bild4;

	public Enemy_Tank(int posy, int width, int height, int leben, Color color) {
		super(posy, width, height, leben, color);
		this.bild4 = BilderLaden.getGegner4();
//		setLeben((int)(getLeben()*1.25f));
		setSpeed(getSpeed()*0.5f);
	}

	public static void spawn(){
		spawnAll();
		
		lebenSpawn *= 2.4;
		
		
		for(int i=0; i<5; i++){
			enemysCounter++;
			Spielablauf.enemys.add(new Enemy_Tank(i*3+2, Spielablauf.quadratGröße, Spielablauf.quadratGröße, lebenSpawn, Color.PINK));
		}
		Spielablauf.LebenGegner = lebenSpawn;
	}
	
	
	public BufferedImage getBild(){
		return bild4;
	}
}
