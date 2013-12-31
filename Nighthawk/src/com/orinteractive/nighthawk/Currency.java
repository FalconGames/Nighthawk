package com.orinteractive.nighthawk;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Currency {

	private static String Name;
	private static float onfirstlogin, timetillearn, amountearn, jackpot;
	private static HashMap<String, Float> wallet, timeOn;
	
	public static void create(String name, Nighthawk n){
		Name = name;
		onfirstlogin = 0.0f;
		timetillearn = 0.0f;
		amountearn = 0.0f;
		jackpot = 0.0f;
		wallet = new HashMap<String, Float>();
		timeOn = new HashMap<String, Float>();
		n.initCurrency();
	}
	
	public static void createWalletUser(String playerName){
		wallet.put(playerName, onfirstlogin);
		timeOn.put(playerName, 0.0f);
	}
	
	public static void setName(String name){
		Name = name;
	}
	
	public static void setLoginEarn(float amount){
		onfirstlogin = amount;
	}
	
	public static void setEarnTime(float minutes, float amount){
		timetillearn = minutes;
		amountearn = amount;
	}
	
	public static void setJackpot(float amount){
		jackpot = amount;
	}
	
	public static float getJackpot(){
		return jackpot;
	}
	
	public static void addMoney(Player p, float amount){
		if(wallet.containsKey(p.getName())) wallet.put(p.getName(), wallet.get(p.getName()) + amount);
	}
	
	public static float getMoney(String name){
		return wallet.get(name);
	}
	
	public static void updateTime(){
		for(Player p : Bukkit.getOnlinePlayers()){
			if(timeOn.containsKey(p.getName())){
				timeOn.put(p.getName(), timeOn.get(p.getName()) + 1);
				if(timeOn.get(p.getName()) >= timetillearn){
					timeOn.put(p.getName(), 0.0f);
					wallet.put(p.getName(), wallet.get(p.getName()) + amountearn);
				}
			}
		}
	}

	public static void addWallet(Player p) {

		wallet.put(p.getName(), onfirstlogin);
		timeOn.put(p.getName(), 0.0f);
		
	}
	
}
