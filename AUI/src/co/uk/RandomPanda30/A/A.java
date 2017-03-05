package co.uk.RandomPanda30.A;

import co.uk.RandomPanda30.AUI.AUI;

public class A {

	private static AUI plugin;

	public static void setInstance(AUI aui) {
		plugin = aui;
	}

	public static AUI getInstance() {
		return plugin;
	}
}
