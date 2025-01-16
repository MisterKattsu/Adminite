package com.fayvl.adminite.mixin;

import com.fayvl.adminite.Adminite;
import com.fayvl.adminite.imgui.ImGuiImpl;
import com.mojang.blaze3d.systems.RenderSystem;
import imgui.ImGui;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.tracy.TracyFrameCapturer;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.nio.ByteBuffer;

@Mixin(Window.class)
public class ViewportMixin {


    @Inject(method = "getX", at = @At("HEAD"), cancellable = true)
    private void getX(CallbackInfoReturnable<Integer> ci) {
        ci.setReturnValue(100);
    }

    @Inject(method = "getY", at = @At("HEAD"), cancellable = true)
    private void getY(CallbackInfoReturnable<Integer> ci) {
        ci.setReturnValue(100);
    }

    @Inject(method = "getFramebufferHeight", at = @At("HEAD"), cancellable = true)
    private void getFramebufferHeight(CallbackInfoReturnable<Integer> ci) {
        ci.setReturnValue(800);
    }

    @Inject(method = "getFramebufferWidth", at = @At("HEAD"), cancellable = true)
    private void getFramebufferWidth(CallbackInfoReturnable<Integer> ci) {
        ci.setReturnValue(600);
    }

}

