package net.roaringmind.slimechunkf3;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.lookup.v1.item.ItemApiLookup;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;

public class SlimeChunkF3 implements ModInitializer {

  public static Logger LOGGER = LogManager.getLogger();

  public static final String MOD_ID = "slimechunkf3";
  public static final String MOD_NAME = "SlimeChunkF3";

  public static final Identifier SEED_PACKET_ID = new Identifier(MOD_ID, "seed_packet_id");

  @Override
  public void onInitialize() {
    log(Level.INFO, "Initializing");
  }

  public static void log(Level level, String message) {
    LOGGER.log(level, String.format("[%s] %s", MOD_NAME, message));
  }
}