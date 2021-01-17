function [F] = objfun_SpeedReducer2(x)

F = ((((745*x(4))/x(2)/x(3))^2+16.9*10^6)^.5)/(.1*x(6)^3);

end