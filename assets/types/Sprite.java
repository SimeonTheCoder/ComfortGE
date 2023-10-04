package assets.types;

import components.Component;
import math.types.Vec2;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Sprite {
    public BufferedImage image;

    public String name;

    public Vec2 pos;
    public Vec2 scale;
    public float rotation;

    public List<Component> components;

    public Sprite(BufferedImage image, Vec2 pos, float rotation) {
        this.image = image;
        this.pos = pos;
        this.rotation = rotation;
        this.scale = new Vec2(1,1);

        components = new ArrayList<>();
    }

    public Sprite(BufferedImage image, Vec2 pos, float rotation, String name) {
        this.image = image;
        this.pos = pos;
        this.rotation = rotation;
        this.scale = new Vec2(1,1);
        this.name = name;

        components = new ArrayList<>();
    }

    public Sprite(BufferedImage image, Vec2 pos, Vec2 scale, float rotation) {
        this.image = image;
        this.pos = pos;
        this.scale = scale;
        this.rotation = rotation;

        components = new ArrayList<>();
    }

    public Sprite(Sprite copy) {
        this.image = copy.image;
        this.pos = new Vec2(copy.pos);
        this.rotation = copy.rotation;
        this.scale = new Vec2(copy.scale);
        this.name = copy.name;
        this.components = new ArrayList<>();

        this.components.addAll(copy.components);
    }
}
