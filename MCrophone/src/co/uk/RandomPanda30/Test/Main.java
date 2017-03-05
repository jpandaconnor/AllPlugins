package co.uk.RandomPanda30.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class Main extends JavaPlugin {

	public Main plugin;

	public BukkitTask checker;

	public static File test = null;

	@Override
	public void onEnable() {
		plugin = this;

		// normalChecker = new DayTimer(this).runTaskTimer(this, 0, 20);

		checker = new Run(this).runTaskTimer(this, 0, 20);
	}

	@Override
	public void onDisable() {

	}

	public void byteToFile(byte[] bFile) {
		AudioInputStream source = null;
		AudioInputStream pcm;

		InputStream b_in = new ByteArrayInputStream(bFile);

		try {
			source = AudioSystem
					.getAudioInputStream(new BufferedInputStream(b_in));
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}

		pcm = AudioSystem.getAudioInputStream(AudioFormat.Encoding.PCM_SIGNED,
				source);
		test = new File("test");
		try {
			AudioSystem.write(pcm, Type.WAVE, test);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			source.close();
			pcm.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
