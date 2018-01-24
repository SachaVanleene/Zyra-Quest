package menu;

import javax.swing.JFrame;
import javax.swing.JPanel;
/* JFrame principal dans laquelle les differents JPannel seront mis*/
public class Main extends JFrame {
	public Main(JPanel menu) {
		java.awt.Window win[] = java.awt.Window.getWindows(); 
		for(int i=0;i<win.length;i++){ 
			win[i].dispose(); 
		} 
		this.setTitle("Menu");
		if (menu.getClass() == (new jeu.Panneau()).getClass()) {
			this.setTitle("Jeu");
		}
		
		this.setSize(800, 600);
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(menu);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

	public static void main(String[] args){
		new Main(new MenuPrincipal());
	}

}
