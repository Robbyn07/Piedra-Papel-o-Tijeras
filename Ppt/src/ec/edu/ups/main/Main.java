package ec.edu.ups.main;

public class Main {

	public static void main(String[] args) {

		MainManager main = new MainManager("Paper, Rock and Scissors", 640, 360);
		main.startGame();
		main.mainLoop();

	}

}
