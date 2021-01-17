% Problem 1: Powell's Method
clear allo;close all; clc;
% initializations
S = eye(3);
x = [3;4;1];
alpha = [-6.9989];

%% move 1 updates
xc = [-3.9989;4;1];
x(:,2) = xc;

%% move 2
a0 = 0; step_size = 0.1; Sdir = S(:,2);
xb = bounding_phase_func(a0,step_size,xc,Sdir);
% make the plot
% tmpint = linspace(xb(1),xb(2));
% figure;
% plot(tmpint,f_linesearch(tmpint,xc,Sdir),'linewidth',2);
% xlabel('x + a^*S'); ylabel('F(x+a^*S)');
% title('Cycle 1 Move 2');

% step size
astar = fminbnd(@(a) f_linesearch(a,xc,Sdir),xb(1),xb(2));
alpha = [alpha,astar];
xc = xc + astar*Sdir;
x(:,3) = xc;

%% move 3
Sdir = S(:,3);
xb = bounding_phase_func(a0,step_size,xc,Sdir);
% make the plot

% tmpint = linspace(xb(1),xb(2))
% figure;
% plot(tmpint,f_linesearch(tmpint,xc,Sdir),'linewidth',2);
% xlabel('x + a^*S'); ylabel('F(x+a^*S)');
% title('Cycle 1 Move 3');

% step size
astar = fminbnd(@(a) f_linesearch(a,xc,Sdir),xb(1),xb(2));
alpha = [alpha,astar];
xc = xc + astar*Sdir;
x(:,4) = xc;

%% move 4 (first-conjugate move)
S(:,1) = xc - x(:,1)
Sdir = S(:,1)
xb = bounding_phase_func(a0,step_size,xc,Sdir)
% make the plot
% tmpint = linspace(xb(1),xb(2));
% figure;
% plot(tmpint,f_linesearch(tmpint,xc,Sdir),'linewidth',2);
% xlabel('x + a^*S'); ylabel('F(x+a^*S)');
% title('Cycle 1 Move 4 (Conjugate Search Direction)');

% step size
astar = fminbnd(@(a) f_linesearch(a,xc,Sdir),xb(1),xb(2))
alpha = [alpha,astar]
xc = xc + astar*Sdir
x(:,5) = xc;

%% move 5 (start of Cycle 2)
cycle2x0 = xc;
Sdir = S(:,2);
xb = bounding_phase_func(a0,step_size,xc,Sdir);
% make the plot
% tmpint = linspace(xb(1),xb(2));
% figure;
% plot(tmpint,f_linesearch(tmpint,xc,Sdir),'linewidth',2);
% xlabel('x + a^*S'); ylabel('F(x+a^*S)');
% title('Cycle 2 Move 1');

% step size
astar = fminbnd(@(a) f_linesearch(a,xc,Sdir),xb(1),xb(2));
alpha = [alpha,astar]
xc = xc + astar*Sdir;
x(:,6) = xc;

%% move 6
Sdir = S(:,3);
xb = bounding_phase_func(a0,step_size,xc,Sdir);
% make the plot
% tmpint = linspace(xb(1),xb(2));
% figure;
% plot(tmpint,f_linesearch(tmpint,xc,Sdir),'linewidth',2);
% xlabel('x + a^*S'); ylabel('F(x+a^*S)');
% title('Cycle 2 Move 2');

% step size
astar = fminbnd(@(a) f_linesearch(a,xc,Sdir),xb(1),xb(2));
alpha = [alpha,astar]
xc = xc + astar*Sdir;
x(:,7) = xc;

%% move 7 (original conjugate)
Sdir = S(:,1);
xb = bounding_phase_func(a0,step_size,xc,Sdir);
% make the plot
% tmpint = linspace(xb(1),xb(2));
% figure;
% plot(tmpint,f_linesearch(tmpint,xc,Sdir),'linewidth',2);
% xlabel('x + a^*S'); ylabel('F(x+a^*S)');
% title('Cycle 2 Move 3');

% step size
astar = fminbnd(@(a) f_linesearch(a,xc,Sdir),xb(1),xb(2));
alpha = [alpha,astar]
xc = xc + astar*Sdir;
x(:,8) = xc;

%% move 8 (second conjugate search)
Sdir = xc - cycle2x0;
S(:,2) = Sdir;
xb = bounding_phase_func(a0,step_size,xc,Sdir);
% make the plot
% tmpint = linspace(xb(1),xb(2));
% figure;
% plot(tmpint,f_linesearch(tmpint,xc,Sdir),'linewidth',2);
% xlabel('x + a^*S'); ylabel('F(x+a^*S)');
% title('Cycle 2 Move 4');

% step size
astar = fminbnd(@(a) f_linesearch(a,xc,Sdir),xb(1),xb(2));
alpha = [alpha,astar]
xc = xc + astar*Sdir;
x(:,9) = xc