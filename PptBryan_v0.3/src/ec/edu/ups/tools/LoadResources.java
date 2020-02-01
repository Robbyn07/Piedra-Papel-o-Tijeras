package ec.edu.ups.tools;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class LoadResources {

    public static BufferedImage loadOpaqueImage(final String path) {
    	Image image = null;
    	
    	try {
    		image = ImageIO.read(ClassLoader.class.getResource(path));
    	} catch (IOException ex) {
    		ex.printStackTrace();
    	}

    	GraphicsConfiguration graphConfig = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
    										.getDefaultConfiguration();

    	BufferedImage buffImage = graphConfig.createCompatibleImage(image.getWidth(null), image.getHeight(null),
    								Transparency.OPAQUE);
	
    	Graphics g = buffImage.getGraphics();
	  	g.drawImage(image, 0, 0, null);
    	g.dispose();
    	
    	return buffImage;
    }

    public static BufferedImage loadTranslucentImage(final String path) {
    	Image image = null;

	
    	try {
    		image = ImageIO.read(ClassLoader.class.getResource(path));	
    	} catch (Exception ex) {
	    	System.out.println("ENTRAAA error\n" + ex.toString());
	   	}

    	GraphicsConfiguration graphConfig = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
    										.getDefaultConfiguration();

	
    	BufferedImage buffImage = graphConfig.createCompatibleImage(image.getWidth(null), image.getHeight(null),
    								Transparency.TRANSLUCENT);
	
    	Graphics g = buffImage.getGraphics();
    	g.drawImage(image, 0, 0, null);
    	g.dispose();

    	return buffImage;
    }

    public static String readTextFile(final String path) {
    	String content = "";

    	InputStream stream = ClassLoader.class.getResourceAsStream(path);
    	BufferedReader read = new BufferedReader(new InputStreamReader(stream));

	   	String line;
	
    	try {
    		
			while ((line = read.readLine()) != null) {
				content += line;
		    }
		
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally {
	    
    		try {
		
    			if (stream != null) {
    				stream.close();
				}

				if (read != null) {
					read.close();
				}
				
    		} catch (IOException ex) {
	    		ex.printStackTrace();
    		}
    		
    	}

    	return content;
    }

}
