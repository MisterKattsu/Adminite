package com.fayvl.adminite.mixin;

import com.fayvl.adminite.imgui.ImGuiImpl;
import com.fayvl.adminite.imgui.Windows.FakeWindow;
import imgui.ImGui;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import com.fayvl.adminite.imgui.ImGuiImpl;

@Mixin(Framebuffer.class)
public class FramebufferMixin {

    @ModifyArgs(method = "draw", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/GlStateManager;_glBlitFrameBuffer(IIIIIIIIII)V"))
    public void adminite$moveGameView(Args args) {
        FakeWindow fakeWindow = (FakeWindow) MinecraftClient.getInstance().getWindow();
        int offsetYForTopCorner = fakeWindow.realWindow.getHeight() - fakeWindow.getHeight();
        int totalOffsetY = offsetYForTopCorner - fakeWindow.offsetY;
        args.set(4, fakeWindow.offsetX);
        args.set(5, totalOffsetY);
        args.set(6, ((int) args.get(6)) + fakeWindow.offsetX);
        args.set(7, ((int) args.get(7)) + totalOffsetY);
    }
}
