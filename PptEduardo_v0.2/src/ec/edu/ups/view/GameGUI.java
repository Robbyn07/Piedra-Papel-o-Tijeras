package ec.edu.ups.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ec.edu.ups.main.Constants;
import ec.edu.ups.model.Player;
import ec.edu.ups.tools.Drawn;

public class GameGUI {

	private Graphics g;
	private Player[] players;

	public GameGUI(Player[] players) {
		this.players = players;

	}

	public void paint(Graphics g) {

		// Base
		Drawn.drawFilledRectangle(g, 0, 0, Constants.WIDTH_WINDOW, Constants.HEIGHT_WINDOW, new Color(59, 6, 44));

		// Figuras Complementarias
		// Centro
		Drawn.drawFilledCircle(g, Constants.MID_WIDTH_WIN - 8, Constants.MID_HEIGHT_WIN - 8, 16, 16, Color.white);
		Drawn.drawLine(g, Constants.MID_WIDTH_WIN, 0, Constants.MID_WIDTH_WIN, Constants.HEIGHT_WINDOW - 30,
				Color.white);
		Drawn.drawCircumference(g, Constants.MID_WIDTH_WIN - 64, Constants.MID_HEIGHT_WIN - 64, 128, 128, Color.white);
		// Marcadores

		Drawn.drawFilledRectangle(g, Constants.MID_WIDTH_WIN - 30, 0, 60, 30, Color.white);
		Drawn.drawFilledRectangle(g, Constants.MID_WIDTH_WIN - 30, Constants.HEIGHT_WINDOW - 30, 60, 30, Color.white);
		Drawn.drawPerimeterRectangle(g, Constants.MID_WIDTH_WIN - 30, 0, 30, 30, Color.black);
		Drawn.drawPerimeterRectangle(g, Constants.MID_WIDTH_WIN, 0, 30, 30, Color.black);

		// Dibujar textos
		// Marcadores
		Drawn.drawString(g, "0", Constants.MID_WIDTH_WIN - 19, 19, Color.BLUE);
		Drawn.drawString(g, "3", Constants.MID_WIDTH_WIN + 12, 19, Color.RED);
		Drawn.drawString(g, "Ronda ", Constants.MID_WIDTH_WIN - 29, Constants.HEIGHT_WINDOW - 10, Color.black);
		// Jugadores
		Drawn.drawString(g, players[0].getName(), Constants.WIDTH_WINDOW - 665, Constants.HEIGHT_WINDOW - 50,
				Color.BLUE);
		Drawn.drawString(g, players[1].getName(), Constants.WIDTH_WINDOW - 200, Constants.HEIGHT_WINDOW - 50,
				Color.RED);

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

}
