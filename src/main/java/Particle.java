import java.util.Objects;

public class Particle {
    private long id;
    private long x;
    private long y;
    private long radius;

    public Particle() {
    }

    public Particle(long id, long x, long y, long radius) {
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Particle particle = (Particle) o;
        return id == particle.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

