package ru.granlovestea;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;

public class ConcreteClipboardOwner implements ClipboardOwner {
    public void copyImageToClipboard(BufferedImage image) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        TransferableImage transferableImage = new TransferableImage(image);
        clipboard.setContents(transferableImage, this );
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        System.out.println("Lost clipboard ownership");
    }
}
