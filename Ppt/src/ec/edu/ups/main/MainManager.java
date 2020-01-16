package ec.edu.ups.main;

import ec.edu.ups.controller.statemachine.StateManager;
import ec.edu.ups.tools.ControlManager;
import ec.edu.ups.view.Screen;
import ec.edu.ups.view.Window;

public class MainManager {

	private boolean working;
	private final String title;
	private final int width;
	private final int height;

	private Screen screen;
	private Window window;
	private StateManager stateManager;

	public MainManager(String title) {
		this.working = false;
		this.title = title;
		this.width = Constants.FULL_WIDTH_WINDOW;
		this.height = Constants.FULL_HEIGHT_WINDOW;
	}

	public void startGame() {
		working = true;
		start();
	}

	public void mainLoop() {
		int aps = 0;
		int fps = 0;
		int ups = 0;

		final int NS_PER_SECOND = 1000000000;
		final byte UPS_OBJECT = 60;
		final double NS_PER_UPDATES = NS_PER_SECOND / UPS_OBJECT;

		long updateReference = System.nanoTime();
		long countReference = System.nanoTime();

		double timeElapsed;
		double delta = 0;

		long loopStart;

		// requestFocus();
		while (working) {
			loopStart = System.nanoTime();

			timeElapsed = loopStart - updateReference;
			updateReference = loopStart;

			delta += timeElapsed / NS_PER_UPDATES;

			while (delta >= 1) {
				update();
				ups++;
				delta--;
			}

			print();
			fps++;

			if (System.nanoTime() - countReference > NS_PER_SECOND) {
				// setTitle(title + " | UPS: " + ups + " | FPS: " + fps);
				ups = 0;
				fps = 0;
				countReference = System.nanoTime();
			}
		}

	}

	private void start() {
		screen = new Screen(width, height);
		window = new Window(this.title, screen, width, height);
		stateManager = new StateManager();
	}

	private void update() {
		ControlManager.keyboard.update();
		stateManager.update();
	}

	private void print() {
		screen.print(stateManager);
	}

}
