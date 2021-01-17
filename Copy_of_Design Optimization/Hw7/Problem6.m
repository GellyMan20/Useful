%FEA Project
%Alex Gellios
clc; clear all; close all; 

%% Problem 6
syms a r s u E P real;
format long;
digits(3)

x1=a; x2=-a; x3=-a; x4=a; x5=0; x6=-a; x7=0; x8=a;
y1=a; y2=a; y3=-a; y4=-a; y5=a; y6=0; y7=-a; y8=0;


h5 = .5*(1-r^2)*(1+s);
h6 = .5*(1-s^2)*(1-r);
h7 = .5*(1-r^2)*(1-s);
h8 = .5*(1-s^2)*(1+r);

h1 = .25*(1+r)*(1+s)-.5*h8-.5*h5;
h2 = .25*(1-r)*(1+s)-.5*h5-.5*h6;
h3 = .25*(1-r)*(1-s)-.5*h6-.5*h7;
h4 = .25*(1+r)*(1-s)-.5*h7-.5*h8;

x = x1*h1+x2*h2+x3*h3+x4*h4+x5*h5+x6*h6+x7*h7+x8*h8;
y = y1*h1+y2*h2+y3*h3+y4*h4+y5*h5+y6*h6+y7*h7+y8*h8;

d1=diff(x,r);
d2=diff(y,r);
d3=diff(x,s);
d4=diff(y,s);

J = [d1 d2; d3 d4];
J1 = inv(J);

b11=diff(h1,r);
b13=diff(h2,r);
b15=diff(h3,r);
b17=diff(h4,r);
b19=diff(h5,r);
b111=diff(h6,r);
b113=diff(h7,r);
b115=diff(h8,r);

b22=diff(h1,s);
b24=diff(h2,s);
b26=diff(h3,s);
b28=diff(h4,s);
b210=diff(h5,s);
b212=diff(h6,s);
b214=diff(h7,s);
b216=diff(h8,s);

B = (1/a)*[b11   0    b13   0   b15   0   b17   0   b19    0    b111    0    b113   0     b115    0 ;...
           0    b22    0   b24   0   b26   0   b28   0    b210   0     b212     0    b214    0    b216;...
           b22  b11   b24  b13  b26  b15  b28  b17  b210   b19   b212  b111    b214  b113   b216  b115];
%U=[2*u;-2*u;-2*u;2*u;0;u;0;-u;-2*u;-2*u;2*u;2*u;u;0;-u;0];
U=[2*u;-2*u;-2*u;-2*u;-2*u;2*u;2*u;2*u;0;u;u;0;0;-u;-u;0];

Ep=B*U;
simplify(Ep)
    