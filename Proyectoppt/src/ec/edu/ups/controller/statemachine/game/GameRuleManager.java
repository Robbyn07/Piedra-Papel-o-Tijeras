
package ec.edu.ups.controller.statemachine.game;

import java.awt.Graphics;

import ec.edu.ups.controller.RuleController;
import ec.edu.ups.controller.statemachine.GameState;
import ec.edu.ups.controller.statemachine.StateManager;
import ec.edu.ups.model.Element;
import ec.edu.ups.model.Player;

public class GameRuleManager implements GameState {

	// SpriteSheet sheet = new
	// SpriteSheet("/ec/edu/ups/resources/textures/damas64.png", 64, false);

	private RuleController ruleController;

	public GameRuleManager() {

		Element[] e1 = new Element[3];
		Element[] e2 = new Element[3];

		e1[0] = new Element(0, 0, null, false, 'r');
		e1[1] = new Element(0, 0, null, false, 'p');
		e1[2] = new Element(0, 0, null, false, 's');

		e2[0] = new Element(0, 0, null, false, 'r');
		e2[1] = new Element(0, 0, null, false, 'p');
		e2[2] = new Element(0, 0, null, false, 's');

		ruleController = new RuleController(new Player("Roby", e1), new Player("Edd", e2));
		ruleController.startGame();

	}

	@Override
	public void update(StateManager stateManager) {
		ruleController.update();
		if (ruleController.getWinner() != null) {

			System.out.println("Gano: " + ruleController.winner().getName());
			stateManager.changeState(1);
		}
		if (!ruleController.isStatus()) {
			System.out.println("Empate +0p");
			ruleController.startGame();
			stateManager.changeState(0);
		}
	}

	@Override
	public void print(Graphics g) {

	}

}
