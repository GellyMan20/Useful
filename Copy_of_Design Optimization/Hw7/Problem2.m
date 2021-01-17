%Homework 7 stuff
%Alex Gellios
%things that need to be returned:
%   Number of function evaluations required by the algorithm
%   Ratio of accepted moves compared against number of function calls
%   Number of uphill moves accepted
%   Objective function at algorithm termination
%   Best objective function value found
clear all; close all; clc;

%% Problem 2 - part 1

% initial temperature - explore different starting temps
T0 = 50;

%define intial parameters
x0 = [10;20;30;40;50;0;0;0;0;0];
delta = 0.2;
temp_reduction_type = 'exponential';
eps = 1e-9;
x_lb = [];
x_ub = [];

%Other given parameters
moves = 1:5:100; %moves per temperature
trv = .4:.05:.9; % temperature reduction variables
[moves_per_temp,temp_reduction] = meshgrid(moves,trv);


nfevals = zeros(size(moves_per_temp));
ratio_accept = nfevals;
nuphill = nfevals;
fval = nfevals;
fval_best = nfevals;

for i = 1:size(moves_per_temp,1)
    for j = 1:size(moves_per_temp,2)
        SA = simulatedannealing(x0,x_lb,x_ub,T0,delta,moves_per_temp(i,j),temp_reduction(i,j),temp_reduction_type,eps);
        
        % write output
        nfevals(i,j) = SA.function_count;
        ratio_accept(i,j) = SA.number_of_accepts/SA.function_count;
        nuphill(i,j) = SA.number_of_uphill_accepts;
        fval(i,j) = SA.F(end);
        fval_best(i,j) = SA.Fbest(end);
    end
end

% graph everything
figure(1);
contourf(moves,trv,nfevals,'showtext','on');
xlabel('Number of Moves per Temperature');
ylabel('Temperature Reduction');
title('Number of Function Evaluations Required');

figure(2);
contourf(moves,trv,ratio_accept,'showtext','on');
xlabel('Number of Moves per Temperature');
ylabel('Temperature Reduction');
title('Ratio of Accepted Moves'); 

figure(3);
contourf(moves,trv,nuphill,'showtext','on');
xlabel('Number of Moves per Temperature');
ylabel('Temperature Reduction');
title('Number of Uphill Moves Accepted');

figure(4);
contourf(moves,trv,fval,'showtext','on');
xlabel('Number of Moves per Temperature');
ylabel('Temperature Reduction');
title('Objective Function Value at Termination');

figure(5);
contourf(moves,trv,fval_best,'showtext','on');
xlabel('Number of Moves per Temperature');
ylabel('Temperature Reduction');
title('Best Objective Value Found');

%% Problem 2 - part 2
% initial temperature - explore different starting temps
T0 = 200;

%define intial parameters
x0 = [10;20;30;40;50;0;0;0;0;0];
delta = 0.2;
temp_reduction_type = 'exponential';
eps = 1e-9;
x_lb = [];
x_ub = [];

%Other given parameters
moves = 1:5:100; %moves per temperature
trv = .4:.05:.9; % temperature reduction variables
[moves_per_temp,temp_reduction] = meshgrid(moves,trv);


nfevals = zeros(size(moves_per_temp));
ratio_accept = nfevals;
nuphill = nfevals;
fval = nfevals;
fval_best = nfevals;

for i = 1:size(moves_per_temp,1)
    for j = 1:size(moves_per_temp,2)
        SA = simulatedannealing(x0,x_lb,x_ub,T0,delta,moves_per_temp(i,j),temp_reduction(i,j),temp_reduction_type,eps);
        
        % write output
        nfevals(i,j) = SA.function_count;
        ratio_accept(i,j) = SA.number_of_accepts/SA.function_count;
        nuphill(i,j) = SA.number_of_uphill_accepts;
        fval(i,j) = SA.F(end);
        fval_best(i,j) = SA.Fbest(end);
    end
end

% make the plots
figure(6);
contourf(moves,trv,nfevals,'showtext','on');
xlabel('Number of Moves per Temperature');
ylabel('Temperature Reduction');
title('Number of Function Evaluations Required');

figure(7);
contourf(moves,trv,ratio_accept,'showtext','on');
xlabel('Number of Moves per Temperature');
ylabel('Temperature Reduction');
title('Ratio of Accepted Moves');

figure(8);
contourf(moves,trv,nuphill,'showtext','on');
xlabel('Number of Moves per Temperature');
ylabel('Temperature Reduction');
title('Number of Uphill Moves Accepted');

figure(9);
contourf(moves,trv,fval,'showtext','on');
xlabel('Number of Moves per Temperature');
ylabel('Temperature Reduction');
title('Objective Function Value at Termination');

figure(10);
contourf(moves,trv,fval_best,'showtext','on');
xlabel('Number of Moves per Temperature');
ylabel('Temperature Reduction');
title('Best Objective Value Found');