package jeu;

import java.util.ArrayList;

public class Salle4 implements Salle {
	private String fond = new String("src/BackGround/Salle4.jpg");
	private int Spawnx;
	private int Spawny;
	private Hitbox_porte porte = new Hitbox_porte(710,285,10,10); // ACHANGER 
	private ArrayList<Ennemie> ennemie; // liste des ennemies
	private ArrayList<Objet> Objet;

	// faire liste obstacle + objet ici 
	public Salle4(){ // de meme pour objetc obstacle
		 this.Spawnx=400;
		 this.Spawny=95;
		 Mage mage = new Mage(100,300);
		 MageG mage2= new MageG(650,300);
		 Pomme pome=new Pomme(400,300);
		this.ennemie=new ArrayList<Ennemie>();
		this.Objet=new ArrayList<Objet>();
		this.ennemie.add(mage);
		this.ennemie.add(mage2);
		this.Objet.add(pome);
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
		return false;
	}
}
