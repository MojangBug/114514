package com.haste;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.plugin.java.JavaPlugin;

public class Haste extends JavaPlugin implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();

        // 检查玩家手持物品为镐子或铲子
        if (item != null && (isPickaxe(item) || isShovel(item))) {
            Material blockType = event.getBlock().getType();

            // 检查玩家挖掘的方块类型
            if (isPickaxe(item) && isOre(blockType)) {
                // 给予镐子挖掘矿石时急迫 III 效果，持续2秒
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 2, 2));
            } else if (isShovel(item) && isDiggable(blockType)) {
                // 给予铲子挖掘可挖掘方块时急迫 III 效果，持续2秒
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 2, 2));
            }
        }
    }

    private boolean isPickaxe(ItemStack item) {
        Material itemType = item.getType();
        return itemType.equals(Material.DIAMOND_PICKAXE) ||
                itemType.equals(Material.IRON_PICKAXE) ||
                itemType.equals(Material.GOLD_PICKAXE) ||
                itemType.equals(Material.STONE_PICKAXE) ||
                itemType.equals(Material.WOOD_PICKAXE);
    }

    private boolean isShovel(ItemStack item) {
        Material itemType = item.getType();
        return itemType.equals(Material.DIAMOND_SPADE) ||
                itemType.equals(Material.IRON_SPADE) ||
                itemType.equals(Material.GOLD_SPADE) ||
                itemType.equals(Material.STONE_SPADE) ||
                itemType.equals(Material.WOOD_SPADE);
    }

    private boolean isOre(Material blockType) {
        return blockType.equals(Material.COAL_ORE) ||
                blockType.equals(Material.DIAMOND_ORE) ||
                blockType.equals(Material.IRON_ORE) ||
                blockType.equals(Material.LAPIS_ORE) ||
                blockType.equals(Material.GOLD_ORE) ||
                blockType.equals(Material.REDSTONE_ORE);
    }

    private boolean isDiggable(Material blockType) {
        return blockType.equals(Material.SAND) ||
                blockType.equals(Material.GRAVEL) ||
                blockType.equals(Material.DIRT);
    }
}
