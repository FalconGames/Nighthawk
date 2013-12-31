package com.orinteractive.nighthawk;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class WalletRegisterListener implements Listener{

	@EventHandler
	public void onPlayerLogin(PlayerJoinEvent e){
		Currency.addWallet(e.getPlayer());
	}
	
}
