package jeu;

import java.util.ArrayList;

public interface Salle {
	
	public String getFond();
	
	public ArrayList<Ennemie> getEnnemie();
	
	public ArrayList<Objet> getObjet();
	
	public boolean posPossible(int dx,int dy);
	
	public int getSpawnx();
	
	public int getSpawny();
	
	public Hitbox getPorte();
	
	public void majHitbox();
	
	public boolean trou(int dx, int dy);
}
