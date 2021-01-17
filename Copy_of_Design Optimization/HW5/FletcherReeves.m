function [xnew,fevals,xvals] = FletcherReeves(x)
% Fletcher-Reeves
clear all; close all; clc; 

%% initializations
x = [10:10:50,zeros(1,5)];
xvals = []; %xvalues
fevals = []; %function evaluations
gradevals = []; %gradient values at each point
S = [];  %search direction
step_size = 0.25;
diff_type = 'forward';
k = 0; %counter
condition = false;
condition2 = 1e-3;


%Steepest Descent
S = -diffF(x,step_size,diff_type);
astar = nd_golden(x,S,nd_bounding(x,S));
xnew = x + astar*S;
fnew = objF(xnew);

xvals = [xvals,xnew];
fevals = [fevals,fnew];
%gradevals = [gradevals,-S];


%% Main Loop
while condition == false
    xold = xnew; fold = fnew;
    
    % compute new search direction
    gradevals = [gradevals,diffF(xold,delta,diff_type)];
    beta = (norm(gradevals(:,end))/norm(gradevals(:,end-1)))^2;
    S = -gradevals(:,end) + beta*S(:,end);
    
    % compute new point and function value
    astar = nd_golden(xold,S,nd_bounding(xold,S));
    xnew = xold + astar*S;
    fnew = objF(xnew);
    fevals = [fevals,fnew];
    xvals = [xvals,xnew];
    
    k = k+1; % update iteration counter
    
    % check convergence
    if abs(fnew-fold) < condition2
        condition = true;
    end
end
