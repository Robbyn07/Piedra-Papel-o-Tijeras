
package ec.edu.ups.controller.statemachine.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ec.edu.ups.controller.RoundController;
import ec.edu.ups.controller.statemachine.GameState;
import ec.edu.ups.controller.statemachine.StateManager;
import ec.edu.ups.main.Constants;
import ec.edu.ups.main.MainManager;
import ec.edu.ups.model.Element;
import ec.edu.ups.model.Player;
import ec.edu.ups.tools.ElementAnimation;
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
    private ElementAnimation elementAni;
    private boolean[] elementAnimation;
    private Player gameWinner;

    private RoundController roundController;
    private GameGUI gameGui;
    private MainManager mainManager;

    private boolean winning;
    private SpriteSheet sSElements;
    private SpriteSheet sSElementsInv;

    public GameRuleManager(MainManager mainManager) {
	this.mainManager = mainManager;
	this.sSElements = new SpriteSheet(Constants.ELEMENTS_PATH, 128, false);
	this.sSElementsInv = new SpriteSheet(Constants.ELEMENTS_INV_PATH, 128,
		false);

	this.players = new Player[2];

	this.players[0] = new Player(null, getElementsPlayer1());
	this.players[1] = new Player(null, getElementsPlayer2());

	roundController = new RoundController(this.players[0], this.players[1]);
	roundController = new RoundController(this.players[0], this.players[1]);
	gameGui = new GameGUI(this);

	this.elementAni = new ElementAnimation(players, this);
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

    public boolean isWinning() {
	return winning;
    }

    public void setWinning(boolean winning) {
	this.winning = winning;
    }

    public Player getGameWinner() {
	return gameWinner;
    }

    public void setGameWinner(Player gameWinner) {
	this.gameWinner = gameWinner;
    }

    public void startState() {
	this.players[0].setWin(0);
	this.players[1].setWin(0);
	status = true;
	winning = false;
	startRound();
	System.out.println("Start");
	gameWinner = null;
    }

    public void startRound() {
	second = 5;
	roundController.startRound();
	estimatedTime = 0;
	startTime = System.nanoTime();
	paintFaces(0);
	winning = false;

	elementAni.startInAnimation(30);

    }

    @Override
    public void update(StateManager stateManager) {
	roundController.update();
	elementAni.update(second);
	estimatedTime += (System.nanoTime() - startTime) / 1000000000;

	if (status) {
	    if (estimatedTime >= 1) {
		second--;

		paintSecond();

		estimatedTime = 0;
		startTime = System.nanoTime();
	    }

	    if (winning) {

		gameGui.setRoundFinishState(true);
		if (second == -3) {
		    if (roundNumber <= roundController.getData()[2]) {
			status = false;

		    }

		    gameGui.setWinner(null);
		    gameGui.setRoundFinishState(false);
		    startRound();
		}
	    }

	} else {
	    System.out.println("Cambio de estado");
	    gameWinner = this.roundController.finalWinner();

	    if (gameWinner == null) {
		this.mainManager.getStateManager().getGameWinnerManager()
			.getEndGameGUI().setGameWinnet("nulo");
	    } else {
		this.mainManager.getStateManager().getGameWinnerManager()
			.getEndGameGUI().setGameWinnet(gameWinner.getName());
	    }

	    this.mainManager.setCurrentPanel(2);
	}
    }

    @Override
    public void paint(Graphics g) {
	this.gameGui.paint(g);
    }

    /**
     * Este metodo se llama una vez cada segundo.
     */
    private void paintSecond() {

	int op1;
	int op2;

	if (second == 0) {

	    elementAni.stopPresentation();
	    roundController.selectOption();

	    op1 = roundController.getRuleController().getP1();
	    op2 = roundController.getRuleController().getP2();

	    winner = roundController.getRoundWinner();

	    elementAni.startAtackAnimation(20);

	    roundController.finishedRound();

	    gameGui.setWinner(winner);

	    if (winner == null) {
		paintFaces(8);
	    }
	    if (op1 != 100) {
		paintFaces(4);
		paintAttackFace(players[0].getElements()[op1]);
	    }

	    if (op2 != 100) {
		if (op1 == 100) {
		    paintFaces(4);
		}
		paintAttackFace(players[1].getElements()[op2]);
	    }

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

	int xFR = 32;
	int yFR = 32;

	int xFP = 5;
	int yFP = 20;

	int xFS = 33;
	int yFS = 27;

	BufferedImage imageR = this.sSElements.getSprites(0, 1).getImage();
	BufferedImage imageP = this.sSElements.getSprites(1, 0).getImage();
	BufferedImage imageS = this.sSElements.getSprites(0).getImage();

	elements[0] = new Element(xR, yR, imageR, false, 'R',
		Constants.FACES_PATH, 64, 9, xFR, yFR);
	elements[1] = new Element(xP, yP, imageP, false, 'P',
		Constants.FACES_PATH, 64, 9, xFP, yFP);
	elements[2] = new Element(xS, yS, imageS, false, 'S',
		Constants.FACES_PATH, 64, 9, xFS, yFS);
	return elements;

    }

    private Element[] getElementsPlayer2() {

	Element[] elements = new Element[3];

	int midMidW = Constants.MID_WIDTH_WIN + (Constants.MID_WIDTH_WIN / 2)
		- 128;
	int midMidH = Constants.MID_HEIGHT_WIN;

	int xR = midMidW;
	int yR = midMidH - 128 - 64;

	int xP = midMidW + 128;
	int yP = midMidH - 40;

	int xS = midMidW;
	int yS = midMidH + 64;

	int xFR = +32;
	int yFR = +32;

	int xFP = +60;
	int yFP = +20;

	int xFS = +32;
	int yFS = +27;

	BufferedImage imageR = this.sSElementsInv.getSprites(1, 1).getImage();
	BufferedImage imageP = this.sSElementsInv.getSprites(0).getImage();
	BufferedImage imageS = this.sSElementsInv.getSprites(1, 0).getImage();

	elements[0] = new Element(xR, yR, imageR, false, 'R',
		Constants.FACES_PATH, 64, 9, xFR, yFR);
	elements[1] = new Element(xP, yP, imageP, false, 'P',
		Constants.FACES_PATH, 64, 9, xFP, yFP);
	elements[2] = new Element(xS, yS, imageS, false, 'S',
		Constants.FACES_PATH, 64, 9, xFS, yFS);
	return elements;

    }

    public int[] getDataGame() {
	return roundController.getData();
    }

    public void paintFaces(int index) {
	for (int i = 0; i < players.length; i++) {
	    for (int j = 0; j < players[i].getElements().length; j++) {
		players[i].getElements()[j].getFaces().setIndex(index);
	    }
	}
    }

    public void paintAttackFace(Element element) {
	switch (element.getOption()) {
	case 'R':
	    element.getFaces().setIndex(1);
	    break;
	case 'P':
	    element.getFaces().setIndex(1);
	    break;
	case 'S':
	    element.getFaces().setIndex(1);
	    break;
	default:
	}
    }

}
