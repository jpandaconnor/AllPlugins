package co.uk.randompanda30.sao.view.selection;

import co.uk.randompanda30.sao.TEMP;
import co.uk.randompanda30.sao.items.GenericItems;
import co.uk.randompanda30.sao.items.ItemBuilder;
import co.uk.randompanda30.sao.view.base.ISelection;
import co.uk.randompanda30.sao.view.data.ViewNames;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Connor Brady on 03/10/2016.
 */
public class SELECT_DISTRICT implements ISelection {

    /*#
    Get districts from config
    Add player to temp
    Assign to editor
     */

    ArrayList<ItemStack> i = new ArrayList<>();
    Inventory inv;

    boolean next = true;
    int page = 1;
    int epp = 36;
    int si = (page - 1) * epp;
    int ei = si + epp;

    Player player;

    public SELECT_DISTRICT(Player player) {
        this.player = player;
        TEMP.selectionDistricts.put(player.getUniqueId(), this);

        List<String> districts = TEMP.playerhousingdisc.getStringList("districts");

        i.addAll(districts.stream().map(s -> ItemBuilder.buildItem(s, Material.BOOK, 1, 1, new ArrayList<>()))
                .collect(Collectors.toList()));
    }

    public void open() {
        inv = Bukkit.createInventory(null, 54, ViewNames.SELECTION_DISTRICT_MENU.value.replace(
                "%page", Integer.toString(page)
        ));

        inv.setItem(36, GenericItems.getItems().getBorder());
        inv.setItem(37, GenericItems.getItems().getBorder());
        inv.setItem(38, GenericItems.getItems().getBorder());
        inv.setItem(39, GenericItems.getItems().getBorder());
        inv.setItem(40, GenericItems.getItems().getBorder());
        inv.setItem(41, GenericItems.getItems().getBorder());
        inv.setItem(42, GenericItems.getItems().getBorder());
        inv.setItem(43, GenericItems.getItems().getBorder());
        inv.setItem(44, GenericItems.getItems().getBorder());

        si = (page - 1) * epp;
        ei = si + epp;

        if(ei > i.size()) {
            ei = i.size();
            next = false;
        }

        for(ItemStack is : i.subList(si, ei)) {
            inv.addItem(is);
        }

        if(next) {
            inv.setItem(46, GenericItems.getItems().getForward());
        }

        if(page != 1) {
            inv.setItem(45, GenericItems.getItems().getBack());
        }

        inv.setItem(52, GenericItems.getItems().getBackExit());
        inv.setItem(53, GenericItems.getItems().getExit());

        player.openInventory(inv);
    }

    public void nextPage() {
        next = true;
        page = page + 1;
        si = (page - 1) * epp;
        ei = si + epp;

        if(ei > i.size()) {
            ei = i.size();
            next = false;
        }

        inv = Bukkit.createInventory(null, 54, ViewNames.SELECTION_DISTRICT_MENU.value.replace(
                "%page", Integer.toString(page)
        ));

        inv.setItem(36, GenericItems.getItems().getBorder());
        inv.setItem(37, GenericItems.getItems().getBorder());
        inv.setItem(38, GenericItems.getItems().getBorder());
        inv.setItem(39, GenericItems.getItems().getBorder());
        inv.setItem(40, GenericItems.getItems().getBorder());
        inv.setItem(41, GenericItems.getItems().getBorder());
        inv.setItem(42, GenericItems.getItems().getBorder());
        inv.setItem(43, GenericItems.getItems().getBorder());
        inv.setItem(44, GenericItems.getItems().getBorder());

        for(ItemStack is : i.subList(si, ei)) {
            inv.addItem(is);
        }

        if(next) {
            inv.setItem(46, GenericItems.getItems().getForward());
        }

        if(page != 1) {
            inv.setItem(45, GenericItems.getItems().getBack());
        }

        inv.setItem(52, GenericItems.getItems().getBackExit());
        inv.setItem(53, GenericItems.getItems().getExit());

        player.openInventory(inv);
    }

    public void previousPage() {
        next = true;
        page = page - 1;
        si = (page - 1) * epp;
        ei = si + epp;

        if(ei > i.size()) {
            ei = i.size();
            next = false;
        }

        inv = Bukkit.createInventory(null, 54, ViewNames.SELECTION_DISTRICT_MENU.value.replace(
                "%page", Integer.toString(page)
        ));

        inv.setItem(36, GenericItems.getItems().getBorder());
        inv.setItem(37, GenericItems.getItems().getBorder());
        inv.setItem(38, GenericItems.getItems().getBorder());
        inv.setItem(39, GenericItems.getItems().getBorder());
        inv.setItem(40, GenericItems.getItems().getBorder());
        inv.setItem(41, GenericItems.getItems().getBorder());
        inv.setItem(42, GenericItems.getItems().getBorder());
        inv.setItem(43, GenericItems.getItems().getBorder());
        inv.setItem(44, GenericItems.getItems().getBorder());

        for(ItemStack is : i.subList(si, ei)) {
            inv.addItem(is);
        }

        if(next) {
            inv.setItem(46, GenericItems.getItems().getForward());
        }

        if(page != 1) {
            inv.setItem(45, GenericItems.getItems().getBack());
        }

        inv.setItem(52, GenericItems.getItems().getBackExit());
        inv.setItem(53, GenericItems.getItems().getExit());

        player.openInventory(inv);
    }

    public void resetItemInventory() {
        next = true;
        page = 1;
        i.clear();
    }
}