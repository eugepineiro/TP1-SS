import numpy as np
import matplotlib.pyplot as plt

SCALE = 50

def plot(particles_x, particles_y, particles_radius, particle, interaction_radius, neighbours, grid_size ): 
   

    # fig = plt.figure()
    # ax = fig.gca()
    # ax.set_xticks(list(range(grid_size)))
    # ax.set_yticks(list(range(grid_size)))

    plt.scatter(particle['x'], particle['y'], s=particle['radius']*SCALE, color='red', alpha=0.5)               #particle
    plt.scatter(particle['x'], particle['y'], s=(interaction_radius + particle['radius'])*SCALE,color="none", edgecolor="black")     #interaction radius 
    plt.scatter(particles_x, particles_y, s=[e * SCALE for e in particles_radius], alpha=0.5)                                        #other particles
    plt.scatter(neighbours[0], neighbours[1], s=[e * SCALE for e in neighbours[2]],color="green", alpha=0.5)    #neighbours
    plt.grid(color='#CCCCCC')

    plt.show()