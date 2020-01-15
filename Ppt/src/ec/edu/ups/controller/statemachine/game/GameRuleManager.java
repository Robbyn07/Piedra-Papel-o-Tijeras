
package ec.edu.ups.controller.statemachine.game;

import java.awt.Graphics;

import ec.edu.ups.controller.RuleController;
import ec.edu.ups.controller.statemachine.GameState;
import ec.edu.ups.controller.statemachine.StateManager;
import ec.edu.ups.model.Element;
import ec.edu.ups.model.Player;


public class GameRuleManager implements GameState {

	private boolean status;

	private long startTime;
	private long estimatedTime;
	private long second;

	private Player player1;
	private Player player2;
	private Player winner;

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

		player1 = new Player("Roby", e1);
		player2 = new Player("Edd", e2);
		ruleController = new RuleController(player1, player2);
		startState();
	}

	
	public void startState() {
		startTime = System.nanoTime();
		second = 5;
		estimatedTime = 0;
		status = true;
	}

	@Override
	public void update(StateManager stateManager) {
		ruleController.updateKeyboard();
		estimatedTime += (System.nanoTime() - startTime) / 1000000000;
		
		if (estimatedTime >= 1 && status) {
			System.out.println("T" + second);
			second--;

			if (second <= 0) {
				status = false;
				ruleController.setOption(player1, ruleController.getP1());
				ruleController.setOption(player2, ruleController.getP2());
				winner = ruleController.winner();
				
				if (winner == null) {
					System.out.println("Empate 0+");
					startState();
				} else {
					System.out.println("Gano: " + winner.getName());
					stateManager.changeState(1);
				}
				
				ruleController.setWinner(null);
			}
			estimatedTime = 0;
			startTime = System.nanoTime();
		}
	}

	@Override
	public void print(Graphics g) {

	}

}
