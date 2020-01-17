package ec.edu.ups.view;

import java.awt.Color;
import java.awt.Graphics;

import ec.edu.ups.main.Constants;
import ec.edu.ups.model.Player;
import ec.edu.ups.tools.Drawn;

public class GameGUI {

	private Graphics g;
	private Player player;

	public GameGUI() {

	}

	public void startPaint(Graphics g) {
		this.g = g;
		stadium();
	}

	private void stadium() {

		// Base
		Drawn.drawFilledRectangle(g, 0, 0, Constants.WIDTH_WINDOW, Constants.HEIGHT_WINDOW, new Color(59, 6, 44));

		// Figuras Complementarias
		// Centro
		Drawn.drawFilledCircle(g, Constants.MID_WIDTH_WIN - 8, Constants.MID_HEIGHT_WIN - 8, 16, 16, Color.white);
		Drawn.drawLine(g, Constants.MID_WIDTH_WIN, 0, Constants.MID_WIDTH_WIN, Constants.HEIGHT_WINDOW, Color.white);
		Drawn.drawCircumference(g, Constants.MID_WIDTH_WIN - 64, Constants.MID_HEIGHT_WIN - 64, 128, 128, Color.white);
		// Marcadores
		Drawn.drawPerimeterRectangle(g, Constants.MID_WIDTH_WIN - 30, 0, 30, 30, Color.white);
		Drawn.drawPerimeterRectangle(g, Constants.MID_WIDTH_WIN, 0, 30, 30, Color.white);

		// Dibujar textos
		// Marcadores
		Drawn.drawString(g, "0", Constants.MID_WIDTH_WIN - 19, 19, Color.BLUE);
		Drawn.drawString(g, "3", Constants.MID_WIDTH_WIN + 12, 19, Color.RED);
		// Jugadores
		Drawn.drawString(g, "Player 1", Constants.WIDTH_WINDOW - 665, Constants.HEIGHT_WINDOW - 50, Color.BLUE);
		Drawn.drawString(g, "Player 2", Constants.WIDTH_WINDOW - 200, Constants.HEIGHT_WINDOW - 50, Color.RED);

		/*
		 * for (int i = 0; i < n; i++) { Drawn.drawElements(g,
		 * player.getElements()[i].getImage(), player.getElements()[i].getPointX(),
		 * player.getElements()[i].getPointY()); }
		 */

	}

}
