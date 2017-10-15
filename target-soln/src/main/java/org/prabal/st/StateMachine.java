package org.prabal.st;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StateMachine {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		FiniteStateMachine finiteStateMachine = new FiniteStateMachine();
		InitiatlizeFSM(finiteStateMachine);
		populateStateMachine(finiteStateMachine);
		
		try(BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in)))
		{
		
		String input = "";
		
		System.out.println("Please enter initial State >");
		input =inputReader.readLine();
		
		if(!finiteStateMachine.isValidStateName(input)) { 
			System.out.println("Sorry entered invalid State. Please try again");
			return;
		}
		
		finiteStateMachine.setActiveState(input);
		System.out.println("<" + finiteStateMachine.getActiveState());
		
		if(finiteStateMachine.isCurrentStateTerminal()) {
			System.out.println("The transition has reached Terminal State");
			return;
		}
		
		while(true)
		{
			System.out.println(">");
			input =inputReader.readLine();
			
			if(!finiteStateMachine.isValidEdgeName(input)) { 
				System.out.println("Invalid transition value entered. Please enter again");
				continue;
			}
			
			if(!finiteStateMachine.ChangeCurrentState(input)) {
				System.out.println("This state transition is not possible. Please enter again");
				continue;
			}
			System.out.println("<" + finiteStateMachine.getActiveState());
			
			if(finiteStateMachine.isCurrentStateTerminal()) {
				System.out.println("The transition has reached Terminal State");
				break;
			}
		}
		}
		catch(Exception ex) {
			System.out.println("There is some exception in the application execution. Please contact Prabal for details");
		}
		
		
	}

	//Populate Finite State Machine
	//Populate Finite State Machine
	public static void InitiatlizeFSM(FiniteStateMachine finiteStateMachine)
	{
		//Define the possible States that the FSM can take
		finiteStateMachine.addValidState("0S");
		finiteStateMachine.addValidState("1S");
		finiteStateMachine.addValidState("2S");
		finiteStateMachine.addValidState("3S");
		finiteStateMachine.addValidState("4S");
		finiteStateMachine.addValidState("5S");
		finiteStateMachine.addValidState("CANCELLED");
		finiteStateMachine.addValidState("COMPLETED");
		
		//Defines the possible edges/transitions that can be assigned to the State
		finiteStateMachine.addValidEdgeName("1R");
		finiteStateMachine.addValidEdgeName("2R");
		finiteStateMachine.addValidEdgeName("CANCEL");
		finiteStateMachine.addValidEdgeName("BUY");
		
	}

	private static void populateStateMachine(FiniteStateMachine finiteStateMachine) {
		//Create FSM
		//States in FSM
		finiteStateMachine.addState("0S", StateType.INITIAL);
		finiteStateMachine.addState("1S", StateType.TRANSITIONAL);
		finiteStateMachine.addState("2S", StateType.TRANSITIONAL);
		finiteStateMachine.addState("3S", StateType.TRANSITIONAL);
		finiteStateMachine.addState("4S", StateType.TRANSITIONAL);
		finiteStateMachine.addState("CANCELLED", StateType.TERMINAL);
		finiteStateMachine.addState("COMPLETED", StateType.TERMINAL);
		
		//State Transition
		finiteStateMachine.addEdge("0S", "1S", "1R");
		finiteStateMachine.addEdge("0S", "2S", "2R");
		finiteStateMachine.addEdge("1S", "2S", "1R");
		finiteStateMachine.addEdge("1S", "3S", "2R");
		finiteStateMachine.addEdge("2S", "3S", "1R");
		finiteStateMachine.addEdge("2S", "4S", "2R");
		finiteStateMachine.addEdge("3S", "4S", "1R");
		finiteStateMachine.addEdge("4S", "COMPLETED", "BUY");
		finiteStateMachine.addEdge("0S", "CANCELLED", "CANCEL");
		finiteStateMachine.addEdge("1S", "CANCELLED", "CANCEL");
		finiteStateMachine.addEdge("2S", "CANCELLED", "CANCEL");
		finiteStateMachine.addEdge("3S", "CANCELLED", "CANCEL");
		finiteStateMachine.addEdge("4S", "CANCELLED", "CANCEL");
	}
}