package com.orinteractive.nighthawk;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ShopListener implements Listener{

	public void onPlayerInteract(PlayerInteractEvent e){
		
		System.out.println("Player interacted!");
		Player p = e.getPlayer();
		Action a = e.getAction();
		if(a == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().equals(Material.SIGN)){
			Sign s = (Sign)e.getClickedBlock();
			if(s.getLines()[0].equals("NHSHOP BUY")){
				Currency.addMoney(p, -Float.parseFloat(s.getLines()[1]));
				p.getInventory().addItem(new ItemStack(Material.getMaterial(s.getLines()[2]), Integer.parseInt(s.getLines()[3])));
			}
			else if(s.getLines()[0].equals("NHSHOP SELL")){
				if(p.getInventory().contains(new ItemStack(Material.getMaterial(s.getLines()[2]), Integer.parseInt(s.getLines()[3])))){
					p.getInventory().remove(new ItemStack(Material.getMaterial(s.getLines()[2]), Integer.parseInt(s.getLines()[3])));
					Currency.addMoney(p, Float.parseFloat(s.getLines()[1]));
				}
			}
		}
		
	}
	
}
