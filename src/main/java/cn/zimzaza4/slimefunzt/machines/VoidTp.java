package cn.zimzaza4.slimefunzt.machines;

import cn.zimzaza4.slimefunzt.SlimefunZT;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class VoidTp extends SlimefunItem {

    public VoidTp(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
        addItemHandler(new BlockUseHandler() {
            @Override
            public void onRightClick(PlayerRightClickEvent playerRightClickEvent) {
                if (BlockStorage.getBlockInfoAsJson(playerRightClickEvent.getClickedBlock().get()).contains("void_tp_is_open")){
                    Player player = playerRightClickEvent.getPlayer();
                    Location loc = player.getLocation();
                    loc.setWorld(Bukkit.getWorld(SlimefunZT.inst.void_world));
                    loc.setY(255);
                    player.teleport(loc);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 600, 0));
                }

            }
        });
    }
}
