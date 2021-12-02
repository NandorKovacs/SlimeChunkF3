package net.roaringmind.slimechunkf3.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import it.unimi.dsi.fastutil.longs.LongSet;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.gen.random.ChunkRandom;
import net.roaringmind.slimechunkf3.ClientSlimeChunkF3;

@Mixin(DebugHud.class)
public class HudMixin extends DrawableHelper {
  public HudMixin() {
    super();
  }

  @Inject(method = "getLeftText", at = @At(value = "INVOKE_ASSIGN", ordinal = 8, target = "Ljava/util/List;add(Ljava/lang/Object;)Z", args = {
      "log=true" }), locals = LocalCapture.CAPTURE_FAILSOFT)
  protected void setCustomDebugLine(CallbackInfoReturnable<List> cir, String string2, BlockPos blockPos, Entity entity,
      Direction direction, String string7, World world, LongSet longSet, List list, WorldChunk worldChunk, int i, int j,
      int k) {

    Long seed = null;

    if (world instanceof StructureWorldAccess) {
      seed = ((StructureWorldAccess) world).getSeed();
    } else {
      seed = ClientSlimeChunkF3.seed;
    }

    String message = "cant access seed";

    if (seed != null) {
      ChunkPos chunkPos = worldChunk.getPos();
      message = String.valueOf(ChunkRandom.getSlimeRandom(chunkPos.x, chunkPos.z, seed, 987234911L).nextInt(10) == 0);
    }

    list.add(String.format("Slime Chunk: %s", message));
  }
}
