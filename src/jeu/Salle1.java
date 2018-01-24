package jeu;
import java.util.ArrayList;

public class Salle1 implements Salle {
		private String fond = new String("src/BackGround/salleDebut.png");
		private ArrayList<Ennemie> ennemie; // liste des ennemies
		private ArrayList<Objet> Objet;
		private int Spawnx;
		private int Spawny;
		private Hitbox_porte porte = new Hitbox_porte(710,300,10,10);
		// faire liste obstacle + objet ici 
		public Salle1(){ // de meme pour objetc obstacle
			this.Spawnx = 5; //inutile pour la prmeiere salle
			this.ennemie=new ArrayList<Ennemie>();
			this.Objet=new ArrayList<Objet>();
			MageG mage = new MageG(600,150);
			MageG mage2= new MageG(600,450);
			Pomme pome=new Pomme(640,300);
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
		public boolean posPossible(int dx,int dy){
			Boolean resu =true;
			if(285<=dy && dy<=300 && dx>300 && dx<=700){
				return resu;
			}
			if(dx<120 || dx>665 || dy<90 || dy>480){
				resu=false;
				return resu;
			}
			return resu;
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
