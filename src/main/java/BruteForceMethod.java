import java.util.List;
import java.util.stream.Collectors;

public class BruteForceMethod {
    public static List<Particle> search(List<Particle> particles, Particle chosen, int interactionRadius) {
        return particles.stream().filter(p -> p != chosen && isInsideRadius(chosen, interactionRadius, p)).collect(Collectors.toList());
    }

    private static boolean isInsideRadius(Particle particle, int interactionRadius, Particle other) {
        double distance = Math.sqrt((particle.getX() - other.getX())^2 + (particle.getY() - other.getY())^2);
        double border_distance = distance - particle.getRadius() - other.getRadius();
        return border_distance >= interactionRadius;
    }
}
