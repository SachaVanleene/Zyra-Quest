package jeu;

import java.util.ArrayList;

public class Salle5 implements Salle {
	private String fond = new String("src/BackGround/salle5.gif");
	private int Spawnx;
	private int Spawny;
	private Hitbox_porte porte = new Hitbox_porte(710,280,10,10); // ACHANGER 
	private ArrayList<Ennemie> ennemie; // liste des ennemies
	private ArrayList<Objet> Objet; // Liste des objets

	// faire liste obstacle + objet ici 
	public Salle5(){ // de meme pour objetc obstacle
		this.Spawnx=125;
		this.Spawny=285;
		MageG mage = new MageG(600,100);
		MageG mage2= new MageG(600,450);
		Pomme pomme=new Pomme(330,135);
		Pomme pomme2=new Pomme(330,475);
		Pomme pomme3=new Pomme(640,300);
		this.ennemie=new ArrayList<Ennemie>();
		ennemie.add(mage);
		ennemie.add(mage2);
		this.Objet=new ArrayList<Objet>();
		this.Objet.add(pomme);
		this.Objet.add(pomme2);
		this.Objet.add(pomme3);

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
		retour=(dy<=480)&&(dy>=90)&&(dx>=120)&&(dx<=665);
		if(dy<=300&&dy>=255){
			retour=(dx>120 && dx<= 700);
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
		boolean retour;
		retour=false;
		if(dy>=150&&dy<=410){
			retour=(dx>230&&dx<415);
		}
		if((dy>140&&dy<210)||(dy>335&&dy<410)){
			retour=(dx>480);
		}
		return retour;
	}

}
