package towerDefense;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Schuss {
	
	private float posx;
	private float posy;
	private BufferedImage bild;
	private BufferedImage bildExplo;
	private Enemy zielEnemy;
	private final float SPEED = 500;
	private final int schussgröße = 8;
	private boolean schussExestiert = true;
	private float timeSinceHitEnemy = 0;
	private Turm turm;
	private Color color = Color.black; 
	private Random rnd = new Random();
	
	public Schuss(float posx, float posy, Enemy zielEnemy, Turm turm) {

		this.zielEnemy = zielEnemy;
		this.turm = turm;
		this.bild = BilderLaden.getSchuss();
		this.posx = posx-bild.getWidth()/2;
		this.posy = posy-bild.getHeight()/2;
		this.bildExplo = BilderLaden.getSchussExplo();
	}
	

	
	public void update(float timeSinceLastFrame){
		
		
		if(schussExestiert){
			//Schuss bewegt sich zum ziel:
			//		float speedx = (float) (zielEnemy.getBounding().getX() - posx);
			//		float speedy = (float) (zielEnemy.getBounding().getY() - posy);
			float speedx = (float) (zielEnemy.getPosX() + Spielablauf.quadratGröße/2 - posx);
			float speedy = (float) (zielEnemy.getPosY() + Spielablauf.quadratGröße/2 - posy);
			
			float speed = (float)Math.sqrt(speedx*speedx+speedy*speedy);
			
			if(speed!=0){
				speedx/=speed;
				speedy/=speed;
				
				speedx*=SPEED*timeSinceLastFrame;
				speedy*=SPEED*timeSinceLastFrame;
				
				posx+=speedx;
				posy+=speedy;
			}
		}else{
			timeSinceHitEnemy += timeSinceLastFrame;
			if(timeSinceHitEnemy >= 0.1)
				Spielablauf.schüsse.remove(this);
		}
		
		//schuss zerstören, wenn gegner berührt 
		if(schussExestiert && this.getBounding().intersects(zielEnemy.getBounding())){
			if(turm.getCrit() >= rnd.nextInt(100)){
				//leben abziehen CRIT!!!
				zielEnemy.lebenVerlieren(turm.getSchaden()*2);
			}else{
				//leben abziehen
				zielEnemy.lebenVerlieren(turm.getSchaden());
			}
			schussExestiert = false;
			
			//zentriere das bild (explosion ist größer als schuss selber
			posx += (bild.getWidth() - bildExplo.getWidth())/2;
			posy += (bild.getHeight() - bildExplo.getHeight())/2;
		}
		//schuss zerstören, falls gegner tot
		if(schussExestiert && zielEnemy.getLeben() <= 0){
			Spielablauf.schüsse.remove(this);
		}
	}
	
	public Rectangle getBounding(){
		return new Rectangle((int)posx, (int)posy, schussgröße, schussgröße);
	}
	public Color getColor(){
		return color;
	}
	public BufferedImage getBild(){
		if(!schussExestiert){
			return bildExplo;
		}
		return bild;
	}
}
