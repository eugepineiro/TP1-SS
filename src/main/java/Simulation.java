import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Simulation {
    // carga config, llama al CIM, devuelve list_vecinos
    
    public static void main(String args[]) {
        ObjectMapper mapper = new ObjectMapper();
        // carga config
        try {
            // JSON file to Java object
            Config config = mapper.readValue(new File("src/main/resources/config/config.json"), Config.class);

//            // pretty print
//            String prettyConfig = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(config);
//            System.out.println(prettyConfig);

            Particle chosen = config.getParticles().get(0);

            List<Particle>[][] matrix = Grid.build(config.getParticles(), config.getM_grid_dimension(), config.getL_grid_side());

            List<Particle> neighbours = CellIndexMethod.search(matrix, chosen, config.getR_interaction_radius(), config.getM_grid_dimension(), (int) config.getL_grid_side()/ config.getM_grid_dimension(), false);

            System.out.println(neighbours);

            //            List<Particle> neighbours = CellIndexMethod.search()

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
