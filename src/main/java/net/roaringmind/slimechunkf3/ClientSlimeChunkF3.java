package net.roaringmind.slimechunkf3;

import static com.mojang.brigadier.arguments.LongArgumentType.getLong;
import static com.mojang.brigadier.arguments.LongArgumentType.longArg;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

import static net.roaringmind.slimechunkf3.SlimeChunkF3.log;

import org.apache.logging.log4j.Level;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;

public class ClientSlimeChunkF3 implements ClientModInitializer {
  public static Long seed = null;

  @Override
  public void onInitializeClient() {
    log(Level.INFO, "Client Initialize");

    ClientPlayNetworking.registerGlobalReceiver(SlimeChunkF3.SEED_PACKET_ID, (client, handler, buf, sender) -> {
      // log(Level.INFO, "packet recieved");

      seed = buf.readLong();
    });

    ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
      dispatcher.register(
        literal("slimechunkf3-seed")
          .then(
            argument("seed", longArg())
            .executes(ctx -> {
              seed = getLong(ctx, "seed");
              return 0;
            })
          ).executes(ctx -> {
            ClientPlayNetworking.send(SlimeChunkF3.SEED_PACKET_ID, PacketByteBufs.create());
            return 0;
          })
          
        );
    });
  }
}
