import java.io.*;

import javax.sound.sampled.*;

public class Audio extends Thread{
	
	private Clip clip = null;
	private AudioInputStream audioStream = null;
	Thread thread;
	
	public Audio() {
		super();
	}
	
	public void playAudio(String pathName) {
		try {
			File audioFile = new File(pathName);
			audioStream = AudioSystem.getAudioInputStream(audioFile);
			
			clip = AudioSystem.getClip(); 
			clip.open(audioStream);
			clip.start();
		}
		catch (LineUnavailableException e) { e.printStackTrace(); }
		catch (UnsupportedAudioFileException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
	}

		@Override
		public void run() {
				playAudio("audio/JingleBellRock.wav");
	}
	
}
