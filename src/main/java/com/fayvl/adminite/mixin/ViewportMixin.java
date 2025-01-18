package com.fayvl.adminite.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Window.class)
public class ViewportMixin {




    @Shadow private int framebufferWidth;
    @Shadow private int framebufferHeight;

    @Shadow private int width;
    @Shadow private int height;

    @Shadow private double scaleFactor;

    @Shadow private int scaledWidth;

    @Shadow private int scaledHeight;

    @Shadow private int windowedWidth;

    @Shadow private long handle;


    @Unique
    private float calculateWidthScaleFactor() {
        return Math.max(1/8f, Math.min(8f, (float) this.framebufferWidth / this.width));
    }

    @Unique
    private float calculateHeightScaleFactor() {
        return Math.max(1/8f, Math.min(8f, (float) this.framebufferHeight / this.height));
    }

    @Unique
    private static int getNewGameWidth(float scale) {
        return Math.max(800, Math.round(1 * scale));
    }

    @Unique
    private static int getNewGameHeight(float scale) {
        return Math.max(600, Math.round(1 * scale));
    }

    @Inject(method = "getX", at=@At("HEAD"), cancellable = true)
    public void getX(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue((int) (Math.random()*1920));
    }

    @Inject(method = "getY", at=@At("HEAD"), cancellable = true)
    public void getY(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue((int) (Math.random()*1080));
    }

    @Inject(method = "getWidth", at=@At("HEAD"), cancellable = true)
    public void getWidth(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(getNewGameWidth(this.calculateWidthScaleFactor()));
    }

    @Inject(method = "getHeight", at=@At("HEAD"), cancellable = true)
    public void getHeight(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(getNewGameHeight(this.calculateHeightScaleFactor()));
    }

    @Inject(method = "getFramebufferWidth", at=@At("HEAD"), cancellable = true)
    public void getFramebufferWidth(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(getNewGameWidth(1));
    }

    @Inject(method = "getFramebufferHeight", at=@At("HEAD"), cancellable = true)
    public void getFramebufferHeight(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(getNewGameHeight(1));
    }

    @Inject(method = "onFramebufferSizeChanged", at=@At("HEAD"), cancellable = true)
    public void onFramebufferSizeChanged(long l, int i, int j, CallbackInfo ci) {
        GLFW.glfwSetWindowPos(this.handle, 50, 50);
        if (l != this.handle) {
            ci.cancel();
        }
    }

    @Inject(method = "calculateScaleFactor", at=@At("HEAD"), cancellable = true)
    public void calculateScaleFactor(int scale, boolean forceEven, CallbackInfoReturnable<Integer> cir) {
            int fbw = getNewGameWidth(this.calculateWidthScaleFactor());
            int fbh = getNewGameHeight(this.calculateHeightScaleFactor());

            int j = 1;
            while (j != scale && j < fbw && j < fbh && fbw / (j + 1) >= 320 && fbh / (j + 1) >= 240) {
                j++;
            }
            if (forceEven && j % 2 != 0) {
                j++;
            }
            cir.setReturnValue(j);
    }

    @Inject(method = "setScaleFactor", at=@At("HEAD"), cancellable = true)
    public void setScaleFactor(double d, CallbackInfo ci) {
        int fbw = getNewGameWidth(this.calculateWidthScaleFactor());
        int fbh = getNewGameHeight(this.calculateHeightScaleFactor());

        this.scaleFactor = d;
        int i = (int)((double)fbw / d);
        this.scaledWidth = (double)fbw / d > (double)i ? i + 1 : i;
        int j = (int)((double)fbh / d);
        this.scaledHeight = (double)fbh / d > (double)j ? j + 1 : j;

        ci.cancel();
    }




}


