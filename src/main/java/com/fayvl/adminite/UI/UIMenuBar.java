package com.fayvl.adminite.UI;

import imgui.ImGui;
import com.fayvl.adminite.managers.DeveloperManager;

public class UIMenuBar {

    public static void render() {
        if (ImGui.beginMainMenuBar()) {
            renderMenuBar();
            ImGui.endMainMenuBar();
        }
    }

    public static void renderMenuBar() {
        // Left-aligned items
        if (ImGui.beginMenu("Player Settings-/ Logs")) {
            if (ImGui.menuItem("Report Options")) {
                return;
            }
            if (ImGui.menuItem("Manage Automatic Punishments")) {
                return;
            }
            ImGui.separator();
            if (ImGui.menuItem("Admin Settings")) {
                return;
            }
            ImGui.endMenu();
        }
        if(DeveloperManager.isAllowed()){
            if (ImGui.menuItem("Anti-Cheat Settings")) {
                return;
            }
        }

        ImGui.separator();


        float rightAlignPos = ImGui.getContentRegionAvailX() - ImGui.calcTextSize("Show Playerlist").x - ImGui.getStyle().getFramePadding().x * 2;
        ImGui.sameLine(rightAlignPos);

        if (ImGui.menuItem("Show Playerlist")) {
            return;
        }

        ImGui.separator();

        ImGui.menuItem("Hide (END)");
    }
}
