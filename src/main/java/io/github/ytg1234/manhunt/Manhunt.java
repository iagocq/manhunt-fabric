package io.github.ytg1234.manhunt;

import io.github.ytg1234.manhunt.config.ManhuntConfig;
import io.github.ytg1234.manhunt.init.EventListener;
import io.github.ytg1234.manhunt.init.ManhuntPackets;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Manhunt implements ModInitializer {
    public static final String MOD_ID = "manhunt";
    public static final String MOD_NAME = "Manhunt Fabric";
    public static ManhuntConfig CONFIG;

    public static Logger LOGGER = LogManager.getLogger(MOD_NAME);

    /**
     * Initializes the mod.
     */
    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");
        AutoConfig.register(ManhuntConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(ManhuntConfig.class).getConfig();

        ServerTickEvents.END_SERVER_TICK.register(EventListener::updateCompasses);
        ServerTickEvents.END_SERVER_TICK.register(EventListener::highlightSpeedrunner);

        CommandRegistrationCallback.EVENT.register(EventListener::registerCommands);

        ManhuntPackets.registerPacketsServer();
    }

    /**
     * Logs a message to the console.
     *
     * @param level   The log level
     * @param message The message being logged
     */
    public static void log(Level level, String message) {
        LOGGER.log(level, message);
    }
}
