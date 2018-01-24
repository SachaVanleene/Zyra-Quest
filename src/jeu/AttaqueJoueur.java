package jeu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class AttaqueJoueur implements ActionListener {

	private Timer tm = new Timer(20,this); /* temps entre chaque deplacement*/
	private int px; /* position courrante en x de l'attaque */
	private int py; /* position courrante en y de l'attaque */
	private int direction; //determine la direction de la boule
	private int degats;
	private int vitesse =5; /* vitesse de deplacement en nombre de pixels par unite de timer */
	private String image; /* image de l'attaque */
	private Hitbox_attaquedistance hitbox;
	private boolean hitboxreel; /* permet de gerer la disparation de l'attaque apres collision*/
	private int Hitbox_attaquex; // hitbox attaque de base
	private int Hitbox_attaquey;
	public AttaqueJoueur(int direction,String image,int degat,int xd,int yd,int hx,int hy){
		this.Hitbox_attaquex=hx;
		this.Hitbox_attaquey=hy;
		this.hitboxreel=true;
		this.direction=direction;
		this.px=xd;
		this.py=yd;
		this.degats=degat;
		this.image =image;
		this.hitbox = new Hitbox_attaquedistance(px,py,hx,hy); /* hitbox de l'attaque */ //
		tm.start();
	}
	public int getX() {
		return this.px;
	}

	public int getY() {
		return this.py;
	}
	public void majHitbox(){
		this.hitbox = new Hitbox_attaquedistance(this.px,this.py,this.Hitbox_attaquex,this.Hitbox_attaquey); // a modifier hitbox si attaque grossis
	}
	public void actionPerformed(ActionEvent e) {
		switch( this.direction){
			case 1: // HAUT
				this.py=this.py-this.vitesse;
				break;
			case 2: //BAS
				this.py=this.py+this.vitesse;
				break;
			case 3: //GAUCHE
				this.px=this.px-this.vitesse;
				break;
			case 4: //DROTIE
				this.px=this.px+this.vitesse;
				break;
			default:
		}
		if(this.hitboxreel){
			this.majHitbox();
		}
		
	}
	public Hitbox getHitbox(){
		return this.hitbox;
	}
	public int getDegat(){
		return this.degats;
	}
	public void setHitbox(){
		this.hitboxreel=false;
		this.hitbox = new Hitbox_attaquedistance(3000,400,4,4);
		this.px=3000;
		this.py=400;
	}
	public String getImage() {
		//System.out.println("DX "+this.delta_x+" DY "+this.delta_y);
		return this.image;
	}
	public int getAttaquex(){
		return this.Hitbox_attaquex;
	}
	public int getAttaquey(){
		return this.Hitbox_attaquey;
	}
	
}
