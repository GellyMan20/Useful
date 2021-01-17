function [ F ] = objF_new( x )

%%
%%%%%%%%%%%%%%%%%%%%%%%%
% Function: objF
% Objective: Calculate objective function
% Input(s): x - Current design variable vector
% Output(s): F - Objective function value
%%%%%%%%%%%%%%%%%%%%%%%%

F = sin(2.2*pi*x(1)+0.5*pi).*((2-abs(x(2)))/2).*((3-abs(x(1)))/2) + ...
    sin(0.5*pi*x(2).^2+0.5*pi).*((2-abs(x(2)))/2).*((3-abs(x(1)))/2);

end