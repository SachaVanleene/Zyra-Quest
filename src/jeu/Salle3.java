package jeu;
import java.util.ArrayList;

public class Salle3 implements Salle{
		private String fond = new String("src/BackGround/Salle3.png");
		private int Spawnx;
		private int Spawny;
		private Hitbox_porte porte = new Hitbox_porte(400,550,10,10); // ACHANGER 
		private ArrayList<Ennemie> ennemie; // liste des ennemies
		private ArrayList<Objet> Objet;
		// faire liste obstacle + objet ici 
		public Salle3(){ // de meme pour objetc obstacle
			this.Spawnx=385;
			this.Spawny=100;
			Mage mage = new Mage(150,135);
			MageG mage2 = new MageG(650,300);
			Mage mage3 = new Mage(150,300);
			MageG mage4 = new MageG(650,450);
			Pomme pomme=new Pomme(650,150);
			Pomme pomme2=new Pomme(400,300);
			Pomme pomme3=new Pomme(150,450);
			Pomme pomme4=new Pomme(400,500);
			this.ennemie=new ArrayList<Ennemie>();
			this.Objet=new ArrayList<Objet>();
			this.ennemie.add(mage);
			this.ennemie.add(mage2);
			this.ennemie.add(mage3);
			this.ennemie.add(mage4);
			this.Objet.add(pomme);
			this.Objet.add(pomme2);
			this.Objet.add(pomme3);
			this.Objet.add(pomme4);
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
			if (retour){
				if (dy==160||dy==250){
					retour=(dx>595);
				}else{
					if(dy==325||dy==420){
						retour=(dx<220);
				

					}else{
						if(dx==210){
							retour=((dy<335)||(dy>405));
						}else{
							if (dx==595){
								retour=((dy<165)||(dy>245));
							}
						}
					}
				}
			}
			if (dy>=475 && dy<=530){
				retour=(dx>=390&&dx<=410);
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