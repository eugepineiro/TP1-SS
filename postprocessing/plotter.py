import numpy as np
import matplotlib.pyplot as plt
from matplotlib import cm
# from mayavi import mlab
import mplcursors

SCALE = 1
COLORS = ['#AD6D70', '#EC2504', '#8C0B90', '#C0E4FF', '#7C60A8', '#CF95D7']

def plot(particles_x, particles_y, particles_radius, particles_id, particle, interaction_radius, neighbours, grid_size, grid_side, periodic_condition): 
 
    figure, axes = plt.subplots()
   
    #print(particle['x'], particle['y'], particle['radius'])

    axes.set_aspect(1)
   
    particle_scatter = plot_chosen_particle(particle, interaction_radius, axes)

    if (periodic_condition):
        plot_periodic_conditions(particle, interaction_radius, grid_side, axes)
 
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
        #axes.annotate(str(p['id']), xy=(p['x'], p['y']), fontsize=15, ha="center", color='cyan')

    particles_scatter = plt.scatter(particles_x, particles_y, s=[e * SCALE for e in particles_radius],color="blue", alpha=0.5, label='Others')
    cursor_particles=mplcursors.cursor(particles_scatter)
    cursor_particles.connect("add", lambda sel: sel.annotation.set_text(particles_id[sel.target.index]))

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
        #axes.annotate(str(n['id']), xy=(n['x'], n['y']), fontsize=15, ha="center", color='cyan')

    neighbours_scatter = plt.scatter(neighbours[0], neighbours[1], s=[e for e in neighbours[2]],color="none", alpha=0.5, label='Neighbours') 
    cursor=mplcursors.cursor(neighbours_scatter)
    cursor.connect("add", lambda sel: sel.annotation.set_text(neighbours[3][sel.target.index]))
  
    cell_size = 1.0 * grid_side / grid_size

    # Major ticks every cell_size, minor ticks every 1
    major_ticks = np.arange(0, grid_side+1, cell_size)
    minor_ticks = np.arange(0, grid_side+1, 1)

    axes.set_xticks(major_ticks)
    axes.set_xticks(minor_ticks, minor=True)
    axes.set_yticks(major_ticks)
    axes.set_yticks(minor_ticks, minor=True)
    plt.grid(color='#CCCCCC') # MxM

    plt.title('Particle')
    # max_radius = grid_side/50

    # plt.xlim([0 - max_radius, grid_side + max_radius])
    # plt.ylim([0 - max_radius, grid_side + max_radius])

    plt.xlim([0, grid_side])
    plt.ylim([0, grid_side])

    plt.legend(handles=[particle_scatter,particles_scatter, neighbours_scatter], bbox_to_anchor=(1.05, 1), loc='upper left')

    plt.show()

def plot_chosen_particle(particle, interaction_radius, axes):
    # Draw particle 
    draw_particle_radius = plt.Circle((particle['x'], particle['y']), particle['radius'], color='k') # particle 
    axes.add_artist(draw_particle_radius)
    particle_scatter = plt.scatter(particle['x'], particle['y'], alpha=0,label='Particle')
    cursor_particles=mplcursors.cursor(particle_scatter)
    cursor_particles.connect("add", lambda sel: sel.annotation.set_text(particle['id']))
    #axes.annotate(str(particle['id']), xy=(particle['x'], particle['y']), fontsize=15, ha="center", color='cyan')

    # Draw interaction radius 
    draw_interaction_radius = plt.Circle((particle['x'], particle['y']), interaction_radius + particle['radius'], fill=False) #interaction radius
    axes.add_artist(draw_interaction_radius)
    plt.scatter(particle['x'], particle['y'], s=(interaction_radius + particle['radius'])*SCALE, color="none", edgecolor="black")      
    
    return particle_scatter
 
def plot_periodic_conditions(particle, rc, grid_side, axes):
        x, y = particle['x'], particle['y']
        r = particle['radius'] 

        def build_and_plot(x, y):
            p = {
                'id': particle['id'],
                'x': x,
                'y': y,
                'radius': r
            }
            plot_chosen_particle(p, rc, axes)

        if (x + r + rc >= grid_side and y + r + rc >= grid_side):   # derecha y arriba
            build_and_plot(x - grid_side, y - grid_side)
      
        if (x + r + rc >= grid_side and y - r - rc < 0):            # derecha y abajo
            build_and_plot(x - grid_side, y + grid_side)

        if (x - r - rc < 0 and y - r - rc < 0):                     # izquierda y abajo 
            build_and_plot(x + grid_side, y + grid_side)
            
        if (x - r - rc < 0 and y + r + rc >= grid_side):            # izquierda y arriba
            build_and_plot(x + grid_side, y - grid_side)

        if (x - r - rc < 0):                                        # columnas a la izquierda 
            build_and_plot(x + grid_side, y)

        if (x + r + rc >= grid_side):                               # columnas a la derecha 
            build_and_plot(x - grid_side, y)

        if (y - r - rc < 0):                                        # filas abajo
            build_and_plot(x, y + grid_side)

        if (y + r + rc >= grid_side):                               # filas arriba
            build_and_plot(x, y - grid_side)
            
