package com.fayvl.adminite.UI;

import com.fayvl.adminite.Adminite;
import com.fayvl.adminite.imgui.TextureLoader;
import imgui.ImGui;
import imgui.flag.ImGuiHoveredFlags;

public class UIMenuItems {

    static int locker1;

    public static void initialize() {
        locker1 = TextureLoader.loadTexture("Locker1.png");
    }

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
            renderIconIfRestricted(state);
            switch (state) {
                case ACCESS_RESTRICTED, PREMIUM -> {
                    ImGui.beginDisabled();
                    ImGui.button(label);
                    ImGui.endDisabled();
                    renderTooltip(state, label);
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
            renderIconIfRestricted(state);
            switch (state) {
                case ACCESS_RESTRICTED, PREMIUM -> {
                    ImGui.beginDisabled();
                    ImGui.checkbox(label, checked);
                    ImGui.endDisabled();
                    renderTooltip(state, label);
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
            renderIconIfRestricted(state);
            switch (state) {
                case ACCESS_RESTRICTED, PREMIUM -> {
                    ImGui.beginDisabled();
                    ImGui.sliderFloat(label, new float[]{value}, minValue, maxValue);
                    ImGui.endDisabled();
                    renderTooltip(state, label);
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

    private static void renderIconIfRestricted(UIState state) {
        if (state == UIState.ACCESS_RESTRICTED || state == UIState.PREMIUM) {
            float iconSize = 16; // Adjust as needed
            float iconPadding = (ImGui.getFontSize() - iconSize) / 2; // Center the icon vertically
            ImGui.setCursorPosY(ImGui.getCursorPosY() + iconPadding); // Offset the Y position
            TextureLoader.drawIcon(locker1, 16.0f, 16.0f);
            ImGui.sameLine();
        }
    }


    private static void renderTooltip(UIState state, String label) {
        if (ImGui.isItemHovered(ImGuiHoveredFlags.AllowWhenDisabled)) {
            ImGui.beginTooltip();
            ImGui.text(String.format(state == UIState.ACCESS_RESTRICTED
                    ? "Access Restricted!"
                    : "%s Is A Premium Feature!", label));
            ImGui.endTooltip();
        }
    }
}
