package co.uk.RandomPanda30.Murge.Events;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkull;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Collection.MiscCollection;
import co.uk.RandomPanda30.Murge.Collection.Effects.BleedCollection;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Stats.StatsHandler;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class OnEntityDamageByEntityEvent implements Listener {

	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
		Entity hurt = event.getEntity();
		Entity damager = event.getDamager();

		Player victim = null;
		UUID victimUUID = null;

		Player attacker = null;
		UUID attackerUUID = null;

		if (hurt instanceof Player) {
			victim = (Player) hurt;
			victimUUID = victim.getUniqueId();

			if (damager instanceof Player) {
				attacker = (Player) damager;
				attackerUUID = attacker.getUniqueId();

				if (StatsHandler.inSpectators(attackerUUID)) {
					event.setCancelled(true);
				}

				if (MurgeData.isPurge()) {
					if (!StatsHandler.inSpectators(attackerUUID)) {
						if (!StatsHandler.inCombatLog(victimUUID)) {
							StatsHandler.addCombatLog(victimUUID, attackerUUID);
							StringMethods.sendMessage((String) Murge.mMap
									.getStat(MessagesValues.INCOMBAT), victim);
							StringMethods
									.sendMessage((String) Murge.mMap
											.getStat(MessagesValues.INCOMBAT),
											attacker);
						}
						try {
							BleedCollection.getCollection().playEffect(victim);
						} catch (NoSuchMethodException | SecurityException
								| ClassNotFoundException
								| InstantiationException
								| IllegalAccessException
								| IllegalArgumentException
								| InvocationTargetException
								| NoSuchFieldException e) {
							e.printStackTrace();
						}
					} else {
						StringMethods.sendMessage((String) Murge.mMap
								.getStat(MessagesValues.SPECTATE_NOPVP),
								attacker);
						event.setCancelled(true);
					}
				} else {
					if (!MiscCollection.isPVPAllowed()) {
						StringMethods.sendMessage((String) Murge.mMap
								.getStat(MessagesValues.PVPNOTALLOWED),
								attacker);
						event.setCancelled(true);
					} else {
						try {
							BleedCollection.getCollection().playEffect(victim);
						} catch (NoSuchMethodException | SecurityException
								| ClassNotFoundException
								| InstantiationException
								| IllegalAccessException
								| IllegalArgumentException
								| InvocationTargetException
								| NoSuchFieldException e) {
							e.printStackTrace();
						}
					}
				}
			} else if (damager instanceof Projectile) {
				if (!(event.getEntity() instanceof Player)) {
					return;
				}

				if (((Projectile) damager).getShooter() instanceof Player) {
					attacker = (Player) ((Projectile) damager).getShooter();
					attackerUUID = attacker.getUniqueId();

					if (MurgeData.isPurge()) {
						if (!StatsHandler.inSpectators(attackerUUID)) {
							if (!StatsHandler.inCombatLog(victimUUID)) {
								StatsHandler.addCombatLog(victimUUID,
										attackerUUID);
								StringMethods.sendMessage((String) Murge.mMap
										.getStat(MessagesValues.INCOMBAT),
										victim);
								StringMethods.sendMessage((String) Murge.mMap
										.getStat(MessagesValues.INCOMBAT),
										attacker);
							}
						} else {
							StringMethods.sendMessage((String) Murge.mMap
									.getStat(MessagesValues.SPECTATE_NOPVP),
									attacker);
							event.setCancelled(true);
						}
					} else {
						if (!MiscCollection.isPVPAllowed()) {
							StringMethods.sendMessage((String) Murge.mMap
									.getStat(MessagesValues.PVPNOTALLOWED),
									attacker);
							event.setCancelled(true);
						}
					}
				} else if (((Projectile) damager).getShooter() instanceof Skeleton) {
					if (!MiscCollection.mobDamageCountAsCombat()) {
						if (!StatsHandler.inCombatLog(victimUUID)) {
							StatsHandler.addCombatLog(victimUUID, null);
							StringMethods.sendMessage((String) Murge.mMap
									.getStat(MessagesValues.INCOMBAT), victim);
						}
					}
				}
			} else if (damager instanceof Zombie) {
				if (!MiscCollection.mobDamageCountAsCombat()) {
					if (!StatsHandler.inCombatLog(victimUUID)) {
						StatsHandler.addCombatLog(victimUUID, null);
						StringMethods.sendMessage((String) Murge.mMap
								.getStat(MessagesValues.INCOMBAT), victim);
					}
				}
			} else if ((damager instanceof Wither)
					|| (damager instanceof WitherSkull)) {
				if (!MiscCollection.mobDamageCountAsCombat()) {
					if (!StatsHandler.inCombatLog(victimUUID)) {
						StatsHandler.addCombatLog(victimUUID, null);
						StringMethods.sendMessage((String) Murge.mMap
								.getStat(MessagesValues.INCOMBAT), victim);
					}
				}
			} else if (damager instanceof Witch) {
				if (!MiscCollection.mobDamageCountAsCombat()) {
					if (!StatsHandler.inCombatLog(victimUUID)) {
						StatsHandler.addCombatLog(victimUUID, null);
						StringMethods.sendMessage((String) Murge.mMap
								.getStat(MessagesValues.INCOMBAT), victim);
					}
				}
			} else if (damager instanceof Creeper) {
				if (!MiscCollection.mobDamageCountAsCombat()) {
					if (!StatsHandler.inCombatLog(victimUUID)) {
						StatsHandler.addCombatLog(victimUUID, null);
						StringMethods.sendMessage((String) Murge.mMap
								.getStat(MessagesValues.INCOMBAT), victim);
					}
				}
			}
		}
	}
}