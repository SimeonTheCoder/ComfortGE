package sensors;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardSensor implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() > 0 && e.getKeyChar() < 256) {
            SensorData.keyTyped[e.getKeyChar()] = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() > 0 && e.getKeyChar() < 256) {
            SensorData.keyStatus[e.getKeyChar()] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyChar() > 0 && e.getKeyChar() < 256) {
            SensorData.keyStatus[e.getKeyChar()] = false;
        }
    }
}
