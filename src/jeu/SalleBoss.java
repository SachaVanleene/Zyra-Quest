package jeu;

import java.util.ArrayList;

public class SalleBoss implements Salle{
	private String fond = new String("src/BackGround/Salleboss.png");
	private int Spawnx;
	private int Spawny;
	private Hitbox_porte porte = new Hitbox_porte(400,70,10,10); // ACHANGER 
	private ArrayList<Ennemie> ennemie; // liste des ennemies
	private ArrayList<Objet> Objet;
	// faire liste obstacle + objet ici 
	public SalleBoss(){ // de meme pour objetc obstacle
		this.Spawnx=400;
		this.Spawny=470;
		Dragon dragon = new Dragon (400,120);
		this.ennemie=new ArrayList<Ennemie>();
		this.ennemie.add(dragon);
		this.Objet=new ArrayList<Objet>();

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
		boolean retour = true;
		if(this.ennemie.size()<=0){
			retour=(dy<=475)&&(dy>=90);
			return retour;
		} else {
			retour = (dy<=475) && (dy>=280); // BLOQUER LE PASSAGE DU JOUEUR Pour pas qu'il ne rush le dragon
			return retour;
		}
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
		retour=(dx<185)||(dx>620);
		if(!retour){
			retour=((405-dx)*(405-dx)+(290-dy)*(290-dy)<=80*80);
		}
		return retour;
	}

}
