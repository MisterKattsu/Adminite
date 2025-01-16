package com.fayvl.adminite.mixin;

import com.fayvl.adminite.Adminite;
import com.fayvl.adminite.imgui.ImGuiImpl;
import imgui.ImGui;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class AdminiteMixin {
    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        Adminite.LOGGER.info("This line is printed by an example mod mixin!");
    }

    @Inject(method = "render", at = @At("RETURN"))
    private void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        ImGuiImpl.draw(io -> {
            float width = MinecraftClient.getInstance().getWindow().getWidth();
            float height = MinecraftClient.getInstance().getWindow().getHeight();

            ImGui.setNextWindowPos(0, 0);
            ImGui.setNextWindowSize(width, height);

            //ImGui.begin("Hello World");
			// Draw something here, see the official example module for more information:
			// https://github.com/ocornut/imgui/blob/master/imgui_demo.cpp
            //ImGui.end();

//            ImGui.showDemoWindow();
            // ImGui.popFont();
        });
    }
}
