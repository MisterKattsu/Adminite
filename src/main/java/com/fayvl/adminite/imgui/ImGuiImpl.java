package com.fayvl.adminite.imgui;

import imgui.*;
import imgui.extension.implot.ImPlot;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import com.fayvl.adminite.imgui.UIStyleSheet;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.util.Window;
import org.apache.commons.compress.utils.IOUtils;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImGuiImpl {
    private final static ImGuiImplGlfw imGuiImplGlfw = new ImGuiImplGlfw();
    private final static ImGuiImplGl3 imGuiImplGl3 = new ImGuiImplGl3();
    private final static UIStyleSheet styleSheet = new UIStyleSheet();

    public static void create(final long handle) throws IOException {
        ImGui.createContext();
        ImPlot.createContext();
        styleSheet.PushUIStyle();


        final ImGuiIO data = ImGui.getIO();
        data.setIniFilename("adminite.ini");
        data.setFontGlobalScale(0.6F);

        MinecraftClient.getInstance().mouse.setResolutionChanged();


        {
            final ImFontAtlas fonts = data.getFonts();
            final ImFontGlyphRangesBuilder rangesBuilder = new ImFontGlyphRangesBuilder();

            rangesBuilder.addRanges(data.getFonts().getGlyphRangesDefault());
            rangesBuilder.addRanges(data.getFonts().getGlyphRangesCyrillic());
            rangesBuilder.addRanges(data.getFonts().getGlyphRangesJapanese());

            final short[] glyphRanges = rangesBuilder.buildRanges();

            final ImFontConfig basicConfig = new ImFontConfig();
            basicConfig.setGlyphRanges(data.getFonts().getGlyphRangesCyrillic());

            final List<ImFont> generatedFonts = new ArrayList<>();
            try {
                byte[] fontData = Files.readAllBytes(Paths.get(Objects.requireNonNull(
                        ImGuiImpl.class.getResource("/fonts/Aller_Bd.ttf")).toURI())); // Correct resource path

                // Use larger font sizes to avoid tiny text
                for (int i = 20; i < 70; i++) { // Start from 20px, up to 70px
                    basicConfig.setName("Aller_Bd " + i + "px");
                    generatedFonts.add(fonts.addFontFromMemoryTTF(fontData, i, basicConfig, glyphRanges));
                }
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
            fonts.build();
            basicConfig.destroy();

            // Select a larger font size (e.g., 50px)
            ImFont defaultFont = generatedFonts.get(25); // 55px font size
        }

        data.setConfigFlags(ImGuiConfigFlags.DockingEnable);

        // In case you want to enable Viewports on Windows, you have to do this instead of the above line:
        // data.setConfigFlags(ImGuiConfigFlags.DockingEnable | ImGuiConfigFlags.ViewportsEnable);

        imGuiImplGlfw.init(handle, true);
        imGuiImplGl3.init();
    }

    public static void draw(final RenderInterface runnable) {
        // start frame
        imGuiImplGl3.newFrame();
        imGuiImplGlfw.newFrame(); // Handle keyboard and mouse interactions
        ImGui.newFrame();

        // do rendering logic
        runnable.render(ImGui.getIO());

        // end frame
        ImGui.render();
        imGuiImplGl3.renderDrawData(ImGui.getDrawData());


        if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
         final long pointer = GLFW.glfwGetCurrentContext();
           ImGui.updatePlatformWindows();
           ImGui.renderPlatformWindowsDefault();

            GLFW.glfwMakeContextCurrent(pointer);
        }
    }

    public static void dispose() {
        imGuiImplGl3.shutdown();

        ImGui.destroyContext();
        ImPlot.destroyContext();
    }

// Can be used to load buffered images in ImGui
//    public static int fromBufferedImage(BufferedImage image) {
//        final int[] pixels = new int[image.getWidth() * image.getHeight()];
//        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
//
//        final ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4);
//
//        for (int y = 0; y < image.getHeight(); y++) {
//            for (int x = 0; x < image.getWidth(); x++) {
//                final int pixel = pixels[y * image.getWidth() + x];
//
//                buffer.put((byte) ((pixel >> 16) & 0xFF));
//                buffer.put((byte) ((pixel >> 8) & 0xFF));
//                buffer.put((byte) (pixel & 0xFF));
//                buffer.put((byte) ((pixel >> 24) & 0xFF));
//            }
//        }
//
//        buffer.flip();
//
//        final int texture = GlStateManager._genTexture();
//        GlStateManager._bindTexture(texture);
//
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
//
//        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
//
//        return texture;
//    }
}
