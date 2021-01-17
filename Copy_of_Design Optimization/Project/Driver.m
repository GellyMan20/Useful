%MAE 531 - Final Project
%Alex Gellios
%things that need to be returned:
%List of function with all constraints
clear all; close all; clc;

%% List of all contraints with main function

%initial point
x = [2.6 .7 28 8.3 7.3 2.9 5];

Lb = [2.6 .7 17 7.3 7.3 2.9 5.0];
Ub = [3.6 .8 28 8.3 8.3 3.9 5.5];

w1 = linspace(0,1,8);

%xnew = fmincon('objfun_multi',x,[],[],[],[],Lb,Ub,'nonlcon')
%[f1,f2]=objfun_multi(xnew)

%tmp=fmincon('objfun_WeightMulti',x,[],[],[],[],Lb,Ub,'nonlcon')
%[F,f1,f2]=objfun_WeightMulti(tmp)

%Final = [tmp,F,f1,f2];

F = FinalProject(:,11)
f1 = FinalProject(:,12)
f2 = FinalProject(:,13)

figure;
plot(f1,f2,'*','linewidth', 4)
xlabel('Objective Function 1'); ylabel('Objective Function 2');
title('Pareto Frontier using Weighted Sum Technique');