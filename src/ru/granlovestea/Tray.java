package ru.granlovestea;

import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

public class Tray {
    public static void setUpTray() {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            URL imgURL = Tray.class.getResource("resources/icon.png");
            Image image = Toolkit.getDefaultToolkit().getImage(imgURL);
            ActionListener listener = e -> System.exit(0);
            PopupMenu popupMenu = new PopupMenu();
            MenuItem menuItem = new MenuItem("Exit");
            menuItem.addActionListener(listener);
            popupMenu.add(menuItem);
            TrayIcon trayIcon = new TrayIcon(image, "Screenshooter", popupMenu);
            trayIcon.addActionListener(listener);

            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Can't set up tray");
        }
    }
}
