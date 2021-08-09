import numpy as np
import matplotlib.pyplot as plt


def plot(particles_x, particles_y, particles_radius, particle, interaction_radius, neighbours ): 
   
    plt.scatter(particle[0], particle[1], s=particle[2], color='red', alpha=0.5)                #particle
    plt.scatter(particle[0], particle[1], s=interaction_radius,color="none", edgecolor="red")   #interaction radius 
    plt.scatter(particles_x, particles_y, s=particles_radius, alpha=0.5)                        #other partciles
    plt.scatter(neighbours[0], neighbours[1], s=neighbours[2],color="green")
    plt.grid(color='#CCCCCC')
    plt.show()