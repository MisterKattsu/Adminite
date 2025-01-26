package com.fayvl.adminite.imgui;

import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBImage;
import java.io.InputStream;
import java.nio.ByteBuffer;
import org.lwjgl.opengl.GL12;
import imgui.ImGui;

public class TextureLoader {

    public static int loadTexture(String path) {
        InputStream textureStream = TextureLoader.class.getClassLoader().getResourceAsStream(path);

        if (textureStream == null) {
            throw new RuntimeException("Failed to load texture: " + path);
        }

        ByteBuffer buffer;
        try {
            buffer = ByteBuffer.wrap(textureStream.readAllBytes());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading texture file: " + path);
        }

        int textureId = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);

        int[] width = new int[1];
        int[] height = new int[1];
        int[] channels = new int[1];
        ByteBuffer image = STBImage.stbi_load(buffer, width, height, channels, 4);
        if (image == null) {
            throw new RuntimeException("Failed to load texture: " + path);
        }

        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width[0], height[0], 0,
                GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, image);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

        STBImage.stbi_image_free(image);

        return textureId;
    }

    public static void drawIcon(int textureId, float width, float height) {
        // This is where we draw the loaded icon
        ImGui.image((long) textureId, width, height);
    }

}
