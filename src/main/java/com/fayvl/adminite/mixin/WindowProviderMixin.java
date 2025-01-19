package com.fayvl.adminite.mixin;

import com.fayvl.adminite.imgui.Windows.FakeWindow;
import com.fayvl.adminite.imgui.Windows.RealWindow;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.WindowSettings;
import net.minecraft.client.util.MonitorTracker;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.WindowProvider;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.Configuration;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(WindowProvider.class)
public class WindowProviderMixin {


    @Shadow @Final private MinecraftClient client;

    @Shadow @Final private MonitorTracker monitorTracker;

    /**
     * @author Blackilykat
     * @reason Need to use fake windows for windowed views of the game with gui around it. Yes this could cause
     *         incompatibilities with other mods but any other mod that would have a reason to mess with this method
     *         could be expected to break with this anyway (I struggle to see a universal way of doing this without
     *         writing logic specific to other specific mods)
     */
    @Overwrite
    public Window createWindow(WindowSettings settings, @Nullable String videoMode, String title) {
        // return new RealWindow(client, monitorTracker, settings, videoMode, title);
        FakeWindow win = new FakeWindow(title, new RealWindow(client, monitorTracker, settings, videoMode, title), monitorTracker);
        GLFW.glfwMakeContextCurrent(win.realWindow.handle);
        return win;
    }}
