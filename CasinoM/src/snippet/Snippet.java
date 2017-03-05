package snippet;

public class Snippet {
	new ConfigH(this);
	new MessagesH(this);
	
	/* Job configs */
	
	new CitizenH(this);
	
	// Check config if enabled here
	
	for (org.bukkit.entity.Player player : Bukkit.getOnlinePlayers()) {
		new Player(player, player.getName(), 100, Job.CITIZEN);
	}
	
	running = true;
	while (running) {
	
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

