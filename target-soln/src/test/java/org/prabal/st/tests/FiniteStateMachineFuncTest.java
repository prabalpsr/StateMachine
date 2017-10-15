package org.prabal.st.tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.prabal.st.FiniteStateMachine;
import org.prabal.st.StateType;

public class FiniteStateMachineFuncTest {
	FiniteStateMachine finiteStateMachine;

	
	@Before
	public void setup()
	{
		finiteStateMachine = new FiniteStateMachine();
		
		//Populate valid states that are allowed in the STM
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
	
	@Test
	public void testBuyFromInitialState() {
		finiteStateMachine.setActiveState("0S");
		finiteStateMachine.ChangeCurrentState("1R");
		finiteStateMachine.ChangeCurrentState("2R");
		finiteStateMachine.ChangeCurrentState("1R");
		finiteStateMachine.ChangeCurrentState("BUY");
		
		assertEquals(finiteStateMachine.getActiveState(), "COMPLETED");
	}

	@Test
	public void testCancelledFromIntermediateState() {
		finiteStateMachine.setActiveState("0S");
		finiteStateMachine.ChangeCurrentState("2R");
		finiteStateMachine.ChangeCurrentState("CANCEL");
		assertEquals(finiteStateMachine.getActiveState(), "CANCELLED");
	}

	@Test
	public void testNoTransitonFromTerminalState() {
		finiteStateMachine.setActiveState("0S");
		finiteStateMachine.ChangeCurrentState("1R");
		finiteStateMachine.ChangeCurrentState("CANCEL");
		
		finiteStateMachine.ChangeCurrentState("1R");
		finiteStateMachine.ChangeCurrentState("2R");
		finiteStateMachine.ChangeCurrentState("3R");
		finiteStateMachine.ChangeCurrentState("BUY");
		assertEquals(finiteStateMachine.getActiveState(), "CANCELLED");
		
	}

	@Test
	public void testNoCertainTerminalStateFromIntermediate() {
		finiteStateMachine.setActiveState("0S");
		finiteStateMachine.ChangeCurrentState("1R");
		finiteStateMachine.ChangeCurrentState("BUY");
		assertEquals(finiteStateMachine.getActiveState(), "1S");
		
		finiteStateMachine.ChangeCurrentState("1R");
		finiteStateMachine.ChangeCurrentState("BUY");
		assertEquals(finiteStateMachine.getActiveState(), "2S");
		
		finiteStateMachine.ChangeCurrentState("1R");
		finiteStateMachine.ChangeCurrentState("BUY");
		assertEquals(finiteStateMachine.getActiveState(), "3S");
		
	}
}
