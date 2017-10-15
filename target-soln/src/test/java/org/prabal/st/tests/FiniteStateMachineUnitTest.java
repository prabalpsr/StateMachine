package org.prabal.st.tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.prabal.st.FiniteStateMachine;
import org.prabal.st.StateType;

public class FiniteStateMachineUnitTest {
	FiniteStateMachine finiteStateMachine;
	
	@Before
	public void setup() {
		finiteStateMachine = new FiniteStateMachine();
		
	}
	
	
	@Test
	public void testAddState() {
		
		finiteStateMachine.addValidState("0S");
		
		assertFalse(finiteStateMachine.addState(null, StateType.INITIAL));
		assertFalse(finiteStateMachine.addState("Test", StateType.INITIAL));
		assertFalse(finiteStateMachine.addState("Test", null));
		assertTrue(finiteStateMachine.addState("0S", StateType.INITIAL));
	}

	@Test
	public void testAddEdge() {
		finiteStateMachine.addValidEdgeName("1R");
		finiteStateMachine.addValidState("0S");
		finiteStateMachine.addState("0S", StateType.INITIAL);
		
		//-ve test cases
		assertFalse(finiteStateMachine.addEdge(null, "0S", "1R"));
		assertFalse(finiteStateMachine.addEdge("0S", null, "1R"));
		assertFalse(finiteStateMachine.addEdge("0S", "0S", null));
		assertFalse(finiteStateMachine.addEdge("TEST", "0S", "1R"));
		assertFalse(finiteStateMachine.addEdge("0S", "TEST", "1R"));
		assertFalse(finiteStateMachine.addEdge("0S", "0S", "TEST"));
		
		//Positive test case
		assertTrue(finiteStateMachine.addEdge("0S", "0S", "1R"));
		
	}

	@Test
	public void testChangeCurrentState() {
		assertFalse(finiteStateMachine.ChangeCurrentState(null));
		assertFalse(finiteStateMachine.ChangeCurrentState("TEST"));
		
		
		
		finiteStateMachine.addValidEdgeName("1R");
		finiteStateMachine.addValidEdgeName("2R");
		finiteStateMachine.addValidState("0S");
		finiteStateMachine.addValidState("1S");
		finiteStateMachine.addState("0S", StateType.INITIAL);
		finiteStateMachine.addState("1S", StateType.TRANSITIONAL);
		finiteStateMachine.addEdge("0S", "1S", "1R");
		finiteStateMachine.setActiveState("0S");
		
		assertFalse(finiteStateMachine.ChangeCurrentState("2R"));

		
		assertTrue(finiteStateMachine.ChangeCurrentState("1R"));
		assertEquals(finiteStateMachine.getActiveState(), "1S");
		
	}

	@Test
	public void testIsCurrentStateTerminal() {
		
		//-ve test case
		finiteStateMachine.addValidState("0S");
		finiteStateMachine.addState("0S", StateType.INITIAL);
		finiteStateMachine.setActiveState("0S");
		
		assertFalse(finiteStateMachine.isCurrentStateTerminal());

		//+ve test case
		finiteStateMachine.addValidState("BUY");
		finiteStateMachine.addState("BUY", StateType.TERMINAL);
		finiteStateMachine.setActiveState("BUY");

		assertTrue(finiteStateMachine.isCurrentStateTerminal());

		
	}

	@Test
	public void testGetActiveState() {

		assertNotEquals(finiteStateMachine.getActiveState(),"0S");

		
		finiteStateMachine.addValidState("0S");
		finiteStateMachine.addState("0S", StateType.INITIAL);
		finiteStateMachine.setActiveState("0S");

		assertEquals(finiteStateMachine.getActiveState(),"0S");
	}

	@Test
	public void testSetActiveState() {
		assertFalse(finiteStateMachine.setActiveState(null));
		assertFalse(finiteStateMachine.setActiveState("TEST"));
		
		
		finiteStateMachine.addValidState("0S");
		finiteStateMachine.addState("0S", StateType.INITIAL);
		assertFalse(finiteStateMachine.setActiveState("TEST"));

		assertTrue(finiteStateMachine.setActiveState("0S"));

	}

	@Test
	public void testAddValidState() {
		assertFalse(finiteStateMachine.addValidState(null));
		
		assertTrue(finiteStateMachine.addValidState("0S"));
	}

	@Test
	public void testIsValidStateName() {
		finiteStateMachine.addValidState("0S");
		
		assertFalse(finiteStateMachine.isValidStateName(null));
		assertFalse(finiteStateMachine.isValidStateName("TEST"));
		
		
		assertTrue(finiteStateMachine.isValidStateName("0S"));
	}

	@Test
	public void testAddValidEdgeName() {
		assertFalse(finiteStateMachine.addValidState(null));
		
		assertTrue(finiteStateMachine.addValidState("0S"));
	}

	@Test
	public void testIsValidEdgeName() {
		finiteStateMachine.addValidEdgeName("1R");
		
		assertFalse(finiteStateMachine.isValidEdgeName(null));
		assertFalse(finiteStateMachine.isValidEdgeName("TEST"));
		
		
		assertTrue(finiteStateMachine.isValidEdgeName("1R"));
	}
}
