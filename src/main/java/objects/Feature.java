package objects;
public abstract class Feature {
    public float x, y, z;

    public Feature(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void dispose() {

    }

    public abstract void draw();
}
