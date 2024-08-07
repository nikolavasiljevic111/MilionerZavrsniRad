package paket1;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Zvuci {
	
	static Zvuci instance;
	static Clip clip;
	private float prethodnaJacina;
	float trenutnaJacina = -10.0f;
	FloatControl fc;
	private boolean mute = false;

	private Zvuci() { }

	public static Zvuci getInstance() {
		if (instance == null) {
			instance = new Zvuci();
		}
		return instance;
	}

	public void setFile(URL url) {
		try {
			if (clip != null && clip.isRunning()) {
				clip.stop();
				clip.close();
			}
			AudioInputStream sound = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(sound);
			fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			applyVolume();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void play() {
		if (clip != null) {
			applyVolume();
			clip.setFramePosition(0);
			clip.start();
		}
	}

	public void loop() {
		if (clip != null) {
			applyVolume();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}

	public void stop() {
		if (clip != null) {
			clip.stop();
		}
	}

	public void pojacajZvuk() {
		trenutnaJacina += 1.0f;
		if (trenutnaJacina > 6.0f) {
			trenutnaJacina = 6.0f;
		}
		applyVolume();
		Frame.getSlider().setValue((int)trenutnaJacina);
	}

	public void smanjiZvuk() {
		trenutnaJacina -= 1.0f;
		if (trenutnaJacina < -80.0f) {
			trenutnaJacina = -80.0f;
		}
		applyVolume();
		Frame.getSlider().setValue((int)trenutnaJacina);
	}

	public void muteZvuk() {
		if (!mute) {
			prethodnaJacina = trenutnaJacina;
			trenutnaJacina = -80.0f;
			mute = true;
		} else {
			trenutnaJacina = prethodnaJacina;
			mute = false;
		}
		applyVolume();
		Frame.getSlider().setValue((int)trenutnaJacina);
	}

	public void applyVolume() {
		if (fc != null) {
			fc.setValue(trenutnaJacina);
		}
	}

	public void pustiMuziku(URL url) {
		setFile(url);
		play();
	}

	public void pustiMuzikuRepeat(URL url) {
		setFile(url);
		play();
		loop();
	}

	public float getTrenutnaJacina() {
		return trenutnaJacina;
	}

	public void setTrenutnaJacina(float trenutnaJacina) {
		this.trenutnaJacina = trenutnaJacina;
	}
}
