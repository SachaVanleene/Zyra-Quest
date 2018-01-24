package jeu;

public class Pomme implements Objet {
	private int dx;
	private int dy;
	private Hitbox_objet hitbox;
	private int hitbox_x=15;
	private int hitbox_y=15;

	
	public Pomme(int posX,int posY){
		this.dx=posX;
		this.dy=posY;
		this.hitbox=new Hitbox_objet(posX,posY,hitbox_x,hitbox_y);
		
	}

	@Override
	public String getNom() {
		return new String("Pomme");
	}

	@Override
	public Point getPosition() {
		return new Point(dx,dy);
	}

	@Override
	public void setPostion(int dx, int dy) {
		this.dx=dx;
		this.dy=dy;
	}

	@Override
	public String getSkin() {
		return new String("src/Objet/Pomme2.png");
	}

	@Override
	public int getHitbox_x() {
		return this.hitbox_x;
	}

	@Override
	public int getHitbox_y() {
		return this.hitbox_y;
	}

	@Override
	public int getSize_x() {
		return 50;
	}

	@Override
	public int getSize_y() {
		// TODO Auto-generated method stub
		return 50;
	}
	public void effet(Joueur j){
		j.setVie(-20);
		if(j.getVie()>100){ //RENDRE VIE CORRECTEMENT
			j.setVie(j.getVie()-100);
		}
	}
	public Hitbox getHitbox(){
		return this.hitbox;
	}
	public void majHitbox(){
		this.hitbox=new Hitbox_objet(3000,4000,hitbox_x,hitbox_y);
	}

}
