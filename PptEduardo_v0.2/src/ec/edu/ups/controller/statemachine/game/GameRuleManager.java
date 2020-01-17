
package ec.edu.ups.controller.statemachine.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ec.edu.ups.controller.RuleController;
import ec.edu.ups.controller.statemachine.GameState;
import ec.edu.ups.controller.statemachine.StateManager;
import ec.edu.ups.main.Constants;
import ec.edu.ups.model.Element;
import ec.edu.ups.model.Player;
import ec.edu.ups.view.GameGUI;
import ec.edu.ups.view.graphics.SpriteSheet;

public class GameRuleManager implements GameState {

	private boolean status;

	private long startTime;
	private long estimatedTime;
	private long second;

	private Player player1;
	private Player player2;
	private Player winner;

	private RuleController ruleController;
	private GameGUI gameGui;

	// Temporal
	private SpriteSheet spriteSheetR;
	private SpriteSheet spriteSheetP;
	private SpriteSheet spriteSheetS;

	public GameRuleManager() {
		// Temporal
		this.spriteSheetR = new SpriteSheet(Constants.ROCK_PATH, 64, false);
		this.spriteSheetP = new SpriteSheet(Constants.PAPER_PATH, 64, false);
		this.spriteSheetS = new SpriteSheet(Constants.SCISSORS_PATH, 64, false);

		// Fin Temporal

		player1 = new Player("Roby", getElementsPlayer1());
		player2 = new Player("Edd", getElementsPlayer2());

		ruleController = new RuleController(player1, player2);
		Player[] players = new Player[2];
		players[0] = player1;
		players[1] = player2;
		gameGui = new GameGUI(players);
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
		this.gameGui.paint(g);
	}

	private Element[] getElementsPlayer1() {

		Element[] elements = new Element[3];

		int midMidW = Constants.MID_WIDTH_WIN / 2;
		int midMidH = Constants.MID_HEIGHT_WIN;

		int xR = midMidW;
		int yR = midMidH - 128 - 64;

		int xP = midMidW - 64 - 64;
		int yP = midMidH - 64;

		int xS = midMidW;
		int yS = midMidH + 64;

		BufferedImage imageR = this.spriteSheetR.getSprites(0).getImage();
		BufferedImage imageP = this.spriteSheetP.getSprites(0).getImage();
		BufferedImage imageS = this.spriteSheetS.getSprites(0).getImage();

		elements[0] = new Element(xR, yR, imageR, false, 'R');
		elements[1] = new Element(xP, yP, imageP, false, 'P');
		elements[2] = new Element(xS, yS, imageS, false, 'S');
		return elements;

	}

	private Element[] getElementsPlayer2() {

		Element[] elements = new Element[3];

		int midMidW = Constants.MID_WIDTH_WIN + (Constants.MID_WIDTH_WIN / 2) - 64;
		int midMidH = Constants.MID_HEIGHT_WIN;

		int xR = midMidW;
		int yR = midMidH - 128 - 64;

		int xP = midMidW + 64 + 64;
		int yP = midMidH - 64;

		int xS = midMidW;
		int yS = midMidH + 64;

		BufferedImage imageR = this.spriteSheetR.getSprites(0).getImage();
		BufferedImage imageP = this.spriteSheetP.getSprites(0).getImage();
		BufferedImage imageS = this.spriteSheetS.getSprites(0).getImage();

		elements[0] = new Element(xR, yR, imageR, false, 'R');
		elements[1] = new Element(xP, yP, imageP, false, 'P');
		elements[2] = new Element(xS, yS, imageS, false, 'S');
		return elements;

	}

}
