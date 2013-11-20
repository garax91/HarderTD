package towerDefense;

import java.awt.Color;
import java.awt.Point;

public class Wall {

	private final int posx;
	private final int posy;
	private final int arrayCoordX;
	private final int arrayCoordY;
	private final static int price = 10;
	private Color color;
	
	Wall(int x, int y, int arrayCoordX, int arrayCoordY){
		posx = x;
		posy = y;
		this.arrayCoordX = arrayCoordX;
		this.arrayCoordY = arrayCoordY;
		color =  color.pink;
	}
	
	public static void wallL�schen(int index){
		//setze das array zur�ck auf 0
		Spielablauf.spielfeldarray[Spielablauf.walls.get(index).getCoord().x][Spielablauf.walls.get(index).getCoord().y] = 0;
		//l�sche den ausgew�hlten turm!
		Spielablauf.walls.remove(index);
	}
	
	
	
	public int getX(){
		return posx;
	}
	public int getY(){
		return posy;
	}
	public Point getCoord(){
		return new Point(arrayCoordX, arrayCoordY);
	}
	public Color getColor(){
		return color;
	}
	public static int getPrice(){
		return price;
	}
}
