package me.tozymc.guilogin.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import static me.tozymc.guilogin.GUILogin.getInstance;
import static me.tozymc.guilogin.utils.Utils.*;

public class GUIKeyBoard {
    private static Inventory inventory;
    private Player player;

    public enum GUIType {
        LOGIN("Login"),
        REGISTER("Register");

        private String string;

        GUIType(String s) {
            this.string = s;
        }

        public String toString() {
            return string;
        }
    }

    public GUIKeyBoard(Player player, GUIType type) {
        this.player = player;
        if (type == GUIType.LOGIN) {
            inventory = Bukkit.createInventory(null, 54,
                    replace(getInstance().getConfig().getString("GUI.Title")
                            .replaceAll("%type%", GUIType.LOGIN.toString())));
        } else {
            inventory = Bukkit.createInventory(null, 54,
                    replace(getInstance().getConfig().getString("GUI.Title")
                            .replaceAll("%type%", GUIType.REGISTER.toString())));
        }
        init();
    }

    private void init() {
        // line 1
        getInventory().setItem(0, createGUIItem(SkullType.WOOD_A));
        getInventory().setItem(1, createGUIItem(SkullType.WOOD_B));
        getInventory().setItem(2, createGUIItem(SkullType.WOOD_C));
        getInventory().setItem(3, createGUIItem(SkullType.WOOD_D));
        getInventory().setItem(4, createGUIItem(SkullType.WOOD_E));

        // line 2
        getInventory().setItem(9, createGUIItem(SkullType.WOOD_F));
        getInventory().setItem(10, createGUIItem(SkullType.WOOD_G));
        getInventory().setItem(11, createGUIItem(SkullType.WOOD_H));
        getInventory().setItem(12, createGUIItem(SkullType.WOOD_I));
        getInventory().setItem(13, createGUIItem(SkullType.WOOD_J));

        // line 3
        getInventory().setItem(18, createGUIItem(SkullType.WOOD_K));
        getInventory().setItem(19, createGUIItem(SkullType.WOOD_L));
        getInventory().setItem(20, createGUIItem(SkullType.WOOD_M));
        getInventory().setItem(21, createGUIItem(SkullType.WOOD_N));
        getInventory().setItem(22, createGUIItem(SkullType.WOOD_O));

        // line 4
        getInventory().setItem(27, createGUIItem(SkullType.WOOD_P));
        getInventory().setItem(28, createGUIItem(SkullType.WOOD_Q));
        getInventory().setItem(29, createGUIItem(SkullType.WOOD_R));
        getInventory().setItem(30, createGUIItem(SkullType.WOOD_S));
        getInventory().setItem(31, createGUIItem(SkullType.WOOD_T));

        // line 5
        getInventory().setItem(36, createGUIItem(SkullType.WOOD_U));
        getInventory().setItem(37, createGUIItem(SkullType.WOOD_V));
        getInventory().setItem(38, createGUIItem(SkullType.WOOD_W));
        getInventory().setItem(39, createGUIItem(SkullType.WOOD_X));
        getInventory().setItem(40, createGUIItem(SkullType.WOOD_Y));

        // line 6
        getInventory().setItem(47, createGUIItem(SkullType.WOOD_Z));

        // numbers | line 1
        getInventory().setItem(6, createGUIItem(SkullType.WOOD_1));
        getInventory().setItem(7, createGUIItem(SkullType.WOOD_2));
        getInventory().setItem(8, createGUIItem(SkullType.WOOD_3));

        // numbers | line 2
        getInventory().setItem(15, createGUIItem(SkullType.WOOD_4));
        getInventory().setItem(16, createGUIItem(SkullType.WOOD_5));
        getInventory().setItem(17, createGUIItem(SkullType.WOOD_6));

        // numbers | line 3
        getInventory().setItem(24, createGUIItem(SkullType.WOOD_7));
        getInventory().setItem(25, createGUIItem(SkullType.WOOD_8));
        getInventory().setItem(26, createGUIItem(SkullType.WOOD_9));

        // numbers | line 4
        getInventory().setItem(34, createGUIItem(SkullType.WOOD_0));

        // actions | line 6
        getInventory().setItem(51, createGUIItem(SkullType.CLEAR));
        getInventory().setItem(53, createGUIItem(SkullType.ENTER));

        // load decorate gui
        decorateGUI();
    }

    private void decorateGUI() {
        getInventory().setItem(5, createDecorateItem(7));
        getInventory().setItem(14, createDecorateItem(7));
        getInventory().setItem(23, createDecorateItem(7));
        getInventory().setItem(32, createDecorateItem(7));
        getInventory().setItem(41, createDecorateItem(7));
        getInventory().setItem(42, createDecorateItem(7));
        getInventory().setItem(43, createDecorateItem(7));
        getInventory().setItem(44, createDecorateItem(7));
        getInventory().setItem(50, createDecorateItem(7));
        getInventory().setItem(52, createDecorateItem(14));
    }

    public void openInventory() {
        player.openInventory(inventory);
    }

    public static Inventory getInventory() {
        return inventory;
    }
}
