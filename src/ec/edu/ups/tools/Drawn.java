package ec.edu.ups.tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Drawn {

	private static int count = 0;

	public static void drawFilledRectangle(final Graphics g, final int x, final int y, final int width,
			final int height, final Color c) {
		g.setColor(c);
		g.fillRect(x, y, width, height);
	}

	public static void drawFilledRectangle(final Graphics g, final Rectangle r, final Color c) {
		g.setColor(c);
		g.fillRect(r.x, r.y, r.width, r.height);
	}

	public static void drawPerimeterRectangle(final Graphics g, final int x, final int y, final int width,
			final int height, final Color c) {
		g.setColor(c);
		g.drawRect(x, y, width, height);
	}

	public static void drawPerimeterRectangle(final Graphics g, final Graphics2D g2d, final int x, final int y,
			final int width, final int height, final Color c) {
		g.setColor(c);
		g2d.setStroke(new BasicStroke(2.0f));
		g.drawRect(x, y, width, height);
	}

	public static void drawString(final Graphics g, final String s, final int x, final int y, final Color c) {
		g.setColor(c);
		g.setFont(new Font(null, Font.PLAIN, 15));
		g.drawString(s, x, y);
	}

	public static void drawFilledCircle(final Graphics g, final int x, final int y, final int width, final int height,
			final Color c) {
		g.setColor(c);
		g.fillOval(x, y, width, height);
	}

	public static void drawCircumference(final Graphics g, final int x, final int y, final int width, final int height,
			final Color c) {
		g.setColor(c);
		g.drawOval(x, y, width, height);
	}

	public static void drawElements(final Graphics g, final BufferedImage img, final int x, final int y) {
		g.drawImage(img, x, y, null);
	}

	public static void drawLine(final Graphics g, final int x, final int y, final int width, final int height,
			final Color c) {
		g.setColor(c);
		g.drawLine(x, y, width, height);
	}
}
