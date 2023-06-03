package com.ryuuta0217.exportinventorycontents.mixin;

import com.ryuuta0217.exportinventorycontents.imixin.IMixinPlayerDataStorage;
import net.minecraft.world.level.storage.PlayerDataStorage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.io.File;

@Mixin(PlayerDataStorage.class)
public class MixinPlayerDataStorage implements IMixinPlayerDataStorage {
    @Shadow @Final private File playerDir;

    @Override
    public File getDataDir() {
        return this.playerDir;
    }
}
