package co.uk.RandomPanda30.VShop.Events;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import co.uk.RandomPanda30.VShop.A;
import co.uk.RandomPanda30.VShop.B;
import co.uk.RandomPanda30.VShop.S;
import co.uk.RandomPanda30.VShop.Files.Messages;
import co.uk.RandomPanda30.VShop.Methods.EBC;
import co.uk.RandomPanda30.VShop.Methods.U;
import co.uk.RandomPanda30.VShop.Methods.X;

public class EB implements Listener {

	@SuppressWarnings("unchecked")
	@EventHandler
	public void onSignChangeEvent(SignChangeEvent event) {
		String[] lines = event.getLines();
		Location location = event.getBlock().getLocation();
		ArrayList<String> signLines = null;

		int lc = 0;

		if (lines[0].equalsIgnoreCase("[" + A.pdfFile.getName() + "]")
				|| lines[0].equalsIgnoreCase("[vs]")) {
			if (lines[1] != null && B.shopsC.contains(lines[1])) {
				String plot = "";
				if (EBC.signLocations.containsValue(location)) {
					for (Map.Entry<String, Location> e : EBC.signLocations
							.entrySet()) {
						if (e.getValue().equals(location)) {
							plot = e.getKey();
						}
					}

					if (U.getPlotState(plot).equals(S.FORSALE)) {
						signLines = (ArrayList<String>) Messages.SIGNS_FORSALE.value;
						String price = "$"
								+ Integer.toString(U.getPlotPrice(plot));
						for (String line : signLines) {
							if (lc <= 3) {
								String fm = X.formatMessage(line);
								if (fm.contains("price")) {
									fm = fm.replace("'price'", price);
								}
								event.setLine(lc, fm);
								event.getBlock().getState().update();
							}
							lc = lc + 1;
						}
					} else if (U.getPlotState(plot).equals(S.OWNED)) {
						signLines = (ArrayList<String>) Messages.SIGNS_OWNED.value;
						String playerName = U.getLastKnowName(plot);
						for (String line : signLines) {
							if (lc <= 3) {
								String fm = X.formatMessage(line);
								fm = fm.replace("'player'", playerName);
								fm = fm.replace("'price'",
										Integer.toString(U.getPlotPrice(plot)));
								event.setLine(lc, fm);
								event.getBlock().getState().update();
							}
							lc = lc + 1;
						}
					} else if (U.getPlotState(plot).equals(S.NENABLED)) {
						signLines = (ArrayList<String>) Messages.SIGNS_NENABLED.value;
						for (String line : signLines) {
							if (lc <= 3) {
								String fm = X.formatMessage(line);
								event.setLine(lc, fm);
								event.getBlock().getState().update();
							}
							lc = lc + 1;
						}
					}
				}
			}
		}
	}
}