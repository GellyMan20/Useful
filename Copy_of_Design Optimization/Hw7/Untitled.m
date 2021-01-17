% driver code for homework 7
% authors: Graham Pash, Noah Johnson
clear all; close all; clc;

%% Problem 2

x0 = [10;20;30;40;50;0;0;0;0;0];
delta = 0.2;
temp_reduction_type = 'exponential';
eps = 1e-9;
x_lb = [];
x_ub = [];

% initial temperature
T0 = 50;
% T0 = 200;
 
mvs = 1:5:100; % moves per temp
tr = .4:0.05:.9; % temp reduction scheme
[moves_per_temp,temp_reduction] = meshgrid(mvs,tr);

% preallocate arrays for output
nfevals = zeros(size(moves_per_temp));
rat_accept = nfevals;
nuphill = nfevals;
fval = nfevals;
fval_best = nfevals;

for i = 1:size(moves_per_temp,1)
    for j = 1:size(moves_per_temp,2)
        tmp = simulatedannealing(x0,x_lb,x_ub,T0,delta,moves_per_temp(i,j),temp_reduction(i,j),temp_reduction_type,eps);
        
        % write output
        nfevals(i,j) = tmp.function_count;
        rat_accept(i,j) = tmp.number_of_accepts/tmp.function_count;
        nuphill(i,j) = tmp.number_of_uphill_accepts;
        fval(i,j) = tmp.F(end);
        fval_best(i,j) = tmp.Fbest(end);
    end
end

% make the plots
figure(1);
contourf(mvs,tr,nfevals,'showtext','on');
xlabel('Number of Moves per Temperature');
ylabel('Temperature Reduction');
title('Number of Function Evaluations Required');

figure(2);
contourf(mvs,tr,rat_accept,'showtext','on');
xlabel('Number of Moves per Temperature');
ylabel('Temperature Reduction');
title('Ratio of Accepted Moves');

figure(3);
contourf(mvs,tr,nuphill,'showtext','on');
xlabel('Number of Moves per Temperature');
ylabel('Temperature Reduction');
title('Number of Uphill Moves Accepted');

figure(4);
contourf(mvs,tr,fval,'showtext','on');
xlabel('Number of Moves per Temperature');
ylabel('Temperature Reduction');
title('Objective Function Value at Termination');

figure(5);
contourf(mvs,tr,fval_best,'showtext','on');
xlabel('Number of Moves per Temperature');
ylabel('Temperature Reduction');
title('Best Objective Value Found');

%% Problem 3

x1 = linspace(-2,2);
x2 = linspace(-2,2);
fval = zeros(numel(x1),numel(x2));
for i = 1:numel(x1)
    for j = 1:numel(x2)
        fval(i,j) = objF([x1(i);x2(j)]);
    end
end
surf(x1,x2,fval);
xlabel('x_1'); ylabel('x_2');

x0 = [1;0];
x_lb = [-2;-2];
x_ub = [2;2];
delta = 0.1;
temp_reduction_type = 'exponential';
eps = 1e-9;

% initial temperature
T0 = 0.5;
%T0 = 10;
 
mvs = 1:5:100; % moves per temp
tr = .4:0.05:.9; % temp reduction scheme
[moves_per_temp,temp_reduction] = meshgrid(mvs,tr);

% preallocate arrays for output
nfevals = zeros(size(moves_per_temp));
rat_accept = nfevals;
nuphill = nfevals;
fval = nfevals;
fval_best = nfevals;

for i = 1:size(moves_per_temp,1)
    for j = 1:size(moves_per_temp,2)
        tmp = simulatedannealing(x0,x_lb,x_ub,T0,delta,moves_per_temp(i,j),temp_reduction(i,j),temp_reduction_type,eps);
        
        % write output
        nfevals(i,j) = tmp.function_count;
        rat_accept(i,j) = tmp.number_of_accepts/tmp.function_count;
        nuphill(i,j) = tmp.number_of_uphill_accepts;
        fval(i,j) = tmp.F(end);
        fval_best(i,j) = tmp.Fbest(end);
    end
end

% make the plots
figure(6);
contourf(mvs,tr,nfevals,'showtext','on');
xlabel('Number of Moves per Temperature');
ylabel('Temperature Reduction');
title('Number of Function Evaluations Required');

figure(7);
contourf(mvs,tr,rat_accept,'showtext','on');
xlabel('Number of Moves per Temperature');
ylabel('Temperature Reduction');
title('Ratio of Accepted Moves');

figure(8);
contourf(mvs,tr,nuphill,'showtext','on');
xlabel('Number of Moves per Temperature');
ylabel('Temperature Reduction');
title('Number of Uphill Moves Accepted');

figure(9);
contourf(mvs,tr,fval,'showtext','on');
xlabel('Number of Moves per Temperature');
ylabel('Temperature Reduction');
title('Objective Function Value at Termination');

figure(10);
contourf(mvs,tr,fval_best,'showtext','on');
xlabel('Number of Moves per Temperature');
ylabel('Temperature Reduction');
title('Best Objective Value Found');