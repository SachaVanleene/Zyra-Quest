package menu;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
/* Implemente le menu des options */
public class MenuOptions extends JPanel{
	private static Dimension maxSize = new Dimension(400, 100);
	private JComboBox<String> jcb;
	private JLabel jl;
	private JButton jb;
	private static String[] diffculty = {"facile","moyen","difficile"};
	private static String[] sound = {"faible", "moyen", "fort"};
	public MenuOptions() {
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		this.jl = new JLabel("Difficulte :");
		this.jl.setAlignmentX(CENTER_ALIGNMENT);
		this.jl.setFont(new Font("Serif", Font.PLAIN, 35));
		this.add(jl);
		
		this.jcb = new JComboBox<String>(diffculty);
		this.jcb.setAlignmentX(CENTER_ALIGNMENT);
		this.jcb.setMaximumSize(maxSize);
		this.jcb.addActionListener(new DiffucltyListener(this.jcb));
		this.add(jcb);
		
		this.add(Box.createRigidArea(new Dimension(400,100)));
		
		this.jl = new JLabel("NIVEAU SONORE :");
		this.jl.setAlignmentX(CENTER_ALIGNMENT);
		this.jl.setFont(new Font("Serif", Font.PLAIN, 35));
		this.add(jl);
		
		this.jcb = new JComboBox<String>(sound);
		this.jcb.setAlignmentX(CENTER_ALIGNMENT);
		this.jcb.setMaximumSize(maxSize);
		this.jcb.addActionListener(new SoundListener(this.jcb));
		this.add(jcb);
		
		this.add(Box.createRigidArea(new Dimension(400,100)));
		this.jb = new JButton("Retour MenuPrincipal");
		this.jb.addActionListener(new RetourPrincipalListener());
		this.jb.setAlignmentX(CENTER_ALIGNMENT);
		this.add(jb);
		
	}

}
