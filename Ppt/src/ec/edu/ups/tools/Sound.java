package ec.edu.ups.tools;

import javax.sound.sampled.Clip;

public class Sound {
	
	final private Clip sound;
	
	public Sound(final String path) {
		this.sound = LoadResources.loadSound(path);
	}
	
	public void play() {
		this.sound.stop();
		this.sound.flush();
		this.sound.setMicrosecondPosition(0);
		this.sound.start();
	}
	
	public void loop() {
		this.sound.stop();
		this.sound.flush();
		this.sound.setMicrosecondPosition(0);
		this.sound.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public Long getDuration() {
		return sound.getMicrosecondLength();
	}

}
