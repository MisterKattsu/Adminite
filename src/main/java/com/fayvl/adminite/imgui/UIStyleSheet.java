package com.fayvl.adminite.imgui;

import imgui.*;
import imgui.flag.ImGuiCol;
import org.jetbrains.annotations.NotNull;

public class UIStyleSheet {

    public void PushUIStyle() {
        ImGuiStyle style = getGuiStyle();

        // Set colors using RGBA float values derived from provided color codes
        style.setColor(ImGuiCol.Text, 1.00f, 1.00f, 1.00f, 1.00f);               // White
        style.setColor(ImGuiCol.TextDisabled, 0.57f, 0.57f, 0.57f, 1.00f);      // Platinum (#E5E5E5)
        style.setColor(ImGuiCol.WindowBg, 0.07f, 0.07f, 0.07f, 1.00f);          // #111212
        style.setColor(ImGuiCol.PopupBg, 0.14f, 0.18f, 0.25f, 1.00f);           // Gunmetal (#242F40)
        style.setColor(ImGuiCol.Border, 0.36f, 0.36f, 0.36f, 0.80f);            // Jet (#363636)
        style.setColor(ImGuiCol.FrameBg, 0.1216f, 0.1137f, 0.1137f, 1f);           // #111212
        style.setColor(ImGuiCol.FrameBgHovered, 0.14f, 0.18f, 0.25f, 1.00f);    // Gunmetal (#242F40)
        style.setColor(ImGuiCol.FrameBgActive, 0.80f, 0.65f, 0.23f, 1.00f);     // Satin Sheen Gold (#CCA43B)
        style.setColor(ImGuiCol.TitleBg, 0.14f, 0.18f, 0.25f, 1.00f);           // Gunmetal (#242F40)
        style.setColor(ImGuiCol.TitleBgCollapsed, 0.07f, 0.07f, 0.07f, 0.75f);  // #111212
        style.setColor(ImGuiCol.TitleBgActive, 0.80f, 0.65f, 0.23f, 1.00f);     // Satin Sheen Gold (#CCA43B)
        style.setColor(ImGuiCol.MenuBarBg, 0.14f, 0.18f, 0.25f, 1.00f);         // Gunmetal (#242F40)
        style.setColor(ImGuiCol.ScrollbarBg, 0.07f, 0.07f, 0.07f, 1.00f);       // #111212
        style.setColor(ImGuiCol.ScrollbarGrab, 0.80f, 0.65f, 0.23f, 1.00f);     // Satin Sheen Gold (#CCA43B)
        style.setColor(ImGuiCol.ScrollbarGrabHovered, 1.00f, 1.00f, 1.00f, 1.00f); // White
        style.setColor(ImGuiCol.ScrollbarGrabActive, 0.80f, 0.65f, 0.23f, 1.00f); // Satin Sheen Gold (#CCA43B)
        style.setColor(ImGuiCol.CheckMark, 0.80f, 0.65f, 0.23f, 1.00f);         // Satin Sheen Gold (#CCA43B)
        style.setColor(ImGuiCol.SliderGrab, 1.00f, 1.00f, 1.00f, 1.00f);        // Gunmetal (#242F40)
        style.setColor(ImGuiCol.SliderGrabActive, 0.80f, 0.65f, 0.23f, 1.00f);  // Satin Sheen Gold (#CCA43B)
        style.setColor(ImGuiCol.Button, 0.14f, 0.18f, 0.25f, 1.00f);            // Gunmetal (#242F40)
        style.setColor(ImGuiCol.ButtonHovered, 0.80f, 0.65f, 0.23f, 1.00f);     // Satin Sheen Gold (#CCA43B)
        style.setColor(ImGuiCol.ButtonActive, 0.57f, 0.57f, 0.57f, 1.00f);      // Platinum (#E5E5E5)
        style.setColor(ImGuiCol.Header, 0.14f, 0.18f, 0.25f, 1.00f);            // Gunmetal (#242F40)
        style.setColor(ImGuiCol.HeaderHovered, 0.80f, 0.65f, 0.23f, 1.00f);     // Satin Sheen Gold (#CCA43B)
        style.setColor(ImGuiCol.HeaderActive, 0.57f, 0.57f, 0.57f, 1.00f);      // Platinum (#E5E5E5)
        style.setColor(ImGuiCol.Tab, 0.14f, 0.18f, 0.25f, 1.00f);               // Gunmetal (#242F40)
        style.setColor(ImGuiCol.TabHovered, 0.80f, 0.65f, 0.23f, 1.00f);        // Satin Sheen Gold (#CCA43B)
        style.setColor(ImGuiCol.TabActive, 0.14f, 0.18f, 0.25f, 1.00f);         // Gunmetal (#242F40)
        style.setColor(ImGuiCol.TextSelectedBg, 0.80f, 0.65f, 0.23f, 1.00f);
    }

    private static @NotNull ImGuiStyle getGuiStyle() {
        ImGuiStyle style = ImGui.getStyle();

        style.setWindowPadding(8.0f, 8.0f);
        style.setWindowRounding(3.0f);
        style.setFramePadding(5.0f, 5.0f);
        style.setFrameRounding(3.0f);
        style.setChildRounding(3.0f);
        style.setItemSpacing(12.0f, 8.0f);
        style.setItemInnerSpacing(8.0f, 6.0f);
        style.setIndentSpacing(25.0f);
        style.setScrollbarSize(15.0f);
        style.setScrollbarRounding(9.0f);
        style.setGrabMinSize(5.0f);
        style.setGrabRounding(3.0f);
        style.setWindowBorderSize(1.5f);
        return style;
    }
}