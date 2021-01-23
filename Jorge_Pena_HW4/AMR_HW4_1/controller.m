function [ u ] = pd_controller(~, s, s_des, params)
%PD_CONTROLLER  PD controller for the height
%
%   s: 2x1 vector containing the current state [z; v_z]
%   s_des: 2x1 vector containing desired state [z; v_z]
%   params: robot parameters

u = 0;


% FILL IN YOUR CODE HERE
% Tuning
kp = 150;
kv = 20;

pos = s(1);	
vel = s(2);
pos_des = s_des(1);
vel_des = s_des(2);
mass = params.mass;
grav = params.gravity;

e_1 = pos_des - pos;
e_2 = vel_des - vel;

u = mass * (0 + (kp * e_1) + (kv * e_2) + grav);

end

