
package ec.edu.ups.view;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import ec.edu.ups.controller.statemachine.StateManager;
import ec.edu.ups.main.Constants;
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

	if (this.isDisplayable()) {
	    BufferStrategy buffer = getBufferStrategy();

	    if (buffer == null) {
		createBufferStrategy(4);
		return;
	    }

	    Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
	    g.scale(Constants.SCALED_X, Constants.SCALED_Y);
	    stateManager.print(g);

	    Toolkit.getDefaultToolkit().sync();
	    g.dispose();

	    buffer.show();
	}

    }

}
