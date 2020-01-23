package ec.edu.ups.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Element {

	private int pointX;
	private int pointY;

	private int pointXF;
	private int pointYF;

	private BufferedImage image;

	private boolean selected;

	private char option;

	private Faces faces;

	public Element(int pointX, int pointY, BufferedImage image, boolean selected, char option, String facesPath,
			int sizeFaces, int nFaces, int pointXF, int pointYF) {
		super();
		this.pointX = pointX;
		this.pointY = pointY;
		this.image = image;
		this.selected = selected;
		this.option = option;

		this.pointXF = pointXF;
		this.pointYF = pointYF;

		faces = new Faces(facesPath, sizeFaces, nFaces);
	}

	public void updateFace(int index) {
		faces.setIndex(index);
	}

	public void paint(Graphics g) {
		g.drawImage(faces.getCurrentFace(), pointXF, pointYF, null);
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

	public Faces getFaces() {
		return faces;
	}

	public void setFaces(Faces faces) {
		this.faces = faces;
	}

}
