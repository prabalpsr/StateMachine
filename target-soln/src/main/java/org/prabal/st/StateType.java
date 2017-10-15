package org.prabal.st;


//defining state types as you can only move from Initial or Transitional states
//we could eliminate this but it adds more meaning and while looking at the FSM
public enum StateType
{
	INITIAL,
	TRANSITIONAL,
	TERMINAL;
}