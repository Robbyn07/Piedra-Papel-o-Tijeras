package ec.edu.ups.tools;

import java.awt.Rectangle;

import ec.edu.ups.controller.statemachine.game.GameRuleManager;
import ec.edu.ups.main.Constants;
import ec.edu.ups.model.Element;
import ec.edu.ups.model.Player;

public class ElementAnimation {

	private double G = 0.2;

	private int[] xD;
	private int[] yD;

	private int[] wD;
	private int[] hD;

	private Player[] players;
	GameRuleManager gameRuleManager;

	private boolean running;

	private boolean[] jumping;
	private boolean[] stateAtacking;
	private boolean atacking;
	private int op1;
	private int op2;

	int NS_PER_SECOND = 1000000000;
	int UPS_OBJECT;
	double NS_PER_UPDATES;

	long updateReference;
	long countReference;

	double timeElapsed;
	double delta = 0;

	long loopStart;

	private int animaOption;

	public ElementAnimation(Player[] players, GameRuleManager gameRuleManager) {
		super();

		this.players = players;
		this.gameRuleManager = gameRuleManager;

		setDefaults(players);
		this.running = false;
		this.atacking = false;
		this.animaOption = 0;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public boolean isAtacking() {
		return atacking;
	}

	public void setAtacking(boolean atacking) {
		this.atacking = atacking;
	}

	public void update(long second) {
		switch (animaOption) {
		case 1:
			presentation();
			break;
		case 2:
			atackAnimation();
			break;
		default:
			break;
		}

	}

	private void atackAnimation() {

		if (running) {

			loopStart = System.nanoTime();

			timeElapsed = loopStart - updateReference;
			updateReference = loopStart;

			delta += timeElapsed / NS_PER_UPDATES;
			int c = 0;
			if (delta >= 1) {
				delta = 0;
				for (int i = 0; i < players.length; i++) {
					for (int j = 0; j < players[i].getElements().length; j++) {

						Element element = players[i].getElements()[j];

						if (element.isSelected()) {
							atackAnimation(element, c);

						} else {
							atackOutAnimation(element);
						}
						c++;
					}
				}

			}

		}

	}

	private void stopAtackAnimation() {

		running = false;
		setAtacking(false);
		gameRuleManager.setWinning(true);

	}

	public void setDefaults(Player[] players) {

		int n = players[0].getElements().length * 2;
		this.xD = new int[n];
		this.yD = new int[n];
		this.wD = new int[n];
		this.hD = new int[n];
		this.jumping = new boolean[n];
		this.stateAtacking = new boolean[n];
		int c = 0;

		for (int i = 0; i < players.length; i++) {
			for (int j = 0; j < players[i].getElements().length; j++) {

				Element element = players[i].getElements()[j];

				this.xD[c] = element.getPointX();
				this.yD[c] = element.getPointY();
				this.wD[c] = element.getWidth();
				this.hD[c] = element.getHeight();
				this.jumping[c] = false;
				this.stateAtacking[c] = false;
				c++;
			}
		}
	}

	public void startPresentation(int velocity) {

		NS_PER_UPDATES = NS_PER_SECOND / velocity;
		UPS_OBJECT = velocity;
		running = true;
		animaOption = 1;
		G = Math.abs(G);
		defaultElements();
		updateReference = System.nanoTime();
		countReference = System.nanoTime();
	}

	public void stopPresentation() {
		running = false;
		animaOption = 0;
	}

	public void presentation() {

		if (running) {

			loopStart = System.nanoTime();

			timeElapsed = loopStart - updateReference;
			updateReference = loopStart;

			delta += timeElapsed / NS_PER_UPDATES;

			if (delta >= 1) {
				delta = 0;
				int nP = players.length;
				int nE;
				int c = 0;
				Element element;

				for (int i = 0; i < nP; i++) {
					nE = players[i].getElements().length;

					for (int j = 0; j < nE; j++) {
						element = players[i].getElements()[j];

						if (jumping[c]) {
							if (G > 0) {
								G = -G;
							}
						}

						int jump = (int) (element.getPointY() + element.getVelocityY());

						element.setVelocityY(element.getVelocityY() + G);

//						element.setVelocityY(element.getVelocityY() * 0.9); // Friccion

						if (jump <= (yD[c] - 2)) {
							jumping[c] = false;
							G = Math.abs(G);
						} else {
							element.setPointY(jump);
						}

						if (jump >= (yD[c] + 2)) {
							element.setPointY(yD[c] + 2);
							jumping[c] = true;
						} else {
							element.setPointY(jump);
						}
						c++;
					}

				}
			}

		}

	}

	public void startAtackAnimation(int velocity) {
		running = true;
		animaOption = 2;
		setStatesAtacking(true);
		NS_PER_UPDATES = NS_PER_SECOND / velocity;
		UPS_OBJECT = velocity;
		G = Math.abs(G);
		updateReference = System.nanoTime();
		countReference = System.nanoTime();
	}

	/**
	 * Este metodo hace que los elementos vayan al centro.
	 */
	public void atackAnimation(Element element, int c) {
		int mX = Constants.MID_WIDTH_WIN;
		int mY = Constants.MID_HEIGHT_WIN;
		element.setVelocityY((element.getVelocityY() + 6 * 0.9));
		element.setVelocityX((element.getVelocityX() + 6 * 0.9));

		int x = element.getPointX();
		int y = element.getPointY();

		if (x < (mX - element.getWidth() / 2)) {
			moveRight(element);
		}

		if (x > mX - (element.getWidth() / 2)) {
			moveLeft(element);
		}

		if (y < mY - (element.getHeight())) {
			moveDown(element);
		}

		if (y > mY - (element.getHeight() / 2)) {
			moveUp(element);

		}

		if (element.collision(new Rectangle(mX, mY, 1, 1))) {
//			stateAtacking[c] = false;
			stopAtackAnimation();
		}

	}

	private void atackOutAnimation(Element element) {

		element.setVelocityY((element.getVelocityY()) + 7 * 0.9);
		element.setVelocityX((element.getVelocityX()) + 7 * 0.9);

		int x = element.getPointX();
		int y = element.getPointY();

		int w = Constants.WIDTH_WINDOW;
		int h = Constants.HEIGHT_WINDOW;

		int mW = Constants.MID_WIDTH_WIN;
		int mH = Constants.MID_HEIGHT_WIN;

		if (element.getOption() == 'R' && y > 0) {

			moveUp(element);

		}

		if (element.getOption() == 'S' && y < h - element.getHeight()) {
			moveDown(element);
		}

		if (element.getOption() == 'P') {
			if (x < mW && x > 0) {
				moveLeft(element);
			}

			if (x > mW && x < w - element.getWidth()) {
				moveRight(element);
			}
		}
	}

	private void moveUp(Element element) {
		element.setPointY((int) (element.getPointY() - element.getVelocityY()));
	}

	private void moveDown(Element element) {
		element.setPointY((int) (element.getPointY() + element.getVelocityY()));
	}

	private void moveLeft(Element element) {
		element.setPointX((int) (element.getPointX() - element.getVelocityX()));
	}

	private void moveRight(Element element) {
		element.setPointX((int) (element.getPointX() + element.getVelocityX()));
	}

	public void defaultElements() {

		int c = 0;
		for (int i = 0; i < players.length; i++) {
			for (int j = 0; j < players[i].getElements().length; j++) {
				players[i].getElements()[j].setPointX(xD[c]);
				players[i].getElements()[j].setPointY(yD[c]);
				players[i].getElements()[j].setWidth(wD[c]);
				players[i].getElements()[j].setHeight(hD[c]);

				c++;
			}
		}

	}

	private void setStatesAtacking(boolean b) {
		for (int i = 0; i < stateAtacking.length; i++) {
			stateAtacking[i] = b;
		}
	}

}
