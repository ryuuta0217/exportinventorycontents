package com.ryuuta0217.exportinventorycontents;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExportInventoryContents implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger(ExportInventoryContents.class.getName());

    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        LOGGER.info("=> Initialize");
    }
}
