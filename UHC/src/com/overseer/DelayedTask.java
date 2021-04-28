package com.overseer;

import org.bukkit.Bukkit;

public abstract class DelayedTask implements Runnable {

    private int taskId;

    public DelayedTask(int arg1) {
        taskId = Bukkit.getScheduler().scheduleSyncDelayedTask(com.overseer.Main.Plugin, this, arg1);
    }

    public void cancel() {
        Bukkit.getScheduler().cancelTask(taskId);
    }

}