package co.uk.RandomPanda30.Murge.Executor.Base;

public interface ExecutorBase {

	public void execute();

	public void setPurge(boolean value);

	public void startTimer(int value);

	public void playSiren();

	public void sendExecutorMessage();

	public void sendExecutorTitle();

	public void checkForEditing();

	public void updateScoreboard();

	public void setWorldTime(long time);
}