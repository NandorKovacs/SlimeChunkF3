package net.roaringmind.slimechunkf3.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(DebugHud.class)
public class HudMixin {
  
  @Inject(method = "getlefttext()L", at = @At(value = "INVOKE_ASSIGN", ordinal = 8, target = "Lnet/minecraft/client/gui/hud/DebugHud;add(Ljava/lang/String;)Z"))
  void setCustomDebugLine(MatrixStack matrices, final CallbackInfo info) {
    System.out.println("mixin working");
  }
}
