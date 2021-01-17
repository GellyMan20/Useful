% Problem 3: Fletcher-Reeves
clear all; close all; clc; format long;
% initializations
gradF = @(x) [2*x(1) + 2*x(2); 4*x(2)+2*x(1)+2*x(3); 4*x(3)+2*x(2)];
a0 = 0;
step_size = 0.1;
alpha = [];
x = [];

%% move 1 (steepest descent)
xc = [3 4 1]';
x(:,1) = xc;
Sdir(:,1) = -gradF(xc)

xb = bounding_phase_func(a0,step_size,xc,Sdir(:,1));
% make the plot
tmpint = linspace(xb(1),xb(2));
figure;
plot(tmpint,f_linesearch(tmpint,xc,Sdir),'linewidth',2);
xlabel('x +alpha*S'); ylabel('F(x+alpha*S)');
title('FR Move 1');

% step size
astar = fminbnd(@(a) f_linesearch(a,xc,Sdir(:,1)),xb(1),xb(2));
alpha = [alpha,astar];
xold = xc;
xc = xc + astar*Sdir(:,1);
x(:,2) = xc;

%% move 2
beta = (norm(gradF(xc))/norm(gradF(xold)))^2;
Sdir(:,2) = -gradF(xc)+beta*Sdir(:,1)

xb = bounding_phase_func(a0,step_size,xc,Sdir(:,2));

% make the plot
tmpint = linspace(xb(1),xb(2));
figure;
plot(tmpint,f_linesearch(tmpint,xc,Sdir(:,2)),'linewidth',2);
xlabel('x+alpha*S'); ylabel('F(x+alpha*S)');
title('FR Move 2');

% step size
astar = fminbnd(@(a) f_linesearch(a,xc,Sdir(:,2)),xb(1),xb(2));
alpha = [alpha,astar];
xold = xc;
xc = xc + astar*Sdir(:,2);
x(:,3) = xc;

%% move 3
beta = (norm(gradF(xc))/norm(gradF(xold)))^2;
Sdir(:,3) = -gradF(xc)+beta*Sdir(:,2)

xb = bounding_phase_func(a0,step_size,xc,Sdir(:,3));

% make the plot
tmpint = linspace(xb(1),xb(2));
figure;
plot(tmpint,f_linesearch(tmpint,xc,Sdir(:,3)),'linewidth',2);
xlabel('x+alpha*S'); ylabel('F(x+alpha*S)');
title('FR Move 3');

% step size
astar = fminbnd(@(a) f_linesearch(a,xc,Sdir(:,3)),xb(1),xb(2));
alpha = [alpha,astar];
xold = xc;
xc = xc + astar*Sdir(:,3);
x(:,4) = xc;

val(:,1) = F(x(:,1));
val(:,2) = F(x(:,2));
val(:,3) = F(x(:,3));
val(:,4) = F(x(:,4));

%stuff to print
x
Sdir
alpha
val

