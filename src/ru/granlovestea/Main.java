package ru.granlovestea;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final char MAKE_SCREENSHOT_KEY_EVENT = NativeKeyEvent.VC_NUM_LOCK;

    public static void main(String[] args) throws AWTException, IOException {
        Tray.setUpTray();
        Robot robot = new Robot();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRect = new Rectangle(0, 0, screenSize.width, screenSize.height);
        ConcreteClipboardOwner clipboardOwner = new ConcreteClipboardOwner();

        disableTrashLogs();
        setKeyListener(() -> {
            BufferedImage screenshot = robot.createScreenCapture(screenRect);
            clipboardOwner.copyImageToClipboard(screenshot);
        });
    }

    private static void setKeyListener(Callback callback) {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(new GlobalKeyListener(MAKE_SCREENSHOT_KEY_EVENT, callback));
    }

    private static void disableTrashLogs() {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);
        logger.setUseParentHandlers(false);
    }
}
