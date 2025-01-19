package com.fayvl.adminite.imgui.Windows;

import com.fayvl.adminite.imgui.ImGuiImpl;
import com.mojang.blaze3d.systems.RenderSystem;
import imgui.ImGui;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.WindowEventHandler;
import net.minecraft.client.WindowSettings;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.util.Icons;
import net.minecraft.client.util.Monitor;
import net.minecraft.client.util.MonitorFactory;
import net.minecraft.client.util.MonitorTracker;
import net.minecraft.client.util.VideoMode;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.tracy.TracyFrameCapturer;
import net.minecraft.resource.ResourcePack;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWWindowCloseCallback;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.BiConsumer;

public class FakeWindow extends Window {
    public RealWindow realWindow;
    public int offsetX = 500;
    public int offsetY = 500;
    public FakeWindow(String title, RealWindow realWindow, MonitorTracker monitorTracker) {
        super(new FakeWindowEventHandler(), monitorTracker, new WindowSettings(600, 600, OptionalInt.empty(), OptionalInt.empty(), false), null, title);
        this.realWindow = realWindow;
        realWindow.fakeWindow = this;
        /*
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_CLIENT_API, GLFW.GLFW_OPENGL_API);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_CREATION_API, GLFW.GLFW_NATIVE_CONTEXT_API);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 2);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, 1);

        this.updateWindowRegion();
        this.updateFramebufferSize();

        GLFW.glfwSetFramebufferSizeCallback(this.handle, this::onFramebufferSizeChanged);
        GLFW.glfwSetWindowPosCallback(this.handle, this::onWindowPosChanged);
        GLFW.glfwSetWindowSizeCallback(this.handle, this::onWindowSizeChanged);
        GLFW.glfwSetWindowFocusCallback(this.handle, this::onWindowFocusChanged);
        GLFW.glfwSetCursorEnterCallback(this.handle, this::onCursorEnterChanged);
        GLFW.glfwSetWindowIconifyCallback(this.handle, this::onMinimizeChanged);
         */
    }

    public static String getGlfwPlatform() {
        int i = GLFW.glfwGetPlatform();
        String var10000;
        switch (i) {
            case 0 -> var10000 = "<error>";
            case 393217 -> var10000 = "win32";
            case 393218 -> var10000 = "cocoa";
            case 393219 -> var10000 = "wayland";
            case 393220 -> var10000 = "x11";
            case 393221 -> var10000 = "null";
            default -> var10000 = String.format(Locale.ROOT, "unknown (%08X)", i);
        }

        return var10000;
    }

    public int getRefreshRate() {
        return realWindow.getRefreshRate();
    }

    public boolean shouldClose() {
        return realWindow.shouldClose();
    }

    public static void acceptError(BiConsumer<Integer, String> consumer) {
    }

    public void setIcon(ResourcePack resourcePack, Icons icons) throws IOException {
    }

    /*
    public void setPhase(String phase) {
        this.phase = phase;
    }
     */

    /*
    private void throwOnGlError() {
        GLFW.glfwSetErrorCallback(Window::throwGlError);
    }
     */

    /*
    private static void throwGlError(int error, long description) {
        String string = "GLFW error " + error + ": " + MemoryUtil.memUTF8(description);
        TinyFileDialogs.tinyfd_messageBox("Minecraft", string + ".\n\nPlease make sure you have up-to-date drivers (see aka.ms/mcdriver for instructions).", "ok", "error", false);
        throw new GlErroredException(string);
    }
     */

    /*
    public void logGlError(int error, long description) {
        RenderSystem.assertOnRenderThread();
        String string = MemoryUtil.memUTF8(description);
        LOGGER.error("########## GL ERROR ##########");
        LOGGER.error("@ {}", this.phase);
        LOGGER.error("{}: {}", error, string);
    }
     */

    /*
    public void logOnGlError() {
        GLFWErrorCallback gLFWErrorCallback = GLFW.glfwSetErrorCallback(this.errorCallback);
        if (gLFWErrorCallback != null) {
            gLFWErrorCallback.free();
        }
    }
     */

    /*
    public void setVsync(boolean vsync) {
        RenderSystem.assertOnRenderThreadOrInit();
        this.vsync = vsync;
        GLFW.glfwSwapInterval(vsync ? 1 : 0);
    }
     */

    public void close() {
        RenderSystem.assertOnRenderThread();
        Callbacks.glfwFreeCallbacks(this.handle);
        this.errorCallback.close();
        GLFW.glfwDestroyWindow(this.handle);
        GLFW.glfwTerminate();
    }

    private void onWindowPosChanged(long window, int x, int y) {
    }

    private void onFramebufferSizeChanged(long window, int width, int height) {
    }

    private void updateFramebufferSize() {
    }

    private void onWindowSizeChanged(long window, int width, int height) {
    }

    private void onWindowFocusChanged(long window, boolean focused) {

    }

    private void onCursorEnterChanged(long window, boolean entered) {

    }

    private void onMinimizeChanged(long window, boolean minimized) {
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

            ImGui.setNextWindowPos(0, 0);
            // this does absolutely nothing in the slightest
            ImGui.setNextWindowSize(width, height);

            //ImGui.begin("Hello World");
            // Draw something here, see the official example module for more information:
            // https://github.com/ocornut/imgui/blob/master/imgui_demo.cpp
            //ImGui.end();
            ImGui.showDemoWindow();

            //            ImGui.showDemoWindow();
            // ImGui.popFont();
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
        return 600;
    }

    public int getFramebufferHeight() {
        return 600;
    }

    public void setFramebufferWidth(int framebufferWidth) {
    }

    public void setFramebufferHeight(int framebufferHeight) {
    }

    public int getWidth() {
        return 600;
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
        // might want to forward to real window, but this is for usage with an external gui so probably not
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
