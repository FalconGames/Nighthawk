package com.orinteractive.nighthawk;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Nighthawk extends JavaPlugin{

	static HashMap<String, Location> QuickWarps;
	DiceShopListener dl;
	QuickWarpListener qw;
	//ShopListener sl;
	Timer t;
	
	@Override
	public void onEnable(){
		dl = new DiceShopListener();
		qw = new QuickWarpListener();
		//sl = new ShopListener();
		getServer().getPluginManager().registerEvents(dl, this);
		getServer().getPluginManager().registerEvents(qw, this);
		//getServer().getPluginManager().registerEvents(sl, this);
	}
	
	@Override
	public void onDisable(){
		t.cancel();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("nhcc")){
			if(args.length != 1){
				sender.sendMessage("Error! Wrong number of arguments!");
				return false;
			}
			else{
				Currency.create(args[0], this);
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("nhct")){
			if(args.length != 2){
				sender.sendMessage("Error! Wrong number of arguments!");
				return false;
			}
			else{
				Currency.setEarnTime(Float.parseFloat(args[0]), Float.parseFloat(args[1]));
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("nhcjp")){
			if(args.length != 1){
				sender.sendMessage("Error! Wrong number of arguments!");
				return false;
			}
			else{
				Currency.setJackpot(Float.parseFloat(args[0]));
				return true;
			}
			
		}
		else if(cmd.getName().equalsIgnoreCase("nhcs")){
			if(args.length != 1){
				sender.sendMessage("Error! Wrong number of arguments!");
				return false;
			}
			else{
				Currency.setLoginEarn(Float.parseFloat(args[0]));
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("nhcqw")){
			QuickWarps.put(args[0], new Location(sender.getServer().getWorlds().get(0), Float.parseFloat(args[0]), Float.parseFloat(args[1]), Float.parseFloat(args[2])));
			return true;
		}
		else if(cmd.getName().equalsIgnoreCase("nhmoney") && sender instanceof Player){
			Currency.getMoney(sender.getName());
		}
		return false;
	}
	
	public void initCurrency(){
		t = new Timer();
		t.scheduleAtFixedRate(new TimerTask(){
			public void run(){
				Currency.updateTime();
			}
		}, 0L, 60000L);
	}
	
	public static Location getLocation(String key){
		return QuickWarps.get(key);
	}
	
}
