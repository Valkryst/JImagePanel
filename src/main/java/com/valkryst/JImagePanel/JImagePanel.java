package com.valkryst.JImagePanel;

import org.imgscalr.Scalr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * <p>
 *     Represents a component which displays a {@link BufferedImage} which automatically resizes itself to fit the
 *     component's bounds.
 * </p>
 *
 * <p>
 *     The image is always drawn in the center of the component, and is scaled to fit the component's bounds while
 *     maintaining its aspect ratio.
 * </p>
 */
public class JImagePanel extends JPanel implements ComponentListener {
    /**
     * <p>
     *     The original {@link BufferedImage} provided to the component during construction or VIA
     *     {@link #setImage(BufferedImage)}.
     * </p>
     *
     * <p>
     *     All operations are performed on this image to ensure that we do not lose any quality due to repeated
     *     alterations.
     * </p>
     */
    private BufferedImage originalImage;

    /** A copy of the {@link #originalImage} which has been scaled to fit the component's bounds. */
    private BufferedImage scaledImage;

    /**
     * Constructs a new {@link JImagePanel}.
     *
     * @param image {@link BufferedImage} to display.
     */
    public JImagePanel(final BufferedImage image) {
        Objects.requireNonNull(image);
        this.setImage(image);
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        if (scaledImage == null) {
            this.componentResized(null);
        }

        final int x = (this.getWidth() - scaledImage.getWidth()) / 2;
        final int y = (this.getHeight() - scaledImage.getHeight()) / 2;
        g.drawImage(scaledImage, x, y, null);
    }

    @Override
    public void componentResized(final ComponentEvent e) {
        if (this.getWidth() + this.getHeight() == 0) {
            return;
        }

        // Calculate the scaling factor to maintain aspect ratio.
        final double panelRatio = (double) this.getWidth() / this.getHeight();
        final double imageRatio = (double) originalImage.getWidth() / originalImage.getHeight();

        int targetWidth, targetHeight;

        if (imageRatio > panelRatio) {
            // Image is wider than panel (relative to height), constrain by width.
            targetWidth = this.getWidth();
            targetHeight = (int) (this.getWidth() / imageRatio);
        } else {
            // Image is taller than panel (relative to width), constrain by height.
            targetHeight = this.getHeight();
            targetWidth = (int) (this.getHeight() * imageRatio);
        }

        // Resize the image maintaining aspect ratio
        scaledImage = Scalr.resize(originalImage, targetWidth, targetHeight);
    }

    @Override
    public void componentMoved(final ComponentEvent e) {}

    @Override
    public void componentShown(final ComponentEvent e) {}

    @Override
    public void componentHidden(final ComponentEvent e) {}

    /**
     * Sets the {@link BufferedImage} to display.
     *
     * @param image {@link BufferedImage} to display.
     */
    public void setImage(final BufferedImage image) {
        Objects.requireNonNull(image);

        originalImage = image;
        this.componentResized(null);
    }
}
