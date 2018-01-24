package jeu;

public interface Objet {
	
	public String getNom();
	
	public Point getPosition();
	
	public void setPostion(int dx, int dy);
	
	public String getSkin();
	
	public int getHitbox_x();
	
	public int getHitbox_y();
	
	public int getSize_x();
	
	public int getSize_y();
	
	public void effet(Joueur j);
	
	public Hitbox getHitbox();
	
	public void majHitbox();
}
