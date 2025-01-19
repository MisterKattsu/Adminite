package com.fayvl.adminite.mixin;

import com.fayvl.adminite.imgui.Windows.FakeWindow;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @ModifyVariable(method = "setup", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    public long adminite$changeSetupWindowHandle(long value) {
        return getRealWindowHandle();
    }

    @ModifyVariable(method = "onKey", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    public long adminite$changeOnKeyWindowHandle(long value) {
        return getFakeWindowHandle();
    }

    @ModifyVariable(method = "onChar", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    public long adminite$changeOnCharWindowHandle(long value) {
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
