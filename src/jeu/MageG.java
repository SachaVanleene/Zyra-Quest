package jeu;

import java.util.ArrayList;

public class MageG implements Ennemie {
	// ENNEMIE MAGE detecte l'aggro  mais ne bouge pas et n'envoie que des boules de feu
		public int vie=100;
		public int viemax=100;
		public int dommage=10; // dommage deal a chaque fois qu'il touche
		public int Imagex = 92; // taille maximal de l'image prise
		public int Imagey = 74;
		public int Hitbox_x = 28; //CaractÃ©ristique necessaiare a la construction de la Hitbox
		public int Hitbox_y = 37;
		private Hitbox_mage hitbox;
		public int px; // position 
		public int py;
		public int aggroX = 400;
		public int aggroY = 400;
		public int Vanimfixe = 5; // Vitesse d'animation 
		public int Vanimcourante;
		public boolean animMort;
		private ArrayList<AttaqueDistance> atk;
		public String image;
		public boolean attaque; //si entrain d'attaquer
		public int nattaque; //entier pour savoir ou on en est dans l'attaque
		private int nmort;
		public MageG (int spawnx,int spawny){
			this.nmort=1;
			this.animMort=true;
			this.px=spawnx;
			this.py=spawny;
			this.hitbox = new Hitbox_mage(px+Hitbox_x,py,Hitbox_x,Hitbox_y); //CENTRER SUR LAFFICHAGE /!\ décaler par rappor au MAGE  
			atk = new ArrayList<AttaqueDistance>();
			this.attaque=false;
			this.nattaque=0;
			this.image = new String("src/MageG/ImmobileInv.png");
		}
	public void attaquer (int px, int py) {
		AttaqueDistance bdf;
		//System.out.println("AggroX: "+Math.abs((this.px-(px)))+" Aggro y"+Math.abs((this.py-(py))));
			if(this.attaque){
				if(this.Vanimfixe==this.Vanimcourante){
					this.Vanimcourante=0;
					switch (nattaque){
						case 1:
							this.image = "src/MageG/Attaque2Inv.png";
							this.nattaque++;
							break;
						case 2 :
							this.image = "src/MageG/Attaque3Inv.png";
							this.nattaque++;
							break;
						case 3:
							this.image = "src/MageG/Attaque4Inv.png";
							this.nattaque++;
							break;
						case 4:
							this.image = "src/MageG/Attaque5Inv.png";
							this.nattaque++;
							break;
						case 5:
							this.image = "src/MageG/AttaquefinInv.png";
							if (this.atk.size() < 40) { //ATTRIBUT
								bdf = new AttaqueDistance(this.dommage,"src/MageG/Bouledefeu.png",this.px,this.py,px,py,4,4,8,8); // dernier parametre ceracrtéristique hitbox , caractéristique image
								this.atk.add(bdf);
							} 
							this.nattaque++;
							break;
						case 6:
							this.image = "src/MageG/ImmobileInv.png";
							this.attaque=false; //fin de l'attaque
							this.nattaque=0;
							break;
						default:		
					}
				} else {
					this.Vanimcourante++;
				}
					
			} else {
				if (Math.abs((this.px-(px)))<aggroX && Math.abs((this.py-(py)))<this.aggroY){
					//System.out.println("AggroX: "+Math.abs((this.px-(px)))+" Aggro y"+Math.abs((this.py-(py))));
					this.attaque=true;
					this.nattaque++;
					this.image = "src/MageG/Attaque1Invisible.png"; //debut de l'attaque
				}
			}
		
	}
	public void deplacer(){
		
	}
	public void animMort(){
		if(this.animMort){
			if(this.Vanimfixe==this.Vanimcourante){
				this.Vanimcourante=0;
				switch (nmort){
					case 1:
						this.nmort++;
						this.image="src/MageG/Mort1Inv.png";
					case 2:
						this.nmort++;
						this.image="src/MageG/Mort2Inv.png";
						break;
					case 3 :
						this.nmort++;
						this.image="src/MageG/Mort3Inv.png";
						break;
					case 4:
						this.nmort++;
						this.image="src/MageG/Mort4Inv.png";
						break;
					case 5:
						this.nmort++;
						this.image="src/MageG/Mort5Inv.png";
						this.animMort=false;
						break;
				}
			} else {
				this.Vanimcourante++;
			}
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
	}
	public int getVie(){
		return this.vie;
	}
	public int getViemax(){
		return this.viemax;
	}
	public void majHitbox(){
		// ne fait rien car le mage ne bouge pas
	}
	public void preDeplacement(){
		// // ne fait rien car le mage ne bouge pas
	}
	public void checkAttaque(){
		for (int k = 0; k < this.atk.size(); k++) {
			if(this.atk.get(k).getX()>800 || this.atk.get(k).getY() > 600 || this.atk.get(k).getX() < 0 || this.atk.get(k).getY() < 0)  {
				this.atk.remove(k);
			}
		}
	}

}

