import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CellIndexMethod {

     public static HashMap<Particle, List<Particle>> search(List<Particle>[][] matrix, double interactionRadius, int M, int L, boolean periodicReturnCond) {

          HashMap<Particle, List<Particle>> particleMap = new HashMap<>();
          // TODO: agregar periodic condition
          // Recorrido de grilla
          for(int i = 0 ; i < M ; i++) {
               for(int j= 0; j < M ; j++) {

                    // partículas de una celda
                    for(int t = 0; t < matrix[i][j].size(); t++){
                         if(!particleMap.containsKey(matrix[i][j].get(t))) {
                              particleMap.put(matrix[i][j].get(t), new ArrayList<>());
                         }
                         // chequeo contra la misma celda
                         for(int k = t + 1; k < matrix[i][j].size(); k++) {
                              addNeighbours(particleMap, matrix[i][j].get(t), matrix[i][j].get(k), interactionRadius, L, periodicReturnCond);
                         }

                         // chequeo celda superior


                         int k;
                         final int k1 = periodicReturnCond? (j-1) % M : (j-1);
                         k = k1;
                         if(k >= 0) {
                              for(Particle p: matrix[i][k]) {
                                   addNeighbours(particleMap, matrix[i][j].get(t), p, interactionRadius, L, periodicReturnCond);
                              }
                         }

                         // chequeo columna derecha (i+1; j-1) (i+1; j) (i+1; j+1)
                         int h = i + 1;
                         k = k1;

                         if(h < M) {
                              for(; k <= j + 1 ; ) {
                                   if(k >= 0 && k < M) {
                                        for(Particle p: matrix[h][k]) {
                                             addNeighbours(particleMap, matrix[i][j].get(t), p, interactionRadius, L, periodicReturnCond);
                                        }
                                   }
                                   k = periodicReturnCond? (k+1) % M : (k+1);
                              }
                         }
                    }

               }
          }
          return particleMap;
     }

     private static void addNeighbours(HashMap<Particle, List<Particle>> particleMap, Particle p1, Particle p2, double interactionRadius, int L, boolean periodicReturnCond) {
          if (p1 != p2 && isInsideRadius(interactionRadius, p1, p2, L,periodicReturnCond)) {
               System.out.println("Added : "+ p1 + " to " + p2);
               if(!particleMap.containsKey(p1)) { particleMap.put(p1, new ArrayList<>()); }
               if(!particleMap.containsKey(p2)) { particleMap.put(p2, new ArrayList<>()); }
               particleMap.get(p1).add(p2);
               particleMap.get(p2).add(p1);
          }
     }

     private static boolean isInsideRadius(double interactionRadius, Particle particle, Particle other, int L,boolean periodicReturnCond) {
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

          if (distance <= (particle.getRadius() + other.getRadius() + interactionRadius) )
               return true;

          return false;
     }
}
