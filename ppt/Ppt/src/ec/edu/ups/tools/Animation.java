package ec.edu.ups.tools;

import java.awt.image.BufferedImage;

public class Animation {

	private BufferedImage[] frames;

	private int velocity;

	private boolean running;

	private int index;
	private int x;
	private int y;

	private long time;
	private long lastTime;

	public Animation(BufferedImage[] frames, int velocity) {

		this.frames = frames;
		this.velocity = velocity;

		time = 0;
		lastTime = System.nanoTime();
		index = 0;
		running = true;

	}

	public Animation(BufferedImage[] frames, int velocity, int x, int y) {

		this.frames = frames;
		this.velocity = velocity;
		this.x = x;
		this.y = y;

		index = 0;
		running = true;

	}

	public void update() {
		time += (System.nanoTime() - lastTime) / 1000000000;

		if (time > velocity) {
			time = 0;
			index++;
			if (index >= frames.length) {
				running = false;
			}
		}
	}

	public BufferedImage getCurrentFrame() {
		return frames[index];
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
