function [results] = simulatedannealing(x0,x_lb,x_ub,T0,delta,moves_per_temp,temp_reduction,temp_reduction_type,eps)

%%%%%%%%%%%%
% Function: simulatedannealing
% Objective: Implements unbconstrained simulated annealing to find optimal x 
% Input(s): x0 - initial esign variable vector
%           x_lb - lower bounds on design varaibles, if not needed then just use [] when calling the function
%           x_ub - upper bounds on design varaibles, if not needed then just use [] when calling the function
%           T0 - the initial temperature value
%           delta - initial size of move limit box
%           moves_per_temp - the number of designs searched before reducing the temperature
%           temp_reduction - the temperature reduction parameter
%           temp_reduction_type - either step (logarithmic) or linear
%           eps - the convergence criteria
% Output(s): results - a structure that contains:
%                         xbest - best design location encountered
%                         Fbest - best objective value encountered
%                         number_of_accepts - number of accepted moves
%                         function_count - number of objective funciton evaluations
%                         x - design vector at each iteration
%                         F - F value at each iteration
%                         temperature - temperature at each iteration
%%%%%%%%%%%%

%% Determine number of design variables and set T = T0

[num_dvs,~] = size(x0);      % Determine number of DVs - must be a column vector
T = T0;

%% Allocation of initial information

f0 = objF(x0);                  % Establish initial objective function value
results.x(:,1) = x0;            % Store initial design in results vector
results.F(1,1) = f0;            % Store initial function value in results vectory
results.xbest(:,1) = x0;        % Store best design location to date
results.Fbest(1,1) = f0;             % Store value of best F to date
results.temperature(1,1) = T;   % Store the temperature values
results.number_of_accepts = 0;  % Store counter of accepted moves (uphill and downhill)
results.number_of_uphill_accepts = 0;   % Store counter of uphill accepted moves
results.function_count = 1;     % Store number of objective function calls

%% Determine move limit box

box_lb = -delta;    % Lower bound on search box
box_ub = delta;     % Upper bound on search box
box_reduc = 0.99;   % Reduces search box by 1% each time temperature changes

%% Create convergence criteria flag

stall_cycles = 0;       % Check to see if fbest improvement has stalled
max_stall_cycles = 5;   % Max number of cycles where fbest has not improved
convergence_flag = 0;   % Create convergence criteria flag

%% Begin simulated annealing algorithm

cycle = 1;
while convergence_flag == 0

    % Establish design at the beginning of the cycle
    x_cycle_start = results.x(:,results.function_count);
    F_cycle_start = results.F(results.function_count,1);
    Fbest_cycle_start = results.Fbest(end,1);
    
    for move_in_current_cycle = 1:moves_per_temp  
        
        % Generate random new point given move limit box
        random_move = box_lb.*(box_reduc^cycle) + (box_ub - box_lb).*(box_reduc^cycle).*rand(num_dvs,1);
        x_new = results.x(:,results.function_count) + random_move; 
        
        % Make sure that new move does not violate lower bounds on x
        % Do this by pulling indpendent x variables back to the lower bound
        if ~isempty(x_lb)
            x_new = max(x_new,x_lb);
        end
        
        % Make sure that new move does not violate upper bounds on x
        % Do this by pulling indpendent x variables back to the upper bound
        if ~isempty(x_ub)
            x_new = min(x_new,x_ub);
        end
        
        % Access objective function value
        F_new = objF(x_new); 
        results.function_count = results.function_count + 1;
        
        % Calculate delta_E
        delta_E = F_new - results.F(results.function_count-1,1);

        if delta_E < 0  % Automatically accept the move
            
            results.x(:,results.function_count) = x_new;
            results.F(results.function_count,1) = F_new;
            results.temperature(results.function_count,1) = T;
            results.xbest(:,1) = x_new;
            results.Fbest(results.function_count,1) = min(results.Fbest(results.function_count-1,1),F_new);    %% MOD HERE 
            results.number_of_accepts = results.number_of_accepts + 1;
            
        else  % Determine if move should be accepted
            
            % Calculate the probability of accepting the new point
            P = min([1,exp(-delta_E/T)]);
               
            if rand < P   % Accept the move
                
                results.x(:,results.function_count) = x_new;
                results.F(results.function_count,1) = F_new;
                results.temperature(results.function_count,1) = T;
                results.number_of_accepts = results.number_of_accepts + 1;
                results.number_of_uphill_accepts = results.number_of_uphill_accepts + 1;
                results.Fbest(results.function_count,1) = results.Fbest(results.function_count-1,1);
                                
            else   % Reject the move and keep previous point
                
                results.x(:,results.function_count) = results.x(:,results.function_count-1);
                results.F(results.function_count,1) = results.F(results.function_count-1,1);
                results.temperature(results.function_count,1) = T;
                results.Fbest(results.function_count,1) = results.Fbest(results.function_count-1,1);
                
            end
        end
    end

    % Check to see if Fbest changed over the cycle
    if (results.Fbest(end,1) == Fbest_cycle_start)
        
        stall_cycles = stall_cycles + 1;
        if (stall_cycles > max_stall_cycles)
            convergence_flag = 1;
        end
    
    else % calculate improvement in objective function
        diff_f = abs(results.Fbest(end,1) - Fbest_cycle_start)/abs(Fbest_cycle_start);
    
        if (diff_f < eps) % evaluate convergence criteria
            convergence_flag = 1;
        end
    end

    % Temperature reduction
    if strcmp(temp_reduction_type,'linear')
        T = max(0,T - (1-temp_reduction)*T0);
    else
        T = max(0,temp_reduction*T);  
    end
    cycle = cycle + 1;
   
end

end