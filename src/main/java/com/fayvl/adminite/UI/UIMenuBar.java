package com.fayvl.adminite.UI;
import imgui.ImGui;

public class UIMenuBar {

    public static void render() {
        if (ImGui.beginMainMenuBar()) {
            renderMenuBar();
            ImGui.endMainMenuBar();
        }
    }

    public static void renderMenuBar() {
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
        if (ImGui.menuItem("Anti-Cheat Settings")) {
            return;
        }

        ImGui.separator();

        if (ImGui.menuItem("Show Playerlist")) {
            return;
        }

        ImGui.separator();

        if (ImGui.menuItem("Hide (END)")) {
            return;
        }
    }

}
