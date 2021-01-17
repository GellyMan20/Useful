clear all;close all; clc;

%and put it all together
x = [10:10:50, zeros(1,5)]';

% reference solution
xstar = fminunc(@hw5problem,x)
objF(xstar)

%% Run 1 -FR
step_size = 0.25;
diff_type = 'forward';

[xnew,fevals,k] = FletcherReeves2(x,diff_type,step_size);
xnew, fevals(end), k

figure;
plot(1:length(fevals),fevals,'linewidth',2);
xlabel('Iteration');
ylabel('Function Value');
%% Run 2 - FR
step_size = 0.025;
diff_type = 'forward';

[xnew,fevals,k] = FletcherReeves2(x,diff_type,step_size);
xnew, fevals(end), k

figure;
plot(1:length(fevals),fevals,'linewidth',2);
xlabel('Iteration');
ylabel('Function Value');
%% Run 3 - FR
step_size = 0.0025;
diff_type = 'forward';

[xnew,fevals,k] = FletcherReeves2(x,diff_type,step_size);
xnew, fevals(end), k

figure;
plot(1:length(fevals),fevals,'linewidth',2);
xlabel('Iteration');
ylabel('Function Value');
%% Run 4 - FR
step_size = 0.25;
diff_type = 'central';

[xnew,fevals,k] = FletcherReeves2(x,diff_type,step_size);
xnew, fevals(end), k

figure;
plot(1:length(fevals),fevals,'linewidth',2);
xlabel('Iteration');
ylabel('Function Value');
%% Run 5 - FR
step_size = 0.025;
diff_type = 'central';

[xnew,fevals,k] = FletcherReeves2(x,diff_type,step_size);
xnew, fevals(end), k

figure;
plot(1:length(fevals),fevals,'linewidth',2);
xlabel('Iteration');
ylabel('Function Value');
%% Run 6 - FR
step_size = 0.0025;
diff_type = 'central';

[xnew,fevals,k] = FletcherReeves2(x,diff_type,step_size);
xnew, fevals(end), k

figure;
plot(1:length(fevals),fevals,'linewidth',2);
xlabel('Iteration');
ylabel('Function Value');
%% Run 1 - BFGS
step_size = 0.25;
diff_type = 'forward';

[xnew,fevals,k] = BFGS(x,diff_type,step_size);
xnew, fevals, k

length(fevals)
figure;
plot(k,fevals,'linewidth',2);
xlabel('Iteration');
ylabel('Function Value');
%% Run 2 - BFGS
step_size = 0.025;
diff_type = 'forward';

[xnew,fevals,k] = BFGS(x,diff_type,step_size);
xnew, fevals(end), k

figure;
plot(1:length(fevals),fevals,'linewidth',2);
xlabel('Iteration');
ylabel('Function Value');
%% Run 3 - BFGS
step_size = 0.0025;
diff_type = 'forward';

[xnew,fevals,k] = BFGS(x,diff_type,step_size);
xnew, fevals(end), k

figure;
plot(1:length(fevals),fevals,'linewidth',2);
xlabel('Iteration');
ylabel('Function Value');
%% Run 4 - BFGS
step_size = 0.25;
diff_type = 'central';

[xnew,fevals,k] = BFGS(x,diff_type,step_size);
xnew, fevals(end), k

figure;
plot(1:length(fevals),fevals,'linewidth',2);
xlabel('Iteration');
ylabel('Function Value');
%% Run 5 - BFGS
step_size = 0.025;
diff_type = 'central';

[xnew,fevals,k] = BFGS(x,diff_type,step_size);
xnew, fevals(end), k

figure;
plot(1:length(fevals),fevals,'linewidth',2);
xlabel('Iteration');
ylabel('Function Value');
%% Run 6 - BFGS
step_size = 0.0025;
diff_type = 'central';

[xnew,fevals,k] = BFGS(x,diff_type,step_size);
xnew, fevals(end), k

figure;
plot(1:length(fevals),fevals,'linewidth',2);
xlabel('Iteration');
ylabel('Function Value');