package ec.edu.ups.view;

import java.awt.Color;
import java.awt.Graphics;

import ec.edu.ups.main.Constants;

public class GameGUI {

	private Graphics g;

	public GameGUI() {
		;
	}

	public void startPaint(Graphics g) {
		this.g = g;
		stadium();
	}

	private void stadium() {

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Constants.WIDTH_WINDOW, Constants.HEIGHT_WINDOW);

		g.setColor(Color.CYAN);
		g.fillOval((int) ((Constants.MID_WIDTH_WIN) - 64), (int) ((Constants.MID_HEIGHT_WIN) - 64), 128, 128);

	}

}
