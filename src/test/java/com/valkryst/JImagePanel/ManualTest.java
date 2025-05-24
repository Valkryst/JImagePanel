package com.valkryst.JImagePanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class ManualTest {
    private final JImagePanel imagePanel;
    private final JSlider widthSlider;
    private final JSlider heightSlider;
    private final JRadioButton squareImageRadio;
    private final JRadioButton wideImageRadio;
    private final JRadioButton tallImageRadio;

    public ManualTest() {
        // Create and set up the main frame
        final JFrame frame = new JFrame("JImagePanel Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create the layouts
        frame.setLayout(new BorderLayout());

        // Create the image panel
        final JPanel imagePanelContainer = new JPanel(new GridBagLayout());
        imagePanelContainer.setBorder(BorderFactory.createTitledBorder("Image Display"));
        frame.add(imagePanelContainer, BorderLayout.CENTER);

        // Create the control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Controls"));
        frame.add(controlPanel, BorderLayout.EAST);

        // Width slider
        JPanel widthPanel = new JPanel(new BorderLayout());
        widthPanel.setBorder(BorderFactory.createTitledBorder("Width"));
        widthSlider = new JSlider(50, 300, 100);
        widthSlider.setMajorTickSpacing(50);
        widthSlider.setMinorTickSpacing(10);
        widthSlider.setPaintTicks(true);
        widthSlider.setPaintLabels(true);
        widthPanel.add(widthSlider);
        controlPanel.add(widthPanel);

        // Height slider
        JPanel heightPanel = new JPanel(new BorderLayout());
        heightPanel.setBorder(BorderFactory.createTitledBorder("Height"));
        heightSlider = new JSlider(50, 300, 100);
        heightSlider.setMajorTickSpacing(50);
        heightSlider.setMinorTickSpacing(10);
        heightSlider.setPaintTicks(true);
        heightSlider.setPaintLabels(true);
        heightPanel.add(heightSlider);
        controlPanel.add(heightPanel);

        // Image type selection
        JPanel imageTypePanel = new JPanel();
        imageTypePanel.setLayout(new BoxLayout(imageTypePanel, BoxLayout.Y_AXIS));
        imageTypePanel.setBorder(BorderFactory.createTitledBorder("Image Type"));

        squareImageRadio = new JRadioButton("Square Image", true);
        wideImageRadio = new JRadioButton("Wide Image (3:1)", false);
        tallImageRadio = new JRadioButton("Tall Image (1:3)", false);

        ButtonGroup imageGroup = new ButtonGroup();
        imageGroup.add(squareImageRadio);
        imageGroup.add(wideImageRadio);
        imageGroup.add(tallImageRadio);

        imageTypePanel.add(squareImageRadio);
        imageTypePanel.add(wideImageRadio);
        imageTypePanel.add(tallImageRadio);
        controlPanel.add(imageTypePanel);

        // Initialize image panel with a default square image
        BufferedImage defaultImage = createSampleImage(64, 64);
        imagePanel = new JImagePanel(defaultImage);
        imagePanel.setPreferredSize(new Dimension(100, 100));
        imagePanel.addComponentListener(imagePanel); // Add component listener to itself
        imagePanelContainer.add(imagePanel);

        // Add listeners
        widthSlider.addChangeListener(this::updatePanelSize);
        heightSlider.addChangeListener(this::updatePanelSize);
        squareImageRadio.addActionListener(e -> setSquareImage());
        wideImageRadio.addActionListener(e -> setWideImage());
        tallImageRadio.addActionListener(e -> setTallImage());

        // Display the window
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void updatePanelSize(ChangeEvent e) {
        int width = widthSlider.getValue();
        int height = heightSlider.getValue();
        imagePanel.setPreferredSize(new Dimension(width, height));
        imagePanel.revalidate();
    }

    private void setSquareImage() {
        BufferedImage image = createSampleImage(64, 64);
        imagePanel.setImage(image);
        imagePanel.repaint();
    }

    private void setWideImage() {
        BufferedImage image = createSampleImage(192, 64);  // 3:1 aspect ratio
        imagePanel.setImage(image);
        imagePanel.repaint();
    }

    private void setTallImage() {
        BufferedImage image = createSampleImage(64, 192);  // 1:3 aspect ratio
        imagePanel.setImage(image);
        imagePanel.repaint();
    }

    private BufferedImage createSampleImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        // Draw a gradient background
        g2d.setPaint(new GradientPaint(0, 0, Color.BLUE, width, height, Color.GREEN));
        g2d.fill(new Rectangle2D.Double(0, 0, width, height));

        // Draw a red circle in the middle
        g2d.setPaint(Color.RED);
        g2d.fillOval(width/4, height/4, width/2, height/2);

        // Draw a grid to help visualize the aspect ratio
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(1f));
        for (int x = 0; x < width; x += 16) {
            g2d.drawLine(x, 0, x, height);
        }
        for (int y = 0; y < height; y += 16) {
            g2d.drawLine(0, y, width, y);
        }

        g2d.dispose();
        return image;
    }

    public static void main(String[] args) {
        // Run the application on the EDT
        SwingUtilities.invokeLater(ManualTest::new);
    }
}