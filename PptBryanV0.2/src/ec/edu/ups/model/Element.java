package ec.edu.ups.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ec.edu.ups.controller.statemachine.GameState;
import ec.edu.ups.controller.statemachine.StateManager;

public class Element implements GameState {

	private int pointX;
	private int pointY;

	private BufferedImage image;

	private boolean selected;

	private char option;

	public Element(int pointX, int pointY, BufferedImage image, 
					boolean selected, char option) {
		super();
		this.pointX = pointX;
		this.pointY = pointY;
		this.image = image;
		this.selected = selected;
		this.option = option;
	}

	public int getPointX() {
		return pointX;
	}

	public void setPointX(int pointX) {
		this.pointX = pointX;
	}

	public int getPointY() {
		return pointY;
	}

	public void setPointY(int pointY) {
		this.pointY = pointY;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public char getOption() {
		return option;
	}

	public void setOption(char option) {
		this.option = option;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public void update(StateManager stateManager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void print(Graphics g) {
		g.drawImage(getImage(), pointX, pointY, null);
		g.dispose();
	}

}
