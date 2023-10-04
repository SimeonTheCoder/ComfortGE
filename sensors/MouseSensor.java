package sensors;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseSensor implements MouseListener {
    public boolean mouse;

    public MouseSensor() {
        this.mouse = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.mouse = true;
        SensorData.mouse = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.mouse = false;
        SensorData.mouse = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
