package fr.mcstudio.menu;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Musique {
	
	Clip clip = null; 	            	
	AudioInputStream audioStream;
	float level ; 
	
	public Musique(String url)
	{  		
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
 
		try {
			audioStream = AudioSystem.getAudioInputStream(new File(url).getAbsoluteFile());
			clip.open(audioStream);
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
			
		this.level = clip.getLevel() ; 
	}
	
	public void jouerMusique()
	{
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.start();
	}
	
	public void arreterMusique()
	{
		clip.stop();
	}
	
	public void augVolMusique()
	{
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		if(level<=5.9999995)
			level = level+2 ;
		gainControl.setValue(level);
		

	}
	
	public void dimVolMusique()
	{
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(level-2f);
		level = level-2 ;
		
	}

}
