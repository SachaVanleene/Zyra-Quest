package menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/* implemente le bouton de menu des scores */
public class ScoreListener implements ActionListener{

	
	public void actionPerformed(ActionEvent e) {
		new Main(new MenuScore());
		
	}
	

}
