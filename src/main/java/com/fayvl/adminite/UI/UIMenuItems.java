package com.fayvl.adminite.UI;

import com.fayvl.adminite.imgui.ImGuiImpl;
import imgui.ImGui;
import imgui.flag.ImGuiHoveredFlags;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;

public class UIMenuItems {

    public enum UIState {
        ACCESS_RESTRICTED,
        PREMIUM,
        DEFAULT
    }

    public static class UIButton {
        private final String label;
        private final UIState state;

        public UIButton(String label, UIState state) {
            this.label = label;
            this.state = state;
        }

        public void render() {
            switch (state) {
                case ACCESS_RESTRICTED, PREMIUM -> {
                    ImGui.beginDisabled();
                    ImGui.button(label);
                    ImGui.endDisabled();
                    if (ImGui.isItemHovered(ImGuiHoveredFlags.AllowWhenDisabled)) {
                        ImGui.beginTooltip();
                        ImGui.text(state == UIState.ACCESS_RESTRICTED
                                ? "Access Restricted!"
                                : "Premium Feature!");
                        ImGui.endTooltip();
                    }
                }
                case DEFAULT -> {
                    if (ImGui.button(label)) {
                        System.out.println(label + " Button Pressed");
                    }
                }
            }
        }
    }

    public static class UICheckbox {
        private final String label;
        private final UIState state;
        private boolean checked;

        public UICheckbox(String label, UIState state, boolean initialChecked) {
            this.label = label;
            this.state = state;
            this.checked = initialChecked;
        }

        public void render() {
            switch (state) {
                case ACCESS_RESTRICTED, PREMIUM -> {
                    ImGui.beginDisabled();
                    ImGui.checkbox(label, checked);
                    ImGui.endDisabled();
                    if (ImGui.isItemHovered(ImGuiHoveredFlags.AllowWhenDisabled)) {
                        ImGui.beginTooltip();
                        ImGui.text(state == UIState.ACCESS_RESTRICTED
                                ? "Access Restricted!"
                                : "Premium Feature!");
                        ImGui.endTooltip();
                    }
                }
                case DEFAULT -> {
                    if (ImGui.checkbox(label, checked)) {
                        checked = !checked;
                        System.out.println(label + " Checkbox Toggled: " + checked);
                    }
                }
            }
        }
    }

    // UISlider Class
    public static class UISlider {
        private final String label;
        private final UIState state;
        private float value;
        private final float minValue;
        private final float maxValue;

        public UISlider(String label, UIState state, float initialValue, float minValue, float maxValue) {
            this.label = label;
            this.state = state;
            this.value = initialValue;
            this.minValue = minValue;
            this.maxValue = maxValue;
        }

        public void render() {
            switch (state) {
                case ACCESS_RESTRICTED, PREMIUM -> {
                    ImGui.beginDisabled();
                    ImGui.sliderFloat(label, new float[]{value}, minValue, maxValue);
                    ImGui.endDisabled();
                    if (ImGui.isItemHovered(ImGuiHoveredFlags.AllowWhenDisabled)) {
                        ImGui.beginTooltip();
                        ImGui.text(state == UIState.ACCESS_RESTRICTED
                                ? "Access Restricted!"
                                : "Premium Feature!");
                        ImGui.endTooltip();
                    }
                }
                case DEFAULT -> {
                    float[] valueArray = {value};
                    if (ImGui.sliderFloat(label, valueArray, minValue, maxValue)) {
                        value = valueArray[0];
                        System.out.println(label + " Slider Changed: " + value);
                    }
                }
            }
        }
    }

}