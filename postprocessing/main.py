from plotter import plot

x = [2,3,4,5]
y = [2,3,4,5]
radius = [200,300,400,500]
particle = [1,1,10] # x, y, rc
interaction_radius = 150 
neighbours_x = [2,3]
neighbours_y = [2,3]
neighbours_radius = [200,300]

plot(x,y, radius, particle, interaction_radius, [neighbours_x, neighbours_y, neighbours_radius])