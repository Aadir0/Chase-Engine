package aadi;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyListner {
    private static KeyListner instance;
    final private boolean keyPressed[] = new boolean[350];

    private KeyListner() {
        
    }

    public static KeyListner get() {
        if (KeyListner.instance == null) {
            KeyListner.instance = new KeyListner();
        }

        return KeyListner.instance;
    }

    public static void keyCallback(long window, int key, int scancode, int action, int mods) {
        if (key < 0 || key >= get().keyPressed.length) {
            return;
        }
        if (action == GLFW_PRESS) {
            get().keyPressed[key] = true;
        } else if (action == GLFW_RELEASE) {
            get().keyPressed[key] = false;
        }
    }

    public static boolean isKeyPressed(int keyCode) {
        if (keyCode < 0 || keyCode >= get().keyPressed.length) {
            return false;
        }
        return get().keyPressed[keyCode];
    }
}
