package co.uk.RandomPanda30.Murge.Collection.Bases;

public interface RegenerationBase {
	
	public boolean isEnabled();
	
	public void setEnabled(boolean c);

	public void loadData();
	
	public void dumpData();
	
	public void checkData();
}