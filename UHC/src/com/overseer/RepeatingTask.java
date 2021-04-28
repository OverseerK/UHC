package com.overseer;

import org.bukkit.Bukkit;

public abstract class RepeatingTask implements Runnable {

	private int taskId;

	public RepeatingTask(int arg1, int arg2) {
		taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(com.overseer.Main.Plugin, this, arg1, arg2);
	}

	public void cancel() {
		Bukkit.getScheduler().cancelTask(taskId);
	}

}