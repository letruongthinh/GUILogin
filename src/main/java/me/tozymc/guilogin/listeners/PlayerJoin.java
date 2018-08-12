package me.tozymc.guilogin.listeners;

import me.tozymc.guilogin.utils.GUIKeyBoard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.tozymc.guilogin.GUILogin.getAuthMeApi;

public class PlayerJoin implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(final PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (getAuthMeApi().isRegistered(player.getName())) {
            GUIKeyBoard keyBoard = new GUIKeyBoard(player, GUIKeyBoard.GUIType.LOGIN);
            player.sendMessage("LOGIN");
            keyBoard.openInventory();
        } else {
            GUIKeyBoard keyBoard = new GUIKeyBoard(player, GUIKeyBoard.GUIType.REGISTER);
            player.sendMessage("REGISTER");
            keyBoard.openInventory();
        }
    }
}
