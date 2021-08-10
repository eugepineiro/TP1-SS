import java.util.List;
import java.util.stream.Collectors;

public class BruteForceMethod {
    public static List<Particle> search(List<Particle> particles, Particle chosen, int interactionRadius) {
        return particles.stream().filter(p -> p != chosen && isInsideRadius(interactionRadius, chosen, p)).collect(Collectors.toList());
    }

    private static boolean isInsideRadius(double interactionRadius, Particle particle, Particle other) {
        double distance = Math.sqrt(Math.pow((particle.getX() - other.getX()),2) + Math.pow((particle.getY() - other.getY()),2));
        return distance <= (particle.getRadius() + other.getRadius() + interactionRadius);
    }
}
