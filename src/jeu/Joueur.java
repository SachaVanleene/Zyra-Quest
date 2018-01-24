package jeu;
import java.util.ArrayList;

 public class Joueur {
	private int px ,py; //position du joueur
	private int vie; // vie du joueur 
	private int Hitbox_x=15; //CaractÃ©ristique necessaire a la construction de la Hitbox
	private int Hitbox_y=24;
	private Hitbox_joueur hitbox;
	private int Hitbox_attaquex = 5; // hitbox attaque de base
	private int Hitbox_attaquey = 5;
	private int NiveauCourant; // salle ou se trouve le joueur
	private ArrayList<Salle> listesalle = new ArrayList<Salle>(); //liste des salles
	private ArrayList<AttaqueJoueur> listeattaque = new ArrayList<AttaqueJoueur> (); //liste des attaques
	private int numeroSalle;
	private String imageattaque; // image de l'attaque du joueur
	private int direction; //direction du joueur 1 HAUT 2 BAS 3 GAUCHE 4 DROITE
	private int degats = 10; //attaque joueur de base 
	private int nbattaque; //nb d'atatque autorisé
	public Joueur(){
			this.nbattaque=10;
			this.imageattaque="src/Attaque/Attaquefaible.png";
			this.direction =2;
			this.numeroSalle=0;
			Salle Salle1 = new Salle1(); //crÃ©ation de la liste de salle , joueur dans constructeur si présence d'objet !!
			Salle Salle2 = new Salle2();
			Salle Salle3 = new Salle3();
			Salle Salle4= new Salle4();
			Salle Salle5= new Salle5();
			Salle Salle6=new Salle6();
			Salle SalleBoss=new SalleBoss();
			this.listesalle.add(Salle1);
			this.listesalle.add(Salle2);
			this.listesalle.add(Salle3);
			this.listesalle.add(Salle4);
			this.listesalle.add(Salle5);
			this.listesalle.add(Salle6);
			this.listesalle.add(SalleBoss);
			this.vie=100;
			this.px=150; //Spawn dans la première salle
			this.py=300;
			this.hitbox = new Hitbox_joueur(this.px-this.Hitbox_x,this.py-this.Hitbox_y,this.Hitbox_x,this.Hitbox_y);
	}
	public int getNiveau(){
		return this.NiveauCourant;
	}
	
	public Salle getSalle(){
		return this.listesalle.get(this.numeroSalle);
	}
	public Salle getSallesuivante(){
		return this.listesalle.get(this.numeroSalle+1);
	}
	public int getVie(){
		return this.vie;
	}
	public int getX(){
		return this.px;
	}
	public int getY(){
		return this.py;
	}
	public void setX(int px){
		this.px=px;
	}
	public void setY(int py){
		this.py=py;
	}
	public void setVie(int degat){
		this.vie=this.vie-degat;
	}
	public void addX(int px){
		this.px=this.px+px;
	}
	public void addY(int py){
		this.py=this.py+py;
	}
	
	public void majHitbox(){
		this.hitbox = new Hitbox_joueur(this.px,this.py,this.Hitbox_x,this.Hitbox_y);
	}
	public Hitbox getHitbox(){
		return this.hitbox;
	}
	public void augmenterSalle(){
		this.numeroSalle++;
	}
	public void setDirection(int direction){
		this.direction=direction;
	}
	public int getNbAttaque() {
		return this.listeattaque.size();
	}
	public AttaqueJoueur getAttaque(int k) {
		return this.listeattaque.get(k);
	}
	public void attaquer(){
		if (this.listeattaque.size() < this.nbattaque) { 
			AttaqueJoueur bdf = new AttaqueJoueur( this.direction, this.imageattaque, this.degats, this.px, this.py, this.Hitbox_attaquex, this.Hitbox_attaquey);
			this.listeattaque.add(bdf);
		} 
	}
	public void checkAttaque(){
		for (int k = 0; k < this.listeattaque.size(); k++) {
			if(this.listeattaque.get(k).getX()>800 || this.listeattaque.get(k).getY() > 600 || this.listeattaque.get(k).getX() < 0 || this.listeattaque.get(k).getY() < 0)  {
				this.listeattaque.remove(k);
			}
		}
		
	}
	public void modifierAttaque(String atk,int hx,int hy,int degat){
		this.imageattaque=atk;
		this.Hitbox_attaquey=hx;
		this.Hitbox_attaquey=hy;
		this.degats=degat;
	}
	public boolean derniereSalle(){
		return(this.numeroSalle==(this.listesalle.size()-1));
	}

	// FAIRE UNE METHODE QUI RENVOIE LA SALLE DANS LAQUELLE SE TROUVE LE PERSONNAGE EN FONCTION DEs ENTIER
}
