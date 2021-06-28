package net.roaringmind.slimechunkf3;

import org.apache.logging.log4j.Level;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ClientSlimeChunkF3 implements ClientModInitializer {
  public static Long seed = null;

  @Override
  public void onInitializeClient() {
    log("Client Initialize");
    
    ClientPlayNetworking.registerGlobalReceiver(SlimeChunkF3.SEED_PACKET_ID, (client, handler, buf, sender) -> {
      log("packet recieved");
      
      seed = buf.readLong();
    });
  }

  private void log(String message) {
    SlimeChunkF3.log(Level.INFO, message);
  }
}
