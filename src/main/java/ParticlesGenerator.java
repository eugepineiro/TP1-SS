import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ParticlesGenerator {
    public static List<Particle> generateRandom(int N, int L) {
        List<Particle> generated = new ArrayList<>();
        double x, y;
        double radius, maxRadius = L/50.0; // TODO: Is it okay?
        for (int i = 0; i < N; i++) {
            x = ThreadLocalRandom.current().nextDouble(0, L); // upper bound excluded
            y = ThreadLocalRandom.current().nextDouble(0, L); // upper bound excluded
            radius = ThreadLocalRandom.current().nextDouble(0, maxRadius); // upper bound excluded
            generated.add(new Particle(i, x, y, 0.25));
        }

        return generated;
    }
}
