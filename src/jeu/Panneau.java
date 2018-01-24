package jeu;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.BasicStroke;
import java.awt.Color;

import java.awt.Image;

import java.io.File;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import menu.Main;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


	public class Panneau extends JPanel implements KeyListener {
		/**
		 * 
		 */
		/**
		 * 
		 */
		private Joueur joueur=new Joueur(); // Utilise les attributs de cette classe pour savoir ce qu'il faut paint 
		private String image = new String("src/Face/ImmobileInv.png");
		private int px;
		private int py;
		private Graphics g ; 
		private boolean victoire; // savoir si le joueur a gagné 
		private boolean Face,Dos,Droite,Gauche; //boolean pour gerer les animations
		private boolean Jdroite,Jgauche,Immobile;
		public Panneau(){ //sans sauvegarde
			this.victoire=false;
			addKeyListener(this) ;
			this.Face=true;
			this.Immobile=true;
			this.Dos=false;
			this.Droite=false;
			this.Gauche=false;
			this.Jdroite=false;
			this.Jgauche=false;
		}
		/*public Panneau(Joueur joueur){ //avec sauvegarde
		this.joueur=joueur;
		addKeyListener(this) ;
		this.Face=true;
		this.Immobile=true;
		this.Dos=false;
		this.Droite=false;
		this.Gauche=false;
		this.Jdroite=false;
		this.Jgauche=false;
		px=this.joueur.getPx()-15; // position du joueur en prenant compte le drawImage et la taille du perso
		py=this.joueur.getPy()-24;
	} */
		public void paintComponent(Graphics g){

			//System.out.println("VIE DU JOUEUR"+this.joueur.getVie());
			if(this.joueur.getVie()>0 && !this.victoire){
				try {
					Image img = ImageIO.read(new File(this.joueur.getSalle().getFond()));
					g.drawImage(img, 0, 0, 800, 600, this);
	
					Image img2 = ImageIO.read(new File(this.image));
					g.drawImage(img2, this.joueur.getX()-15,this.joueur.getY()-24,30,48, this); // on centre le placement de l'image au centre de gravite du personnage
					//HITBOX JOUEUR
					g.drawRect(this.joueur.getHitbox().rectX(), this.joueur.getHitbox().rectY(), 30, 48); //HITBOX JOUEUR
					// g.drawRect(710,300,10,10); //HITBOX TESTR PORTE
					
					// Affichage HUD
					Color c = new Color(255,0,0); //ROUGE
					Color c1 = new Color(0,0,0); //NOIR
					g.setColor(c1);
					g.drawRect(0, 0, 300, 15);
					g.setColor(c);
					g.fillRect(0, 0, 3*this.joueur.getVie(), 15);
					g.setColor(c1);
					// AFFICHAGE ATTAQUE JOUEUR
					for(int k = 0; k < this.joueur.getNbAttaque(); k++) {
						AttaqueJoueur atk = this.joueur.getAttaque(k);
						Image img4 = ImageIO.read(new File(atk.getImage())); //image attaque
						g.drawImage(img4, atk.getX()-atk.getAttaquex(), atk.getY()-atk.getAttaquey(), 2*atk.getAttaquex(), 2*atk.getAttaquey(), this);
						g.drawRect(atk.getHitbox().rectX(), atk.getHitbox().rectY(),2*atk.getAttaquex() , 2*atk.getAttaquey()); //HITBOX attaque
					}
					//CHECK ATTAQUE
					this.joueur.checkAttaque();
					// UPDATE TRANSITION DE SALLE
					ArrayList<Ennemie> ennemi2e= this.joueur.getSalle().getEnnemie();
					if(!this.joueur.derniereSalle()) { // si il reste une salle
						if(ennemi2e.size()<=0){
							if (this.joueur.getSalle().getPorte().Collision(this.joueur.getHitbox())){
								this.joueur.getSalle().majHitbox();
								this.joueur.setX(this.joueur.getSallesuivante().getSpawnx());
								this.joueur.setY(this.joueur.getSallesuivante().getSpawny());
								this.joueur.majHitbox();
								this.joueur.augmenterSalle();
							} else {
								if(this.joueur.getHitbox().Collision(this.joueur.getSalle().getPorte())){
									this.joueur.getSalle().majHitbox();
									this.joueur.setX(this.joueur.getSallesuivante().getSpawnx());
									this.joueur.setY(this.joueur.getSallesuivante().getSpawny());
									this.joueur.majHitbox();
									this.joueur.augmenterSalle();
								}
							}
						}
					} else { // si c'est la derniere salle
						if(ennemi2e.size()<=0){
							if (this.joueur.getSalle().getPorte().Collision(this.joueur.getHitbox())){
								this.victoire=true;
							} else {
								if(this.joueur.getHitbox().Collision(this.joueur.getSalle().getPorte())){
									this.victoire=true;
								}
							}
						}
					}
					//Les objets
					ArrayList<Objet> Objet=this.joueur.getSalle().getObjet();
					for(int m=0;m<Objet.size();m++){
						Objet Ocourrant=Objet.get(m);
						Image Obj=ImageIO.read(new File(Ocourrant.getSkin()));
						// HITBOX 
						g.drawRect(Ocourrant.getHitbox().rectX(), Ocourrant.getHitbox().rectY(), 2*Ocourrant.getHitbox_x(),2*Ocourrant.getHitbox_y());
						g.drawImage(Obj, Ocourrant.getPosition().getX()-Ocourrant.getHitbox_x(), Ocourrant.getPosition().getY()-Ocourrant.getHitbox_y(), 2*Ocourrant.getHitbox_x(), 2*Ocourrant.getHitbox_y(), this);
						if (Ocourrant.getHitbox().Collision(this.joueur.getHitbox())){
							System.out.println("Collision");
							Ocourrant.effet(this.joueur);
							Ocourrant.majHitbox(); //METTRE A JOUR LA HITBOX OBJET
							Objet.remove(m); //ENELEVER OBJET
						} else {
							if(this.joueur.getHitbox().Collision(Ocourrant.getHitbox())){
								System.out.println("Collision");
								Ocourrant.effet(this.joueur);
								Ocourrant.majHitbox(); //METTRE A JOUR LA HITBOX OBJET
								Objet.remove(m); //ENELEVER OBJET
							}
						}
					}
					// LES ENNEMIES
					ArrayList<Ennemie> ennemie= this.joueur.getSalle().getEnnemie();
					for(int j=0;j<ennemie.size();j++){ //AFFICHAGE + UPDATE ENNEMIE
						Ennemie Ecourant = ennemie.get(j);
						if(Ecourant.getVie()>0){
							// AFFICHAGE
							Image img3 = ImageIO.read(new File(Ecourant.image()));
							g.drawImage(img3, Ecourant.getX()-Ecourant.getHitbox_x(), Ecourant.getY()-Ecourant.getHitbox_y(), Ecourant.getImagex(), Ecourant.getImagey(), this);
							// HITBOX
							g.drawRect(Ecourant.getHitbox().rectX(), Ecourant.getHitbox().rectY(), 2*Ecourant.getHitbox_x(), 2*Ecourant.getHitbox_y()); //HITBOX ENNEMIE
							// AFFICHAGE HUD ENNEMIE
							g.setColor(c1);
							g.drawRect(Ecourant.getX()-2*Ecourant.getHitbox_x(), Ecourant.getY()-2*Ecourant.getHitbox_y(), 100, 3);
							g.setColor(c);
							g.fillRect(Ecourant.getX()-2*Ecourant.getHitbox_x(), Ecourant.getY()-2*Ecourant.getHitbox_y(), (int)Math.floor(100*((float)Ecourant.getVie()/(float)Ecourant.getViemax())), 3);
							g.setColor(c1);
							// UPDATE
							/* if (Ecourant.getHitbox().Collision(this.joueur.getHitbox())){
								System.out.println("Ca marche !!!");
							} else {
								if(this.joueur.getHitbox().Collision(Ecourant.getHitbox())){
									System.out.println("Ca marche !!!");
								}
							} */
							Ecourant.preDeplacement();
							Ecourant.deplacer();
							Ecourant.majHitbox(); //mise a jour de la hitbox si l'ennemie se deplace
							Ecourant.attaquer(this.joueur.getX(),this.joueur.getY());
							Ecourant.checkAttaque();
							for(int k = 0; k < Ecourant.getNbAttaque(); k++) { // ATTAQUE ENNEMIE PAR RAPPORT AU JOUEUR
								// UPDATE
								AttaqueDistance atk = Ecourant.getAttaque(k);
								if (atk.getHitbox().Collision(this.joueur.getHitbox())){
									this.joueur.setVie(atk.getDegat());
									//System.out.println("VIE DU JOUEUR "+this.joueur.getVie());
									atk.setHitbox();
								} else {
									if(this.joueur.getHitbox().Collision(atk.getHitbox())){
										this.joueur.setVie(atk.getDegat());
										//System.out.println("VIE DU JOUEUR "+this.joueur.getVie());
										atk.setHitbox();
									}
								}
								// AFFICHAGE
								//HITBOX
								g.drawRect(atk.getHitbox().rectX(), atk.getHitbox().rectY(), 2*atk.getHitbox_x(), 2*atk.getHitbox_y()); //HITBOX attaque
								Image img4 = ImageIO.read(new File(atk.getImage())); //image attaque
								g.drawImage(img4, atk.getX()-atk.getHitbox_x(), atk.getY()-atk.getHitbox_y(), atk.getImagex(), atk.getImagey(), this);
							}
							for(int k = 0; k < this.joueur.getNbAttaque(); k++) { //ATTAQUE JOUEUR PAR RAPPORT AUX ENNEMIES
								// UPDATE
								AttaqueJoueur atk = this.joueur.getAttaque(k);
								if (atk.getHitbox().Collision(Ecourant.getHitbox())){
									Ecourant.setVie(atk.getDegat());
									atk.setHitbox();
								} else {
									if(Ecourant.getHitbox().Collision(atk.getHitbox())){
										Ecourant.setVie(atk.getDegat());
										atk.setHitbox();
									}
								}
							}
						} else { // On supprime l'ennemie tout en l'animant avant
							if(Ecourant.getMort()){
								Ecourant.animMort(); //ANIMATION DE MORT
								Image img3 = ImageIO.read(new File(Ecourant.image()));
								g.drawImage(img3, Ecourant.getX()-Ecourant.getHitbox_x(), Ecourant.getY()-Ecourant.getHitbox_y(), Ecourant.getImagex(), Ecourant.getImagey(), this);
							} else {
								ennemie.remove(j);
							}
						}
					}
					//TimeUnit.MILLISECONDS.sleep(100);
	
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					
				} 
				repaint();
			} else { // FIN DU GAME 
				try {
					String img = new String();
					if(this.victoire){
						img="src/BackGround/victoire.png";
					} else {
						img="src/BackGround/game_over.png";
					}
					Image img5 = ImageIO.read(new File(img));
					g.drawImage(img5, 0, 0, 800, 600, this);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		public void addNotify(){
			super.addNotify();
			requestFocus();
		}
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_Y && this.joueur.getVie() <= 0) {
				new menu.Main(new jeu.Panneau());
			}
			if (e.getKeyCode() == KeyEvent.VK_R && this.joueur.getVie() <= 0) {
				new menu.Main(new menu.MenuPrincipal());
			}
			if (e.getKeyCode() == KeyEvent.VK_Y && this.victoire) {
				new menu.Main(new jeu.Panneau());
			}
			if (e.getKeyCode() == KeyEvent.VK_R && this.victoire) {
				new menu.Main(new menu.MenuPrincipal());
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN){
				System.out.println("POSITION DU PERSONNAGE"+" ("+this.joueur.getX()+","+this.joueur.getY()+")");
				if(this.joueur.getSalle().posPossible(this.joueur.getX(), this.joueur.getY()+5)){
					this.joueur.addY(5);
					this.joueur.majHitbox();
					this.Dos=false; // on met les autre booleen a faux caro n ne connais pas l'origine du mopuvement
					this.Droite=false;
					this.Gauche=false;
					if (this.Face){ //si il est deja vers la face
						if(Immobile){
							this.Immobile=false;
							this.image="src/Face/JdroiteInv.png";
							this.Jdroite=true;
						} else {
							if(Jdroite){
								this.Jdroite=false;
								this.image="src/Face/JgaucheInv.png";
								this.Jgauche=true;
							} else {
								this.Jgauche=false;
								this.image="src/Face/ImmobileInv.png";
								this.Immobile=true;
							}
						}
					} else { //s'il vient d'une autre direction on le fait avancer
						this.joueur.setDirection(2);
						this.Jdroite=true; // on le fait avancer directement
						this.Jgauche=false;
						this.Immobile=false;
						this.image="src/Face/JdroiteInv.png";	
						this.Face=true;
					}
					//this.paintComponent(g);
					if(this.joueur.getSalle().trou(this.joueur.getX(), this.joueur.getY())){
						this.joueur.setX(this.joueur.getSalle().getSpawnx());
						this.joueur.setY(this.joueur.getSalle().getSpawny());
						this.joueur.setVie(30);
					}
				} else {
					//this.paintComponent(g); // on ne fait rien limite atteinte
				}
				if(this.joueur.getSalle().trou(this.joueur.getX(), this.joueur.getY())){
					this.joueur.setX(this.joueur.getSalle().getSpawnx());
					this.joueur.setY(this.joueur.getSalle().getSpawny());
					this.joueur.setVie(30);
				}
			}
			else if(e.getKeyCode() == KeyEvent.VK_UP) {
				System.out.println("POSITION DU PERSONNAGE"+" ("+this.joueur.getX()+","+this.joueur.getY()+")");
				if (this.joueur.getSalle().posPossible(this.joueur.getX(), this.joueur.getY()-5)){
					this.joueur.addY(-5);
					this.joueur.majHitbox();
					this.Face=false; // on met les autre booleen a faux car on ne connait pas l'origine du mouvement
					this.Droite=false;
					this.Gauche=false;
					if (this.Dos){ //s'il est deja vers la face
						if(Immobile){
							this.Immobile=false;
							this.image="src/Dos/JdroiteInv.png";
							this.Jdroite=true;
						} else {
							if(Jdroite){
								this.Jdroite=false;
								this.image="src/Dos/JgaucheInv.png";
								this.Jgauche=true;
							} else {
								this.Jgauche=false;
								this.image="src/Dos/ImmobileInv.png";
								this.Immobile=true;
							}
						}
					} else { //si il vient d'une autre direction on le fait avancer
						this.joueur.setDirection(1);
						this.Jdroite=true; // on le fait avancer directement
						this.Jgauche=false;
						this.Immobile=false;
						this.image="src/Dos/JdroiteInv.png";	
						this.Dos=true;
					}
					//this.paintComponent(g);
				} else {
					//this.paintComponent(g);
				}
				if(this.joueur.getSalle().trou(this.joueur.getX(), this.joueur.getY())){
					this.joueur.setX(this.joueur.getSalle().getSpawnx());
					this.joueur.setY(this.joueur.getSalle().getSpawny());
					this.joueur.setVie(30);
				}
			}
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if(this.joueur.getSalle().posPossible(this.joueur.getX()+5, this.joueur.getY())){
					System.out.println("POSITION DU PERSONNAGE"+" ("+this.joueur.getX()+","+this.joueur.getY()+")");
					this.joueur.addX(5);
					this.joueur.majHitbox();
					this.Face=false; // on met les autres booleens a faux car on ne connait pas l'origine du mouvement
					this.Dos=false;
					this.Gauche=false;
					if (this.Droite){ //s'il est deja vers la face
						if(Immobile){
							this.Immobile=false;
							this.image="src/Droit/JdroiteInv.png";
							this.Jdroite=true;
						} else {
							if(Jdroite){
								this.Jdroite=false;
								this.image="src/Droit/JgaucheInv.png";
								this.Jgauche=true;
							} else {
								this.Jgauche=false;
								this.image="src/Droit/ImmobileInv.png";
								this.Immobile=true;
							}
						}
					} else { //s'il vient d'une autre direction on le fait avancer
						this.joueur.setDirection(4);
						this.Jdroite=true; // on le fait avancer directement
						this.Jgauche=false;
						this.Immobile=false;
						this.image="src/Droit/JdroiteInv.png";	
						this.Droite=true;
					}
					//this.paintComponent(g);
				} else {
					//this.paintComponent(g);
				}
				if(this.joueur.getSalle().trou(this.joueur.getX(), this.joueur.getY())){
					this.joueur.setX(this.joueur.getSalle().getSpawnx());
					this.joueur.setY(this.joueur.getSalle().getSpawny());
					this.joueur.setVie(30);
				}
			}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				if(this.joueur.getSalle().posPossible(this.joueur.getX()-5, this.joueur.getY())){
					System.out.println("POSITION DU PERSONNAGE"+" ("+this.joueur.getX()+","+this.joueur.getY()+")");
					this.joueur.addX(-5);
					this.joueur.majHitbox();
					this.Face=false; // on met les autres booleens a faux car on ne connait pas l'origine du mouvement
					this.Dos=false;
					this.Droite=false;
					if (this.Gauche){ //s'il est deja vers la face
						if(Immobile){
							this.Immobile=false;
							this.image="src/Gauche/JdroiteInv.png";
							this.Jdroite=true;
						} else {
							if(Jdroite){
								this.Jdroite=false;
								this.image="src/Gauche/JgaucheInv.png";
								this.Jgauche=true;
							} else {
								this.Jgauche=false;
								this.image="src/Gauche/ImmobileInv.png";
								this.Immobile=true;
							}
						}
					} else { //s'il vient d'une autre direction on le fait avancer
						this.joueur.setDirection(3);
						this.Jdroite=true; // on le fait avancer directement
						this.Jgauche=false;
						this.Immobile=false;
						this.image="src/Gauche/JdroiteInv.png";	
						this.Gauche=true;
					}
					//this.paintComponent(g);
				} else {
					//this.paintComponent(g);
				}
				if(this.joueur.getSalle().trou(this.joueur.getX(), this.joueur.getY())){
					this.joueur.setX(this.joueur.getSalle().getSpawnx());
					this.joueur.setY(this.joueur.getSalle().getSpawny());
					this.joueur.setVie(30);
				}
			}
			else if(e.getKeyCode() == KeyEvent.VK_SPACE){
				this.joueur.attaquer();
			}
			//this.repaint();	
		}
		public void keyReleased(KeyEvent e) {

		}
		public void keyTyped(KeyEvent e) {


		}
		public void setteX(int dx){
			this.px=dx;
		}
		public int getteX(){
			return this.px;
		}


	}