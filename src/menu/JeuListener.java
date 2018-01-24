package menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/* Classe permettant de demarrer le jeu */
public class JeuListener implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
		new Main(new jeu.Panneau());
		
	}

}
