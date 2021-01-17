function [g] = objfun_multi(x)

f1 = objfun_SpeedReducer(x);
f2 = objfun_SpeedReducer2(x);

g = [f1,f2]

end

