import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ParticlesGenerator {
    public static List<Particle> generateRandom(int N, int L) {
        List<Particle> generated = new ArrayList<>();
        int x, y;
        double radius, maxRadius = L/20.0; // TODO: Is it okay?
        for (int i = 0; i < N; i++) {
            x = ThreadLocalRandom.current().nextInt(0, L); // upper bound excluded
            y = ThreadLocalRandom.current().nextInt(0, L); // upper bound excluded
            radius = ThreadLocalRandom.current().nextDouble(0, maxRadius); // upper bound excluded
            generated.add(new Particle(i, x, y, radius));
        }

        return generated;
    }
}
