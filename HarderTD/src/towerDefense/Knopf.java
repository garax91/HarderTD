package towerDefense;
import java.awt.Color;
import java.awt.Rectangle;


public class Knopf {
	private Rectangle bounding;
	private Color colorNormal;
	private Color colorAction;
	private boolean action = false;
	
	//Constructor mit 2 Farben
	Knopf(int posx, int posy, int width, int height, Color color1, Color color2){
		this.bounding = new Rectangle(posx, posy, width, height);
		this.colorNormal = color1;
		this.colorAction = color2;
	}
	//Constructor mit 1 Farbe
	Knopf(int posx, int posy, int width, int height, Color color1){
		this(posx, posy, width, height, color1, color1);
	}
	
	
	public Rectangle getBounding(){
		return bounding;
	}
	public Color getColor(){
		if(action){
			return colorAction;
		}
		else{
			return colorNormal;
		}
	}
	public boolean getAction(){
		return action;
	}
	public void setAction(boolean action){
		this.action = action;
	}
}
