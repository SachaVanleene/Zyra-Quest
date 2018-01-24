package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/* implemente le bouton de retour au menu principal */
public class RetourPrincipalListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		new Main(new MenuPrincipal());

	}

}
