import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

public class Canvas extends JFrame implements MouseMotionListener, ActionListener {
    protected Color color;

    protected JMenuBar menuBar;
    protected JPanel canvas;
    protected JButton toggle;
    protected JButton clear;
    protected JButton changeColJButton;
    protected ColorMixer colorMixer;
    protected JComboBox size;
    protected Dimension dimension;
    protected static int sizeX;
    protected static int sizeY;
    protected Point userInput;
    protected String[] sizes = { "Small", "Medium", "Large" };

    public Canvas() {
        sizeX = 20;
        sizeY = 20;
        setTitle("Canvas");
        addMouseMotionListener(this);
        setSize(1000, 800);
        setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        // Set the size of the frame to match the screen dimensions
        setSize(screenWidth, screenHeight);
        getContentPane().setBackground(Color.WHITE);
        createMenuBar();
    }

    public void setColor(Color aColor) {
        color = aColor;
    }

    private void createMenuBar() {
        menuBar = new JMenuBar();
        this.size = new JComboBox(sizes);
        size.setSelectedItem("Medium");
        size.setEditable(true);
        size.addActionListener(this);
        toggle = new JButton("Draw");
        clear = new JButton("Clear");
        changeColJButton = new JButton("Change Color");

        changeColJButton.addActionListener(this);
        toggle.addActionListener(this);
        clear.addActionListener(this);
        menuBar.add(toggle);

        menuBar.add(size);
        menuBar.add(changeColJButton);

        menuBar.add(clear);
        this.add(menuBar, BorderLayout.NORTH);
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
        Graphics g = getGraphics();
        g.setColor(color);
        g.fillOval(e.getX(), e.getY(), sizeX, sizeY);

    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == toggle) {
            if (color == Color.BLACK) {
                erase();
            } else {
                draw();
            }
        }
        if (e.getSource() == clear) {
            draw();
            repaint();
        }
        if (e.getSource() == changeColJButton) {
            this.colorMixer = new ColorMixer(this);
        }
        if (e.getSource() == size) {
            String selectedSize = (String) size.getSelectedItem();
            switch (selectedSize) {
                case "Small":
                    sizeX = 10;
                    sizeY = 10;
                    break;
                case "Medium":
                    sizeX = 20;
                    sizeY = 20;
                    break;
                case "Large":
                    sizeX = 30;
                    sizeY = 30;
                    break;
                default:
                    parseDimension(selectedSize);
                    break;
            }

        }
    }

    public void draw() {
        toggle.setText("Draw");
        setColor(Color.BLACK);
    }

    public void erase() {
        toggle.setText("Erase");
        setColor(Color.WHITE);
    }

    public static void changeSize(Point point) {
        sizeX = (int) point.getX();
        sizeY = (int) point.getY();
    }

    public void changeColor(Color color2) {
        color = color2;
    }

    public static void parseDimension(String dimension) {
        if (!dimension.contains("x")) {
            JOptionPane.showMessageDialog(null, "Dimension must contain 'x' delimiter", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            String[] parts = dimension.split("x");

        try {
            Point point = new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            changeSize(point);
            if (sizeX <= 0 || sizeY <= 0) {
                JOptionPane.showMessageDialog(null, "Width and height must be positive integers", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid dimension format. Width and height must be integers separated by 'x'.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

        
    }

}
