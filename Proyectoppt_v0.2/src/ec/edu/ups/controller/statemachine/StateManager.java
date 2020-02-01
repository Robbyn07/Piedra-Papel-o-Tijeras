package ec.edu.ups.controller.statemachine;

import java.awt.Graphics;

import ec.edu.ups.controller.statemachine.game.GameRuleManager;
import ec.edu.ups.controller.statemachine.game.GameWinnerManager;
import ec.edu.ups.controller.statemachine.game.StartGameState;
import ec.edu.ups.main.MainManager;

public class StateManager {

    private StartGameState startGameState;
    private GameRuleManager gameRuleManager;

    private MainManager mainManager;

    private GameState[] states;
    private GameState currentState;

    public StateManager(MainManager mainManager) {
	this.mainManager = mainManager;
	createStates();
	createCurrentState();
    }

    public GameState getCurrentState() {
	return currentState;
    }

    public GameState getState(int index) {
	return states[index];

    }

    private void createStates() {
	states = new GameState[3];

	startGameState = new StartGameState(mainManager);
	gameRuleManager = new GameRuleManager(2, "Roby", "Edd");

	states[0] = startGameState;
	states[1] = gameRuleManager;
	states[2] = new GameWinnerManager();
    }

    private void createCurrentState() {
	currentState = states[0];
    }

    public void update() {
	currentState.update(this);
    }

    public void print(final Graphics g) {
	currentState.paint(g);
    }

    public void changeState(int newState) {
	currentState = states[newState];
    }

    public StartGameState getStartGameState() {
	return startGameState;
    }

    public void setStartGameState(StartGameState startGameState) {
	this.startGameState = startGameState;
    }

    public GameRuleManager getGameRuleManager() {
	return gameRuleManager;
    }

    public void setGameRuleManager(GameRuleManager gameRuleManager) {
	this.gameRuleManager = gameRuleManager;
    }

}
