package jeu;

public class Bouleverte implements Objet{
	private int dx;
	private int dy;
	private Hitbox_objet hitbox;
	private int hitbox_x=5;
	private int hitbox_y=5;
	private String image = "src/Objet/Attaquefaible2.png";
	private int nouveaudegat=100;
	
	public Bouleverte(int posX,int posY){
		this.dx=posX;
		this.dy=posY;
		this.hitbox=new Hitbox_objet(posX,posY,hitbox_x,hitbox_y);
		
	}

	@Override
	public String getNom() {
		return new String("Bouleverte");
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
		return this.image;
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
		j.modifierAttaque(this.image,this.hitbox_x,this.hitbox_y,this.nouveaudegat);
	}
	public Hitbox getHitbox(){
		return this.hitbox;
	}
	public void majHitbox(){
		this.hitbox=new Hitbox_objet(3000,4000,hitbox_x,hitbox_y);
	}
}
