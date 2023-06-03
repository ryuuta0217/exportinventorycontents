package com.ryuuta0217.exportinventorycontents.mixin;

import com.ryuuta0217.exportinventorycontents.imixin.IMixinMinecraftServer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.storage.PlayerDataStorage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer implements IMixinMinecraftServer {
    @Shadow @Final protected PlayerDataStorage playerDataStorage;

    @Override
    public PlayerDataStorage getPlayerDataStorage() {
        return this.playerDataStorage;
    }
}
