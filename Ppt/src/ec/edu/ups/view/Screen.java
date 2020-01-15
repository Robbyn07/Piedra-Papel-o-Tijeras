
package ec.edu.ups.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import ec.edu.ups.controller.statemachine.StateManager;
import ec.edu.ups.tools.ControlManager;

public class Screen extends Canvas {

	private final int width;
	private final int height;

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Screen(final int width, final int height) {
		this.width = width;
		this.height = height;

		setIgnoreRepaint(true);
		setPreferredSize(new Dimension(width, height));
		addKeyListener(ControlManager.keyboard);

		setFocusable(true);
		requestFocus();
	}

	public void print(final StateManager stateManager) {
		BufferStrategy buffer = getBufferStrategy();
		
		if (buffer == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = buffer.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);

		stateManager.print(g);

		Toolkit.getDefaultToolkit().sync();
		g.dispose();

		buffer.show();
	}

}
