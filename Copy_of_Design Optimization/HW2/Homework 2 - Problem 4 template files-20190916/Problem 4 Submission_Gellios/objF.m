function [ F ] = objF( x )

%%%%%%%%%%%%%%%%%%%%
% Code for objective function (objF)
%   
% Inputs:
%   - Current design vector (x)
%   
% Outputs:
%   - Objective function value (F)
%
%%%%%%%%%%%%%%%%%%%%

% Objective function 
F = 2*(x-3)^2+exp(.5*x^2);       %% COMPLETE THIS LINE

end

