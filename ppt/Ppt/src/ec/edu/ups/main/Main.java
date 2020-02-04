package ec.edu.ups.main;

import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {
    	try {
//        	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO: handle exception
		}
	MainManager main = new MainManager("Paper, Rock and Scissors");
	main.startGame();
	main.mainLoop();
    }

}
