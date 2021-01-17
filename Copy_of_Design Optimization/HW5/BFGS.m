function [xnew,fevals,k] = BFGS(x,diff_type,step_size)

%BFGS

%initializations
xvals = [x]; %xvalues, old and new
fevals = [objF(x)]; %function evaluations, old and new
gradvals = []; %gradient values at each point, old and new
S = [];  %search direction
k = 1; %counter
H = eye(length(x)); %initial Hessian

%Steepest Descent
S = -diffF(x,step_size,diff_type);
astar = nd_golden(x,S,nd_bounding(x,S));
xnew = x + astar*S;
fnew = objF(xnew);  
xvals = [xvals,xnew];
fevals = {fevals,fnew}

gradvals = [gradvals,-S];

c = -S;

%Enter BFGS
%while not converged develop convergence criteria
while abs(fevals{end} - fevals{end-1}) 1e-3 || k < 8;

    xold = xnew; fold = fnew;

    %get search direction
    S = H\(-c);
    %find a*
    astar = nd_golden(xnew,S,nd_bounding(xnew,S));
    %update x-values
    xnew = xnew + astar*S;
    fnew = objF(xnew);
    
    xvals = [xvals,xnew];
    fevals = [fevals,fnew];
    
    %update H, H = H + D+ E
    p = xnew - xvals(:,end-1);
    cold = c;
    c = diffF(xnew,step_size,diff_type);
    y = c - cold;
    
    D = (y*y')/(y'*p);
    E = (cold*cold')/(cold'*S);
    H = H + D + E;
    
    % update iteration counter
    k = k + 1;
        
end