package co.uk.RandomPanda30.WastedMC.Methods;

import net.minecraft.server.v1_7_R4.ChatSerializer;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.spigotmc.ProtocolInjector;

public class T {

	private static int VERSION = 47;

	public static void st(Player player, String title) {
		if (((CraftPlayer) player).getHandle().playerConnection.networkManager
				.getVersion() < VERSION) {
			return;
		}
		((CraftPlayer) player).getHandle().playerConnection
				.sendPacket(new ProtocolInjector.PacketTitle(
						ProtocolInjector.PacketTitle.Action.TITLE,
						ChatSerializer.a("{\"text\": \"\"}").a(title)));
	}
}