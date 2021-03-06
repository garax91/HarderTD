package towerDefense;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy {
	private float posx;
	private float posy;
	private Rectangle bounding;
	protected BufferedImage bild1;
	private float speed = 80;
	private int leben;
	protected static int lebenSpawn;
	private static float multiplikator = 1; //zum lebenSpawn berechnen
	private Color color;
	protected static int enemysCounter = 0;
	private static boolean enemysAlive = false;
	private static Point mittelPunkt;
	//private int[][] schnellsterWeg = {{Spielablauf.spielfeldBreite/2, 0}, {Spielablauf.spielfeldBreite/2, 11}, {14, 11}, {14, 22}};
	public static ArrayList<Point> schnellsterWeg = new ArrayList<Point>();
	private boolean spawned = false;
	private boolean slow = false;
	private float timeSinceSlowStart = 0;
	private float timeSlow = 2;
	private int indexWeg = 0; //index für schnellsterWeg[]
	int toleranzbereich = Spielablauf.quadratGröße/4;


	private int diffX;
	private int diffY;

	
	public Enemy(int posy, int width, int height, int leben, Color color) {
		this.color = color;
		this.posx = (Spielablauf.spielfeldBreite-1)*Spielablauf.quadratGröße/2;
		this.posy = 0-posy*height;
		this.leben = leben;
		this.bild1 = BilderLaden.getGegner1();
		bounding = new Rectangle((int)posx, (int)this.posy, width, height);
		mittelPunkt = new Point((int)this.posx + bounding.width/2, (int)this.posy + bounding.height/2);
	}
	
	public static void spawn(){
		spawnAll();
		
		for(int i=0; i<5; i++){
			enemysCounter++;
			Spielablauf.enemys.add(new Enemy(i*3+2, Spielablauf.quadratGröße, Spielablauf.quadratGröße, lebenSpawn, Color.PINK));
		}
		Spielablauf.LebenGegner = lebenSpawn;
	}
	protected static void spawnAll(){
		enemysAlive = true;
		Spielablauf.waveCounter++;
		Spielablauf.goldFürGegner += 1.5;
		
		if(Spielablauf.waveCounter % 5 == 0){
			multiplikator *= 1.5;						//änderung der schwiereigkeitsgrades
		}else{
			multiplikator *= 1.001;
		}
		lebenSpawn = (int)(Spielablauf.waveCounter*multiplikator*10);
	}
	
	public void update(float timeSinceLastFrame){
		System.out.println(timeSinceLastFrame);
		
		//leben generieren
		lebenGenerieren(timeSinceLastFrame);
		lebenVerlieren(0); //für schildupdate
		
		//gegner geslowed?
		if(slow){
			timeSinceSlowStart += timeSinceLastFrame;
			if(timeSinceSlowStart >= timeSlow){
				speed *= 2;
				slow = false;
			}
		}
		
	//schnellsten weg gehen:
		int wegpunktX = (int) (schnellsterWeg.get(indexWeg).getX()*Spielablauf.quadratGröße);
		int wegpunktY = (int) (schnellsterWeg.get(indexWeg).getY()*Spielablauf.quadratGröße);
		
		if(posx != wegpunktX){
			diffX = (int) (posx - wegpunktX);
			if(diffX < 0){
				posx += speed*timeSinceLastFrame;
			}else if(diffX > 0){
				posx -= speed*timeSinceLastFrame;
			}
		}
		if(posy != wegpunktY){
			diffY = (int) (posy - wegpunktY);
			if(diffY < 0){
				posy += speed*timeSinceLastFrame;
			}else if(diffY > 0){
				posy -= speed*timeSinceLastFrame;
			}
		}
		
		if((int)posx <= wegpunktX+toleranzbereich && (int)posy <= wegpunktY+toleranzbereich && (int)posx >= wegpunktX-toleranzbereich && (int)posy >= wegpunktY-toleranzbereich){
			indexWeg++;
		}
		
	//spawned wenn enemy auf feld
		if(posy >= -10){
			spawned = true;
		}
		
//	//mittelpunkt aktualisieren
//		mittelPunkt.x = (int)posx + bounding.width/2;
//		mittelPunkt.y = (int)posy + bounding.height/2;
//		

	//nach Liste Enemys resetten
		if(indexWeg == schnellsterWeg.size()){
			Spielablauf.enemys.remove(this);
			enemysCounter--;
			//Leben verieren
			Spielablauf.leben--;
		}
	
	//gegner tot?!
		if(leben <= 0){
			Spielablauf.goldCounter += Spielablauf.goldFürGegner;
			Spielablauf.enemys.remove(this);
			enemysCounter--;
		}
		
		
	//wave beenden
		//alle Enemys tot -> wave beenden
		if(enemysCounter == 0){
			enemysAlive = false;
		}
		//SpawnKnopf zurücksetzen
		if(!enemysAlive){
			Frame.knSpawn.setAction(false);
		}
		
		
		bounding.x = (int)posx;
		bounding.y = (int)posy;
	}
	
	//leben verlieren
	public void lebenVerlieren(int lebendsAbzug){
		leben -= lebendsAbzug;
	}
	
	//Leben generieren (Für healenemy)
	protected void lebenGenerieren(float timeSinceLastFrame){
	}
	
	//Enemy slowen
	public void slowen(){
		speed /= 2;
		slow = true;
		timeSinceSlowStart = 0;
	}
	
	
	
	public Rectangle getBounding(){
		return bounding;
	}
	public Color getColor(){
		return color;
	}
	public BufferedImage getBild(){
		return bild1;
	}
	public boolean getEnemysAlive(){
		return enemysAlive;
	}
	public Point getMittelPunkt(){
		return mittelPunkt;
	}
	public int getLeben(){
		return leben;
	}
	public float getSpeed(){
		return speed;
	}
	public int getIndexWeg(){
		return indexWeg;
	}
	public boolean getSpawned(){
		return spawned;
	}
	public float getPosX(){
		return posx;
	}
	public float getPosY(){
		return posy;
	}
//	public void setEnemysAlive(boolean enemysAlive){
//		Enemy.enemysAlive = enemysAlive;
//	}
	
	void setSpeed(float n){
		this.speed = n;
	}
	void setLeben(int n){
		this.leben = n;
	}
	
}
