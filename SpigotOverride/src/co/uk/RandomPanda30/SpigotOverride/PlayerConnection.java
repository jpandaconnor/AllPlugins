package co.uk.RandomPanda30.SpigotOverride;

import net.minecraft.server.v1_7_R4.*;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class PlayerConnection implmenets ItemChecker extends net.minecraft.server.v1_7_R4.PlayerConnection {

    public PlayerConnection(MinecraftServer minecraftserver, NetworkManager networkmanager, EntityPlayer entityplayer) {
        super(minecraftserver, networkmanager, entityplayer);
    }

    @Override
    public void a(PacketPlayInHeldItemSlot packetplayinhelditemslot) {
        if(!this.player.dead) {
            if(packetplayinhelditemslot.c() >= 0 && packetplayinhelditemslot.c() < PlayerInventory.getHotbarSize()) {
                PlayerItemHeldEvent event = new PlayerItemHeldEvent(this.getPlayer(), this.player.inventory.itemInHandIndex, packetplayinhelditemslot.c());
                Main.plugin.getServer().getPluginManager().callEvent(event);
                if(event.isCancelled()) {
                    this.sendPacket(new PacketPlayOutHeldItemSlot(this.player.inventory.itemInHandIndex));
                    this.player.v();
                    return;
                }

                this.player.inventory.itemInHandIndex = packetplayinhelditemslot.c();
                this.player.v();
            }
        }
    }
}