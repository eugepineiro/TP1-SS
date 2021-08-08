import java.util.ArrayList;
import java.util.List;

public class CellIndexMethod {
     public static List<Particle> search(List<Particle>[][] matrix, Particle particle, int interactionRadius, int M, int cellSize, boolean periodicReturnCond) {
          int particleGridIdxI = (int) (particle.getX() / cellSize);
          int particleGridIdxJ = (int) (particle.getY() / cellSize);

          ArrayList<Particle> neighbours = new ArrayList<>();

          for (int i = particleGridIdxI - 1; i < 3; i++) {
               for (int j = particleGridIdxJ - 1; j < 3; j++) {
                    if (periodicReturnCond) {
                         i %= M;
                         j %= M;
                    }
                    if (i >= 0 && i < M && j >= 0 && j < M) {
                         for (Particle p: matrix[i][j]) {
                              if (p != particle && isInsideRadius(particle, interactionRadius, p)) {
                                   neighbours.add(p);
                              }
                         }
                    }
               }
          }

          return neighbours;
     }

     private static boolean isInsideRadius(Particle particle, int interactionRadius, Particle other) {
          double distance = Math.sqrt((particle.getX() - other.getX())^2 + (particle.getY() - other.getY())^2);
          double border_distance = distance - particle.getRadius() - other.getRadius();
          return border_distance >= interactionRadius;
     }
}
