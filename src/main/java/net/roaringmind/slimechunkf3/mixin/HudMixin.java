package net.roaringmind.slimechunkf3.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(DebugHud.class)
public class HudMixin extends DrawableHelper{
  public HudMixin() {
    super();
  }

  @Inject(method = "renderLeftText", at = @At(value = "INVOKE_ASSIGN", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", args = {
    "log=true" }))
  void setCustomDebugLine(MatrixStack matrices, final CallbackInfo info) {
    System.out.println("mixin working");
  }
}
