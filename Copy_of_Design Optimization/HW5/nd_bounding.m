function [alpha_bounds] = nd_bounding(x,S)

%%
%%%%%%%%%%%%%%%%%%%%%%%%
% Function: nd_bounding
% Objective: Determine bounds on alpha starting at alpha = 0 from current x
% Input(s): x - Current design variable vector
%           S - Current search vector  
% Output(s): alpha_bounds - Bounds on alpha
%%%%%%%%%%%%%%%%%%%%%%%%

%% Evaluate objective function at current location
f0 = objF(x);                                          %% \\ Complete this line \\
fcurrent = f0;

%% Establish step size (delta) for bounding phase
step_size = .05;

% Determine the correct sign on step size
x_left = x - abs(step_size)*S;                                      %% \\ Complete this line \\
f_left = objF(x_left);     

x_right = x + abs(step_size)*S;                                     %% \\ Complete this line \\
f_right = objF(x_right);   

if (f_left >= f0) && (f0 >= f_right);
    step_size = step_size; 
elseif (f_left <= f0) && (f0 <= f_right)
    step_size = -1*step_size;
else
    astar_lb = -step_size;
    astar_ub = step_size;
    step_size = 0;
end

%% If step size does not bound the space, complete bounding procedure
if (abs(step_size) > 0)
    k = 0;
    flag = 0;
    acurrent = 0;
    a_full_vector = [acurrent];
    f_full_vector = [f0];

    while (flag == 0)

        % Determine new value of alpha
        anew = acurrent + (2^k)*step_size;             %% \\ Complete this line \\
        
        % Determine new value for x given new alpha
        x_new = x + anew*S;                               %% \\ Complete this line \\
        
        % Determine new objective function value
        fnew = objF(x_new);                   

        if (fnew < fcurrent)
            a_full_vector = [a_full_vector; anew];
            f_full_vector = [f_full_vector; fnew];
            acurrent = anew;
            fcurrent = fnew;
            k = k + 1;
        else
            a_full_vector = [a_full_vector; anew];
            f_full_vector = [f_full_vector; fnew];
            [m,n] = size(a_full_vector);
            abounds = [a_full_vector(m-2,1) a_full_vector(m,1)];
            flag = 1;
        end
    end

    if (abounds(1) < abounds(2))
        astar_lb = abounds(1);
        astar_ub = abounds(2);
    else
        astar_lb = abounds(2);
        astar_ub = abounds(1);
    end
end

alpha_bounds = [astar_lb astar_ub];
end

