package io.github.maheevil.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DeathScreen.class)
public class DeathScreenMixin extends Screen {
    protected DeathScreenMixin(Component component) {
        super(component);
    }

    @Inject(
            method = "render",
            //at = @At("HEAD"),
            at = @At(
                    value = "INVOKE",
                    shift = At.Shift.BEFORE,
                    target = "net/minecraft/client/gui/screens/Screen.render(Lcom/mojang/blaze3d/vertex/PoseStack;IIF)V"
            ),
            cancellable = true
    )
    public void render(PoseStack poseStack, int i, int j, float f, CallbackInfo ci){
        assert this.minecraft != null;
        assert this.minecraft.player != null;
        String xyz = "[X: " + minecraft.player.blockPosition().getX() + "/ Y: " + minecraft.player.blockPosition().getY() + "/ Z: " + minecraft.player.blockPosition().getZ() + "]";
        String zyx = "XYZ: " + this.minecraft.player.blockPosition().getX() + " / " + minecraft.player.blockPosition().getY() + " / " + minecraft.player.blockPosition().getZ();
        drawCenteredString(poseStack,this.font, xyz, this.width / 2, 115, 16777215);

    }
}
