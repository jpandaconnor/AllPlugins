package co.uk.RandomPanda30.CasinoM.Methods;

import java.util.Random;

public class RandomMethods {

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int ran = rand.nextInt((max - min) + 1) + min;
		return ran;
	}
}