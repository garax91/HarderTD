package towerDefense;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;

public class MouseHandler implements MouseListener, MouseMotionListener{
	
	public static int turmAuswahlIndex = 0;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		boolean clicked = false;
	
		
		//Turm auswählen
		if(!clicked && !Frame.knTurmLöschen.getAction() && Spielablauf.spielfeld.getBounds().contains(e.getX(), e.getY())){
			if(Spielablauf.spielfeldarray[e.getX()/Spielablauf.quadratGröße][e.getY()/Spielablauf.quadratGröße] == 1){
				clicked = true;
				for(int i = 0; i<Spielablauf.türme.size(); i++){
					if(((Spielablauf.türme.get(i).getX() == (e.getX()/Spielablauf.quadratGröße)*Spielablauf.quadratGröße+1 ) && (Spielablauf.türme.get(i).getY() == (e.getY()/Spielablauf.quadratGröße)*Spielablauf.quadratGröße+1))){
						turmAuswahlIndex = i;
						//ausgewählter turm:
						Spielablauf.türme.get(i).setTurmAusgewählt(true);
					}else{
						//alle anderen deaktivieren
						Spielablauf.türme.get(i).setTurmAusgewählt(false);
					}
				}
			}
		}
		
		if(Frame.turmmenu){
			//Turmmenu beenden
			if(!clicked && Frame.knTurmmenu.getBounding().contains(e.getX(), e.getY())){
				clicked = true;
				Spielablauf.türme.get(turmAuswahlIndex).setTurmAusgewählt(false);
				Frame.turmmenu = false;
			}
			
			if(!Frame.knSpawn.getAction()){
				
				//UpgradeKnöpfe
				if(!clicked && Frame.knUGDmg.getBounding().contains(e.getX(), e.getY())){
					Spielablauf.türme.get(turmAuswahlIndex).dmgUP();
				}
				if(!clicked && Frame.knUGSpeed.getBounding().contains(e.getX(), e.getY())){
					Spielablauf.türme.get(turmAuswahlIndex).speedUP();
				}
				if(!clicked && Frame.knUGRange.getBounding().contains(e.getX(), e.getY())){
					Spielablauf.türme.get(turmAuswahlIndex).rangeUP();
				}
				if(!clicked && Frame.knUGCrit.getBounding().contains(e.getX(), e.getY())){
					Spielablauf.türme.get(turmAuswahlIndex).critUP();
				}	
			}
		}
		
