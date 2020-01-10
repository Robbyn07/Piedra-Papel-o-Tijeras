package ec.edu.ups.tools;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

	private final int KEY_NUM = 256;
    private final boolean[] keys = new boolean[KEY_NUM];

    private boolean stone1;
    private boolean scissors1;
    private boolean paper1;

    private boolean stone2;
    private boolean scissors2;
    private boolean paper2;

    public boolean isStone1() {
	return stone1;
    }

    public boolean isScissors1() {
	return scissors1;
    }

    public boolean isPaper1() {
	return paper1;
    }

    public boolean isStone2() {
	return stone2;
    }

    public boolean isScissors2() {
	return scissors2;
    }

    public boolean isPaper2() {
	return paper2;
    }

    public void update() {
	stone1 = keys[KeyEvent.VK_A];
	paper1 = keys[KeyEvent.VK_S];
	scissors1 = keys[KeyEvent.VK_D];

	stone2 = keys[KeyEvent.VK_LEFT];
	paper2 = keys[KeyEvent.VK_UP];
	scissors2 = keys[KeyEvent.VK_RIGHT];

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
	keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
	keys[e.getKeyCode()] = false;
    }
	
}
