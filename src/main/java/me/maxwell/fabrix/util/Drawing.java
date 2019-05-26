package me.maxwell.fabrix.util;

/**
 * @author lazy
 * @since 5/26/2019 at 1:58 AM
 */
public enum Drawing {
    INSTANCE;

    Drawing() { }

    public void drawString(String string, int x, int y, int color) {
        Wrapper.INSTANCE.getDefaultTextRenderer().draw(string, x, y, color);
    }

    public void drawStringWithShadow(String string, int x, int y, int color) {
        Wrapper.INSTANCE.getDefaultTextRenderer().drawWithShadow(string, x, y, color);
    }
}
