package menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/* implemente l'action du bouton des options */
public class OptionsListener implements ActionListener {

	
	public void actionPerformed(ActionEvent e) {
		new Main(new MenuOptions());
		
	}

}
