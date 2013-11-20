package towerDefense;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Enemy_Heal extends Enemy{
	
	private float timeSinceLastHeal = 0;
	private float healRate = 1f;
	private BufferedImage bild3;

	public Enemy_Heal(int posy, int width, int height, int leben, Color color) {
		super(posy, width, height, leben, color);
		this.bild3 = BilderLaden.getGegner3();
	}

	public static void spawn(){
		spawnAll();
		
		for(int i=0; i<5; i++){
			enemysCounter++;
			Spielablauf.enemys.add(new Enemy_Heal(i*3+2, Spielablauf.quadratGr��e, Spielablauf.quadratGr��e, lebenSpawn, Color.PINK));
		}
		Spielablauf.LebenGegner = lebenSpawn;
	}
	
	protected void lebenGenerieren(float timeSinceLastFrame){
		timeSinceLastHeal += timeSinceLastFrame;
		
		if(timeSinceLastHeal >= healRate){
			timeSinceLastHeal -= healRate;
			
			setLeben(getLeben() +5);
		}
	}
	
	public BufferedImage getBild(){
		return bild3;
	}
}
