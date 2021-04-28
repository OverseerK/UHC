package com.overseer;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class Event implements Listener {

	@EventHandler
	public void uhcGraceDamage(EntityDamageByEntityEvent e) {
		Entity victim = e.getEntity();
		Entity damager = e.getDamager();
		if (victim instanceof Player && damager instanceof Player && com.overseer.Main.Game == true) {
			e.setCancelled(true);
			damager.sendMessage("§c평화 기간일 때 다른 사람을 공격할 수는 없습니다!");
		}
	}

	@EventHandler
	public void uhcPlayerDie(PlayerDeathEvent e) {
		if (com.overseer.Main.Game == true) {
			World w = Bukkit.getWorld("UHC");
			w.strikeLightning(e.getEntity().getLocation());
		}
	}

	@EventHandler
	public void uhcGameEnd(PlayerDeathEvent e) {
		if (com.overseer.Main.Game == true) {
			World w = Bukkit.getWorld("UHC");
			if (w.getPlayers().size() == 1) {
				com.overseer.Main.Game = false;
				Player p = w.getPlayers().get(0);
				Bukkit.broadcastMessage("§b게임이 끝났습니다!");
				Bukkit.broadcastMessage("§6" + p.getName() + "(이)가 승리를 거머쥐었습니다!");
				p.sendMessage("10초 뒤에 자동으로 게임이 끝납니다. 기다려 주세요.");
				new DelayedTask(200) {
					@Override
					public void run() {
						p.setGameMode(GameMode.CREATIVE);
						p.setOp(true);
						p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
						for (PotionEffect effect : p.getActivePotionEffects())
							p.removePotionEffect(effect.getType());
						p.teleport(new Location(Bukkit.getWorld("Creative"), 236.5, 5, 121.5));
					}
				};
			}
		}
	}

	@EventHandler
	public void uhcGameEnd2(PlayerDeathEvent e) {
		if (com.overseer.Main.Game == true) {
			World w = Bukkit.getWorld("UHC");
			if (w.getPlayers().size() == 1) {
				com.overseer.Main.Game = false;
				Player p = w.getPlayers().get(0);
				Bukkit.broadcastMessage("§b게임이 끝났습니다!");
				Bukkit.broadcastMessage("§6" + p.getName() + "(이)가 승리를 거머쥐었습니다!");
				p.sendMessage("10초 뒤에 자동으로 게임이 끝납니다. 기다려 주세요.");
				new DelayedTask(200) {
					@Override
					public void run() {
						p.setGameMode(GameMode.CREATIVE);
						p.setOp(true);
						p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
						for (PotionEffect effect : p.getActivePotionEffects())
							p.removePotionEffect(effect.getType());
						p.teleport(new Location(Bukkit.getWorld("Creative"), 236.5, 5, 121.5));
					}
				};
			}
		}
	}
	
	@EventHandler
	public void useExSeal(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Action a = e.getAction();
		if (a == Action.RIGHT_CLICK_AIR) {
			ItemStack Mainhand = p.getInventory().getItemInMainHand();
			ItemStack Offhand = p.getInventory().getItemInOffHand();
			if (Mainhand.getItemMeta().getDisplayName() == "Expert Seal") {
				if (Offhand == null) {
					p.sendMessage("§c왼손에 날카로움이나 보호 마법부여가 있는 장비를 들고 있어야 합니다.");
				} else if (Offhand.containsEnchantment(Enchantment.DAMAGE_ALL)) {
					Offhand.addUnsafeEnchantment(Enchantment.DAMAGE_ALL,
							Offhand.getEnchantmentLevel(Enchantment.DAMAGE_ALL) + 1);
					p.getInventory().remove(Material.NETHER_STAR);
				} else if (Offhand.containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)) {
					Offhand.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,
							Offhand.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) + 1);
					p.getInventory().remove(Material.NETHER_STAR);
				} else {
					p.sendMessage("§c왼손에 날카로움이나 보호 마법부여가 있는 장비를 들고 있어야 합니다.");
				}
			}
		}
	}
}
