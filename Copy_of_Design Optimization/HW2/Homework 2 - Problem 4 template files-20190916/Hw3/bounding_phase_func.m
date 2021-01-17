function [xbounds,function_calls] = bounding_phase_func(x0,step_size,xc,S)
%%
%%%%%%%%%%%%%%%%%%%%
% Code for Bounding phase algorithm (bounding_phase_func)
%   
% Inputs:
%   - Starting location (x0)
%   - Step size for bounding phase (step_size)
%   
% Outputs:
%   - Bounding phase bounds (xbounds)
%   - Number of objective function calls needed (function_calls)
%
% Requires 1 additional file
%   1) f_linesearch.m (objective function)
%%%%%%%%%%%%%%%%%%%%

%% Initialization
f0 = f_linesearch(x0,xc,S);          % Determine current objective function value
function_calls = 1;     % Account for first objective function call

%% Define step size direction 

x_left = x0 - abs(step_size);
f_left = f_linesearch(x_left,xc,S);
function_calls = function_calls + 1;

x_right = x0 + abs(step_size);
f_right = f_linesearch(x_right,xc,S);
function_calls = function_calls + 1;

if (f_left >= f0) && (f0 >= f_right);
    step_size = step_size;
elseif (f_left <= f0) && (f0 <= f_right)
    step_size = -1*step_size;
else
    step_size = 0;
end

%% Begin bounding procedure

if (step_size == 0)     % Solution is bounded by the step size
    xbounds = [x_left x_right];
else
    k = 0;              % Set k = 0 so that 2^0 = 1 for first iteration
    convergence_flag = 0;   % Set convergence flag = 0
    xcurrent = x0;
    fcurrent = f0;
    x_full_vector = [xcurrent]; % Build a list of all visited x locations

    while (convergence_flag == 0)

        xnew = xcurrent + (2^k)*step_size;
        fnew = f_linesearch(xnew,xc,S);
        function_calls = function_calls + 1;

        if (fnew < fcurrent)
            x_full_vector = [x_full_vector; xnew];
            xcurrent = xnew;
            fcurrent = fnew;
            k = k + 1;
        else
            x_full_vector = [x_full_vector; xnew];
            [m,n] = size(x_full_vector);
            xbound_left = min(x_full_vector(m-2,1),x_full_vector(m,1));
            xbound_right = max(x_full_vector(m-2,1),x_full_vector(m,1));
            xbounds = [xbound_left xbound_right];
            convergence_flag = 1;
        end
    end
end

end
    
    