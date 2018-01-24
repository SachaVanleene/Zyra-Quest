package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.JComboBox;
/* Classe permettant de recuperer la diffculte que le joueur a choisi, pour ensuite la transmettre au jeu */
public class DiffucltyListener implements ActionListener {
	private JComboBox<String> jcb;
	FileOutputStream fw;
	OutputStreamWriter osw; 
	public DiffucltyListener(JComboBox<String> jcb) {
		this.jcb = jcb;
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			fw = new FileOutputStream("TestKeyListener/menu/difficulte.txt");
			osw  = new OutputStreamWriter(fw);
			osw.write("difficulte : " + this.jcb.getSelectedItem().toString());
			osw.close();
			fw.close();
		} catch (IOException f) {
			f.getStackTrace();
		}
	}

}
