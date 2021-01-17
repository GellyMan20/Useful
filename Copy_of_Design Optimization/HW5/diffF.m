function [gradF] = diffF(x,step_size,diff_type)
%%
%%%%%%%%%%%%%%%%%%%%%%%%
% Function: diffF
% Objective: Calculate gradient using finite differences
% Input(s): x - Current design variable vector
%           step_size - Size of the approximation step (delta x)
%           diff_type - Finite difference technique 
%                       ('forward', 'backward', 'central')
%                       (Algorithm defaults to central if flag not specified
%                       correctly)
% Output(s): gradF - Gradient at current design location
%%%%%%%%%%%%%%%%%%%%%%%%

%% Pull out the number of design variables in the problem, take absolute value of step size
[m,n] = size(x);
step_size = abs(step_size);

%% Evaluate the objective function at the current location (x)
Fcurrent = objF(x);                                    %% \\ Complete this line \\

%% Calculate the forward difference gradient by looping over all the design variables
if strcmp(diff_type,'forward')

    for i = 1:m
        % Initialize variable new_x so that we can change one element and then
        % reset it
        new_x = x;                               %% \\ Complete this line \\

        % Calculate the new design location by varying x_sub_i
        new_x(i,1) = new_x(i,1)+step_size;                           %% \\ Complete this line \\

        % Calculate the new value of F at the new location
        Fnew = objF(new_x);                               %% \\ Complete this line \\    

        % Calculate the gradient for this displacement
        gradF(i,1) = (Fnew-Fcurrent)/step_size;                          %% \\ Complete this line \\
    end
    
%% Calculate the backward difference gradient by looping over all the design variables
elseif strcmp(diff_type,'backward')

    for i = 1:m

        % Initialize variable new_x so that we can change one element and then
        % reset it
        new_x = x;

        % Calculate the new design location by varying x_sub_i
        new_x(i,1) = new_x(i,1)-step_size;      

        % Calculate the new value of F at the new location
        Fnew = objF(new_x);       
        
        % Calculate the gradient for this displacement
        gradF(i,1) = (Fcurrent-Fnew)/step_size;    
    end

%% Calculate the central difference gradient by looping over all the design variables
else

    for i = 1:m

        % Initialize variable new_x so that we can change one element and then
        % reset it
        new_x = x;

        % Calculate the new design location by varying x_sub_i by adding
        % half the step size
        new_x(i,1) = new_x(i,1)+step_size/2;                          %% \\ Complete this line \\

        % Calculate the new value of F at the new location
        Fnew_plus = objF(new_x);                           %% \\ Complete this line \\

        % Calculate the new design location by varying x_sub_i by
        % subtracting half the step size
        new_x(i,1) = new_x(i,1)-step_size/2;                          %% \\ Complete this line \\
        
        % Calculate the new value of F at the new location
        Fnew_minus = objF(new_x);                          %% \\ Complete this line \\      
        
        % Calculate the gradient for this displacement
        gradF(i,1) = (Fnew_plus-Fnew_minus)/step_size;                          %% \\ Complete this line \\
    end
end
end

