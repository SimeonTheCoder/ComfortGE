package math.types;

public class Vec2 {
    public float x, y;

    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2(Vec2 copy) {
        this.x = copy.x;
        this.y = copy.y;
    }
}
