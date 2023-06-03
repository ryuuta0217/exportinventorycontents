package com.ryuuta0217.exportinventorycontents.imixin;

import net.minecraft.world.level.storage.PlayerDataStorage;

public interface IMixinMinecraftServer {
    PlayerDataStorage getPlayerDataStorage();
}
