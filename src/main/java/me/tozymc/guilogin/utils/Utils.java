package me.tozymc.guilogin.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static me.tozymc.guilogin.GUILogin.getInstance;

public class Utils {
    public static String replace(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String arraytoString(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : list) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    static ItemStack createDecorateItem(int durability) {
        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        item.setDurability((short) durability);
        meta.setDisplayName(replace("&2☢"));
        item.setItemMeta(meta);
        return item;
    }

    static ItemStack createGUIItem(SkullType skullType) {
        ItemStack item = customSkull(skullType.getSkull());
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(replace(getInstance().getConfig().getString("GUI.Name") + skullType.getName()));
        List<String> lores = new ArrayList<>();
        for (String s : getInstance().getConfig().getStringList("GUI.Lore")) {
            lores.add(replace(s).replaceAll("%name%", skullType.getName()));
        }
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack customSkull(String textures) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM);
        item.setDurability((short) 3);
        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
        gameProfile.getProperties().put("textures", new Property("textures", textures));
        Field field;
        try {
            field = skullMeta.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(skullMeta, gameProfile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        item.setItemMeta(skullMeta);
        return item;
    }
}
