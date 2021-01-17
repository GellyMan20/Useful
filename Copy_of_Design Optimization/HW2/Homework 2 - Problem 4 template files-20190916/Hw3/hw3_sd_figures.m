% Problem 2: Steepest-Descent

% initializations
gradF = @(x) [2*x(1) + 2*x(2); 4*x(2)+2*x(1)+2*x(3); 4*x(3)+2*x(2)];
a0 = 0;
step_size = 0.1;
alpha = [];
x = [];

%% move 1
xc = [3 4 1]';
x(:,1) = xc;
Sdir = -gradF(xc);

xb = bounding_phase_func(a0,step_size,xc,Sdir);
% make the plot
tmpint = linspace(xb(1),xb(2));
figure;
plot(tmpint,f_linesearch(tmpint,xc,Sdir),'linewidth',2);
xlabel('x + \alpha^*S'); ylabel('F(x+\alpha^*S)');
title('Steepest Descent Move 1');

% step size
astar = fminbnd(@(a) f_linesearch(a,xc,Sdir),xb(1),xb(2));
alpha = [alpha,astar];
xc = xc + astar*Sdir;
x(:,2) = xc;

%% move 2
Sdir = -gradF(xc);

xb = bounding_phase_func(a0,step_size,xc,Sdir);
% make the plot
tmpint = linspace(xb(1),xb(2));
figure;
plot(tmpint,f_linesearch(tmpint,xc,Sdir),'linewidth',2);
xlabel('x + \alpha^*S'); ylabel('F(x+\alpha^*S)');
title('Steepest Descent Move 2');

% step size
astar = fminbnd(@(a) f_linesearch(a,xc,Sdir),xb(1),xb(2));
alpha = [alpha,astar]
xc = xc + astar*Sdir;
x(:,3) = xc;

%% move 3
Sdir = -gradF(xc);

xb = bounding_phase_func(a0,step_size,xc,Sdir);
% make the plot
tmpint = linspace(xb(1),xb(2));
figure;
plot(tmpint,f_linesearch(tmpint,xc,Sdir),'linewidth',2);
xlabel('x + \alpha^*S'); ylabel('F(x+\alpha^*S)');
title('Steepest Descent Move 3');

% step size
astar = fminbnd(@(a) f_linesearch(a,xc,Sdir),xb(1),xb(2));
alpha = [alpha,astar]
xc = xc + astar*Sdir;
x(:,4) = xc;