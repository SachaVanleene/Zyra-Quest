package menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
/* implemente le bouton de reset des scores */
public class ResetScoreListener implements ActionListener{

	public void actionPerformed(ActionEvent arg0) {
		File file = new File("src/menu/scores.txt");
		file.delete();
		new Main(new MenuScore());
		
		
	}
	

}
