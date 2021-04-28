package com.overseer;

import java.io.File;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin {

	Random rand = new Random();

	static Main Plugin;

	ItemStack ExSeal = new ItemStack(Material.NETHER_STAR);

	@Override
	public void onEnable() {
		System.out.println("[UHC] Enabled.");
		Plugin = this;
		Bukkit.getPluginManager().registerEvents(new Event(), this);
		ShapedRecipe LightApple = new ShapedRecipe(new NamespacedKey(this, "light_apple"),
				new ItemStack(Material.GOLDEN_APPLE));
		LightApple.shape(" G ", "GAG", " G ");
		LightApple.setIngredient('G', Material.GOLD_INGOT);
		LightApple.setIngredient('A', Material.APPLE);
		Bukkit.addRecipe(LightApple);
		ItemStack SBook = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta SBookMeta = (EnchantmentStorageMeta) SBook.getItemMeta();
		SBookMeta.addStoredEnchant(Enchantment.DAMAGE_ALL, 2, true);
		SBook.setItemMeta(SBookMeta);
		ShapedRecipe SharpnessBook = new ShapedRecipe(new NamespacedKey(this, "sharpness_book"), SBook);
		SharpnessBook.shape("   ", " PP", " PD");
		SharpnessBook.setIngredient('P', Material.PAPER);
		SharpnessBook.setIngredient('D', Material.DIAMOND);
		Bukkit.addRecipe(SharpnessBook);
		ShapedRecipe NetheriteSword = new ShapedRecipe(new NamespacedKey(this, "netherite_sword"),
				new ItemStack(Material.NETHERITE_SWORD));
		NetheriteSword.shape("MGM", " D ", "OGO");
		NetheriteSword.setIngredient('M', Material.MAGMA_BLOCK);
		NetheriteSword.setIngredient('O', Material.OBSIDIAN);
		NetheriteSword.setIngredient('G', Material.GOLD_INGOT);
		NetheriteSword.setIngredient('D', Material.DIAMOND_SWORD);
		Bukkit.addRecipe(NetheriteSword);
		ItemMeta Meta = ExSeal.getItemMeta();
		Meta.setDisplayName("§bExpert Seal");
		ExSeal.setItemMeta(Meta);
		ShapedRecipe ExpertSeal = new ShapedRecipe(new NamespacedKey(this, "expert_seal"), ExSeal);
		ExpertSeal.shape("EDE", "DDD", "EDE");
		ExpertSeal.setIngredient('D', Material.DIAMOND);
		ExpertSeal.setIngredient('E', Material.EXPERIENCE_BOTTLE);
		Bukkit.addRecipe(ExpertSeal);
		ShapedRecipe EXPBottle = new ShapedRecipe(new NamespacedKey(this, "exp_bottle"),
				new ItemStack(Material.EXPERIENCE_BOTTLE));
		EXPBottle.shape("LLL", "LGL", "LLL");
		EXPBottle.setIngredient('G', Material.GLASS_BOTTLE);
		EXPBottle.setIngredient('L', Material.LAPIS_LAZULI);
		Bukkit.addRecipe(EXPBottle);
		ShapedRecipe IronPack = new ShapedRecipe(new NamespacedKey(this, "iron_pack"),
				new ItemStack(Material.IRON_INGOT, 10));
		IronPack.shape("III", "ICI", "III");
		IronPack.setIngredient('C', Material.COAL);
		IronPack.setIngredient('I', Material.IRON_ORE);
		Bukkit.addRecipe(IronPack);
		ShapedRecipe GoldPack = new ShapedRecipe(new NamespacedKey(this, "gold_pack"),
				new ItemStack(Material.GOLD_INGOT, 10));
		GoldPack.shape("GGG", "GCG", "GGG");
		GoldPack.setIngredient('C', Material.COAL);
		GoldPack.setIngredient('G', Material.GOLD_ORE);
		Bukkit.addRecipe(GoldPack);
		ItemStack QPick = new ItemStack(Material.IRON_PICKAXE);
		QPick.addEnchantment(Enchantment.DIG_SPEED, 1);
		ShapedRecipe QuickPick = new ShapedRecipe(new NamespacedKey(this, "quick_pick"), QPick);
		QuickPick.shape("III", "CSC", " S ");
		QuickPick.setIngredient('I', Material.IRON_ORE);
		QuickPick.setIngredient('C', Material.COAL);
		QuickPick.setIngredient('S', Material.STICK);
		Bukkit.addRecipe(QuickPick);
	}

	@Override
	public void onDisable() {
		System.out.println("[UHC] Disabled.");
	}

	static boolean Game = false;
	static boolean Grace = false;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("uhccreate")) {
			Player p = (Player) sender;
			p.sendMessage("새로운 전장이 생성되는 중입니다. 잠시 기다려 주세요.");
			createWorld("UHC", 1000);
			p.sendMessage("§a전장이 생성되었습니다.");
			return true;
		} else if (cmd.getName().equalsIgnoreCase("uhctp")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (Bukkit.getWorld("UHC") != null) {
					World w = Bukkit.getWorld("UHC");
					p.teleport(new Location(w, 0, w.getHighestBlockYAt(0, 0), 0));
					p.sendMessage("§a전장의 중심으로 이동되었습니다.");
				} else {
					sender.sendMessage("§c전장이 존재하지 않습니다. /uhccreate로 전장을 생성해 주세요.");
				}
				return true;
			}
		} else if (cmd.getName().equalsIgnoreCase("uhcgetout")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				p.teleport(new Location(Bukkit.getWorld("Creative"), 236.5, 5, 121.5));
				return true;
			}
		} else if (cmd.getName().equalsIgnoreCase("uhcdelete")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (Bukkit.getWorld("UHC").getPlayers().isEmpty()) {
					File UHC = Bukkit.getWorld("UHC").getWorldFolder();
					deleteWorld(UHC);
					p.sendMessage("§a전장을 삭제하였습니다.");
				} else {
					p.sendMessage("§c전장을 삭제하기 전에 먼저 전장을 비워주세요.");
				}
			}
			return true;
		} else if (cmd.getName().equalsIgnoreCase("uhcstart")) {
			if (Bukkit.getWorld("UHC") != null) {
				if (Bukkit.getOnlinePlayers().size() >= 1) {
					World w = Bukkit.getWorld("UHC");
					w.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
					w.setGameRule(GameRule.NATURAL_REGENERATION, false);
					w.setGameRule(GameRule.DISABLE_RAIDS, false);
					WorldBorder wb = w.getWorldBorder();
					w.setTime(0L);
					wb.setWarningDistance(10);
					wb.setDamageAmount(2);
					for (Player p : Bukkit.getOnlinePlayers()) {
						int X = rand.nextInt(1000) - 500;
						int Z = rand.nextInt(1000) - 500;
						int Y = w.getHighestBlockYAt(X, Z);
						p.teleport(new Location(w, X, Y, Z));
						p.setGameMode(GameMode.SURVIVAL);
						p.setOp(false);
						p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
						p.setHealth(40);
						p.setFoodLevel(20);
						for (PotionEffect effect : p.getActivePotionEffects())
							p.removePotionEffect(effect.getType());
						p.getInventory().clear();
						p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
						p.getInventory().addItem(new ItemStack(Material.STONE_PICKAXE));
						p.getInventory().addItem(new ItemStack(Material.STONE_AXE));
						p.getInventory().addItem(new ItemStack(Material.STONE_SHOVEL));
						p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 12000, 0, false, false));
					}
					Game = true;
					Grace = true;
					Bukkit.broadcastMessage("§aUHC가 시작되었습니다. 앞으로 10분간은 평화 기간입니다.");
					Bukkit.broadcastMessage("§b평화 기간 동안은 다른 사람을 직접적으로 공격할 수 없습니다.");
					Bukkit.broadcastMessage("§b평화 기간이 끝나면 전장이 천천히 줄어들 것입니다.");

					new RepeatingTask(1200, 1200) {
						int i = 10;

						@Override
						public void run() {
							i--;
							if (i == 0 || Game == false) {
								cancel();
							} else {
								Bukkit.broadcastMessage("§c평화 기간이 " + i + "분 남았습니다!");
							}
						}
					};
					new DelayedTask(12000) {
						@Override
						public void run() {
							Bukkit.broadcastMessage("§c평화 기간이 끝났습니다!");
							Bukkit.broadcastMessage("§b앞으로 전장이 계속 줄어들 것입니다.");
							Grace = false;
							new RepeatingTask(0, 20) {
								int i = 1000;

								@Override
								public void run() {
									i--;
									if (Game == false) {
										cancel();
									} else if (i <= 40) {
										Bukkit.broadcastMessage("§c전장 크기가 최고로 줄어들었습니다. 행운을 빕니다!");
										cancel();
									} else if (i % 20 == 0) {
										wb.setSize(i);
										Bukkit.broadcastMessage("§b전장 크기: " + i);
									} else {
										wb.setSize(i);
									}
								}
							};
						}
					};
				} else {
					sender.sendMessage("§c게임을 플레이하려면 적어도 2명의 플레이어가 있어야 합니다.");
				}
			} else {
				sender.sendMessage("§c전장이 존재하지 않습니다. /uhccreate로 전장을 생성해 주세요.");
			}
			return true;
		} else if (cmd.getName().equalsIgnoreCase("uhcstop")) {
			if (Game == true) {
				Game = false;
				for (Player p : Bukkit.getWorld("UHC").getPlayers()) {
					p.setGameMode(GameMode.CREATIVE);
					p.setOp(true);
					p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
					for (PotionEffect effect : p.getActivePotionEffects())
						p.removePotionEffect(effect.getType());
					p.teleport(new Location(Bukkit.getWorld("Creative"), 236.5, 5, 121.5));
				}
				sender.sendMessage("§a진행 중인 게임이 중지되었습니다.");
			} else {
				sender.sendMessage("§c진행 중인 게임이 없습니다.");
			}
			return true;
		} else if (cmd.getName().equalsIgnoreCase("uhcendcheck")) {
			if (Game == true) {
				World w = Bukkit.getWorld("UHC");
				if (w.getPlayers().size() == 1) {
					Game = false;
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
				} else {
					sender.sendMessage("§c아직 게임이 진행 중입니다.");
				}
			} else {
				sender.sendMessage("§c게임이 진행 중이지 않습니다.");
			}
		}
		return false;
	}

	public void createWorld(String name, int size) {
		WorldCreator wc = new WorldCreator(name);
		wc.environment(World.Environment.NORMAL);
		wc.type(WorldType.NORMAL);
		wc.createWorld();
		WorldBorder wb = Bukkit.getWorld(name).getWorldBorder();
		wb.setCenter(0, 0);
		wb.setSize(size);
	}

	public boolean deleteWorld(File path) {
		if (path.exists()) {
			File files[] = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteWorld(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		return (path.delete());
	}
}