package jeu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
/* implemente toutes les attaques a distance */
public class AttaqueDistance implements ActionListener{
	private Timer tm = new Timer(20,this); /* temps entre chaque deplacement*/
	private String image; /* image de l'attaque */
	private int degats; /* degats de l'attaque */
	private int px; /* position courrante en x de l'attaque */
	private int py; /* position courrante en y de l'attaque */
	private boolean cible_en_haut; /* determine si la cible est en haut ou en bas */
	private boolean cible_a_gauche; /* determine si la cible est a gauche ou a droite */
	private int delta_x; /* ecart en x entre la position initiale et la position de la cible*/
	private int delta_y; /* ecart en y entre la position initiale et la position de la cible */
	private int vitesse; /* vitesse de deplacement en nombre de pixels par unite de timer */
	private int compteur; /* permet de gerer le coefficient directeur */
	private boolean delta_sup; /* permet de gerer le ratio du coefficient directeur */
	private Hitbox_attaquedistance hitbox; /* hitbox de l'attaque */
	private boolean hitboxreel; /* permet de gerer la disparation de l'attaque apres collision*/
	private int h_x; //caractéristique de la hitbox
	private int h_y;
	private  int imagex; // caractéristique de l'image a affiché
	private int imagey;

	public AttaqueDistance(int degats, String image,int x_d, int y_d, int x_a, int y_a,int h_x,int h_y,int imagex,int imagey) {
		this.imagex=imagex;
		this.imagey=imagey;
		this.h_x=h_x;
		this.h_y=h_y;
		this.hitboxreel=true;
		this.degats = degats;
		this.image = image;
		this.vitesse = 5;
		this.px = x_d;
		this.py = y_d;
		this.hitbox = new Hitbox_attaquedistance(px,py,h_x,h_y);
		if (x_d < x_a) {
			this.cible_a_gauche = false;
		} else {
			this.cible_a_gauche = true;
		}
		if (y_d < y_a) {
			this.cible_en_haut = false;
		} else {
			this.cible_en_haut = true;
		}
		this.delta_x = Math.abs(x_d - x_a);
		this.delta_y = Math.abs(y_d - y_a);
		if(this.delta_x / this.delta_y > 1 ){
			this.delta_sup = true;
			this.compteur = this.delta_x/this.delta_y;
		} else  {
			this.delta_sup = false;
			this.compteur = this.delta_y/this.delta_x;
		}

		tm.start();

	}
	/* actionPerfomed va permettre a chaque tick de timer de faire se deplacer l'attaque dans la bonne direction
	 * avec la vitesse donnee en attribut
	 */
	public void actionPerformed(ActionEvent e) {
		if (this.delta_x / this.delta_y == 1) {
			if (!this.cible_en_haut) {
				if(this.cible_a_gauche) {
					this.px = this.px - this.vitesse;
				} else {
					this.px = this.px + this.vitesse;
				}
			this.py = this.py + this.vitesse;
			} else {
				if(this.cible_a_gauche) {
					this.px = this.px - this.vitesse;
				} else {
					this.px = this.px + this.vitesse;
				}
				this.py = this.py - this.vitesse;
			}
			
		}else if (this.delta_sup) {
			if (this.cible_a_gauche) {
				if (this.compteur != 0) {
					this.px = this.px - this.vitesse;
					this.compteur --;
				} else {
					if (this.cible_en_haut) {
						this.py = this.py - this.vitesse;
					} else {
						this.py = this.py + this.vitesse;
					}
					this.compteur = this.delta_x/this.delta_y;
				}
			} else {
				if (this.compteur != 0) {
					this.px = this.px + this.vitesse;
					this.compteur --;
				} else {
					if (this.cible_en_haut) {
						this.py = this.py - this.vitesse;
					} else {
						this.py = this.py + this.vitesse;
					}
					this.compteur = this.delta_x/this.delta_y;
				}
			}
		} else {
			if (this.cible_en_haut) {
				if (this.compteur != 0) {
					this.py = this.py - this.vitesse;
					this.compteur --;
				} else {
					if (this.cible_a_gauche) {
						this.px = this.px - this.vitesse;
					} else {
						this.px = this.px + this.vitesse;
					}
					this.compteur = this.delta_y/this.delta_x;
				} 
			} else {
				if (this.compteur != 0) {
					this.py = this.py + this.vitesse;
					this.compteur --;
				} else {
					if (this.cible_a_gauche) {
						this.px = this.px - this.vitesse;
					} else {
						this.px = this.px + this.vitesse;
					}
					this.compteur = this.delta_y/this.delta_x;
				}
			}
		}
		if(this.hitboxreel){
			this.majHitbox();
		}
	}

	public String getImage() {
		//System.out.println("DX "+this.delta_x+" DY "+this.delta_y);
		return this.image;
	}

	public int getX() {
		return this.px;
	}

	public int getY() {
		return this.py;
	}
	public Hitbox getHitbox(){
		return this.hitbox;
	}
	public int getHitbox_x(){
		return this.h_x;
	}
	public int getHitbox_y(){
		return this.h_y;
	}
	public int getDegat(){
		return this.degats;
	}
	public void majHitbox(){
		this.hitbox = new Hitbox_attaquedistance(this.px,this.py,this.h_x,this.h_y);
	}
	/* Permet de faire disparaite la hitbox et l'image apres collision */
	public void setHitbox(){
		this.hitboxreel=false;
		this.hitbox = new Hitbox_attaquedistance(3000,400,4,4);
		this.px=3000;
		this.py=400;
	}
	public int getImagex(){
		return this.imagex;
	}
	public int getImagey(){
		return this.imagey;
	}
}
