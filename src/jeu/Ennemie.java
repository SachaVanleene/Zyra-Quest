package jeu;

public interface Ennemie {
	public void attaquer (int px, int py);
	
	public void deplacer();
	
	public int getX();
	
	public int getY();
	
	public String image();
	
	public int getNbAttaque();
	
	public AttaqueDistance getAttaque(int k);
	
	public void checkAttaque();
	
	public int getImagex();
	
	public int getImagey();
	
	public int getHitbox_x(); // caractéristique pour construire la hitbox et centré l'image pour l'affichage ! 
	
	public int getHitbox_y();
	
	public Hitbox getHitbox();
	
	public void majHitbox();
	
	public void setVie(int degat);
	
	public int getVie();
	
	public int getViemax();
	
	public boolean getMort();
	
	public void animMort();
	
	public void preDeplacement();
}
