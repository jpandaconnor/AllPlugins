package co.uk.RandomPanda30.Murge.Kits;

import java.util.UUID;

import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Util.KeyUtil;
import co.uk.RandomPanda30.Murge.Util.KeyUtil.Mode;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class KitSetup {

	public enum KitCreationSteps {
		NAME, COOLDOWN;
	}

	private KitCreationSteps step;
	private String name;
	private String cooldown;
	private Player player;
	private UUID uuid;

	public KitSetup (Player player) {
		this.player = player;
		init();
	}

	public void init() {
		this.step = KitCreationSteps.NAME;
		this.uuid = player.getUniqueId();
		KitTemp.addAdding(uuid);
		StringMethods.sendMessage(
				(String) Murge.mMap.getStat(MessagesValues.SETUP_ENTERKITNAME),
				player);
	}

	public void next() {
		switch (step) {
		case NAME:
			this.step = KitCreationSteps.COOLDOWN;
			StringMethods.sendMessage((String) Murge.mMap
					.getStat(MessagesValues.SETUP_ENTERKITCOOLDOWN), player);
			break;
		case COOLDOWN:
			end();
			break;
		}
	}

	public void end() {
		KeyUtil keyUtil = new KeyUtil(5, Mode.ALPHANUMERIC);
		keyUtil.generate();
		Kit kit = new Kit(this.name, null, null, null, "." + name,
				this.cooldown, keyUtil.getKey(), false);
		KitTemp.addKit(kit);
		KitTemp.removeAdding(uuid);
		StringMethods.sendMessage(
				(String) Murge.mMap.getStat(MessagesValues.SETUP_KITMADE),
				player);
	}

	public KitCreationSteps getStep() {
		return step;
	}

	public void setStep(KitCreationSteps kitCreationSteps) {
		this.step = kitCreationSteps;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setCooldown(String cooldown) {
		this.cooldown = cooldown;
	}

	public String getCooldown() {
		return cooldown;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}
}