package co.uk.RandomPanda30.FunSao;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import co.uk.RandomPanda30.FunSao.Main.Sender;

public class CMD_ufab implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Sender s = new Sender(sender);
		if (args.length == 0) {
			s.sendMessage(Main.tag
					+ "&CError, you have use the command incorrectly. Usage: /ufab <Player name>");
			return true;
		}
		
		if (s.player) {
			if (!s.sendp.hasPermission("funsao.ufab")) {
				Main.sendMessage("&CHahahaha no", s.sendp);
				return true;
			}
		}

		if (Bukkit.getPlayer(args[0]) == null) {
			s.sendMessage("&CThis player is not online");
			return true;
		}

		Player player = Bukkit.getPlayer(args[0]);

		// player.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 5);
		// player.playEffect(player.getLocation(), Effect.SMOKE, 5);

		Firework fw = (Firework) player.getWorld()
				.spawnEntity(player.getLocation(), EntityType.FIREWORK);

		Firework fw1 = (Firework) player.getWorld().spawnEntity(
				player.getLocation().add(1, 0, 0), EntityType.FIREWORK);
		Firework fw2 = (Firework) player.getWorld().spawnEntity(
				player.getLocation().subtract(1, 0, 0), EntityType.FIREWORK);

		Firework fw3 = (Firework) player.getWorld().spawnEntity(
				player.getLocation().add(0, 0, 1), EntityType.FIREWORK);
		Firework fw4 = (Firework) player.getWorld().spawnEntity(
				player.getLocation().subtract(0, 0, 1), EntityType.FIREWORK);

		FireworkMeta fwm = fw.getFireworkMeta();

		FireworkEffect effect = FireworkEffect.builder().flicker(false)
				.withColor(Color.FUCHSIA).withFade(Color.FUCHSIA)
				.with(Type.BALL_LARGE).trail(true).build();

		fwm.addEffect(effect);

		fwm.setPower(2);

		fw.setFireworkMeta(fwm);
		fw1.setFireworkMeta(fwm);
		fw2.setFireworkMeta(fwm);
		fw3.setFireworkMeta(fwm);
		fw4.setFireworkMeta(fwm);
		
		return true;
	}

}
