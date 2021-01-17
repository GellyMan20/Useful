function [alpha_star] = nd_golden(x,S,alpha_bounds)

%%
%%%%%%%%%%%%%%%%%%%%%%%%
% Function: nd_golden
% Objective: Determine alpha star
% Input(s): x - Current design variable vector
%           S - Current search vector  
%           alpha_bounds - Bounds on alpha (from nd_bounding)
% Output(s): alpha_star - Alpha star
%%%%%%%%%%%%%%%%%%%%%%%%

%% Conduct Golden Section from current x, S, and alpha_bounds

% Define tau
tau = 0.381966;

% Define lower and upper bound values of alpha and calculate F
astar_lb = alpha_bounds(1);
xeval = x + astar_lb*S; 
Flb = objF(xeval);

astar_ub = alpha_bounds(2);
xeval = x - astar_ub*S;                                       %% \\ Complete this line \\
Fub = objF(xeval);

% Calculate the two interior points for golden section
astar_1 = astar_lb + tau*(astar_ub-astar_lb);                                     %% \\ Complete this line \\
xeval = x + astar_1*S;                                       %% \\ Complete this line \\
F1 = objF(xeval);

astar_2 = astar_lb + (1-tau)*(astar_ub-astar_lb);                                     %% \\ Complete this line \\
xeval = x + astar_2*S;                                       %% \\ Complete this line \\
F2 = objF(xeval);
K = 2;  

% Hard code convergence criteria (we are running for 18 iterations [N-K])
N = 20;

% Begin loop procedure for Golden Section
while (K < N)    

    if (F1 > F2)
        astar_lb = astar_1;
        Flb = F1;
        astar_1 = astar_2;
        F1 = F2;
        astar_2 = tau*astar_lb + (1-tau)*astar_ub;
        xeval = x + astar_2*S; 
        F2 = objF(xeval);
        K = K + 1;
    else
        astar_ub = astar_2;
        Fub = F2;
        astar_2 = astar_1;
        F2 = F1;
        astar_1 = (1-tau)*astar_lb + tau*astar_ub;
        xeval = x + astar_1*S; 
        F1 = objF(xeval);
        K = K + 1;
    end   
end

% Define alpha_star as the average of upper and lower bounds at end of loop
alpha_star = (astar_lb + astar_ub)/2;

end