package ec.edu.ups.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Element {

	private int pointX;
	private int pointY;

	private double velocityX;
	private double velocityY;

	private int width;
	private int height;

	private int pointXF;
	private int pointYF;

	private BufferedImage image;

	private boolean selected;

	private char option;

	private Faces faces;

	private final Rectangle edge;

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

		this.width = image.getWidth();
		this.height = image.getHeight();

		faces = new Faces(facesPath, sizeFaces, nFaces);

		this.edge = new Rectangle(this.pointX, this.pointY, width, height);

		this.velocityX = 0;
		this.velocityY = 0;
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
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

	public Rectangle getEdge() {
		return edge;
	}

	public void updateFace(int index) {
		faces.setIndex(index);
	}

	public double getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}

	public int getPointXF() {
		return pointXF;
	}

	public void setPointXF(int pointXF) {
		this.pointXF = pointXF;
	}

	public int getPointYF() {
		return pointYF;
	}

	public void setPointYF(int pointYF) {
		this.pointYF = pointYF;
	}

	public void paint(Graphics g) {
		g.drawImage(image, pointX, pointY, null);
		edge.setRect(pointX, pointY, width, height);
		g.drawImage(faces.getCurrentFace(), pointX + pointXF, pointY + pointYF, null);
	}

	public boolean collision(Rectangle edge) {
		if (this.edge.intersects(edge)) {
			return true;
		}

		return false;
	}

}
