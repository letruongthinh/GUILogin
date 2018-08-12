package me.tozymc.guilogin.listeners;

import me.tozymc.guilogin.GUILogin;
import me.tozymc.guilogin.utils.GUIKeyBoard;
import me.tozymc.guilogin.utils.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static me.tozymc.guilogin.utils.Utils.arraytoString;
import static me.tozymc.guilogin.utils.Utils.replace;

public class InventoryListener implements Listener {
    private List<String> pass = new ArrayList<>();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            Inventory inv = event.getInventory();
            String invName = GUIKeyBoard.getInventory().getName();
            if (inv.getName().equals(invName)) {
                event.setCancelled(true);
                ItemStack clickedItem = event.getCurrentItem();
                if (clickedItem != null) {
                    if (clickedItem.hasItemMeta()) {
                        ItemMeta meta = clickedItem.getItemMeta();
                        for (char c = 'A'; c <= 'Z'; ++c) {
                            if (meta.getDisplayName().equals(replace(GUILogin.getInstance().getConfig().getString("GUI.Name") + c))) {
                                pass.add(String.valueOf(c).toLowerCase());
                            }
                        }
                        for (char c = '0'; c <= '9'; ++c) {
                            if (meta.getDisplayName().equals(replace(GUILogin.getInstance().getConfig().getString("GUI.Name") + c))) {
                                pass.add(String.valueOf(c));
                            }
                        }
                        if (meta.getDisplayName().equals(replace(GUILogin.getInstance().getConfig().getString("GUI.Name") + SkullType.ENTER.getName()))) {
                            player.closeInventory();
                            if (invName.endsWith(GUIKeyBoard.GUIType.LOGIN.toString())) {
                                player.chat("/login " + arraytoString(pass));
                            } else if (invName.endsWith(GUIKeyBoard.GUIType.REGISTER.toString())) {
                                player.chat("/register " + arraytoString(pass) + " " + arraytoString(pass));
                            }
                            pass.clear();
                        }
                        if (meta.getDisplayName().equals(replace(GUILogin.getInstance().getConfig().getString("GUI.Name") + SkullType.CLEAR.getName()))) {
                            pass.clear();
                            player.sendMessage("Password cleaned!");
                        }
                    }
                }
            }
        }
    }
}
