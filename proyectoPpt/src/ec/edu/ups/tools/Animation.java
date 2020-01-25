package ec.edu.ups.tools;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ec.edu.ups.controller.statemachine.game.GameRuleManager;

public class Animation {

	private BufferedImage[] frames;

	private int velocity;

	private boolean running;

	private int index;
	private int x;
	private int y;

	private long time;
	private long lastTime;
	
	private boolean infinite;
	private GameRuleManager gameRuleManager;

	public Animation(BufferedImage[] frames, int velocity) {

		this.frames = frames;
		this.velocity = velocity;

		time = 0;
		index = 0;

	}

	public Animation(BufferedImage[] frames, int velocity, int x, int y, boolean infinite, GameRuleManager gameRuleManager) {

		this.frames = frames;
		this.velocity = velocity;
		this.x = x;
		this.y = y;
		index = 0;
		this.infinite = infinite;
		this.gameRuleManager = gameRuleManager;
	}
	
	public void startAnimation() {
		index = 0;
		lastTime = System.nanoTime();
		running = true;
	}
	
	public void stopAnimation() {
		
		running = false;
	}

	public void update() {
		time += (System.nanoTime() - lastTime) / (1000000000 / velocity);

		if (time >= 1 && running) {
			time = 0;
			index++;
			if (index >= frames.length) {
				if (infinite) {
					index = 0;
				}else {
					gameRuleManager.getStatesAnimation()[1] = true;
					index = frames.length - 1;
					running = false;
				}
			}
			lastTime=System.nanoTime();
		}
	}
	
	public void print(Graphics g) {
		if (running) {
			g.drawImage(getCurrentFrame(), x - getCurrentFrame().getWidth()/2, y - getCurrentFrame().getHeight()/2, null);
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

	public boolean isInfinite() {
		return infinite;
	}

	public void setInfinite(boolean infinite) {
		this.infinite = infinite;
	}

	public BufferedImage[] getFrames() {
		return frames;
	}

	public void setFrames(BufferedImage[] frames) {
		this.frames = frames;
	}
	

}
