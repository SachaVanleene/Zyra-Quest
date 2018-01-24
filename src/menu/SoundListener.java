package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.JComboBox;
/* implemente le niveau de son choisi par l'utilisateur*/
public class SoundListener implements ActionListener {

	private JComboBox<String> jcb;
	FileOutputStream fw;
	OutputStreamWriter osw; 
	public SoundListener(JComboBox<String> jcb) {
		this.jcb = jcb;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			fw = new FileOutputStream("TestKeyListener/menu/soundlevel.txt");
			osw  = new OutputStreamWriter(fw);
			osw.write("difficulte : " + this.jcb.getSelectedItem().toString());
			osw.close();
			fw.close();
		} catch (IOException f) {
			f.getStackTrace();
		}
	}
}
