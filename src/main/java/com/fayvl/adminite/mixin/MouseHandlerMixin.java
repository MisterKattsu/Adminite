package com.fayvl.adminite.mixin;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.client.util.VideoMode;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;
import java.util.Optional;

@Mixin(Mouse.class)
public class MouseHandlerMixin {
    @Shadow private double cursorDeltaX;
    @Shadow private double cursorDeltaY;
    @Shadow private double x;
    @Shadow private double y;

    @Inject(method = "onCursorPos", at=@At("HEAD"), cancellable = true)
    private void onCursorPos(long handle, double x, double y, CallbackInfo ci) {
        MinecraftClient instance = MinecraftClient.getInstance();
        Window window = instance.getWindow();
        if (handle == window.getHandle()) {

            VideoMode videoMode = Objects.requireNonNull(window.getMonitor()).findClosestVideoMode(Optional.empty());
            double mouseXScale = (double) 800 /videoMode.getWidth();
            double mouseYScale = (double) 600 /videoMode.getHeight();

            this.x *= mouseXScale;
            this.y *= mouseYScale;

            if (instance.isWindowFocused()) {
                this.cursorDeltaX += x - this.x;
                this.cursorDeltaY += y - this.y;
            }
            this.x = x;
            this.y = y;
        }

        ci.cancel();
    }

}