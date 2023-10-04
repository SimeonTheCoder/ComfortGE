package scenes;

public class SceneContainer {
    public Scene[] scenes;
    public int activeScene;

    public SceneContainer() {
        this.scenes = new Scene[32];
        activeScene = -1;
    }

    public Scene getActiveScene() {
        return scenes[activeScene];
    }
}
