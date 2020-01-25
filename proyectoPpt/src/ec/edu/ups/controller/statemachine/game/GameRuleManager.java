
package ec.edu.ups.controller.statemachine.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.text.StyledEditorKit.BoldAction;

import ec.edu.ups.controller.RoundController;
import ec.edu.ups.controller.statemachine.GameState;
import ec.edu.ups.controller.statemachine.StateManager;
import ec.edu.ups.main.Constants;
import ec.edu.ups.model.Element;
import ec.edu.ups.model.Player;
import ec.edu.ups.tools.Animation;
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
	private Animation animationTornado;
	private BufferedImage[] tornadoInitial;
//	private Animation animationFinal;
	private BufferedImage[] tornadoFinal;

	private RoundController roundController;
	private GameGUI gameGui;

	private boolean winning;
	private boolean[] statesAnimation;
	
	private SpriteSheet tornadoSheet;

	// Temporal
	// private SpriteSheet spriteSheetR;
	// private SpriteSheet spriteSheetP;
	// private SpriteSheet spriteSheetS;
	private SpriteSheet sSElements;
	private SpriteSheet sSElementsInv;

	public GameRuleManager(int roundNumber, String player1, String player2) {
		// Temporal
		// this.spriteSheetR = new SpriteSheet(Constants.ROCK_PATH, 64, false);
		// this.spriteSheetP = new SpriteSheet(Constants.PAPER_PATH, 64, false);
		// this.spriteSheetS = new SpriteSheet(Constants.SCISSORS_PATH, 64, false);

		// Fin Temporal\
		this.sSElements = new SpriteSheet(Constants.ELEMENTS_PATH, 128, false);
		this.sSElementsInv = new SpriteSheet(Constants.ELEMENTS_INV_PATH, 128, false);

		this.players = new Player[2];

		this.players[0] = new Player(player1, getElementsPlayer1());
		this.players[1] = new Player(player2, getElementsPlayer2());

		this.roundNumber = roundNumber;

		roundController = new RoundController(this.players[0], this.players[1]);
		gameGui = new GameGUI(this);
		
		tornadoSheet=new SpriteSheet(Constants.TORNADO_PATH, 128, false);

		this.elementAni = new ElementAnimation(players, this);
		this.tornadoInitial = getTornadoInitial();
		this.tornadoFinal = getTornadoFinal();
		
		
		this.animationTornado = new Animation(tornadoInitial, 20, Constants.MID_WIDTH_WIN,
				Constants.MID_HEIGHT_WIN, false, this);
		
		this.statesAnimation = new boolean[4];
		this.statesAnimation[0] = true;
		this.statesAnimation[1] = false;
		this.statesAnimation[2] = false;
		this.statesAnimation[3] = false;
		

		
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

	public ElementAnimation getElementAni() {
		return elementAni;
	}

	public void setElementAni(ElementAnimation elementAni) {
		this.elementAni = elementAni;
	}

	public Animation getAnimationInitial() {
		return animationTornado;
	}

	public void setAnimationInitial(Animation animationInitial) {
		this.animationTornado = animationInitial;
	}


	public RoundController getRoundController() {
		return roundController;
	}

	public void setRoundController(RoundController roundController) {
		this.roundController = roundController;
	}

	public GameGUI getGameGui() {
		return gameGui;
	}

	public void setGameGui(GameGUI gameGui) {
		this.gameGui = gameGui;
	}

	public void setTornado(BufferedImage[] tornado) {
		this.tornadoInitial = tornado;
	}

	public boolean[] getStatesAnimation() {
		return statesAnimation;
	}

	public void setStatesAnimation(boolean[] statesAnimation) {
		this.statesAnimation = statesAnimation;
	}

	public void startState() {
		status = true;
		winning = false;
		startRound();
	}

	public void startRound() {
		second = 5;
		roundController.startRound();
		estimatedTime = 0;
		startTime = System.nanoTime();
		paintFaces(0);
		winning = false;
		elementAni.startPresentation(20);
		this.statesAnimation[0] = true;
		this.statesAnimation[1] = false;
		this.statesAnimation[2] = false;
		this.statesAnimation[3] = false;
		
		this.animationTornado.setRunning(false);
		this.animationTornado.setFrames(tornadoInitial);
		this.animationTornado.setInfinite(false);
	}

	@Override
	public void update(StateManager stateManager) {
		roundController.update();
		elementAni.update(second);
		animationTornado.update();
		estimatedTime += (System.nanoTime() - startTime) / 1000000000;

		if (status) {
			if (estimatedTime >= 1) {
				second--;

				paintSecond();

				estimatedTime = 0;
				startTime = System.nanoTime();
			}
		} else {
			stateManager.changeState(1);
		}

	}

	@Override
	public void paint(Graphics g) {
		this.gameGui.paint(g);
	}

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

			if (winner != null) {
				roundController.finishedRound();
				System.out.println("W: " + winner.getName() + " | selected: " + roundController.getOption());
			}

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
		
		if (winning) {
			if (statesAnimation[0]) {
				animationTornado.startAnimation();
				statesAnimation[0] = false;
//				statesAnimation[1] =true;
			}
			
			if (statesAnimation[1]) {
				animationTornado.setFrames(tornadoFinal);
				animationTornado.setInfinite(true);
				animationTornado.startAnimation();
//				animationFinal.startAnimation();
				
			}
			
			gameGui.setRoundFinishState(true);
			if (second == -10) {

				if (roundNumber <= roundController.getData()[2]) {
					status = false;

				}
				statesAnimation[1] = false;
				gameGui.setWinner(null);
				gameGui.setRoundFinishState(false);
				startRound();
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

		elements[0] = new Element(xR, yR, imageR, false, 'R', Constants.FACES_PATH, 64, 9, xFR, yFR);
		elements[1] = new Element(xP, yP, imageP, false, 'P', Constants.FACES_PATH, 64, 9, xFP, yFP);
		elements[2] = new Element(xS, yS, imageS, false, 'S', Constants.FACES_PATH, 64, 9, xFS, yFS);
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

		int xFR = +32;
		int yFR = +32;

		int xFP = +60;
		int yFP = +20;

		int xFS = +32;
		int yFS = +27;

		// BufferedImage imageR = this.spriteSheetE.getSprites(0).getImage();
		// BufferedImage imageP = this.spriteSheetE.getSprites(0).getImage();
		// BufferedImage imageS = this.spriteSheetE.getSprites(0).getImage();
		BufferedImage imageR = this.sSElementsInv.getSprites(1, 1).getImage();
		BufferedImage imageP = this.sSElementsInv.getSprites(0).getImage();
		BufferedImage imageS = this.sSElementsInv.getSprites(1, 0).getImage();

		elements[0] = new Element(xR, yR, imageR, false, 'R', Constants.FACES_PATH, 64, 9, xFR, yFR);
		elements[1] = new Element(xP, yP, imageP, false, 'P', Constants.FACES_PATH, 64, 9, xFP, yFP);
		elements[2] = new Element(xS, yS, imageS, false, 'S', Constants.FACES_PATH, 64, 9, xFS, yFS);
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

	public boolean collisionElements() {

		return false;
	}

	private BufferedImage[] getTornadoInitial() {
		
		BufferedImage[] tornado = new BufferedImage[8];
		
		for (int i = 0; i < 8; i++) {
			tornado[i] = tornadoSheet.getSprites(i).getImage();
		}
		
		return tornado;
	}
	
private BufferedImage[] getTornadoFinal() {
		
		BufferedImage[] tornado = new BufferedImage[8];

		for (int i = 0; i < 8; i++) {
			tornado[i] = tornadoSheet.getSprites(i,1).getImage();
		}
		
		return tornado;
	}
	
}
