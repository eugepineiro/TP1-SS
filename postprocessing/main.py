import json, random

from numpy import number
from plotter import plot

with open("../src/main/resources/config/config.json") as f:
    config = json.load(f) 

with open("../src/main/resources/result.json") as f:
    all_particles = json.load(f) 

interaction_radius = config["r_interaction_radius"] 
number_of_particles = config["n_number_of_particles"]
M_grid_size = config["m_grid_dimension"]
L_grid_side = config["l_grid_side"]

idx = random.randint(0,number_of_particles-1)

particle = all_particles[idx]
print("ID: ", particle['id'])

all_particles_x = []
all_particles_y = []
all_particles_radius = []
neighbours_x = []
neighbours_y = []
neighbours_radius = []


for i in range(number_of_particles): 
    if(i != idx):
        all_particles_x.append(all_particles[i]['x'])
        all_particles_y.append(all_particles[i]['y'])
        all_particles_radius.append(all_particles[i]['radius'])

for i in range(len(all_particles[idx]['neighbours'])): 
    neighbours_x.append(all_particles[idx]['neighbours'][i]['x'])
    neighbours_y.append(all_particles[idx]['neighbours'][i]['y'])
    neighbours_radius.append(all_particles[idx]['neighbours'][i]['radius'])    

plot(all_particles_x,all_particles_y, all_particles_radius, particle, interaction_radius, [neighbours_x, neighbours_y, neighbours_radius], M_grid_size, L_grid_side)