package ec.edu.ups.main;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import ec.edu.ups.controller.statemachine.StateManager;
import ec.edu.ups.tools.ControlManager;
import ec.edu.ups.tools.Sound;
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

    private JPanel[] panels;
    private int currentPanel;

    private Sound music;

    int aps;
    int fps;
    int ups;

    public MainManager(String title) {
	this.working = false;
	this.title = title;
	this.width = Constants.FULL_WIDTH_WINDOW;
	this.height = Constants.FULL_HEIGHT_WINDOW;

	this.panels = new JPanel[3];
	this.currentPanel = 0;

    }

    public void startGame() {
	working = true;
	start();
	this.music = new Sound("/ec/edu/ups/resources/sounds/warmusic.wav");
	this.music.loop();
    }

    public void mainLoop() {
	aps = 0;
	fps = 0;
	ups = 0;

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
		print();
		fps++;

	    }

	    if (System.nanoTime() - countReference > NS_PER_SECOND) {
		ups = 0;
		fps = 0;
		countReference = System.nanoTime();
	    }
	}

    }

    private void start() {
	stateManager = new StateManager(this);
	screen = new Screen(width, height);
	JPanel screenPanel = new JPanel(new BorderLayout());
	screenPanel.add(screen, BorderLayout.CENTER);

	JPanel startPanel = stateManager.getStartGameState().getStartGameGUI();

	JPanel winnerPanel = stateManager.getGameWinnerManager()
		.getEndGameGUI();

	this.panels[0] = startPanel;
	this.panels[1] = screenPanel;
	this.panels[2] = winnerPanel;

	window = new Window(this.title, width, height);

	addCurrentPanel();

    }

    private void addCurrentPanel() {
	this.window.setPanel(panels[currentPanel]);

//		this.window.setLocationRelativeTo(null);
	if (currentPanel == 1) {
	    this.window.pack();
	    this.screen.requestFocus();

	}

    }

    private void update() {
	if (currentPanel == 1) {
	    ControlManager.keyboard.update();
	    stateManager.update();
	}

    }

    private void print() {
	if (currentPanel == 1) {
	    screen.print(stateManager);
	}
    }

    public int getAps() {
	return aps;
    }

    public void setAps(int aps) {
	this.aps = aps;
    }

    public int getFps() {
	return fps;
    }

    public void setFps(int fps) {
	this.fps = fps;
    }

    public int getUps() {
	return ups;
    }

    public void setUps(int ups) {
	this.ups = ups;
    }

    public boolean isWorking() {
	return working;
    }

    public void setWorking(boolean working) {
	this.working = working;
    }

    public int getCurrentPanel() {
	return currentPanel;
    }

    public StateManager getStateManager() {
	return stateManager;
    }

    public void setStateManager(StateManager stateManager) {
	this.stateManager = stateManager;
    }

    public JPanel[] getPanels() {
	return panels;
    }

    public void setPanels(JPanel[] panels) {
	this.panels = panels;
    }

    public void setCurrentPanel(int currentPanel) {
	this.currentPanel = currentPanel;

	this.stateManager.changeState(currentPanel);
	addCurrentPanel();

    }

}
