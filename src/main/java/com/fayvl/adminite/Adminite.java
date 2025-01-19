package com.fayvl.adminite;

import com.fayvl.adminite.imgui.ImGuiImpl;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static net.minecraft.server.command.CommandManager.*;
import imgui.ImGui;

public class Adminite implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("adminite");

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(
                literal("foo")
                        .executes(context -> {
                            context.getSource().sendFeedback(() -> Text.literal("Opening ImGui Demo Window"), false);

//                            // Call to open the demo window
//                            ImGui.showDemoWindow();

                            return 1;
                        })
        ));

        LOGGER.info("Hello Fabric world!");
    }
}
