package menu;

import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/* implemente le menu des scores */
public class MenuScore extends JPanel{ 
	private static Dimension maxSize = new Dimension(400, 100);
	private JLabel jl;
	private JButton jb;
	
	public MenuScore(){
		String line;
		FileInputStream file;
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		jl = new JLabel("Meilleurs Scores");
		jl.setAlignmentX(CENTER_ALIGNMENT);
		jl.setFont(new Font("Serif", Font.PLAIN, 55));
		this.add(jl);
		
		try {
			file = new FileInputStream("src/menu/scores.txt");
			BufferedReader buff = new BufferedReader(new InputStreamReader(file));
			
			for (int i = 0; i<5; i++) {
				if ((line = buff.readLine()) != null) {
					jl = new JLabel(line);
					jl.setAlignmentX(CENTER_ALIGNMENT);
					jl.setFont(new Font("Serif", Font.PLAIN, 35));
					this.add(jl);
				}
			}
			buff.close();
		}
		catch (FileNotFoundException e) {
			jl = new JLabel(" Aucun Score pour le moment");
			jl.setAlignmentX(CENTER_ALIGNMENT);
			jl.setFont(new Font("Serif", Font.PLAIN, 35));
			this.add(jl);
			}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		jb = new JButton("Reset Scores");
		jb.addActionListener(new ResetScoreListener());
		jb.setAlignmentX(CENTER_ALIGNMENT);
		this.add(jb);
		
		jb = new JButton("Retour MenuPrincipal");
		jb.addActionListener(new RetourPrincipalListener());
		jb.setAlignmentX(CENTER_ALIGNMENT);
		this.add(jb);
		
		
		
		
		
	}
}
		
