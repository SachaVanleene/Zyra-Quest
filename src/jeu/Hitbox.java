package jeu;

public abstract class Hitbox {
	private Point Topl;
	private Point Topr;
	private Point Botl;
	private Point Botr;
	
	public  Hitbox(int px, int py ,int ix , int iy){
		this.Topl = new Point(px-ix,py-iy);
		this.Topr = new Point(px+ix,py-iy);
		this.Botl = new Point(px-ix,py+iy);
		this.Botr = new Point(px+ix,py+iy);
	}
	
	
	public Boolean inHitbox(Point p){
		Boolean in = false;
		if (this.Topl.getY()<p.getY() && this.Botl.getY()>p.getY()){
			if (this.Topl.getX()<p.getX() && this.Topr.getX()>p.getX()){
				in = true;
				return in;
			} else {
				return in;
			}
		}else {
			return in;
		}
	}
	public Boolean Collision(Hitbox H){
		Boolean collision = false;
		if (H.inHitbox(Topl)){
			collision = true;
			return collision;
		}
		if(H.inHitbox(Topr)){
			collision = true;
			return collision;
		}
		if(H.inHitbox(Botl)){
			collision = true;
			return collision;
		}
		if(H.inHitbox(Botr)){
			collision = true;
			return collision;
		} 
		return collision;
	}
	public void Afficher(){
		System.out.println("Coordonnes de la Hitbox");
		System.out.println("Topl: "+"("+this.Topl.getX()+","+this.Topl.getY()+")");
		System.out.println("Topr: "+"("+this.Topr.getX()+","+this.Topr.getY()+")");
		System.out.println("Botl: "+"("+this.Botl.getX()+","+this.Botl.getY()+")");
		System.out.println("Botr: "+"("+this.Botr.getX()+","+this.Botr.getY()+")");
	}
	public int rectX(){
		return this.Topl.getX();
	}
	public int rectY(){
		return this.Topl.getY();
	}
}
