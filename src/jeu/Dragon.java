package jeu;

import java.util.ArrayList;
import java.util.Random;

public class Dragon implements Ennemie {
	// ENNEMIE MAGE detecte l'aggro  mais ne bouge pas et n'envoie que des boules de feu
			public int vie=1000;
			public int viemax=1000;
			public int dommage=30; // dommage deal a chaque fois qu'il touche
			public int Imagex = 180; // taille maximal de l'image prise A CHANGER
			public int Imagey = 160;
			public int Hitbox_x = 55; //CaractÃ©ristique necessaiare a la construction de la Hitbox A CHANGER
			public int Hitbox_y = 45;
			private Hitbox_dragon hitbox; // A CHANGER
			public int px; // position 
			public int py;
			public int aggroX = 500; // A CHANGER
			public int aggroY = 500;
			public int Vanimfixe = 1; // Vitesse d'animation A CHANGER 
			public int Vanimcourante;
			public boolean animMort;
			private ArrayList<AttaqueDistance> atk;
			public String image;
			public boolean attaque; //si entrain d'attaquer
			public boolean deplacement; // si entrain de se deplacer
			public boolean atkordp; //savoir si on doit se depalcero u atatquer (pour ne pas interrompre les deux animations
			public int nattaque; //entier pour savoir ou on en est dans l'attaque
			private int nmort; // -------------------------------------- anim mort
			private int ndep; // --------------------------------------- deplacement
			private int choixdep; // 5 position  d'attaque attribut servant a determiné le prochain déplacement
			private Random random;
			private boolean droit; // savoir si le dragon est vers la drotie ou la gauche
			private boolean choixfait; // savoir si le choix du prochain deplacemnt a deja été fait
			private int nbattaque; //nb d'atatque autoriusée
			private boolean hardcoremode; // mode hardcore du dragon activé une fois ces hp diminué
			public Dragon (int spawnx,int spawny){
				this.hardcoremode=false;
				this.nbattaque=3;
				this.choixfait=true;
				this.random = new Random();
				this.choixdep=2;
				this.atkordp=false; // on se deplace avant d'attaquer
				this.ndep=1;
				this.droit=false;
				this.nmort=1;
				this.animMort=true;
				this.px=spawnx;
				this.py=spawny;
				this.hitbox = new Hitbox_dragon(px,py,Hitbox_x,Hitbox_y); //CENTRER SUR LAFFICHAGE /!\ // A MODIFIER
				atk = new ArrayList<AttaqueDistance>();
				this.attaque=false;
				this.nattaque=0;
				this.image = new String("src/Dragon/Immobile D/Immobile.png");
			}
		public void attaquer (int px, int py) {
			AttaqueDistance bdf;
			if(this.atkordp){
				if(this.attaque){
					if(this.Vanimfixe==this.Vanimcourante){
						this.Vanimcourante=0;
							if(this.droit){
								switch (nattaque){
									case 1:
										this.image = "src/Dragon/Attaque D/Attaque2.png";
										this.nattaque++;
										break;
									case 2 :
										this.image = "src/Dragon/Attaque D/Attaque3.png";
										this.nattaque++;
										if (this.atk.size() < this.nbattaque) { //ATTRIBUT
											bdf = new AttaqueDistance(this.dommage,"src/Dragon/Attaque D/FlammeD.png",this.px+this.Hitbox_x,this.py+this.Hitbox_y,px,py,27,50,55,100); // dernier parametre ceracrtéristique hitbox , caractéristique image
											this.atk.add(bdf);
										}
										// boule de feu créer ici
										break;
									case 3:
										this.image = "src/Dragon/Attaque D/Attaque4.png";
										this.nattaque++;
										break;
									case 4:
										this.image = "src/Dragon/Immobile D/Preimmobile.png";
										this.nattaque++;
										break;
									case 5:
										this.image = "src/Dragon/Immobile D/Immobile.png";
										this.attaque=false; //fin de l'attaque
										this.nattaque=0;
										this.atkordp = false; //fin du deplacement place a lattaque
										break;
									default:		
								}
							} else {
								switch (nattaque){
								case 1:
									this.image = "src/Dragon/Attaque G/Attaque2.png";
									this.nattaque++;
									break;
								case 2 :
									this.image = "src/Dragon/Attaque G/Attaque3.png";
									this.nattaque++;
									if (this.atk.size() < this.nbattaque) { //ATTRIBUT
										bdf = new AttaqueDistance(this.dommage,"src/Dragon/Attaque G/FlammeG.png",this.px,this.py+this.Hitbox_y,px,py,27,50,55,100); // dernier parametre ceracrtéristique hitbox , caractéristique image
										this.atk.add(bdf);
									}
									// boule de feu créer ici
									break;
								case 3:
									this.image = "src/Dragon/Attaque G/Attaque4.png";
									this.nattaque++;
									break;
								case 4:
									this.image = "src/Dragon/Immobile G/Preimmobile.png";
									this.nattaque++;
									break;
								case 5:
									this.image = "src/Dragon/Immobile G/Immobile.png";
									this.attaque=false; //fin de l'attaque
									this.nattaque=0;
									this.atkordp = false; //fin du deplacement place a lattaque
									break;
								default:		
								}
							}
					} else {
						this.Vanimcourante++;
					}
						
				} else {
					if (Math.abs((this.px-(px)))<aggroX && Math.abs((this.py-(py)))<this.aggroY){
						//System.out.println("AggroX: "+Math.abs((this.px-(px)))+" Aggro y"+Math.abs((this.py-(py))));
						this.attaque=true;
						this.nattaque++;
						if(this.droit){
							this.image = "src/Dragon/Attaque D/Attaque1.png"; //debut de l'attaque
						} else {
							this.image = "src/Dragon/Attaque G/Attaque1.png";
						}
					}
				}
			} else {
				// si ce n'est pas maintenat qu'il faut attaquer on ne le fait pas
			}
			
		}
		public void deplacer(){
			if(!this.atkordp){
					if(this.deplacement){
						if(this.droit){
							//System.out.println("JE PASSE ICI");
							if(this.Vanimfixe==this.Vanimcourante){
								this.Vanimcourante=0;
								switch (ndep){
									case 1:
										this.ndep++;
										this.image="src/Dragon/Deplacement D/Deplacement1.png";
										break;
									case 2:
										this.ndep++;
										this.image="src/Dragon/Deplacement D/Deplacement2.png";
										break;
									case 3 :
										this.ndep++;
										this.px=this.px+10;
										this.image="src/Dragon/Deplacement D/Deplacement3.png";
										break;
									case 4:
										this.ndep++;
										this.px=this.px+60;
										this.image="src/Dragon/Deplacement D/Deplacement4.png";
										break;
									case 5:
										this.ndep++;
										this.px=this.px+60;
										this.image="src/Dragon/Deplacement D/Deplacement5.png";
										break;
									case 6:
										this.ndep++;
										this.px=this.px+60;
										this.image="src/Dragon/Deplacement D/Deplacement6.png";
										break;
									case 7:
										this.ndep++;
										this.px=this.px+10; // A VOIR
										this.image="src/Dragon/Deplacement D/Deplacement7.png";
										break;
									case 8:
										this.ndep++;
										this.px=this.px+10;
										this.image="src/Dragon/Deplacement D/Deplacement8.png";
										break;
									case 9:
										this.ndep++;
										this.px=this.px+10;
										this.image="src/Dragon/Deplacement D/Deplacement9.png";
										break;
									case 10:
										this.ndep++;
										this.px=this.px+10;
										this.image="src/Dragon/Deplacement D/Deplacement10.png";
										break;
									case 11:
										this.ndep++;
										this.px=this.px+10;
										this.image="src/Dragon/Deplacement D/Deplacement11.png";
										break;
									case 12:
										this.ndep++;
										//this.px=this.px+5;
										this.image="src/Dragon/Deplacement D/Deplacement12.png";
										break;
									case 13:
										this.ndep++;
										//this.px=this.px+5;
										this.image="src/Dragon/Deplacement D/Deplacement13.png";
										this.ndep=0;
										this.deplacement=false;
										this.choixfait=false;
										this.choixdep++;
										this.atkordp=true;
										break;
									default:
									
								}
							} else {
								this.Vanimcourante++;
							}
						} else {
							//System.out.println("JE PASSE ICI");
							if(this.Vanimfixe==this.Vanimcourante){
								this.Vanimcourante=0;
								switch (ndep){
									case 1:
										this.ndep++;
										this.image="src/Dragon/Deplacement G/Deplacement1.png";
										break;
									case 2:
										this.ndep++;
										this.image="src/Dragon/Deplacement G/Deplacement2.png";
										break;
									case 3 :
										this.ndep++;
										this.px=this.px-10;
										this.image="src/Dragon/Deplacement G/Deplacement3.png";
										break;
									case 4:
										this.ndep++;
										this.px=this.px-60;
										this.image="src/Dragon/Deplacement G/Deplacement4.png";
										break;
									case 5:
										this.ndep++;
										this.px=this.px-60;
										this.image="src/Dragon/Deplacement G/Deplacement5.png";
										break;
									case 6:
										this.ndep++;
										this.px=this.px-60;
										this.image="src/Dragon/Deplacement G/Deplacement6.png";
										break;
									case 7:
										this.ndep++;
										this.px=this.px-10; // A VOIR
										this.image="src/Dragon/Deplacement G/Deplacement7.png";
										break;
									case 8:
										this.ndep++;
										this.px=this.px-10;
										this.image="src/Dragon/Deplacement G/Deplacement8.png";
										break;
									case 9:
										this.ndep++;
										this.px=this.px-10;
										this.image="src/Dragon/Deplacement G/Deplacement9.png";
										break;
									case 10:
										this.ndep++;
										this.px=this.px-10;
										this.image="src/Dragon/Deplacement G/Deplacement10.png";
										break;
									case 11:
										this.ndep++;
										this.px=this.px-10;
										this.image="src/Dragon/Deplacement G/Deplacement11.png";
										break;
									case 12:
										this.ndep++;
										//this.px=this.px+5;
										this.image="src/Dragon/Deplacement G/Deplacement12.png";
										break;
									case 13:
										this.ndep++;
										//this.px=this.px+5;
										this.image="src/Dragon/Deplacement G/Deplacement13.png";
										this.ndep=0;
										this.deplacement=false;
										this.choixdep--;
										this.choixfait=false;
										this.atkordp=true;
										break;
									default:
									
								}
							} else {
								this.Vanimcourante++;
							}
						}
					} else {
						this.ndep=1;
						this.deplacement=true;
					}
			} else {
				// ne rien faire on attend la fin de l'attaque
			}
		}
		public void animMort(){
			if(this.animMort){
				if(this.Vanimfixe==this.Vanimcourante){
					this.Vanimcourante=0;
						if(this.droit){
							switch (nmort){
								case 1:
									this.nmort++;
									this.image="src/Dragon/Mort D/Mort1.png";
								case 2:
									this.nmort++;
									this.image="src/Dragon/Mort D/Mort2.png";
									break;
								case 3 :
									this.nmort++;
									this.image="src/Dragon/Mort D/Mort3.png";
									break;
								case 4:
									this.nmort++;
									this.image="src/Dragon/Mort D/Mort4.png";
									break;
								case 5:
									this.nmort++;
									this.image="src/Dragon/Mort D/Mort5.png";
									break;
								case 6:
									this.nmort++;
									this.image="src/Dragon/Mort D/Mort6.png";
									this.animMort=false;
									break;
							}
						} else {
							switch (nmort){
							case 1:
								this.nmort++;
								this.image="src/Dragon/Mort G/Mort1.png";
							case 2:
								this.nmort++;
								this.image="src/Dragon/Mort G/Mort2.png";
								break;
							case 3 :
								this.nmort++;
								this.image="src/Dragon/Mort G/Mort3.png";
								break;
							case 4:
								this.nmort++;
								this.image="src/Dragon/Mort G/Mort4.png";
								break;
							case 5:
								this.nmort++;
								this.image="src/Dragon/Mort G/Mort5.png";
								break;
							case 6:
								this.nmort++;
								this.image="src/Dragon/Mort G/Mort6.png";
								this.animMort=false;
								break;
						}
						}
				} else {
					this.Vanimcourante++;
				}
			}
		}
		public void preDeplacement(){ // déplacement possibledu dragon  sur la carte 1 -- 2 -- 3 
			if(this.atkordp && !this.choixfait) {
				this.choixfait=true;
				switch (this.choixdep) {
					case 1:
						this.droit=true; // il faut forcément aller a droite
						break;
					case 2:
						this.droit=this.random.nextBoolean();
						break;
					case 3:
						this.droit=false;
						break;
					//System.out.println("Position du deplacement"+ this.choixdep);
				}
			} else {
				// ne rien faire attednre la fin du deplacement
			}
		}
		public boolean getMort(){
			return this.animMort;
		}
		public String image(){
			return this.image;
		}
		public int getX(){
			return this.px;
		}
		public int getY(){
			return this.py;
		}
		public int getImagex(){
			return this.Imagex;
		}
		public int getImagey(){
			return this.Imagey;
		}
		public int getHitbox_x(){
			return this.Hitbox_x;
		}
		public int getHitbox_y(){
			return this.Hitbox_y;
		}
		public Hitbox getHitbox(){
			return this.hitbox;
		}
		public int getNbAttaque() {
			return this.atk.size();
		}
		public AttaqueDistance getAttaque(int k) {
			return this.atk.get(k);
		}
		public void setVie(int degat){
			this.vie=this.vie-degat;
			if (this.vie<200 & !this.hardcoremode){
				this.nbattaque=10;
			}
		}
		public int getVie(){
			return this.vie;
		}
		public int getViemax(){
			return this.viemax;
		}
		public void majHitbox(){
			if(this.droit){
				this.hitbox = new Hitbox_dragon(px,py,Hitbox_x,Hitbox_y);
			} else {
				this.hitbox = new Hitbox_dragon(px+Hitbox_x,py,Hitbox_x,Hitbox_y);
			}
		}
		public void checkAttaque(){
			for (int k = 0; k < this.atk.size(); k++) {
				if(this.atk.get(k).getX()>800 || this.atk.get(k).getY() > 600 || this.atk.get(k).getX() < 0 || this.atk.get(k).getY() < 0)  {
					this.atk.remove(k);
				}
			}
		}
	
}
