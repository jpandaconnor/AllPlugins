package co.uk.RandomPanda30.Murge.Executor;

import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Executor.Base.CleanupBase;
import co.uk.RandomPanda30.Murge.Executor.Base.ExecutorBase;
import co.uk.RandomPanda30.Murge.Handlers.InworldHandler;
import co.uk.RandomPanda30.Murge.Handlers.SpawnHandler;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Stats.StatsHandler;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class DayExecutor implements ExecutorBase, CleanupBase {

	private static DayExecutor instance = new DayExecutor();
	public final InworldHandler general = new InworldHandler();
	public final SpawnHandler sh = new SpawnHandler();

	public static DayExecutor getExecutor() {
		return instance;
	}

	@Override
	public void execute() {
		for (Player player : general.getPlayers()) {
			if (StatsHandler.inSpectators(player.getUniqueId())) {
				sh.spawnAsPlayer(player);
			}
		}
		setPurge(false);
		playSiren();
		sendExecutorTitle();
		sendExecutorMessage();
		checkForEditing();
		updateScoreboard();
		setWorldTime(1000L);
		StatsHandler.incrementPurgeNo();
		general.sendEndStats();

		clearSpectatorList();
		clearTotalKills();
		clearPlayersKills();
	}

	@Override
	public void setPurge(boolean value) {
		MurgeData.setPurge(value);
	}

	@Override
	public void startTimer(int value) {
	}

	@Override
	public void playSiren() {
		general.sendSound("siren", 1, 1);
	}

	@Override
	public void sendExecutorMessage() {
		general.sendMessage((String) Murge.mMap.getStat(MessagesValues.ENDED));
	}

	@Override
	public void sendExecutorTitle() {
		general.sendTitle(StringMethods.formatMessage((String) Murge.mMap
				.getStat(MessagesValues.TITLE_PURGEENDED)), StringMethods
				.formatMessage((String) Murge.mMap
						.getStat(MessagesValues.SUBTITLE_PURGEENDED)), 30,
				100, 30);
	}

	@Override
	public void checkForEditing() {
		for (Player player : general.getPlayers()) {
			if (player == MurgeData.getEditingLocation()) {
				player.showPlayer(MurgeData.getEditingLocation());
			}
		}
	}

	@Override
	public void updateScoreboard() {
		general.sendScoreboard();
	}

	@Override
	public void clearSpectatorList() {
		StatsHandler.clearSpectators();
	}

	@Override
	public void clearTotalKills() {
		StatsHandler.resetKillNo();
	}

	@Override
	public void clearPlayersKills() {
		StatsHandler.clearPlayerKill();
	}

	@Override
	public void setWorldTime(long time) {
		MurgeData.getWorld().setTime(time);
	}
}