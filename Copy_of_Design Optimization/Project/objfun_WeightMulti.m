function [F,f1,f2] = objfun_WeightMulti(x)

w1 = linspace(0,1,8);
w2 = flip(w1);

f1opt = 2994.3415956776603;
f2opt = 694.7057455595395;

f1 = objfun_SpeedReducer(x);
f2 = objfun_SpeedReducer2(x);

F = w1(8)*(f1/f1opt)+w2(8)*(f2/f2opt);