		if(!Frame.turmmenu){
			//Turm BAUEN, falls aktiviert und Feld frei bzw. gültig
			if(!clicked && Frame.knTurmbau.getAction() && Spielablauf.spielfeld.getBounds().contains(e.getX(), e.getY())){	
				clicked = true;
				if(Spielablauf.spielfeldarray[e.getX()/Spielablauf.quadratGröße][e.getY()/Spielablauf.quadratGröße] == 0){
					Spielablauf.türme.add(new Turm((e.getX()/Spielablauf.quadratGröße)*Spielablauf.quadratGröße+1, (e.getY()/Spielablauf.quadratGröße)*Spielablauf.quadratGröße+1, e.getX()/Spielablauf.quadratGröße, e.getY()/Spielablauf.quadratGröße, Color.ORANGE));
					//Frame.knTurmbau.setAction(false);
					Spielablauf.spielfeldarray[e.getX()/Spielablauf.quadratGröße][e.getY()/Spielablauf.quadratGröße] = 1;
					Spielablauf.goldCounter -= Turm.getPrice();
					
					/**/
					Wegfinden.wegfinden();
					//falls jetzt kein weg mehr existiert...oder zu wenig gold
					if(! Wegfinden.wegIstGefunden() || Spielablauf.goldCounter < 0){
						
						Spielablauf.goldCounter += Turm.getPrice();

						Turm.turmLöschen(Spielablauf.türme.size()-1);
					}
				}
			}
			
			//Wall BAUEN, falls aktiviert und Feld frei bzw. gültig
			if(!clicked && Frame.knWallbau.getAction() && Spielablauf.spielfeld.getBounds().contains(e.getX(), e.getY())){	
				clicked = true;
				if(Spielablauf.spielfeldarray[e.getX()/Spielablauf.quadratGröße][e.getY()/Spielablauf.quadratGröße] == 0){
					Spielablauf.walls.add(new Wall((e.getX()/Spielablauf.quadratGröße)*Spielablauf.quadratGröße+1, (e.getY()/Spielablauf.quadratGröße)*Spielablauf.quadratGröße+1, e.getX()/Spielablauf.quadratGröße, e.getY()/Spielablauf.quadratGröße));
					//Frame.knTurmbau.setAction(false);
					Spielablauf.spielfeldarray[e.getX()/Spielablauf.quadratGröße][e.getY()/Spielablauf.quadratGröße] = 1;
					Spielablauf.goldCounter -= Wall.getPrice();
					
					/**/
					Wegfinden.wegfinden();
					//falls jetzt kein weg mehr existiert...oder zu wenig gold
					if(! Wegfinden.wegIstGefunden() || Spielablauf.goldCounter < 0){
						
						Spielablauf.goldCounter += Wall.getPrice();

						Wall.wallLöschen(Spielablauf.walls.size()-1);
					}
				}
			}
			
			//Turm LÖSCHEN, falls aktiviert und Turm besetzt
			if(!clicked && Frame.knTurmLöschen.getAction() && Spielablauf.spielfeld.getBounds().contains(e.getX(), e.getY())){	
				clicked = true;
				if(Spielablauf.spielfeldarray[e.getX()/Spielablauf.quadratGröße][e.getY()/Spielablauf.quadratGröße] == 1){
					//Türme
					for(int i = 0; i<Spielablauf.türme.size(); i++){
						Turm t = Spielablauf.türme.get(i);
						if(t.getX()/Spielablauf.quadratGröße == e.getX()/Spielablauf.quadratGröße && t.getY()/Spielablauf.quadratGröße == e.getY()/Spielablauf.quadratGröße){
							Spielablauf.goldCounter += Spielablauf.türme.get(i).getPriceGesammt() * 0.8;
							Turm.turmLöschen(i);
							break;
						}
					}
					
					//Walls
					for(int i = 0; i<Spielablauf.walls.size(); i++){
						Wall w = Spielablauf.walls.get(i);
						if(w.getX()/Spielablauf.quadratGröße == e.getX()/Spielablauf.quadratGröße && w.getY()/Spielablauf.quadratGröße == e.getY()/Spielablauf.quadratGröße){
							Wall.wallLöschen(i);
							break;
						}
					}
				}
			}
			
			//Knopfdrücken -> Turm bauen aktivieren
			if(!clicked && !Frame.knSpawn.getAction() && Frame.knTurmbau.getBounding().contains(e.getX(), e.getY())){
				clicked = true;
				if(Frame.knTurmbau.getAction()==true){
					Frame.knTurmbau.setAction(false);
				}
				else{
					Frame.knTurmbau.setAction(true);
					Frame.knTurmLöschen.setAction(false);
					Frame.knWallbau.setAction(false);
				}	
			}
			
			//Knopfdrücken -> Wall bauen aktivieren
			if(!clicked && !Frame.knSpawn.getAction() && Frame.knWallbau.getBounding().contains(e.getX(), e.getY())){
				clicked = true;
				if(Frame.knWallbau.getAction()==true){
					Frame.knWallbau.setAction(false);
				}
				else{
					Frame.knWallbau.setAction(true);
					Frame.knTurmLöschen.setAction(false);
					Frame.knTurmbau.setAction(false);
				}	
			}
			
			//Knopfdrücken -> Turm löschen aktivieren
			if(!clicked && !Frame.knSpawn.getAction() && Frame.knTurmLöschen.getBounding().contains(e.getX(), e.getY())){
				clicked = true;
				
				if(Frame.knTurmLöschen.getAction()==true){
					Frame.knTurmLöschen.setAction(false);
				}
				else{
					Frame.knTurmLöschen.setAction(true);
					Frame.knTurmbau.setAction(false);
					Frame.knWallbau.setAction(false);
				}
			}
			
			//KnopfSpawn
			if(!clicked && Frame.knSpawn.getBounding().contains(e.getX(), e.getY())){
				clicked = true;
				if(Frame.knSpawn.getAction()==false){
					/**/Wegfinden.wegfinden();
					
					switch(Spielablauf.waveCounter % 8){
					case 0:
					case 1:
					case 2:
						Enemy.spawn();
						break;
					case 3: 
						Enemy_Speed.spawn();
						break;
					case 4:
						Enemy_Tank.spawn();
						break;
					case 5:
						Enemy_Heal.spawn();
						break;
					case 6:
						Enemy_Schild.spawn();
						break;
					case 7:
						Enemy_Tank.spawn();
						break;
					default:
						Enemy.spawn();
					}
					
					
					Frame.knSpawn.setAction(true);
					Frame.knTurmbau.setAction(false);
					Frame.knTurmLöschen.setAction(false);
					Frame.knWallbau.setAction(false);
				}
			}
		}
		
		//Beendenknopf
		if(!clicked && Frame.knBeenden.getBounding().contains(e.getX(), e.getY())){
			clicked = true;
			int result = JOptionPane.showConfirmDialog(null, "Wollen Sie das Spiel beenden?", "BEENDEN", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				System.exit(0);
			} else if (result == JOptionPane.NO_OPTION) {
			}
		}
		
		clicked = false;
		
	}
	

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	//	System.out.println("CLICK: position: " + e.getX() + " " + e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	
	
	/*MouseMotion*/
	
	@Override
	public void mouseDragged(MouseEvent e) {
		//System.out.println("test123456");
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	//	System.out.println("position: " + e.getX() + " " + e.getY());
		
		
		//Schattenkästechen
	/*	if(Turm.getTurmAusgewählt() && e.getX()<Spielablauf.spielfeldBreite*Spielablauf.quadratGröße+1){
			Spielablauf.türme.add(new Turm((e.getX()/Spielablauf.quadratGröße)*Spielablauf.quadratGröße+1, (e.getY()/Spielablauf.quadratGröße)*Spielablauf.quadratGröße+1));
			Turm.setTurmAusgewählt(false);
			Spielablauf.spielfeld[e.getX()/Spielablauf.quadratGröße][e.getY()/Spielablauf.quadratGröße] = 1;
		}
	*/	
	}
	
}
