package jeu;

import java.util.ArrayList;

public class Salle6 implements Salle{
	private String fond = new String("src/BackGround/Salle6.png");
	private int Spawnx;
	private int Spawny;
	private Hitbox_porte porte = new Hitbox_porte(415,90,10,10); // ACHANGER 
	private ArrayList<Ennemie> ennemie; // liste des ennemies
	private ArrayList<Objet> Objet;
	// faire liste obstacle + objet ici 
	public Salle6(){ // de meme pour objetc obstacle
		this.Spawnx=105;
		this.Spawny=295;
		this.ennemie=new ArrayList<Ennemie>();
		Pomme pomme=new Pomme(300,300);
		Pomme pomme2=new Pomme(350,300);
		Pomme pomme3=new Pomme(400,300);
		Pomme pomme4=new Pomme(450,300);
		Pomme pomme5=new Pomme(500,300);
		Pomme pomme6=new Pomme(550,300);
		Pomme pomme7=new Pomme(600,300);
		Pomme pomme8=new Pomme(600,250);
		Pomme pomme9=new Pomme(550,200);
		Pomme pomme10=new Pomme(500,150);
		Pomme pomme11=new Pomme(600,350);
		Pomme pomme12=new Pomme(550,400);
		Pomme pomme13=new Pomme(500,450);
		Bouleverte bv = new Bouleverte( 650,300);
		this.Objet=new ArrayList<Objet>();
		this.Objet.add(bv);
		this.Objet.add(pomme);
		this.Objet.add(pomme2);
		this.Objet.add(pomme3);
		this.Objet.add(pomme4);
		this.Objet.add(pomme5);
		this.Objet.add(pomme6);
		this.Objet.add(pomme7);
		this.Objet.add(pomme8);
		this.Objet.add(pomme9);
		this.Objet.add(pomme10);
		this.Objet.add(pomme11);
		this.Objet.add(pomme12);
		this.Objet.add(pomme13);
		

		//this.ennemie.add(mage);
	}
	public String getFond(){
		return this.fond;
	}
	public ArrayList<Ennemie> getEnnemie(){
		return this.ennemie;
	}
	public ArrayList<Objet> getObjet(){
		return this.Objet;
	}
	public boolean posPossible(int dx,int dy) {
		boolean retour =true;
		retour=(dy<=480)&&(dy>=90)&&(dx>=95)&&(dx<=710);
		if(dx<=435&&dx>=385){
			retour=(dy<480 && dy>= 100);
		}
		return retour;
	}
	
	public int getSpawnx(){
		return this.Spawnx;
	}
	public int getSpawny(){
		return this.Spawny;
	}
	public Hitbox getPorte(){
		return this.porte;
	}
	public void majHitbox(){
		this.porte = new Hitbox_porte(1000,1000,10,10);
	}
	@Override
	public boolean trou(int dx, int dy) {
		return false;
	}

}
