package com.fayvl.adminite.imgui.Windows;

import com.fayvl.adminite.UI.UIMenuBar;
import com.fayvl.adminite.UI.UIMenuItems;
import com.fayvl.adminite.imgui.ImGuiImpl;
import com.mojang.blaze3d.systems.RenderSystem;
import imgui.ImGui;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.WindowEventHandler;
import net.minecraft.client.WindowSettings;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.util.Icons;
import net.minecraft.client.util.Monitor;
import net.minecraft.client.util.MonitorTracker;
import net.minecraft.client.util.VideoMode;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.tracy.TracyFrameCapturer;
import net.minecraft.resource.ResourcePack;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;
import java.util.Optional;
import java.util.OptionalInt;

public class FakeWindow extends Window {
    public RealWindow realWindow;
    public int offsetX = 500;
    public int offsetY = 500;
    public FakeWindow(String title, RealWindow realWindow, MonitorTracker monitorTracker) {
        super(new FakeWindowEventHandler(), monitorTracker, new WindowSettings(800, 600, OptionalInt.empty(), OptionalInt.empty(), false), null, title);
        GLFW.glfwHideWindow(this.getHandle());
        this.realWindow = realWindow;
        realWindow.fakeWindow = this;
    }

    public int getRefreshRate() {
        return realWindow.getRefreshRate();
    }

    public boolean shouldClose() {
        return realWindow.shouldClose();
    }

    public void setIcon(ResourcePack resourcePack, Icons icons) throws IOException {
    }

    public void close() {
        RenderSystem.assertOnRenderThread();
        Callbacks.glfwFreeCallbacks(this.handle);
        this.errorCallback.close();
        GLFW.glfwDestroyWindow(this.handle);
        GLFW.glfwTerminate();
    }

    public void swapBuffers(@Nullable TracyFrameCapturer capturer) {
        Framebuffer framebuffer = MinecraftClient.getInstance().getFramebuffer();
        // crazy that you can just like, do this
        framebuffer.viewportHeight = realWindow.getHeight();
        framebuffer.viewportWidth = realWindow.getWidth();

        ImGuiImpl.draw(io -> {
            FakeWindow fakeWindow = (FakeWindow) MinecraftClient.getInstance().getWindow();
            float width = fakeWindow.realWindow.getWidth();
            float height = fakeWindow.realWindow.getHeight();

            //ImGui.setNextWindowPos(0, 0);
            // this does absolutely nothing in the slightest
            //ImGui.setNextWindowSize(width, height);
            ImGui.begin("Adminite Menu");

            UIMenuItems.UIButton button = new UIMenuItems.UIButton("Style Editor", UIMenuItems.UIState.PREMIUM);
            UIMenuItems.UICheckbox checkbox = new UIMenuItems.UICheckbox("Enable this style", UIMenuItems.UIState.DEFAULT, true);
            UIMenuItems.UISlider slider = new UIMenuItems.UISlider("Volume", UIMenuItems.UIState.PREMIUM, 0.5f, 0.0f, 1.0f);

            button.render();
            checkbox.render();
            slider.render();

            ImGui.end();

            ImGui.showDemoWindow();
            UIMenuBar.render();

        });

        realWindow.swapBuffers(capturer);

        framebuffer.viewportHeight = realWindow.fakeWindow.getHeight();
        framebuffer.viewportWidth = realWindow.fakeWindow.getWidth();
    }

    public Optional<VideoMode> getFullscreenVideoMode() {
        return Optional.empty();
    }

    public void setFullscreenVideoMode(Optional<VideoMode> fullscreenVideoMode) {
    }

    public void applyFullscreenVideoMode() {
    }


    public void toggleFullscreen() {
    }

    public void setWindowedSize(int width, int height) {
        // probably want to implement this
    }

    private void updateFullscreen(boolean vsync, @Nullable TracyFrameCapturer capturer) {
    }

    public int calculateScaleFactor(int guiScale, boolean forceUnicodeFont) {
        return 1;
    }

    public void setScaleFactor(double scaleFactor) {
        super.setScaleFactor(scaleFactor);
    }

    public void setTitle(String title) {
        // nah
    }

    public long getHandle() {
        return this.handle;
    }

    public boolean isFullscreen() {
        return false;
    }

    public boolean isMinimized() {
        return false;
    }

    public int getFramebufferWidth() {
        return 800;
    }

    public int getFramebufferHeight() {
        return 600;
    }

    public void setFramebufferWidth(int framebufferWidth) {
    }

    public void setFramebufferHeight(int framebufferHeight) {
    }

    public int getWidth() {
        return 800;
    }

    public int getHeight() {
        return 600;
    }

    public int getScaledWidth() {
        return super.getScaledWidth();
    }

    public int getScaledHeight() {
        return super.getScaledHeight();
    }

    public int getX() {
        return 0;
    }

    public int getY() {
        return 0;
    }

    public double getScaleFactor() {
        return super.getScaleFactor();
    }

    @Nullable
    public Monitor getMonitor() {
        return null;
    }

    public void setRawMouseMotion(boolean rawMouseMotion) {
        // Should forward to realWindow (only when appropriate)
    }

    public void setCloseCallback(Runnable callback) {
        super.setCloseCallback(callback);
    }

    public boolean hasZeroWidthOrHeight() {
        return realWindow.hasZeroWidthOrHeight();
    }

    public static class FakeWindowEventHandler implements WindowEventHandler {
        @Override
        public void onWindowFocusChanged(boolean focused) {}

        @Override
        public void onResolutionChanged() {}

        @Override
        public void onCursorEnterChanged() {}
    }
}
