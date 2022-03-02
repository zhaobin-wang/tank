package tank;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class Audio {

	byte[] b = new byte[1024 * 1024 * 15];


	public void loop() {
		try {

			while (true) {
				int len = 0;
				sourceDataLine.open(audioFormat, 1024 * 1024 * 15);
				sourceDataLine.start();
				//System.out.println(audioInputStream.markSupported());
				audioInputStream.mark(12358946);
				while ((len = audioInputStream.read(b)) > 0) {
					sourceDataLine.write(b, 0, len);
				}
				audioInputStream.reset();

				sourceDataLine.drain();
				sourceDataLine.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private AudioFormat audioFormat = null;
	private SourceDataLine sourceDataLine = null;
	private DataLine.Info dataLine_info = null;

	private AudioInputStream audioInputStream = null;

	public Audio(String fileName) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(Audio.class.getClassLoader().getResource(fileName));
			audioFormat = audioInputStream.getFormat();
			dataLine_info = new DataLine.Info(SourceDataLine.class, audioFormat);
			sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLine_info);
			//FloatControl volctrl=(FloatControl)sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);   
			//volctrl.setValue(-40);// 

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void play() {
		try {
			byte[] b = new byte[1024 * 5];
			int len = 0;
			sourceDataLine.open(audioFormat, 1024 * 5);
			sourceDataLine.start();
			//System.out.println(audioInputStream.markSupported());
			audioInputStream.mark(12358946);
			while ((len = audioInputStream.read(b)) > 0) {
				sourceDataLine.write(b, 0, len);
			}
			// audioInputStream.reset();

			sourceDataLine.drain();
			sourceDataLine.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void close() {
		try {
			audioInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//Audio a = new Audio("audio/explode.wav");
		//Audio a = new Audio("/Users/wangzhaobin/Downloads/tankProject/src/audio/war1.wav");
		//a.loop();
		play("/Users/wangzhaobin/Downloads/tankProject/src/audio/tank_fire.wav");

	}

	public static void play(String Filename) {

		try {

// 用输入流打开一音频文件

			InputStream in = new FileInputStream(Filename);//FIlename 是你加载的声音文件如(“game.wav”)

// 从输入流中创建一个AudioStream对象

			AudioStream as = new AudioStream(in);

			AudioPlayer.player.start(as);//用静态成员player.start播放音乐

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}