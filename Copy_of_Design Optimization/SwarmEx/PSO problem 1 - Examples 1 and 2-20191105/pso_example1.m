clear all
clc

swarm_size = 20;
c1 = 2;
c2 = 2;
theta_max = 0.9;
theta_min = 0.1;
num_iterations = 20;

num_dvs = 2;
x_lb = [-3;-3];
x_ub = [3;3];

%% Generate the contour map of the objective function

[x1_contour,x2_contour] = meshgrid(x_lb(1):.1:x_ub(1),x_lb(2):.1:x_ub(2));

[m_contour,n_contour] = size(x1_contour);

for i = 1:m_contour
    for j = 1:n_contour
        F_contour(i,j) = 10 - x1_contour(i,j)+2*x1_contour(i,j)^2+x2_contour(i,j)^2;
    end
end
v_contour = [0 10 12 14 16 18 20 22 24 26 28 30];

%% PSO algorithm

for i = 1:swarm_size
    for j = 1:num_dvs
        particle(i,j) = x_lb(j,1) + rand*(x_ub(j,1)-x_lb(j,1));       
    end
    particle(i,num_dvs+1) = objF(particle(i,1:num_dvs)');
    particle_best = particle;
    particle_velocity(i,1:num_dvs) = 0;
end

[global_best_F,ind] = min(particle_best(:,num_dvs+1));
global_best_X = particle_best(ind,1:num_dvs); 

for i = 1:num_iterations
    theta = theta_max - ((theta_max-theta_min)/num_iterations)*i; 
    
    old_particle = particle;
    for j = 1:swarm_size
       
       particle_velocity(j,:) = theta*particle_velocity(j,:)+c1*rand*(particle_best(j,1:num_dvs)-particle(j,1:num_dvs))+c2*rand*(global_best_X-particle(j,1:num_dvs));
       particle(j,1:num_dvs) = particle(j,1:num_dvs)+particle_velocity(j,1:num_dvs);
       particle(j,num_dvs+1) = objF(particle(j,1:num_dvs)');
       
       if(particle(j,num_dvs+1) <= particle_best(j,num_dvs+1))
           particle_best(j,:) = particle(j,:);
       end
    end
    [global_best_F,ind] = min(particle_best(:,num_dvs+1));
    global_best_X = particle_best(ind,1:num_dvs); 

    clf
    [c_contour,h_contour] = contour(x1_contour,x2_contour,F_contour,v_contour);
    clabel(c_contour,h_contour);
    xlabel('x1')
    ylabel('x2')
    title(['Iteration #' num2str(i)])
    hold on
    for j = 1:swarm_size
        x_loc = [old_particle(j,1) particle(j,1)];
        y_loc = [old_particle(j,2) particle(j,2)];
        plot(x_loc,y_loc,'b-')
        plot(particle(j,1),particle(j,2),'bx','LineWidth',2,'MarkerSize',8)   % drawing swarm movements
    end
    plot(global_best_X(:,1),global_best_X(:,2),'rx','LineWidth',2,'MarkerSize',5)
    axis([x_lb(1) x_ub(1) x_lb(2) x_ub(2)]);
    pause(.2)
    
end
    
    