def plot_comparison(data, grid_side):
    
    proc_data = {'M': [], 'N': [], 'time_cim': [], 'time_bf': []}

    for d in data:
        proc_data['M'].append(d['M'])
        proc_data['N'].append(d['N'])
        proc_data['time_cim'].append(d['time_cim'])
        proc_data['time_bf'].append(d['time_bf'])

    X = np.array(proc_data['M'])
    Y = np.array(proc_data['N'])
    Z_CIM = np.array(proc_data['time_cim'])
    Z_BF = np.array(proc_data['time_bf'])

    # fig = plt.figure()

    # ax = fig.add_subplot(111, projection='3d')
    # ax.scatter(xs = proc_data['M'], ys = proc_data['N'], zs = proc_data['time_cim'])
    # ax.set_title(f"M x N Timings with L={grid_side}")
    # ax.set_xlabel("M (cells per row/column)")
    # ax.set_ylabel("N (number of particles)")
    # ax.set_zlabel("Time (seconds)")
    # plt.show()

    # MAYAVI

    # fig = mlab.figure()

    # ax_ranges = [-2, 2, -2, 2, 0, 8]
    # ax_scale = [1.0, 1.0, 0.4]
    # ax_extent = ax_ranges * np.repeat(ax_scale, 2)

    # surf3 = mlab.surf(proc_data['M'], proc_data['N'], proc_data['time_cim'], colormap='Blues')
    # surf4 = mlab.surf(proc_data['M'], proc_data['N'], proc_data['time_bf'], colormap='Oranges')

    # surf3.actor.actor.scale = ax_scale
    # surf4.actor.actor.scale = ax_scale
    # mlab.view(60, 74, 17, [-2.5, -4.6, -0.3])
    # mlab.outline(surf3, color=(.7, .7, .7), extent=ax_extent)
    # mlab.axes(surf3, color=(.7, .7, .7), extent=ax_extent,
    #           ranges=ax_ranges,
    #           xlabel='x', ylabel='y', zlabel='z')

    # # Transparency
    # # surf3.actor.property.opacity = 0.5
    # # surf4.actor.property.opacity = 0.5
    # # fig.scene.renderer.use_depth_peeling = 1

    fig = plt.figure()

    ax = fig.gca(projection='3d')

    ax.plot_trisurf(X, Y, Z_CIM, linewidth=1, cmap=cm.summer, alpha = 0.5)
    ax.plot_trisurf(X, Y, Z_BF, linewidth=1, cmap=cm.autumn, alpha = 1)

    ax.set_title(f"M x N Timings with L={grid_side}")
    ax.set_xlabel("M (cells per row/column)")
    ax.set_ylabel("N (number of particles)")
    ax.set_zlabel("Time (seconds)")

    plt.show()
    # Transparency
    # surf3.actor.property.opacity = 0.5
    # surf4.actor.property.opacity = 0.5
    # fig.scene.renderer.use_depth_peeling = 1


def plot_comparison_2D(data): 

    proc_data = {'M': [], 'N': [], 'time_cim': [], 'time_bf': []}

    for d in data:
        # proc_data['M'].append(d['M'])
        if d['M'] == 20:    
            proc_data['N'].append(d['N'])
            proc_data['time_cim'].append(d['time_cim'])
            proc_data['time_bf'].append(d['time_bf'])


    bf_scatter = plt.scatter(proc_data['N'], proc_data['time_bf'], label='Brute Force')
    cim_scatter = plt.scatter(proc_data['N'], proc_data['time_cim'], label='Cell Index Method')

    ax = plt.gca()
    ax.set_yscale('log')
    ax.set_xlabel('N particles')
    ax.set_ylabel('time (s)')

    plt.legend(handles=[bf_scatter,cim_scatter], loc='upper center')
    
    plt.grid()
    plt.show()
