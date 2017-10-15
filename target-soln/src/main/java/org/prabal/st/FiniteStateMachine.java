package org.prabal.st;


import java.util.ArrayList;
import java.util.List;

public class FiniteStateMachine {

	List<String> validStateList;
	List<String> validEdgeNamesList;
	Integer activeStatePosition;//-1
	//List of states
	List<State> states;
	
	
	public FiniteStateMachine() {
		validStateList = new ArrayList<String>();
		validEdgeNamesList = new ArrayList<String>();
		states = new ArrayList<State>();
		activeStatePosition=-1;
	}

	public Boolean addState(String stateName, StateType stateType) {
		if(stateName == null || stateName.isEmpty()||
				stateType == null)
			return false;
		
		if(!isValidStateName(stateName)) return false;
		
		if(states.stream().filter(c->c.getStateName().equals(stateName)).count()> 0) return false;
		
		states.add(new State(stateName, stateType));
		return true;
	}
	
	public Boolean addEdge(String fromState, String toState, String edgeName) {
	
		if((fromState == null || fromState.isEmpty())||
				(toState == null|| toState.isEmpty())||
				(edgeName == null || edgeName.isEmpty()))
			return false;
		
		//Check if edge is a allowed 
		if(!validEdgeNamesList.stream().anyMatch(c->c.equals(edgeName))) return false;

		//Assuming that the From and to states are valid and if so should be in the States list 
		//if they are not in the list either they are invalid names or they don't exist in the state machine and 
		//hence no transition can exist b/w them so even if the 
		State state = states.stream().filter(c->c.getStateName().equals(fromState)).findFirst().orElse(null);
		if(state == null) return false;
		
		//finding the position of the toState (as we are not storing the object but its position)
		int count;
		for(count=0; count<=states.size()-1;count++ ) {
			if(states.get(count).getStateName().equals(toState)) {
				break;
			}
		}
		
		//if the destination state is not found return out as the edge can't be added
		if(count>=states.size()) return false;
		
		state.addNewEdge(edgeName, count);
		return true;
	}
	
	public Boolean ChangeCurrentState(String edgeName)
	{
		if(!isValidEdgeName(edgeName)) return false;
		
		//TODO break to check for null
		State currentState = states.get(activeStatePosition);
		if(currentState == null) return false;
		
		Edge edge = currentState.getEdge(edgeName);
		if(edge== null) return false;
		
		Integer newStatePosition = edge.getDestinationStatePosition();
		
		if(newStatePosition == -1) return false;
		
		//Change current state to new state
		activeStatePosition = newStatePosition;
		return true;
	}
	
	public Boolean isCurrentStateTerminal()
	{
		if(activeStatePosition>=0 && activeStatePosition < states.size() ) 
		return states.get(activeStatePosition).isTerminal();
		
		return false;
	}
	
	public String getActiveState() {
		if(activeStatePosition>=0 && activeStatePosition < states.size() ) 
		return states.get(activeStatePosition).getStateName();
		
		return "";
	}

	public Boolean setActiveState(String activeStateName) {
		if(activeStateName == null || activeStateName.isEmpty())
			return false;
			
		//find the position of the state in the states list
		int count;
		for(count=0; count<=states.size()-1;count++ ) {
			if(states.get(count).getStateName().equals(activeStateName)) {
				activeStatePosition = count;
				break;
			}
		}
		//no match found
		if(count >= states.size()) return false;
		
		return true;
	}
	
	public Boolean addValidState(String stateName){
		if(stateName == null || stateName.isEmpty())
			return false;
	
		
		return validStateList.add(stateName);
	}
	
	public Boolean isValidStateName(String stateName){
		if(stateName == null || stateName.isEmpty())
			return false;

		return validStateList.stream().anyMatch(c->c.equals(stateName));
	}
	
	public Boolean addValidEdgeName(String edgeName){
		if(edgeName == null || edgeName.isEmpty())
			return false;

		
		return validEdgeNamesList.add(edgeName);
	}
	
	public Boolean isValidEdgeName(String edgeName){
		if(edgeName == null || edgeName.isEmpty())
			return false;
		
		return validEdgeNamesList.stream().anyMatch(c->c.equals(edgeName));
	}
}