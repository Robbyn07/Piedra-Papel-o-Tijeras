package ec.edu.ups.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ec.edu.ups.controller.statemachine.game.GameRuleManager;
import ec.edu.ups.main.Constants;
import ec.edu.ups.model.Player;
import ec.edu.ups.tools.Drawn;

public class GameGUI {

	private Graphics g;
	private Player[] players;
	private Player winner;

	private GameRuleManager gameRuleManager;

	private int w;
	private int h;
	private int mw;
	private int mh;

	private boolean roundFinishState;

	public GameGUI(GameRuleManager gameRuleManager) {
		this.gameRuleManager = gameRuleManager;
		this.players = gameRuleManager.getPlayers();
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public boolean isRoundFinishState() {
		return roundFinishState;
	}

	public void setRoundFinishState(boolean roundFinishState) {
		this.roundFinishState = roundFinishState;
	}

	public void paint(Graphics g) {
		this.g = g;
		w = Constants.WIDTH_WINDOW;
		h = Constants.HEIGHT_WINDOW;
		mw = Constants.MID_WIDTH_WIN;
		mh = Constants.MID_HEIGHT_WIN;
		paintScena();
		paintGameData();

		if (roundFinishState && winner != null) {
			paintWinner();

		} else if (roundFinishState && winner == null) {
			paintDraw();
		}
	}

	private void paintScena() {

		// Base
		Drawn.drawFilledRectangle(g, 0, 0, w, h, new Color(59, 6, 44));

		// Figuras Complementarias
		// Centro
		Drawn.drawFilledCircle(g, mw - 8, mh - 8, 16, 16, Color.white);
		Drawn.drawLine(g, mw, 0, mw, h - 30, Color.white);
		Drawn.drawCircumference(g, mw - 64, mh - 64, 128, 128, Color.white);
		// Marcadores

		Drawn.drawFilledRectangle(g, mw - 30, 0, 60, 30, Color.white);
		Drawn.drawFilledRectangle(g, mw - 30, h - 30, 60, 30, Color.white);
		Drawn.drawPerimeterRectangle(g, mw - 30, 0, 30, 30, Color.black);
		Drawn.drawPerimeterRectangle(g, mw, 0, 30, 30, Color.black);

		int n = players.length;
		int nE;
		BufferedImage image;
		int xE;
		int yE;

		for (int i = 0; i < n; i++) {
			nE = players[i].getElements().length;
			for (int j = 0; j < nE; j++) {
				image = players[i].getElements()[j].getImage();
				xE = players[i].getElements()[j].getPointX();
				yE = players[i].getElements()[j].getPointY();
				g.drawImage(image, xE, yE, null);
			}

		}

	}

	private void paintGameData() {

		int data[] = gameRuleManager.getDataGame();

		// Dibujar textos
		// Marcadores
		Drawn.drawString(g, "" + data[0], mw - 19, 19, Color.BLUE);
		Drawn.drawString(g, "" + data[1], mw + 12, 19, Color.RED);

		// Jugadores
		Drawn.drawString(g, players[0].getName(), w - 665, h - 50, Color.BLUE);
		Drawn.drawString(g, players[1].getName(), w - 200, h - 50, Color.RED);

		// Dibujar Tiempo
		if (gameRuleManager.getSecond() > 0) {
			Drawn.drawString(g, "Tiempo " + gameRuleManager.getSecond(), 20, 20, Color.white);
		} else {
			Drawn.drawString(g, "Tiempo " + 0, 20, 20, Color.white);
		}

		if (data[2] > gameRuleManager.getRoundNumber()) {
			Drawn.drawString(g, "Fin!!", mw - 15, h - 10, Color.black);
		} else {
			Drawn.drawString(g, "Ronda " + data[2], mw - 29, h - 10, Color.black);
		}

	}

	public void paintWinner() {
		Drawn.drawFilledRectangle(g, mw - 64, mh - 32, 128, 64, Color.WHITE);
		Drawn.drawString(g, "" + winner.getName() + " " + winner.getWin() + " pts.", mw - 64, mh + (32 / 2) - 10,
				Color.BLACK);
	}

	public void paintDraw() {
		Drawn.drawFilledRectangle(g, mw - 64, mh - 32, 128, 64, Color.WHITE);
		Drawn.drawString(g, " Empate +0 pts.", mw - 64, mh + (32 / 2) - 10, Color.BLACK);
	}

}
