package fr.mcstudio.menu;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Used to play music.
 */
public class Musique {
	
	// The constructor of the class Musique. It is used to initialize the variables.
	public Musique(String url) {  		
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		} 
 
		try {
			audioStream = AudioSystem.getAudioInputStream(new File(url).getAbsoluteFile());
			clip.open(audioStream);
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} 
		((FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(0);	
	}
	
	// A variable that is used to store the audio file.
	Clip clip = null; 	     

	// A variable that is used to store the audio file.
	AudioInputStream audioStream;

	// Setting the volume to 2.
	float level = 2f; 
	
	/**
	 * It plays the music
	 */
	public void jouerMusique() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.start();
		((FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(0);
	}
	
	/**
	 * This function stops the music
	 */
	public void arreterMusique() {
		clip.stop();
	}
	
	/**
	 * It increases the volume of the music by 2
	 */
	public void augVolMusique() {
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		if(level<=5.9999995)
			level = level+2 ;
		gainControl.setValue(level);
	}
	
	/**
	 * This function is used to decrease the volume of the music
	 */
	public void dimVolMusique() {
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(level-2f);
		level = level-2 ;
	}

}
