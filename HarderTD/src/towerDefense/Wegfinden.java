package towerDefense;

import java.awt.Point;
import java.util.ArrayList;

public class Wegfinden {
	
	private static ArrayList<Point> blockierteFelder = new ArrayList<Point>();
	private static Point startFeld = new Point(Spielablauf.spielfeldBreite/2, 0);
	private static Tree<Point> wegTree = new Tree<Point>(startFeld);
	private static int xCoord;
	private static int yCoord;
	private static boolean wegGefunden = false;
	
	
	private static void türmeEintragen(){
		
		blockierteFelder.clear();
		
		//türme werden eingetragen
		for(int i = 0; i<Spielablauf.türme.size(); i++){
			Turm t = Spielablauf.türme.get(i);
			blockierteFelder.add(t.getCoord());
		}
		//walls werden eingetragen
		for(int i = 0; i<Spielablauf.walls.size(); i++){
			Wall w = Spielablauf.walls.get(i);
			blockierteFelder.add(w.getCoord());
		}
		//startfeld wird blockiert
		blockierteFelder.add(startFeld);
		
		//die ränder werden eingetragen
		for(int i = -1; i <= Spielablauf.spielfeldBreite; i++){
			blockierteFelder.add(new Point(i, -1));
		}
		for(int i = -1; i <= Spielablauf.spielfeldHöhe; i++){
			blockierteFelder.add(new Point(-1, i));
			blockierteFelder.add(new Point(Spielablauf.spielfeldBreite, i));
		}
	}
	

	
	@SuppressWarnings("unchecked")
	private static void wegpunkteSpeichern(){
		
		Enemy.schnellsterWeg.clear();
		
		Node<Point> aktuellerNode = wegTree.findID(0);
		Enemy.schnellsterWeg.add(aktuellerNode.p);
		
		for(int i = 0; i < Tree.indexWeg.size(); i++){
			
			aktuellerNode = aktuellerNode.children.get(Tree.indexWeg.get(i));
			Enemy.schnellsterWeg.add(aktuellerNode.p);
			
		}
	}
	
	
	
	public static void wegfinden(){

		
		wegTree.clearTree();
		türmeEintragen();

		//Koordinaten werden initialisiert auf Start des Baums
		xCoord = wegTree.findID(0).p.x;
		yCoord = wegTree.findID(0).p.y;
		
		for(int idCounter = 0; wegTree.findID(idCounter) != null; idCounter++){
			wegGefunden = false;
			
			//Weg gefunden ( letzter Schleifendurchlauf ) !LETZTE KOORDINATEN!
			if(yCoord > Spielablauf.spielfeldHöhe){
				//erstelle mit 'find' den Weg im Baum!
				wegTree.find(new Point(xCoord, yCoord));
				wegpunkteSpeichern();
				wegGefunden = true;
				break;
			}

			
			//Koordinaten werden aktualisiert
			xCoord = wegTree.findID(idCounter).p.x;
			yCoord = wegTree.findID(idCounter).p.y;
			
			
			//Weg verlässt das Spielfeld
			if(xCoord < 0 || yCoord < 0 || xCoord > Spielablauf.spielfeldBreite || yCoord > Spielablauf.spielfeldHöhe){
				continue;
			}

			
			//Vom Ausgangspunkt werden alle 4 Himmelsrichtungen untersucht und ggf. hinzugefügt
			if(!blockierteFelder.contains(new Point(xCoord, yCoord+1))){
				blockierteFelder.add(new Point(xCoord, yCoord+1));
				wegTree.insert(wegTree.findID(idCounter), new Point(xCoord, yCoord+1));
			}
			if(!blockierteFelder.contains(new Point(xCoord+1, yCoord))){
				blockierteFelder.add(new Point(xCoord+1, yCoord));
				wegTree.insert(wegTree.findID(idCounter), new Point(xCoord+1, yCoord));
			}
			if(!blockierteFelder.contains(new Point(xCoord-1, yCoord))){
				blockierteFelder.add(new Point(xCoord-1, yCoord));
				wegTree.insert(wegTree.findID(idCounter), new Point(xCoord-1, yCoord));
			}
			if(!blockierteFelder.contains(new Point(xCoord, yCoord-1))){
				blockierteFelder.add(new Point(xCoord, yCoord-1));
				wegTree.insert(wegTree.findID(idCounter), new Point(xCoord, yCoord-1));
			}
		}
	}
	
	public static boolean wegIstGefunden(){
		return wegGefunden;
	}
}
