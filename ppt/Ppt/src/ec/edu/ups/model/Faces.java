package ec.edu.ups.model;

import java.awt.image.BufferedImage;

import ec.edu.ups.view.graphics.SpriteSheet;

public class Faces {

	private BufferedImage[] faces;
	private SpriteSheet spriteSheet;
	private int index;

	public Faces(String path, int sizeSprite, int nSprites) {

		index = 0;

		faces = new BufferedImage[nSprites];
		int n = faces.length;

		spriteSheet = new SpriteSheet(path, sizeSprite, false);

		for (int i = 0; i < n; i++) {
			faces[i] = spriteSheet.getSprites(i).getImage();
		}
	}

	public BufferedImage[] getFaces() {
		return faces;
	}

	public void setFaces(BufferedImage[] faces) {
		this.faces = faces;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public BufferedImage getCurrentFace() {
		return faces[index];
	}

}
