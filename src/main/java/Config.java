// https://www.baeldung.com/jackson-yaml
// https://mkyong.com/java/jackson-how-to-parse-json/

import java.util.List;

public class Config {

    private Integer m_grid_dimension;
    private Integer n_number_of_particles;
    private Double r_interaction_radius;
    private Integer l_grid_side;
    private List<Particle> particles;

    public Integer getM_grid_dimension() {
        return m_grid_dimension;
    }

    public void setM_grid_dimension(Integer m_grid_dimension) {
        this.m_grid_dimension = m_grid_dimension;
    }

    public Integer getN_number_of_particles() {
        return n_number_of_particles;
    }

    public void setN_number_of_particles(Integer n_number_of_particles) {
        this.n_number_of_particles = n_number_of_particles;
    }

    public Double getR_interaction_radius() {
        return r_interaction_radius;
    }

    public void setR_interaction_radius(Double r_interaction_radius) {
        this.r_interaction_radius = r_interaction_radius;
    }

    public Integer getL_grid_side() {
        return l_grid_side;
    }

    public void setL_grid_side(Integer l_grid_side) {
        this.l_grid_side = l_grid_side;
    }

    public List<Particle> getParticles() {
        return particles;
    }

    public void setParticles(List<Particle> particles) {
        this.particles = particles;
    }

    @Override
    public String toString() {
        return "Config{" +
                "\nm_grid_dimension=" + m_grid_dimension +
                "\nn_number_of_particles=" + n_number_of_particles +
                "\nr_interaction_radius=" + r_interaction_radius +
                "\nl_grid_side=" + l_grid_side +
                "\nparticles=" + particles +
                "\n}";
    }
}