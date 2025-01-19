package com.fayvl.adminite.imgui.Windows;

import net.minecraft.client.WindowEventHandler;
import net.minecraft.client.WindowSettings;
import net.minecraft.client.util.MonitorTracker;
import net.minecraft.client.util.Window;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

public class RealWindow extends Window {
    public FakeWindow fakeWindow;
    public RealWindow(WindowEventHandler eventHandler, MonitorTracker monitorTracker, WindowSettings settings, @Nullable String fullscreenVideoMode, String title) {
        super(eventHandler, monitorTracker, settings, fullscreenVideoMode, title);
        GLFW.glfwSetWindowSizeCallback(this.handle, (window, width, height) -> {
            if(window != this.getHandle()) return;
            fakeWindow.offsetX = width / 2 - (fakeWindow.getWidth() / 2);
            fakeWindow.offsetY = height / 2 - (fakeWindow.getHeight() / 2);
            super.onWindowSizeChanged(window, width, height);
        });
    }
}
