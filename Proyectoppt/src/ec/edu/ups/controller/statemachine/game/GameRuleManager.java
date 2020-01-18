
package ec.edu.ups.controller.statemachine.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ec.edu.ups.controller.RoundController;
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
	private int second;

	private int roundNumber;

	private Player[] players;
	private Player winner;

	private RoundController roundController;
	private GameGUI gameGui;

	// Temporal
	// private SpriteSheet spriteSheetR;
	// private SpriteSheet spriteSheetP;
	// private SpriteSheet spriteSheetS;
	private SpriteSheet spriteSheetE;
	private SpriteSheet spriteSheetInv;

	public GameRuleManager(int roundNumber, String player1, String player2) {
		// Temporal
		// this.spriteSheetR = new SpriteSheet(Constants.ROCK_PATH, 64, false);
		// this.spriteSheetP = new SpriteSheet(Constants.PAPER_PATH, 64, false);
		// this.spriteSheetS = new SpriteSheet(Constants.SCISSORS_PATH, 64, false);
		this.spriteSheetE = new SpriteSheet(Constants.ELEMENTS_PATH, 128, false);
		this.spriteSheetInv = new SpriteSheet(Constants.ELEMENTS_INV_PATH, 128, false);

		// Fin Temporal\

		this.players = new Player[2];

		this.players[0] = new Player(player1, getElementsPlayer1());
		this.players[1] = new Player(player2, getElementsPlayer2());

		this.roundNumber = roundNumber;

		roundController = new RoundController(this.players[0], this.players[1]);
		gameGui = new GameGUI(this);
		startState();
	}

	public long getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getRoundNumber() {
		return roundNumber;
	}

	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public void startState() {
		status = true;
		startRound();
	}

	public void startRound() {
		second = 5;
		roundController.startRound();
		estimatedTime = 0;
		startTime = System.nanoTime();
	}

	@Override
	public void update(StateManager stateManager) {
		roundController.update();
		estimatedTime += (System.nanoTime() - startTime) / 1000000000;

		if (roundNumber >= roundController.getData()[2] && status) {
			if (estimatedTime >= 1) {
				second--;

				paintSecond();

				estimatedTime = 0;
				startTime = System.nanoTime();
			}
		} else {
			status = false;
			stateManager.changeState(1);
		}

	}

	@Override
	public void paint(Graphics g) {
		this.gameGui.paint(g);
	}

	private void paintSecond() {

		if (second == 0) {
			roundController.selectOption();
			winner = roundController.getRoundWinner();
			if (winner != null)
				roundController.finishedRound();
		}
		if (second <= 0) {

			gameGui.setWinner(winner);
			gameGui.setRoundFinishState(true);
		}

		if (second <= -3) {
			gameGui.setWinner(null);
			gameGui.setRoundFinishState(false);
			startRound();
		}
	}

	private Element[] getElementsPlayer1() {

		Element[] elements = new Element[3];

		int midMidW = Constants.MID_WIDTH_WIN / 2;
		int midMidH = Constants.MID_HEIGHT_WIN;

		int xR = midMidW;
		int yR = midMidH - 128 - 64;

		int xP = midMidW - 64 - 64;
		int yP = midMidH - 40;

		int xS = midMidW;
		int yS = midMidH + 64;

		// BufferedImage imageR = this.spriteSheetR.getSprites(0).getImage();
		// BufferedImage imageP = this.spriteSheetP.getSprites(0).getImage();
		// BufferedImage imageS = this.spriteSheetS.getSprites(0).getImage();
		BufferedImage imageR = this.spriteSheetE.getSprites(0, 1).getImage();
		BufferedImage imageP = this.spriteSheetE.getSprites(1, 0).getImage();
		BufferedImage imageS = this.spriteSheetE.getSprites(0).getImage();

		elements[0] = new Element(xR, yR, imageR, false, 'R');
		elements[1] = new Element(xP, yP, imageP, false, 'P');
		elements[2] = new Element(xS, yS, imageS, false, 'S');
		return elements;

	}

	private Element[] getElementsPlayer2() {

		Element[] elements = new Element[3];

		int midMidW = Constants.MID_WIDTH_WIN + (Constants.MID_WIDTH_WIN / 2) - 128;
		int midMidH = Constants.MID_HEIGHT_WIN;

		int xR = midMidW;
		int yR = midMidH - 128 - 64;

		int xP = midMidW + 128;
		int yP = midMidH - 40;

		int xS = midMidW;
		int yS = midMidH + 64;

		// BufferedImage imageR = this.spriteSheetE.getSprites(0).getImage();
		// BufferedImage imageP = this.spriteSheetE.getSprites(0).getImage();
		// BufferedImage imageS = this.spriteSheetE.getSprites(0).getImage();
		BufferedImage imageR = this.spriteSheetInv.getSprites(1, 1).getImage();
		BufferedImage imageP = this.spriteSheetInv.getSprites(0).getImage();
		BufferedImage imageS = this.spriteSheetInv.getSprites(1, 0).getImage();

		elements[0] = new Element(xR, yR, imageR, false, 'R');
		elements[1] = new Element(xP, yP, imageP, false, 'P');
		elements[2] = new Element(xS, yS, imageS, false, 'S');
		return elements;

	}

	public int[] getDataGame() {
		return roundController.getData();
	}

}
