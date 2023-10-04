package graphics;

import assets.types.Sprite;
import components.Component;
import globaldata.GlobalFlags;
import parser.RuntimeExecutor;
import scenes.Scene;
import scenes.SceneContainer;
import sensors.SensorData;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Map;

public class Renderer {
    public static SceneContainer scenes;
    
    public static Sprite clipboard = null;
    public static Sprite lastSelected = null;
    public static int clipboardCount = 1;

    private static boolean selection = false;

    public static void render(Graphics2D g) {
        Scene currScene = scenes.getActiveScene();

        int mx = MouseInfo.getPointerInfo().getLocation().x - Window.frame.getLocation().x;
        int my = MouseInfo.getPointerInfo().getLocation().y - Window.frame.getLocation().y;

        if (SensorData.keyTyped['r'] && GlobalFlags.DEBUG) {
            GlobalFlags.RUNNING = true;
        }

        for (Map.Entry<String, Sprite> entry : currScene.imageHolder.sprites.entrySet()) {
            Sprite currSprite = entry.getValue();

            if(GlobalFlags.RUNNING) {
                for (Component component : currSprite.components) {
                    RuntimeExecutor.floats[0] = currSprite.pos.x;
                    RuntimeExecutor.floats[1] = currSprite.pos.y;
                    RuntimeExecutor.floats[2] = currSprite.rotation;

                    RuntimeExecutor.execute(component.parsed);

                    currSprite.pos.x = RuntimeExecutor.floats[0];
                    currSprite.pos.y = RuntimeExecutor.floats[1];
                    currSprite.rotation = RuntimeExecutor.floats[2];
                }
            }

            if (mx >= currSprite.pos.x && mx <= currSprite.pos.x + currSprite.scale.x * currSprite.image.getWidth() &&
                    my >= currSprite.pos.y && my <= currSprite.pos.y + currSprite.scale.y * currSprite.image.getHeight() &&
                    !selection) {
                if (SensorData.mouse && GlobalFlags.DEBUG) {
                    currSprite.pos.x = mx - currSprite.scale.x * currSprite.image.getWidth() / 2;
                    currSprite.pos.y = my - currSprite.scale.y * currSprite.image.getHeight() / 2;

                    lastSelected = currSprite;

                    selection = true;
                }

                if (SensorData.keyStatus['['] && GlobalFlags.DEBUG) {
                    currSprite.scale.x -= 0.1;
                    currSprite.scale.y -= 0.1;
                }

                if (SensorData.keyStatus[']'] && GlobalFlags.DEBUG) {
                    currSprite.scale.x += 0.1;
                    currSprite.scale.y += 0.1;
                }

                if (SensorData.keyStatus['.'] && GlobalFlags.DEBUG) {
                    currSprite.rotation -= 0.3;
                }

                if (SensorData.keyStatus[','] && GlobalFlags.DEBUG) {
                    currSprite.rotation += 0.3;
                }

                if (SensorData.keyStatus['c'] && GlobalFlags.DEBUG) {
                    clipboard = currSprite;
                }
            } else {
                selection = false;
            }

            AffineTransform old = g.getTransform();
            g.translate(currSprite.pos.x + currSprite.scale.x * currSprite.image.getWidth() / 2, currSprite.pos.y + currSprite.scale.y * currSprite.image.getHeight() / 2);
            g.rotate(Math.toRadians(currSprite.rotation));
            g.translate(-currSprite.pos.x - currSprite.scale.x * currSprite.image.getWidth() / 2, -currSprite.pos.y -currSprite.scale.y * currSprite.image.getHeight() / 2);

            g.drawImage(
                    currSprite.image,
                    (int) currSprite.pos.x,
                    (int) currSprite.pos.y,
                    (int) (currSprite.scale.x * currSprite.image.getWidth()),
                    (int) (currSprite.scale.y * currSprite.image.getHeight()),
                    null
            );

            g.setTransform(old);
        }

        if (SensorData.keyTyped['v'] && GlobalFlags.DEBUG) {
            Sprite newSprite = new Sprite(clipboard);

            newSprite.pos.x = mx - newSprite.scale.x * newSprite.image.getWidth() / 2;
            newSprite.pos.y = my - newSprite.scale.y * newSprite.image.getHeight() / 2;
            newSprite.name = clipboard.name + "(" + clipboardCount + ")";

            scenes.getActiveScene().imageHolder.sprites.put(newSprite.name, newSprite);

            clipboardCount ++;
        }

        SensorData.keyTyped = new boolean[256];
    }
}
