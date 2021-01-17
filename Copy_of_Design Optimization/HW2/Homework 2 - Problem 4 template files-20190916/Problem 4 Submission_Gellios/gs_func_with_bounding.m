function [xbounds,xopt,Fopt,function_calls,iteration_results] = gs_func_with_bounding(x0,step_size,ex_terminate)
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

%% Initialization (DO NOT EDIT)
tau = 0.381966;
current_convergence_ratio = 1;
counter = 1;

%% Call bounding phase algorithm

[xbounds,function_calls] = bounding_phase_func(x0,step_size);   %% COMPLETE THIS LINE

%% Assign variable names to bounds

xL = min(xbounds);
xR = max(xbounds);
FL = objF(xL);
FR = objF(xR);

%%
%%%%%%%%%%%%%%%%%%%
% Generalized golden section code
%%%%%%%%%%%%%%%%%%%

% Define original region of uncertainty (L_original)
L_original = abs(xR-xL);                      %% COMPLETE THIS LINE
L = L_original;

% Determine x1, F1, x2, and F2
x1 = (1-tau)*xL+tau*xR;                              %% COMPLETE THIS LINE
F1 = objF(x1);                              %% COMPLETE THIS LINE
function_calls = function_calls + 1;

x2 = tau*xL + (1-tau)*xR;                              %% COMPLETE THIS LINE
F2 = objF(x2);                              %% COMPLETE THIS LINE
function_calls = function_calls + 1;

% Save results of xL, x1, x2, and xR each iteration
iteration_results(counter,:) = [xL x1 x2 xR];

% Loop of golden section code
while (current_convergence_ratio > ex_terminate)    
    
    if (F1 > F2)
        
        xL = x1;          %% COMPLETE THIS LINE
        FL = objF(xL);          %% COMPLETE THIS LINE
        x1 = x2;          %% COMPLETE THIS LINE
        F1 = objF(x1);          %% COMPLETE THIS LINE
        x2 = tau*xL + (1-tau)*xR;          %% COMPLETE THIS LINE
        F2 = objF(x2);          %% COMPLETE THIS LINE
        function_calls = function_calls + 1;
       
    else
        xR = x2;          %% COMPLETE THIS LINE
        FR = objF(xR);          %% COMPLETE THIS LINE
        x2 = x1;          %% COMPLETE THIS LINE
        F2 = objF(x2);          %% COMPLETE THIS LINE
        x1 = (1-tau)*xL+tau*xR;          %% COMPLETE THIS LINE
        F1 = objF(x1);          %% COMPLETE THIS LINE
        function_calls = function_calls + 1;
  
    end   

    % Determine new region of uncertainty
    L = abs(xR-xL);               %% COMPLETE THIS LINE
    
    % Calculate new convergence ratio
    current_convergence_ratio = L/L_original;       %% COMPLETE THIS LINE
    
    counter = counter + 1;
    iteration_results(counter,:) = [xL x1 x2 xR];
end

xopt = (xL + xR)/2;
Fopt = objF(xopt);
function_calls = function_calls + 1;
end