package com.ediTv2.Basics.Handlers;

import org.bukkit.event.Listener;

import com.ediTv2.Basics.Basics;

public class ListenerH {
	
	public static void addListener(Listener listener) {
		Basics.listeners.add(listener);
	}
}
