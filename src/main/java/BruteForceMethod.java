import java.util.HashMap;
import java.util.List;

public class BruteForceMethod extends SearchMethod {
    public static HashMap<Particle, List<Particle>> search(List<Particle> particles, double interactionRadius, int L, boolean periodic) {
        HashMap<Particle, List<Particle>> result = new HashMap<>();

        for(int i = 0 ; i < particles.size() ; i++) {
            for(int j = i+1 ; j < particles.size() ; j++) {
                // si o si hay que hacerlo con la diagonal porque sino add neighbour agrega repetidos a la lista
                addNeighbours(result, particles.get(i), particles.get(j), interactionRadius, L, periodic);
            }
        }

        return result;
    }
}
