package com.ryuuta0217.exportinventorycontents.mixin;

import com.ryuuta0217.exportinventorycontents.ExportInventoryContents;
import com.ryuuta0217.exportinventorycontents.imixin.IMixinMinecraftServer;
import com.ryuuta0217.exportinventorycontents.imixin.IMixinPlayerDataStorage;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Mixin(Inventory.class)
public class MixinInventory {
    @Shadow @Final public Player player;

    @Shadow @Final public NonNullList<ItemStack> armor;

    @Shadow @Final public NonNullList<ItemStack> offhand;

    @Shadow @Final public NonNullList<ItemStack> items;

    @Inject(method = "save", at = @At("TAIL"))
    public void onSaveBeforeComplete(ListTag listTag, CallbackInfoReturnable<ListTag> cir) {
        long start = System.nanoTime();
        ExportInventoryContents.LOGGER.info("=> Saving!");
        if (this.player.getServer() instanceof IMixinMinecraftServer mixinServer) {
            if (mixinServer.getPlayerDataStorage() instanceof IMixinPlayerDataStorage storage) {
                File out = new File(storage.getDataDir(), this.player.getStringUUID() + ".txt");
                try {
                    if ((out.getParentFile().exists() || out.getParentFile().mkdirs()) && (out.exists() || out.createNewFile())) {
                        ArrayList<String> lines = new ArrayList<>();
                        lines.add("# ITEMS");
                        this.items.forEach(item -> lines.add(item.getItem() + " " + item.getCount()));
                        lines.add("# ARMOR");
                        this.armor.forEach(item -> lines.add(item.getItem() + " " + item.getCount()));
                        lines.add("# OFFHAND");
                        this.offhand.forEach(item -> lines.add(item.getItem() + " " + item.getCount()));
                        Files.write(out.toPath(), lines, StandardCharsets.UTF_8, StandardOpenOption.WRITE);
                    }
                } catch(IOException ignored) {}
            }
        }
        long end = System.nanoTime();
        ExportInventoryContents.LOGGER.info("=> Saved, elapsed " + (end-start) + "ns (" + TimeUnit.NANOSECONDS.toMillis(end-start) + "ms)");
    }
}
