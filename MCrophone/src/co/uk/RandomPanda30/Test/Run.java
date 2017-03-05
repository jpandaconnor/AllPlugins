package co.uk.RandomPanda30.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class Run extends BukkitRunnable {

	public Main plugin;

	public Run (Main plugin) {
		this.plugin = plugin;
	}

	File test = null;

	Thread thread;

	@Override
	public void run() {

		thread = new Thread();
		thread.start();

		if (Main.test != null) {
			Bukkit.broadcastMessage(ChatColor.RED + "Test");
		}

		String name = "RandomPanda30";

		if (Bukkit.getPlayer(name) != null) {
			Bukkit.broadcastMessage(ChatColor.GREEN + "Test1");
			Player player = Bukkit.getPlayer(name);

			if (player.getItemInHand().getType().equals(Material.GRASS)) {
				Bukkit.broadcastMessage(ChatColor.RED + "Test2");
				AudioFormat format = new AudioFormat(8000.0f, 16, 1, true,
						true);
				TargetDataLine microphone;
				SourceDataLine speakers;
				try {
					Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "Test3");
					microphone = AudioSystem.getTargetDataLine(format);

					DataLine.Info info = new DataLine.Info(TargetDataLine.class,
							format);
					microphone = (TargetDataLine) AudioSystem.getLine(info);
					microphone.open(format);

					ByteArrayOutputStream out = new ByteArrayOutputStream();
					int numBytesRead;
					int CHUNK_SIZE = 1024;
					byte[] data = new byte[microphone.getBufferSize() / 5];
					microphone.start();

					int bytesRead = 0;
					DataLine.Info dataLineInfo = new DataLine.Info(
							SourceDataLine.class, format);
					speakers = (SourceDataLine) AudioSystem
							.getLine(dataLineInfo);
					speakers.open(format);
					speakers.start();
					while (bytesRead < 60000) {
						numBytesRead = microphone.read(data, 0, CHUNK_SIZE);
						bytesRead += numBytesRead;
						// write the mic data to a stream for use later
						out.write(data, 0, numBytesRead);
						// write mic data to stream for immediate playback
						speakers.write(data, 0, numBytesRead);

						Bukkit.broadcastMessage(
								ChatColor.BOLD + Integer.toString(bytesRead));
						Thread.sleep(100);
					}

					speakers.drain();
					speakers.close();
					microphone.close();

					Bukkit.broadcastMessage(ChatColor.BLUE + "Test4");
				} catch (LineUnavailableException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}