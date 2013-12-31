package com.orinteractive.nighthawk;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class QuickWarpListener implements Listener{

	ArrayList<PlayerPortal> pps = new ArrayList<PlayerPortal>();
	
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e){
		System.out.println("Player threw an item!");
		
		Item i = e.getItemDrop();
		if(i.getType().equals(Material.STONE_PLATE) && i.getItemStack().getItemMeta().getDisplayName().startsWith("QuickWarp")){
		
			String s = i.getItemStack().getItemMeta().getDisplayName();
			s.replace("QuickWarp ", "");
			Location l = i.getLocation();
			i.remove();
			pps.add(new PlayerPortal(l.toVector(), Nighthawk.getLocation(s)));
			
		}
		
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		System.out.println("Player moved!");
		for(PlayerPortal pp : pps){
			pp.update();
		}
	}
	
}
