package ec.edu.ups.controller.statemachine.game;

import java.awt.Graphics;

import ec.edu.ups.controller.statemachine.GameState;
import ec.edu.ups.controller.statemachine.StateManager;
import ec.edu.ups.main.MainManager;
import ec.edu.ups.view.StartGameGUI;

public class StartGameState implements GameState {

	private StartGameGUI startGameGUI;
	private MainManager mainMager;

	public StartGameState(MainManager mainMager) {
		super();
		this.mainMager = mainMager;
		startGameGUI = new StartGameGUI(this);
	}

	@Override
	public void update(StateManager stateManager) {

	}

	@Override
	public void paint(Graphics g) {

	}

	public StartGameGUI getStartGameGUI() {
		return startGameGUI;
	}

	public void setStartGameGUI(StartGameGUI startGameGUI) {
		this.startGameGUI = startGameGUI;
	}

	public MainManager getMainMager() {
		return mainMager;
	}

	public void setMainMager(MainManager mainMager) {
		this.mainMager = mainMager;
	}

}
