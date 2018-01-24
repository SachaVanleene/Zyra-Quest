package menu;
import javax.swing.Box;
import javax.swing.BoxLayout;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;
/* implemente le menu principal */
public class MenuPrincipal extends JPanel {
	
	private static Dimension maxSize = new Dimension(400, 100);
	private JButton jbJeu,jbScore,jbSauvegarde,jbOptions;
	public MenuPrincipal() {
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		this.add(Box.createRigidArea(new Dimension(400,25)));
		jbJeu = new JButton("Lancer Jeu");
		jbJeu.setMaximumSize(maxSize);
		jbJeu.setAlignmentX(CENTER_ALIGNMENT);
		jbJeu.addActionListener(new JeuListener());
		this.add(jbJeu);
		
		this.add(Box.createRigidArea(new Dimension(400,50)));
		jbScore = new JButton("Menu Score");
		jbScore.setMaximumSize(maxSize);
		jbScore.setAlignmentX(CENTER_ALIGNMENT);
		jbScore.addActionListener(new ScoreListener());
		this.add(jbScore);
		
		this.add(Box.createRigidArea(new Dimension(400,50)));
		jbSauvegarde = new JButton("Menu Sauvegarde");
		jbSauvegarde.setMaximumSize(maxSize);
		jbSauvegarde.setAlignmentX(CENTER_ALIGNMENT);
		jbSauvegarde.addActionListener(new SauvegardeListener());
		this.add(jbSauvegarde);
		
		this.add(Box.createRigidArea(new Dimension(400,50)));
		jbOptions = new JButton("Menu Options");
		jbOptions.setMaximumSize(maxSize);
		jbOptions.setAlignmentX(CENTER_ALIGNMENT);
		jbOptions.addActionListener(new OptionsListener());
		this.add(jbOptions);
		this.add(Box.createRigidArea(new Dimension(400,25)));


		
	}

}
