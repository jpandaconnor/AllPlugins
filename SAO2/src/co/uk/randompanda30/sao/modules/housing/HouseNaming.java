package co.uk.randompanda30.sao.modules.housing;

import co.uk.randompanda30.sao.SAO;
import co.uk.randompanda30.sao.config.Messages;
import co.uk.randompanda30.sao.items.ItemBuilder;
import co.uk.randompanda30.sao.reflection.AnvilGUI;
import co.uk.randompanda30.sao.string.Dispatch;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Connor Brady on 05/10/2016.
 */
public class HouseNaming {

    private Player player;

    public HouseNaming(Player player) {
        this.player = player;
        start();
    }

    private void start() {
        player.closeInventory();

        // Message here
        player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1);
        Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(SAO.getPlugin(), () -> {
            AnvilGUI gui = new AnvilGUI(player, event -> {
                if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
                    if (event.getName() != null || !event.getName().equals("w ") || !event.getName().isEmpty()) {
                        event.setWillClose(true);
                        event.setWillDestroy(true);

                        // stop(event.getName());
                    } else {
                        Dispatch.sendMessage((String) Messages.MessagesValues.
                                MODULES_PLAYERHOUSING_DISTRICTCREATE_NOTEXT.value, player);

                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);

                        event.setWillClose(true);
                        event.setWillDestroy(true);
                    }
                } else if (event.getSlot() == AnvilGUI.AnvilSlot.INPUT_LEFT) {
                    event.setWillClose(false);
                    event.setWillDestroy(false);
                } else if (event.getSlot() == AnvilGUI.AnvilSlot.INPUT_RIGHT) {
                    event.setWillClose(false);
                    event.setWillDestroy(false);
                }
            });

            gui.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, ItemBuilder.buildItem("Information", Material.PAPER,
                    1, 0, new ArrayList<String>() {
                        {
                            add("%TClick on the output name tag to create a");
                            add("%Tdistrict with that name");
                            add(" ");
                            add("%TIf the input is empty, nothing will be created");
                            add("  ");
                            add("%TTo exit, click outside the inventory or exit as normal");
                        }
                    }));

            gui.open();
        }, 100L);
    }
}