package com.orinteractive.nighthawk;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class PlayerPortal {

	Vector v;
	Location l;
	
	public PlayerPortal(Vector v, Location l){
		this.v = v;
		this.l = l;
	}
	
	public void update(){
		for(Player p : Bukkit.getOnlinePlayers()){
			if(p.getLocation().toVector().isInSphere(v, 2)) p.teleport(l);
		}
	}
	
}
