package ec.edu.ups.controller.statemachine;

import java.awt.Graphics;

public interface GameState {

	void update(StateManager stateManager);

	void paint(Graphics g);

}
