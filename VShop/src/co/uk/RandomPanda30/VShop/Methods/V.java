package co.uk.RandomPanda30.VShop.Methods;

import org.bukkit.Location;

import co.uk.RandomPanda30.VShop.B;
import co.uk.RandomPanda30.VShop.S;

public class V {

	public static void createPlot(String name, Location pos1, Location pos2,
			int price) {
		B.shopsC.set(name, true);
		B.shopsC.set(name + ".pos1", X.compileLocation(pos1));
		B.shopsC.set(name + ".pos2", X.compileLocation(pos2));
		B.shopsC.set(name + ".price", price);
		B.shopsC.set(name + ".state", S.FORSALE.toString());
		B.shopsC.set(name + ".owner.uuid", "n/a");
		B.shopsC.set(name + ".owner.lastname", "n/a");
		B.shopsC.set(name + ".owner.time", "n/a");
		Z.saveShops();

		B.getKeys();
		EBC.loadSigns();
		U.updateSigns();
	}
}