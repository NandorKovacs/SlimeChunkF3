package net.roaringmind.slimechunkf3;

import org.apache.logging.log4j.Level;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.world.World;

public class ServerSlimeChunkF3 implements DedicatedServerModInitializer {
  @Override
  public void onInitializeServer() {
    log("Server Initialize");
    
    ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
      log("on join");
      
      PacketByteBuf buf = PacketByteBufs.create();
      buf.writeLong(server.getWorld(World.OVERWORLD).getSeed());

      ServerPlayNetworking.send(handler.player, SlimeChunkF3.SEED_PACKET_ID, buf);
    });
  }

  private void log(String message) {
    SlimeChunkF3.log(Level.INFO, message);
  }
}
