package co.uk.RandomPanda30.Murge.Handlers;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_8_R3.PlayerConnection;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class TitleHandler {

	private static TitleHandler ti = new TitleHandler();

	public static TitleHandler getHandler() {
		return ti;
	}

	@SuppressWarnings("rawtypes")
	public void sendTitle(Player player, String title, String subtitle,
			int fadeIn, int stay, int fadeOut) {
		CraftPlayer craftplayer = (CraftPlayer) player;
		PlayerConnection connection = craftplayer.getHandle().playerConnection;
		IChatBaseComponent titleJSON = ChatSerializer.a("{'text': '"
				+ ChatColor.translateAlternateColorCodes('&', title) + "'}");
		IChatBaseComponent subtitleJSON = ChatSerializer.a("{'text': '"
				+ ChatColor.translateAlternateColorCodes('&', subtitle) + "'}");
		Packet length = new PacketPlayOutTitle(EnumTitleAction.TIMES,
				titleJSON, fadeIn, stay, fadeOut);
		Packet titlePacket = new PacketPlayOutTitle(EnumTitleAction.TITLE,
				titleJSON, fadeIn, stay, fadeOut);
		Packet subtitlePacket = new PacketPlayOutTitle(
				EnumTitleAction.SUBTITLE, subtitleJSON, fadeIn, stay, fadeOut);
		connection.sendPacket(titlePacket);
		connection.sendPacket(length);
		connection.sendPacket(subtitlePacket);
	}

	public void sendActionBar(Player player, String message) {
		IChatBaseComponent cbc = ChatSerializer.a("{\"text\": \"" + message
				+ "\"}");
		PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte) 2);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(ppoc);
	}
}