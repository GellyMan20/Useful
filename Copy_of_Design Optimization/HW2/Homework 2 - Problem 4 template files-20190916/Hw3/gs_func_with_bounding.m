function [xbounds,xopt,Fopt,function_calls,iteration_results] = gs_func_with_bounding(x0,step_size,ex_terminate,xc,S)
%%
%%%%%%%%%%%%%%%%%%%%
% Code for Golden Section with bounding phase (gs_func_with_bounding)
%   
% Inputs:
%   - Starting location (x0)
%   - Step size for bounding phase (step_size)
%   - Convergence tolerance (ex_terminate)
%   
% Outputs:
%   - Bounding phase bounds (xbounds)
%   - Optimal solution for x (xopt)
%   - Objective function value of optimal solution (Fopt)
%   - Number of objective function calls needed (function_calls)
%   - Iteration results from each step of golden section (iteration_results)
%
% Requires 2 additional files
%   1) bounding_phase_func.m (bounding phase algorithm function)
%   2) objF.m (objective function)
%%%%%%%%%%%%%%%%%%%%

%% Initialization
tau = 0.381966;
current_convergence_ratio = 1;
counter = 1;

%% Call bounding phase algorithm

[xbounds,function_calls] = bounding_phase_func(x0,step_size,xc,S);

%% Assign variable names to bounds

xL = min(xbounds);
xR = max(xbounds);
FL = f_linesearch(xL,xc,S);
FR = f_linesearch(xR,xc,S);

%% Golden section code

% Define original region of uncertainty (L_original)
L_original = xR - xL;
L = L_original;

% Determine x1, F1, x2, and F2
x1 = (1-tau)*xL + tau*xR;
F1 = f_linesearch(x1,xc,S);
function_calls = function_calls + 1;

x2 = tau*xL + (1-tau)*xR;
F2 = f_linesearch(x2,xc,S);
function_calls = function_calls + 1;

% Save results of xL, x1, x2, and xR each iteration
iteration_results(counter,:) = [xL x1 x2 xR];

% Loop of golden section code
while (current_convergence_ratio > ex_terminate)    
    
    if (F1 > F2)
        
        xL = x1;
        FL = F1;
        x1 = x2;
        F1 = F2;
        x2 = tau*xL + (1-tau)*xR;
        F2 = objF(x2);
        function_calls = function_calls + 1;
       
    else
        xR = x2;
        FR = F2;
        x2 = x1;
        F2 = F1;
        x1 = (1-tau)*xL + tau*xR;
        F1 = objF(x1);
        function_calls = function_calls + 1;
  
    end   

    L = xR - xL;
    current_convergence_ratio = L/L_original;
    counter = counter + 1;
    iteration_results(counter,:) = [xL x1 x2 xR];
end

xopt = (xL + xR)/2;
Fopt = objF(xopt);
function_calls = function_calls + 1;
end