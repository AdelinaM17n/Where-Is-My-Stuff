package io.github.maheevil.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(DeathScreen.class)
public class DeathScreenMixin extends Screen {
    @Shadow @Final private List<Button> exitButtons;

    @Shadow @Final private boolean hardcore;

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
        //System.out.println(this.height);
        String xyz = "[X: " + minecraft.player.blockPosition().getX() + "/ Y: " + minecraft.player.blockPosition().getY() + "/ Z: " + minecraft.player.blockPosition().getZ() + "]";
        String zyx = "XYZ: " + this.minecraft.player.blockPosition().getX() + " / " + minecraft.player.blockPosition().getY() + " / " + minecraft.player.blockPosition().getZ();
        drawCenteredString(poseStack,this.font, xyz, this.width / 2, this.height - this.height / 5, 16777215);

    }


}
