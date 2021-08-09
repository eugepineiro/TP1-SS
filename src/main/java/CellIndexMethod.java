import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CellIndexMethod {

     public static HashMap<Particle, List<Particle>> search(List<Particle>[][] matrix, int interactionRadius, int M, boolean periodicReturnCond) {

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
                              addNeighbours(particleMap, matrix[i][j].get(t), matrix[i][j].get(k), interactionRadius);
                         }

                         // chequeo celda superior
                         int k = j - 1;
                         if(k >= 0) {
                              for(Particle p: matrix[i][k]) {
                                   addNeighbours(particleMap, matrix[i][j].get(t), p, interactionRadius);
                              }
                         }

                         // chequeo columna derecha (i+1; j-1) (i+1; j) (i+1; j+1)
                         int h = i + 1;
                         k = j - 1;

                         if(h < M && k >= 0) {
                              for(; k <= j + 1 ; k++) {
                                   if(k < M) {
                                        for(Particle p: matrix[h][k]) {
                                             addNeighbours(particleMap, matrix[i][j].get(t), p, interactionRadius);
                                        }
                                   }
                              }
                         }
                    }

               }
          }
          return particleMap;
     }

     private static void addNeighbours(HashMap<Particle, List<Particle>> particleMap, Particle p1, Particle p2, int interactionRadius) {
          if (p1 != p2 && isInsideRadius(interactionRadius, p1, p2)) {
               if(!particleMap.containsKey(p1)) {
                    particleMap.put(p1, new ArrayList<>());
               }
               if(!particleMap.containsKey(p2)) {
                    particleMap.put(p2, new ArrayList<>());
               }
               particleMap.get(p1).add(p2);
               particleMap.get(p2).add(p1);
          }
     }

//private static List<Particle> searchOneParticle(List<Particle>[][] matrix, Particle particle, int interactionRadius, int M, int cellSize, boolean periodicReturnCond) {
//          int particleGridIdxJ = (int) (particle.getX() / cellSize);
//          int particleGridIdxI = (int) (particle.getY() / cellSize);
//
//          List<Particle> neighbours = new ArrayList<>();
//          int limit_i = particleGridIdxI + 2;
//          int limit_j = particleGridIdxJ - 1 + 3;
//
//          // caso celda superior
//          int j = particleGridIdxJ - 1;
//          if (periodicReturnCond) {
//               j %= M;
//          }
//          if (j >= 0) {
//               for (Particle p: matrix[particleGridIdxI][j]) {
//                    if (p != particle && isInsideRadius(interactionRadius, particle, p)) {
//                         neighbours.add(p);
//                    }
//               }
//          }
//
//          // caso columna derecha
//          for (int i = particleGridIdxI; i < limit_i; i++) { //search in L optimization
//               for (int j = particleGridIdxJ - 1; j < limit_j; j++) {
//                    if (periodicReturnCond) {
//                         i %= M;
//                         j %= M;
//                    }
//                    if (i >= 0 && i < M && j >= 0 && j < M) {
//                         for (Particle p: matrix[i][j]) {
//                              if (p != particle && isInsideRadius(interactionRadius, particle, p)) {
//                                   neighbours.add(p);
//                              }
//                         }
//                    }
//               }
//          }
//
//          return neighbours;
//     }


     private static boolean isInsideRadius(int interactionRadius, Particle particle, Particle other) {
          // TODO periodic condition
          double distance = Math.sqrt((particle.getX() - other.getX())^2 + (particle.getY() - other.getY())^2);
          return distance <= particle.getRadius() + other.getRadius() + interactionRadius;
     }
}
