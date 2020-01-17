package ec.edu.ups.controller.statemachine;

import java.awt.Graphics;

import ec.edu.ups.controller.statemachine.game.GameRuleManager;
import ec.edu.ups.controller.statemachine.game.GameWinnerManager;


public class StateManager {

	private GameState[] states;
	private GameState currentState;

	
	public StateManager() {
		createStates();
		createCurrentState();
	}

	
	public GameState getCurrentState() {
		return currentState;
	}

	
	private void createStates() {
		states = new GameState[2];
		states[0] = new GameRuleManager();
		states[1] = new GameWinnerManager();
	}

	private void createCurrentState() {
		currentState = states[0];
	}

	public void update() {
		currentState.update(this);
	}

	public void print(final Graphics g) {
		currentState.print(g);
	}

	public void changeState(int newState) {
		currentState = states[newState];
	}
}
