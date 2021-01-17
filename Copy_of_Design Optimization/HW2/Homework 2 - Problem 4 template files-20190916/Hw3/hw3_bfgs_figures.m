% Problem 3: Fletcher-Reeves
clear all;close all;clc; format long;
% initializations
gradF = @(x) [2*x(1) + 2*x(2); 4*x(2)+2*x(1)+2*x(3); 4*x(3)+2*x(2)];
a0 = 0;
step_size = 0.1;
H = eye(3); % initialize the approximate Hessian

%% update 1 (steepest descent)
xc = [3 4 1]';

c = gradF(xc);
Sdir = H\(-c)

xb = bounding_phase_func(a0,step_size,xc,Sdir);
% make the plot
tmpint = linspace(xb(1),xb(2));
figure;
plot(tmpint,f_linesearch(tmpint,xc,Sdir),'linewidth',2);
xlabel('x+alpha*S'); ylabel('F(x+alpha*S)');
title('BFGS Move 1');

% step size
astar = fminbnd(@(a) f_linesearch(a,xc,Sdir),xb(1),xb(2))
xold = xc
xc = xc + astar*Sdir
p = xc - xold;
y = gradF(xc) - gradF(xold);
D = y*y'/(y'*p)
E = c*c'/(c'*Sdir)

H = H + D + E

val = F(xc)

%% update 2

c = gradF(xc);
Sdir = H\(-c)

xb = bounding_phase_func(a0,step_size,xc,Sdir);
% make the plot
tmpint = linspace(xb(1),xb(2));
figure;
plot(tmpint,f_linesearch(tmpint,xc,Sdir),'linewidth',2);
xlabel('x+alpha*S'); ylabel('F(x+alpha*S)');
title('BFGS Move 2');

% step size
astar = fminbnd(@(a) f_linesearch(a,xc,Sdir),xb(1),xb(2))
xold = xc;
xc = xc + astar*Sdir
p = xc - xold
y = gradF(xc) - gradF(xold);
D = y*y'/(y'*p)
E = c*c'/(c'*Sdir)

H = H + D + E

val = F(xc)
