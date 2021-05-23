package cn.zimzaza4.slimefunzt.Items.material;

import io.github.thebusybiscuit.slimefun4.api.geo.GEOResource;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;

public class EnderOre implements GEOResource {
    public final  NamespacedKey key;
    public final  ItemStack item;

    public EnderOre(Plugin plugin, ItemStack item) {
        this.key = new NamespacedKey(plugin, "zim_ender_ore");
        this.item = item;
    }
    @Override
    public int getDefaultSupply(@Nonnull World.Environment environment, @Nonnull Biome biome) {
        if (environment == World.Environment.THE_END) {
            return 10;
        }
        else {
            return 0;
        }
    }

    @Override
    public int getMaxDeviation() {
        return 3;
    }

    @Nonnull
    @Override
    public String getName() {
        return "紫影矿";
    }

    @Nonnull
    @Override
    public ItemStack getItem() {
        return item.clone();
    }

    @Override
    public boolean isObtainableFromGEOMiner() {
        return true;
    }

    @Override
    public NamespacedKey getKey() {
        return key;
    }
}
