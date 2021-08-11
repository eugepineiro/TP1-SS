import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Simulation {
    // carga config, llama al CIM, devuelve list_vecinos
    
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        // carga config
        try {
            // JSON file to Java object
            Config config = mapper.readValue(new File("src/main/resources/config/config.json"), Config.class);

//            // pretty print
//            String prettyConfig = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(config);
//            System.out.println(prettyConfig);

            if (1.0 * config.getL_grid_side() / config.getM_grid_dimension() <= (config.getR_interaction_radius() + 2.0 * config.getL_grid_side() / 50) ) {
                throw new IllegalArgumentException("L/M > rc + 2 * max_particle_radius (L/50.0)");
            }

            if (config.getRandom_generator()) {
                config.setParticles(ParticlesGenerator.generateRandom(config.getN_number_of_particles(), config.getL_grid_side()));
            }
            else {
                config.setN_number_of_particles(config.getParticles().size());
                List<Particle> particles = config.getParticles();
                for (int i = 0; i < config.getN_number_of_particles(); i++){
                    particles.get(i).setId(i);
                }
            }

//            System.out.println(config.getN_number_of_particles());

            List<Particle>[][] matrix = Grid.build(config.getParticles(), config.getM_grid_dimension(), config.getL_grid_side());
            HashMap<Particle, List<Particle>> neighbours;

            long startTime = System.nanoTime();
            if(config.getBrute_force()) {
                neighbours = BruteForceMethod.search(config.getParticles(), config.getR_interaction_radius(), config.getL_grid_side(), config.getPeriodic_return());
            } else {
                neighbours = CellIndexMethod.search(matrix, config.getR_interaction_radius(), config.getM_grid_dimension(), config.getL_grid_side(), config.getPeriodic_return());
            }
            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            System.out.println("Search execution time in milliseconds: " + timeElapsed / 1000000.0);


            List<Map<String, Object>> to_ret = neighbours.entrySet().stream().map(entry -> {
                Map<String, Object> obj = new HashMap<>();
                obj.put("id", entry.getKey().getId());
                obj.put("x", entry.getKey().getX());
                obj.put("y", entry.getKey().getY());
                obj.put("radius", entry.getKey().getRadius());
                obj.put("neighbours", entry.getValue());
                return obj;
            }).sorted(Comparator.comparingLong(a -> (Long) a.get("id"))).collect(Collectors.toList());

            Gson gson = new Gson();
            String json = gson.toJson(to_ret);
//            System.out.println(json);
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/result.json"));
            writer.write(json);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
