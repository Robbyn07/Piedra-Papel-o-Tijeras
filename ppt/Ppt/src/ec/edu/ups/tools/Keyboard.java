package ec.edu.ups.tools;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private final int KEY_NUM = 256;
	private final boolean[] keys = new boolean[KEY_NUM];

	private boolean rock1;
	private boolean scissors1;
	private boolean paper1;

	private boolean rock2;
	private boolean scissors2;
	private boolean paper2;

	public Keyboard() {
		super();
	}

	public Keyboard(String rock1, String scissors1, String paper1, String rock2, String scissors2, String paper2) {

	}

	public boolean isRock1() {
		return rock1;
	}

	public boolean isScissors1() {
		return scissors1;
	}

	public boolean isPaper1() {
		return paper1;
	}

	public boolean isRock2() {
		return rock2;
	}

	public boolean isScissors2() {
		return scissors2;
	}

	public boolean isPaper2() {
		return paper2;
	}

	public void update() {
		rock1 = keys[KeyEvent.VK_A];
		paper1 = keys[KeyEvent.VK_S];
		scissors1 = keys[KeyEvent.VK_D];

		rock2 = keys[KeyEvent.VK_LEFT];
		paper2 = keys[KeyEvent.VK_UP];
		scissors2 = keys[KeyEvent.VK_RIGHT];
	}

	@Override
	public void keyTyped(KeyEvent e) {
//		System.out.println("k: " + e.getKeyChar());
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
