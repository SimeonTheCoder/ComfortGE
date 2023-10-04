package graphics;

import scenes.SceneContainer;
import sensors.KeyboardSensor;
import sensors.MouseSensor;

import javax.swing.*;
import java.awt.*;

public class Window extends JPanel {
    public SceneContainer sceneContainer;

    public static JFrame frame;

    public Window(String title, int width, int height) {
        frame = new JFrame(title);
        frame.setSize(width, height);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addMouseListener(new MouseSensor());
        frame.addKeyListener(new KeyboardSensor());

        frame.add(this);

        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Renderer.render((Graphics2D) g);

        repaint();
    }
}
