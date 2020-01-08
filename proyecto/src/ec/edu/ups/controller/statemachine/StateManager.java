package ec.edu.ups.controller.statemachine;

import java.awt.Graphics;

import ec.edu.ups.controller.statemachine.game.GameManager;

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

	states = new GameState[1];
	states[0] = new GameManager();

    }

    private void createCurrentState() {
	currentState = states[0];
    }

    public void update() {
	currentState.update();
    }

    public void print(final Graphics g) {
	currentState.print(g);
    }

    public void changeState(int newState) {
	currentState = states[newState];
    }
}
