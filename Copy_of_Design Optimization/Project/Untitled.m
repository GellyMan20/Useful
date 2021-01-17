%% Part 4: Weighted Sum

load('weighted_sum_results.mat');
f = zeros(2,8);
for ii = 1:8
    tmp = multiobjfun(xstar(:,ii));
    f(1,ii) = tmp(1);
    f(2,ii) = tmp(2);
end
figure;
plot(f(1,:), f(2,:), 'x', 'linewidth', 4);
xlabel('Value of Objective Function 1'); ylabel('Value of Objective Function 2');
title('Pareto Frontier of Weighted Sum Technique');

%% Part 4: 

load('multiobjgaresults.mat');
[d,~] = size(optimresults.x);
f = zeros(2,d);
for ii = 1:d
    tmp = multiobjfun(optimresults.x(ii,:));
    f(1,ii) = tmp(1);
    f(2,ii) = tmp(2);
end
figure;
plot(f(1,:), f(2,:), 'x', 'linewidth', 4);
xlabel('Value of Objective Function 1'); ylabel('Value of Objective Function 2');
title('Pareto Frontier of Multiobjective Genetic Algorithm');