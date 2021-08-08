import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grid {

    public static List<Particle>[][] build(List<Particle> particles, int M, int L){
        int cellSize = M/L;

        //List<List<List<Particle>>> matrix = new ArrayList<>(M);
        List<Particle>[][] matrix = new ArrayList[M][M];
//            int [][] =  [5][5]

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                matrix[i][j] = new ArrayList<>();
            }
        }

//        Arrays.stream(matrix).forEach(() -> ArrayList::new);

        particles.forEach(p -> matrix[(int) p.getY() / M][(int) p.getX() / M].add(p));

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(matrix[i][j].toString()).append(", ");
            }
            sb.reverse().delete(0, 2).reverse();
            sb.append("\n");
        }

        System.out.println(sb);

        return matrix;
    }
}
