package co.uk.RandomPanda30.Murge.Executor;

import org.bukkit.entity.Player;

import co.uk.RandomPanda30.Murge.Murge;
import co.uk.RandomPanda30.Murge.MurgeData;
import co.uk.RandomPanda30.Murge.Checkers.PurgeTimer;
import co.uk.RandomPanda30.Murge.Executor.Base.ExecutorBase;
import co.uk.RandomPanda30.Murge.Handlers.InworldHandler;
import co.uk.RandomPanda30.Murge.Methods.StringMethods;
import co.uk.RandomPanda30.Murge.Values.Enums.MessagesValues;

public class PurgeExecutor implements ExecutorBase {

	private static PurgeExecutor instance = new PurgeExecutor();
	public final InworldHandler general = new InworldHandler();

	public static PurgeExecutor getExecutor() {
		return instance;
	}

	@Override
	public void execute() {
		setPurge(true);
		startTimer(0);
		playSiren();
		sendExecutorTitle();
		sendExecutorMessage();
		checkForEditing();
		updateScoreboard();
		setWorldTime(13000L);
	}

	@Override
	public void setPurge(boolean value) {
		MurgeData.setPurge(value);
	}

	@Override
	public void startTimer(int value) {
		if (value == 0) {
			Murge.purgeChecker = new PurgeTimer(MurgeData.getPlugin())
					.runTaskTimer(MurgeData.getPlugin(), 0, 20);
		}
	}

	@Override
	public void playSiren() {
		general.sendSound("siren", 1, 1);
	}

	@Override
	public void sendExecutorMessage() {
		general.sendMessage((String) Murge.mMap.getStat(MessagesValues.STARTED));
	}

	@Override
	public void sendExecutorTitle() {
		general.sendTitle(StringMethods.formatMessage((String) Murge.mMap
				.getStat(MessagesValues.TITLE_PURGESTARTED)), StringMethods
				.formatMessage((String) Murge.mMap
						.getStat(MessagesValues.SUBTITLE_PURGESTARTED)), 30,
				100, 30);
	}

	@Override
	public void checkForEditing() {
		for (Player player : general.getPlayers()) {
			if (player == MurgeData.getEditingLocation()) {
				StringMethods.sendMessage((String) Murge.mMap
						.getStat(MessagesValues.STILLEDITING), player);
				player.hidePlayer(MurgeData.getEditingLocation());
			}
		}
	}

	@Override
	public void updateScoreboard() {
		general.sendScoreboard();
	}

	@Override
	public void setWorldTime(long time) {
		MurgeData.getWorld().setTime(13000L);
	}
}