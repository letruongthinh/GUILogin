package me.tozymc.guilogin;

import fr.xephi.authme.api.v3.AuthMeApi;
import me.tozymc.guilogin.cmds.CommandGuiLogin;
import me.tozymc.guilogin.listeners.InventoryListener;
import me.tozymc.guilogin.listeners.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class GUILogin extends JavaPlugin {
    private static GUILogin instance;
    private static AuthMeApi authMeApi;

    @Override
    public void onEnable() {
        instance = this;
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("AuthMe")) {
            authMeApi = AuthMeApi.getInstance();
            Bukkit.getServer().getPluginManager().registerEvents(new InventoryListener(), this);
            Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
            getCommand("gl").setExecutor(new CommandGuiLogin());
            loadDefaultConfig();
        } else {
            Bukkit.getServer().getConsoleSender().sendMessage("§4The AuthMe plugin was not found!");
            Bukkit.getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadDefaultConfig() {
        getInstance().getConfig().options().copyDefaults(true);
        getInstance().getConfig().addDefault("GUI.Title", "&8GUI Login");
        getInstance().getConfig().addDefault("GUI.Name", "&a&l");
        getInstance().getConfig().addDefault("GUI.Lore", Arrays.asList("&r", "&eNhập &a&l%name%"));
        getInstance().saveConfig();
    }

    public static GUILogin getInstance() {
        return instance;
    }

    public static AuthMeApi getAuthMeApi() {
        return authMeApi;
    }
}
