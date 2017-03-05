package co.uk.randompanda30.sao.object;

/* 
   Created by panda on 20/08/16.
   
   Copyright 2016 JPanda (Connor Brady)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;

public class PlayerData {

    public GameMode gamemode;

    public ItemStack[] inventory;
    public ItemStack[] armour;

    public float exp;
    public int level;

    public double health;
    public int food;

    public Collection<PotionEffect> effects;

    public boolean allowFlight;
    public boolean flying;

    public PlayerData(Player player) {
        this.gamemode = player.getGameMode();
        this.inventory = player.getInventory().getContents();
        this.armour = player.getInventory().getArmorContents();
        this.exp = player.getExp();
        this.level = player.getLevel();
        this.health = player.getHealth();
        this.food = player.getFoodLevel();
        this.effects = player.getActivePotionEffects();
        this.allowFlight = player.getAllowFlight();
        this.flying = player.isFlying();
    }
}