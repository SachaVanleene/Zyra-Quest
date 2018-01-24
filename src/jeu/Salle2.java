package jeu;
import java.util.ArrayList;

public class Salle2 implements Salle{
		private String fond = new String("src/BackGround/Salle2.png");
		private int Spawnx;
		private int Spawny;
		private Hitbox_porte porte = new Hitbox_porte(400,550,10,10); // ACHANGER 
		private ArrayList<Ennemie> ennemie; // liste des ennemies
		private ArrayList<Objet> Objet;
		// faire liste obstacle + objet ici 
		public Salle2(){ // de meme pour objetc obstacle
			 this.Spawnx=105;
			 this.Spawny=295;
			this.ennemie=new ArrayList<Ennemie>();
			this.Objet=new ArrayList<Objet>();
			MageG mage = new MageG(600,150);
			MageG mage2= new MageG(600,450);
			Pomme pome=new Pomme(400,500);
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
			retour=(dy<=480)&&(dy>=90)&&(dx>=95)&&(dx<=710);
			if(dx<=410&&dx>=400){
				retour=(dy>90 && dy<=530);
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