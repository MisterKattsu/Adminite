package com.fayvl.adminite.mixin;


import com.fayvl.adminite.imgui.Windows.FakeWindow;
import com.fayvl.adminite.imgui.Windows.RealWindow;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.client.util.VideoMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Optional;

@Mixin(Mouse.class)
public class MouseHandlerMixin {
    @Shadow private double cursorDeltaX;
    @Shadow private double cursorDeltaY;
    @Shadow private double x;
    @Shadow private double y;

    /**
     * @author Kiritsuu (+Blackilykat)
     * @reason cursor pos calculation is very different when minecraft doesn't use the entirety of its window
     */
    @Overwrite
    private void onCursorPos(long handle, double x, double y) {
        MinecraftClient instance = MinecraftClient.getInstance();

        FakeWindow fakeWindow = ((FakeWindow) instance.getWindow());
        x -= fakeWindow.offsetX;
        y -= fakeWindow.offsetY;

        RealWindow window = fakeWindow.realWindow;
        if (handle == window.getHandle() && window.getMonitor() != null) {

            VideoMode videoMode = window.getMonitor().findClosestVideoMode(Optional.empty());
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
    }

    @ModifyVariable(method = "setup", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    public long adminite$changeSetupWindowHandle(long value) {
        return getRealWindowHandle();
    }


    @ModifyVariable(method = "onMouseButton", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    public long adminite$changeOnMouseButtonWindowHandle(long value) {
        return getFakeWindowHandle();
    }

    @ModifyVariable(method = "onMouseScroll", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    public long adminite$changeOnMouseScrollWindowHandle(long value) {
        return getFakeWindowHandle();
    }


    @ModifyVariable(method = "onFilesDropped", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    public long adminite$changeOnFilesDroppedWindowHandle(long value) {
        return getFakeWindowHandle();
    }

    @Unique
    private static long getRealWindowHandle() {
        return ((FakeWindow) MinecraftClient.getInstance().getWindow()).realWindow.handle;
    }

    @Unique
    private static long getFakeWindowHandle() {
        return MinecraftClient.getInstance().getWindow().handle;
    }

}