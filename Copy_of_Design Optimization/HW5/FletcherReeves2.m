function [xnew,fevals,k] = FletcherReeves2(x,diff_type,step_size)

% Fletcher-Reeves
x0 = x;

%initializations
xvals = []; %xvalues, old and new
fevals = []; %function evaluations, old and new
gradvals = []; %gradient values at each point, old and new
S = [];  %search direction
k = 1; %counter
convergance = 1e-3; %convergance criteria

%Steepest Descent
S = -diffF(x0,step_size,diff_type);
astar = nd_golden(x0,S,nd_bounding(x0,S));
xnew = x0 + astar*S;
fnew = objF(xnew);
fold = fnew;
xold = x0;

gradvals = [gradvals,-S];

%enter FR
%while not converged
while abs(xnew - xold) > convergance
    
    xold = xnew; fold = fnew;
    
    %find gradient of x(k)
    gradvals = [gradvals,diffF(xold,step_size,diff_type)];
    %calculate beta(k-1)
    Beta = (norm(gradvals(:,end))/norm(gradvals(:,end-1)))^2;
    %find new search direction
    S = -gradvals(:,end) + Beta*S(:,end);
    %find alpha star
    astar = nd_golden(xold,S,nd_bounding(xold,S));
    %find x(k+1)
    xnew = xold+astar*S;
    fnew = objF(xnew);
    
    xvals = [xvals,xnew];
    fevals = [fevals,fnew];
    
    %add iteration
    k=k+1;
    
%end loop
end 
