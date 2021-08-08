
public class Particle {
    private long x;
    private long y;
    private long radius;

    public Particle() {
    }

    public Particle(long x, long y, long radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public long getRadius() {
        return radius;
    }

    public void setX(long x) {
        this.x = x;
    }

    public void setY(long y) {
        this.y = y;
    }

    public void setRadius(long radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Particle{" +
                "x=" + x +
                ", y=" + y +
                ", radius=" + radius +
                '}';
    }
}

