/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.view.graphics;

import java.awt.image.BufferedImage;

import ec.edu.ups.view.graphics.Sprite;
import ec.edu.ups.tools.LoadResources;

public class SpriteSheet {

	final private int widthSheetPixels;
    final private int heightSheetPixels;

    final private int widthSheetSprite;
    final private int heightSheetSprite;

    final private int widthSprite;
    final private int heightSprite;

    // final private BufferedImage image;
    final private Sprite[] sprites;

    public Sprite getSprites(final int index) {
	return sprites[index];
    }

    public Sprite getSprites(final int x, final int y) {
	return sprites[x + y * widthSheetSprite];
    }

    public SpriteSheet(final String path, final int sizeSprite, final boolean opaqueSheet) {

	final BufferedImage image;

	if (opaqueSheet) {
	    image = LoadResources.loadOpaqueImage(path);
	} else {
	    image = LoadResources.loadTranslucentImage(path);
	}

	widthSheetPixels = image.getWidth();
	heightSheetPixels = image.getHeight();

	widthSheetSprite = widthSheetPixels / sizeSprite;
	heightSheetSprite = heightSheetPixels / sizeSprite;

	widthSprite = sizeSprite;
	heightSprite = sizeSprite;

	sprites = new Sprite[widthSheetSprite * heightSheetSprite];

	fillSpritesFromImage(image);

    }

    public SpriteSheet(final String path, final int widthSprite, final int heightSprite, final boolean opaqueSheet) {
	final BufferedImage image;

	if (opaqueSheet) {
	    image = LoadResources.loadOpaqueImage(path);
	} else {
	    image = LoadResources.loadTranslucentImage(path);
	}

	widthSheetPixels = image.getWidth();
	heightSheetPixels = image.getHeight();

	widthSheetSprite = widthSheetPixels / widthSprite;
	heightSheetSprite = heightSheetPixels / heightSprite;

	this.widthSprite = widthSprite;
	this.heightSprite = heightSprite;

	sprites = new Sprite[widthSheetSprite * heightSheetSprite];
	fillSpritesFromImage(image);

    }

    private void fillSpritesFromImage(final BufferedImage image) {
	for (int y = 0; y < heightSheetSprite; y++) {
	    for (int x = 0; x < widthSheetSprite; x++) {

		final int pointX = x * widthSprite;
		final int pointY = y * heightSprite;

		sprites[x + y * widthSheetSprite] = new Sprite(
			image.getSubimage(pointX, pointY, widthSprite, heightSprite));
	    }
	}
    }
	
}
