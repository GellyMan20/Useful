function [ u1, u2 ] = controller(~, state, des_state, params)
%CONTROLLER  Controller for the planar quadrotor
%
%   state: The current state of the robot with the following fields:
%   state.pos = [y; z], state.vel = [y_dot; z_dot], state.rot = [phi],
%   state.omega = [phi_dot]
%
%   des_state: The desired states are:
%   des_state.pos = [y; z], des_state.vel = [y_dot; z_dot], des_state.acc =
%   [y_ddot; z_ddot]
%
%   params: robot parameters

%   Using these current and desired states, you have to compute the desired
%   controls

u1 = 0;
u2 = 0;

% FILL IN YOUR CODE HERE


kp = [50; 100; 7000];
kv = [10; 1500; 70];

%kp = [50; 100; 5000];
%kv = [1.5; 1500; 40];

mass = params.mass;
grav = params.gravity;
phi_dotc = 0;           %angular velocity

e_1 = des_state.pos - state.pos;
e_2 = des_state.vel - state.vel;

phi_c = (-1 / grav) * (des_state.acc(1) + kv(1) * e_2(1) + kp(1) * e_1(1));
u1    = mass * (grav + des_state.acc(2) + kv(2) * e_2(2) + kp(2) * e_1(2));
u2    = params.Ixx * (kv(3) * 0 + kv(3) * (phi_dotc - state.omega) + kp(3) * (phi_c - state.rot));
end

