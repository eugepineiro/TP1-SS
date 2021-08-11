import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CellIndexMethod extends SearchMethod {

     public static Map<Particle, List<Particle>> search(List<Particle>[][] matrix, double interactionRadius, int M, int L, boolean periodicReturnCond) {

          Map<Particle, List<Particle>> particleMap = new HashMap<>();
          // TODO: agregar periodic condition
          // Recorrido de grilla
          for(int i = 0 ; i < M ; i++) {
               for(int j= 0; j < M ; j++) {

                    // partículas de una celda
                    for(int t = 0; t < matrix[i][j].size(); t++){
                         // chequeo contra la misma celda
                         for(int k = t + 1; k < matrix[i][j].size(); k++) {
                              addNeighbours(particleMap, matrix[i][j].get(t), matrix[i][j].get(k), interactionRadius, L, periodicReturnCond);
                         }

                         // chequeo celda superior

                         int k = periodicReturnCond? (j-1) % M : (j-1);
                         if(k >= 0) {
                              for(Particle p: matrix[i][k]) {
                                   addNeighbours(particleMap, matrix[i][j].get(t), p, interactionRadius, L, periodicReturnCond);
                              }
                         }

                         // chequeo columna derecha (i+1; j-1) (i+1; j) (i+1; j+1)
                         int h = periodicReturnCond? (i+1) % M :(i + 1);

                         if(h < M) {
                              for (int r=0; r < 3; r++) {
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
}
