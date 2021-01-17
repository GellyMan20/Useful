function [fval] = f_linesearch(alpha,x0,S)
x = x0 + alpha.*S;
fval = x(1,:).^2 + 2*x(2,:).^2 + 2*x(3,:).^2 + 2*x(1,:).*x(2,:) + 2*x(2,:).*x(3,:);
end