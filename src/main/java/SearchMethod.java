import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

abstract class SearchMethod {
    protected static void addNeighbours(HashMap<Particle, List<Particle>> particleMap, Particle p1, Particle p2, double interactionRadius, int L, boolean periodicReturnCond) {
        if(!particleMap.containsKey(p1)) { particleMap.put(p1, new ArrayList<>()); }
        if(!particleMap.containsKey(p2)) { particleMap.put(p2, new ArrayList<>()); }
        if (p1 != p2 && isInsideRadius(interactionRadius, p1, p2, L, periodicReturnCond)) {
//            System.out.println("Added : "+ p1 + " to " + p2);
            particleMap.get(p1).add(p2);
            particleMap.get(p2).add(p1);
        }
    }

    protected static boolean isInsideRadius(double interactionRadius, Particle particle, Particle other, int L,boolean periodicReturnCond) {
        // TODO periodic condition
        double distance = Math.sqrt(Math.pow((particle.getX() - other.getX()),2) + Math.pow((particle.getY() - other.getY()),2));

        if (!periodicReturnCond)
            return distance <= (particle.getRadius() + other.getRadius() + interactionRadius);

        double max_x, min_x, max_y, min_y;

        if (particle.getX() > other.getX()) {
            max_x = particle.getX();
            min_x = other.getX();
        }
        else {
            max_x = other.getX();
            min_x = particle.getX();
        }

        if (particle.getY() > other.getY()) {
            max_y = particle.getY();
            min_y = other.getY();
        }
        else {
            max_y = other.getY();
            min_y = particle.getY();
        }

        final double x_diff_pow = Math.pow((L - max_x + min_x), 2);
        distance = Math.sqrt(x_diff_pow + Math.pow((max_x - min_x),2)); // Espejo en x

        if (distance <= (particle.getRadius() + other.getRadius() + interactionRadius) )
            return true;

        final double y_diff_pow = Math.pow((L - max_y) + min_y, 2);
        distance = Math.sqrt(Math.pow((max_x - min_x),2) + y_diff_pow); // Espejo en y

        if (distance <= (particle.getRadius() + other.getRadius() + interactionRadius) )
            return true;

        distance = Math.sqrt(x_diff_pow + y_diff_pow); // Espejo en x e y

        return distance <= (particle.getRadius() + other.getRadius() + interactionRadius);
    }
}
