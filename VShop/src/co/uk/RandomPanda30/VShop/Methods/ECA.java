package co.uk.RandomPanda30.VShop.Methods;

import org.bukkit.block.Block;

public class ECA {

	public static boolean isSign(Block block) {
		boolean t = false;
		switch (block.getType()) {
		case SIGN:
			t = true;
			break;
		case WALL_SIGN:
			t = true;
			break;
		case SIGN_POST:
			t = true;
			break;
		default:
			t = false;
			break;
		}
		return t;
	}
}