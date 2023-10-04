package scenes;

import assets.ImgHolder;

public class Scene {
    public ImgHolder imageHolder;

    public String name;

    public Scene(String name) {
        this.name = name;

        imageHolder = new ImgHolder();
    }
}
