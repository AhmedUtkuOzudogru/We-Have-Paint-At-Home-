import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorMixer extends JFrame implements ChangeListener, ActionListener {
    protected Canvas canvas;
    protected JPanel colorPanel;
    protected JPanel controlPanel;
    protected Color color;
    protected JSlider redJSlider;
    protected JSlider greenJSlider;
    protected JSlider blueJSlider;
    protected JButton changeColor;
    protected JButton exitJButton;

    public ColorMixer(Canvas canvas) {
        super("Color Mixer");
        setSize(400, 300);
        this.canvas = canvas;
        createControlPanel(); // Moved adding colorPanel to createControlPanel method
        colorPanel = new JPanel();
        add(colorPanel, BorderLayout.CENTER);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createControlPanel() {
        controlPanel = new JPanel(new GridLayout(4, 2));

        changeColor = new JButton("Change Color");
        changeColor.addActionListener(this);

        exitJButton = new JButton("Exit");
        exitJButton.addActionListener(this);

        redJSlider = new JSlider(0, 255, 175);
        redJSlider.addChangeListener(this);
        blueJSlider = new JSlider(0, 255, 175);
        blueJSlider.addChangeListener(this);
        greenJSlider = new JSlider(0, 255, 175);
        greenJSlider.addChangeListener(this);

        controlPanel.add(new JLabel("Red"));
        controlPanel.add(redJSlider);
        controlPanel.add(new JLabel("Green"));
        controlPanel.add(greenJSlider);
        controlPanel.add(new JLabel("Blue"));
        controlPanel.add(blueJSlider);

        controlPanel.add(changeColor);
        controlPanel.add(exitJButton);

        add(controlPanel, BorderLayout.SOUTH); // Added controlPanel to JFrame
    }

    private void setSampleColor() {
        int red = redJSlider.getValue();
        int green = greenJSlider.getValue();
        int blue = blueJSlider.getValue();
        color = new Color(red, green, blue);
       
        colorPanel.setBackground(color);
        colorPanel.repaint();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        setSampleColor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changeColor) {
            canvas.changeColor(color);
        }
        if (e.getSource() == exitJButton) {
            dispose();
        }
    }
}
