import numpy as np
import matplotlib.pyplot as plt

SCALE = 1
COLORS = ['#AD6D70', '#EC2504', '#8C0B90', '#C0E4FF', '#7C60A8', '#CF95D7']

def plot(particles_x, particles_y, particles_radius, particles_id, particle, interaction_radius, neighbours, grid_size, grid_side): 
   

    # fig = plt.figure()
    # ax = fig.gca()
    # ax.set_xticks(list(range(grid_size)))
    # ax.set_yticks(list(range(grid_size)))

    # plt.scatter(particle['x'], particle['y'], s=particle['radius']*SCALE, color='red', alpha=0.5)               #particle
    # plt.scatter(particle['x'], particle['y'], s=(interaction_radius + particle['radius'])*SCALE, color="none", edgecolor="black")     #interaction radius 
    # plt.scatter(particles_x, particles_y, s=[e * SCALE for e in particles_radius], alpha=0.5)                                        #other particles
    # plt.scatter(neighbours[0], neighbours[1], s=[e * SCALE for e in neighbours[2]],color="green", alpha=0.5)    #neighbours
    # plt.grid(color='#CCCCCC') # MxM

    # plt.scatter(particle['x'], particle['y'], color='red', alpha=0.5)               #particle
    # plt.Circle((particle['x'], particle['y']), particle['radius'])                #radius
    # plt.scatter(particle['x'], particle['y'], s=(interaction_radius + particle['radius'])*SCALE, color="none", edgecolor="black")     #interaction radius 
    # plt.scatter(particles_x, particles_y, s=[e * SCALE for e in particles_radius], alpha=0.5)                                        #other particles
    # plt.scatter(neighbours[0], neighbours[1], s=[e * SCALE for e in neighbours[2]],color="green", alpha=0.5)    #neighbours
    # plt.grid(color='#CCCCCC') # MxM

    #plt.show()


    
    figure, axes = plt.subplots()
    draw_particle_radius = plt.Circle((particle['x'], particle['y']), particle['radius'], color='k') # particle 
    draw_interaction_radius = plt.Circle((particle['x'], particle['y']), interaction_radius + particle['radius'], fill=False) #interaction radius
    axes.annotate(str(particle['id']), xy=(particle['x'], particle['y']), fontsize=15, ha="center", color='cyan')
    print(particle['x'], particle['y'], particle['radius'])


    axes.set_aspect(1)
    axes.add_artist(draw_particle_radius)
    axes.add_artist(draw_interaction_radius)

    plt.scatter(particle['x'], particle['y'], color='red', alpha=0.5)               #particle
    plt.scatter(particle['x'], particle['y'], s=(interaction_radius + particle['radius'])*SCALE, color="none", edgecolor="black")      
    
    # Draw other particles
    for i in range(0, len(particles_x)):
        p = {
            'x': particles_x[i],
            'y': particles_y[i],
            'radius': particles_radius[i],
            'id': particles_id[i]
        }

        draw_particle_radius = plt.Circle((p['x'], p['y']), p['radius'], color="blue", alpha=0.5)
        axes.add_artist(draw_particle_radius)
        axes.annotate(str(p['id']), xy=(p['x'], p['y']), fontsize=15, ha="center", color='cyan')

    # Draw neighbours    
    for i in range(0, len(neighbours[0])):
        n = {
            'x': neighbours[0][i],
            'y': neighbours[1][i],
            'radius': neighbours[2][i],
            'id': neighbours[3][i]
        }

        draw_neighbour_radius = plt.Circle((n['x'], n['y']), n['radius'], color="green", alpha=0.5)
        axes.add_artist(draw_neighbour_radius)
        axes.annotate(str(n['id']), xy=(n['x'], n['y']), fontsize=15, ha="center", color='cyan')
 
    cell_size = 1.0 * grid_side / grid_size

    # Major ticks every cell_size, minor ticks every 1
    major_ticks = np.arange(0, grid_side+1, cell_size)
    minor_ticks = np.arange(0, grid_side+1, 1)

    axes.set_xticks(major_ticks)
    axes.set_xticks(minor_ticks, minor=True)
    axes.set_yticks(major_ticks)
    axes.set_yticks(minor_ticks, minor=True)
    plt.grid(color='#CCCCCC') # MxM

    plt.title('Circle')
    max_radius = grid_side/50

    plt.xlim([0 - max_radius, grid_side + max_radius])
    plt.ylim([0 - max_radius, grid_side + max_radius])
    plt.show()