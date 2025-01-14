package com.fayvl.adminite.imgui;

import imgui.*;
import imgui.flag.ImGuiCol;

public class UIStyleSheet {

    public void PushUIStyle() {
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

        // Set colors using RGBA float values
        style.setColor(ImGuiCol.Text, 0.80f, 0.80f, 0.83f, 1.00f);
        style.setColor(ImGuiCol.TextDisabled, 0.24f, 0.23f, 0.29f, 1.00f);
        style.setColor(ImGuiCol.WindowBg, 0.06f, 0.05f, 0.07f, 1.00f);
        style.setColor(ImGuiCol.PopupBg, 0.07f, 0.07f, 0.09f, 1.00f);
        style.setColor(ImGuiCol.Border, 0.90588f, 0.89412f, 0.89412f, 0.2f);
        style.setColor(ImGuiCol.FrameBg, 0.10f, 0.09f, 0.12f, 1.00f);
        style.setColor(ImGuiCol.FrameBgHovered, 0.24f, 0.23f, 0.29f, 1.00f);
        style.setColor(ImGuiCol.FrameBgActive, 0.56f, 0.56f, 0.58f, 1.00f);
        style.setColor(ImGuiCol.TitleBg, 0.10f, 0.09f, 0.12f, 1.00f);
        style.setColor(ImGuiCol.TitleBgCollapsed, 1.00f, 0.98f, 0.95f, 0.75f);
        style.setColor(ImGuiCol.TitleBgActive, 0.07f, 0.07f, 0.09f, 1.00f);
        style.setColor(ImGuiCol.MenuBarBg, 0.10f, 0.09f, 0.12f, 1.00f);
        style.setColor(ImGuiCol.ScrollbarBg, 0.10f, 0.09f, 0.12f, 1.00f);
        style.setColor(ImGuiCol.ScrollbarGrab, 0.80f, 0.80f, 0.83f, 0.31f);
        style.setColor(ImGuiCol.ScrollbarGrabHovered, 0.56f, 0.56f, 0.58f, 1.00f);
        style.setColor(ImGuiCol.ScrollbarGrabActive, 0.06f, 0.05f, 0.07f, 1.00f);
        style.setColor(ImGuiCol.CheckMark, 0.90f, 0.22f, 0.34f, 1.0f);
        style.setColor(ImGuiCol.SliderGrab, 0.80f, 0.80f, 0.83f, 0.31f);
        style.setColor(ImGuiCol.SliderGrabActive, 0.06f, 0.05f, 0.07f, 1.00f);
        style.setColor(ImGuiCol.Button, 0.10f, 0.09f, 0.12f, 1.00f);
        style.setColor(ImGuiCol.ButtonHovered, 0.24f, 0.23f, 0.29f, 1.00f);
        style.setColor(ImGuiCol.ButtonActive, 0.56f, 0.56f, 0.58f, 1.00f);
        style.setColor(ImGuiCol.Header, 0.10f, 0.09f, 0.12f, 1.00f);
        style.setColor(ImGuiCol.HeaderHovered, 0.90f, 0.22f, 0.34f, 1.0f);
        style.setColor(ImGuiCol.HeaderActive, 0.06f, 0.05f, 0.07f, 1.00f);
        style.setColor(ImGuiCol.ResizeGrip, 0.00f, 0.00f, 0.00f, 0.00f);
        style.setColor(ImGuiCol.ResizeGripHovered, 0.56f, 0.56f, 0.58f, 1.00f);
        style.setColor(ImGuiCol.ResizeGripActive, 0.06f, 0.05f, 0.07f, 1.00f);
        style.setColor(ImGuiCol.PlotLines, 0.40f, 0.39f, 0.38f, 0.63f);
        style.setColor(ImGuiCol.PlotLinesHovered, 0.25f, 1.00f, 0.00f, 1.00f);
        style.setColor(ImGuiCol.PlotHistogram, 0.40f, 0.39f, 0.38f, 0.63f);
        style.setColor(ImGuiCol.PlotHistogramHovered, 0.25f, 1.00f, 0.00f, 1.00f);
        style.setColor(ImGuiCol.TextSelectedBg, 0.25f, 1.00f, 0.00f, 0.43f);
        style.setColor(ImGuiCol.ModalWindowDimBg, 1.00f, 0.98f, 0.95f, 0.73f);
        style.setColor(ImGuiCol.DockingPreview,0.79f, 0.12f, 0.26f, 1.0f);
        style.setColor(ImGuiCol.Tab, 0.88f, 0.17f, 0.30f, 1.0f);          // #e02b4c
        style.setColor(ImGuiCol.TabHovered, 0.94f, 0.27f, 0.38f, 1.0f);   // #f04561
        style.setColor(ImGuiCol.TabActive, 0.85f, 0.14f, 0.28f, 1.0f);
        style.setColor(ImGuiCol.TextSelectedBg, 0.90f, 0.22f, 0.34f, 1.0f);

    }
}
