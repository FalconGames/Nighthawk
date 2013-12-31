package com.orinteractive.nighthawk;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DiceShopListener implements Listener{
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(p.getItemInHand().getType().equals(Material.QUARTZ_BLOCK) && p.getItemInHand().getItemMeta().getDisplayName().equals("Dice") && rightClicked(e)){
			roll(p);
		}
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK && (e.getClickedBlock().equals(Material.SIGN) || e.getClickedBlock().equals(Material.SIGN_POST))){
			System.out.println("It's a sign!");
			Sign s = (Sign)e.getClickedBlock();
			if(s.getLines()[0].equals("NHSHOP BUY")){
				if(Currency.getMoney(e.getPlayer().getName()) >= Float.parseFloat(s.getLines()[1]));
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
	
	private boolean rightClicked(PlayerInteractEvent e){
		Action a = e.getAction();
		if(a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) return true;
		return false;
	}
	
	private void roll(Player p){
		Random r = new Random();
		switch(r.nextInt(10)){
		case 0:
		case 1:
		case 3:
			p.giveExpLevels(5);
			break;
		case 4:
		case 5:
			p.damage(20);
			break;
		case 6:
			p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 3));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 3));
			p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60, 3));
			p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 3));
			p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 600, 3));
			p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 600, 3));
			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 3));
			break;
		case 7:
			Currency.addMoney(p, Currency.getJackpot());
			System.out.println("Adding money!");
			break;
		case 8:
			Currency.addMoney(p, Currency.getJackpot()/2);
			System.out.println("Adding money!");
			break;
		case 9:
			Currency.addMoney(p, Currency.getJackpot()/4);
			System.out.println("Adding money!");
			break;
		case 10:
			p.setHealth(40);
			System.out.println("Adding health!");
		}
		
	}

}
