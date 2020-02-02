package ec.edu.ups.controller.statemachine.game;

import java.awt.Graphics;

import ec.edu.ups.controller.statemachine.GameState;
import ec.edu.ups.controller.statemachine.StateManager;
import ec.edu.ups.main.MainManager;
import ec.edu.ups.view.EndGameGUI;

public class GameWinnerManager implements GameState {

	private EndGameGUI endGameGUI;
	private MainManager mainMager;

	public GameWinnerManager(MainManager mainMager) {
		super();
		this.mainMager = mainMager;
		endGameGUI = new EndGameGUI(this);
	}

	@Override
	public void update(StateManager stateManager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub

	}

	public EndGameGUI getEndGameGUI() {
		return endGameGUI;
	}

	public void setEndGameGUI(EndGameGUI endGameGUI) {
		this.endGameGUI = endGameGUI;
	}

	public MainManager getMainMager() {
		return mainMager;
	}

	public void setMainMager(MainManager mainMager) {
		this.mainMager = mainMager;
	}

}
