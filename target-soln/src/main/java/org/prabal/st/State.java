package org.prabal.st;


import java.util.ArrayList;
import java.util.List;

//The class defines the States in FSM
public class State {

	//State
	private String stateName;
	
	//State type: can not continue after reaching a Terminal State
	private StateType stateType;
	
	//Defines the available paths to move to a different state
	private List<Edge> edges;
	
	//Stores the last path that was used
	private Integer currentEdgeCount;
	
	public State(String stateName, StateType stateType) {
		this.stateName = stateName;
		this.stateType = stateType;
		this.currentEdgeCount =-1;
		this.edges = new ArrayList<Edge>();
	}

	
	public Boolean isTerminal()
	{
		if(stateType == StateType.TERMINAL) return true;
		
		return false;
	}
	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public StateType getStateType() {
		return stateType;
	}

	public void setStateType(StateType stateType) {
		this.stateType = stateType;
	}


	//retrieves the Edge object by edge Name
	public Edge getEdge(String edgeName) {
		
		return edges.stream().
				filter(c->c.getEdgeName().equals(edgeName)).
				findFirst().
				orElse(null);
		//TODO Populate
	}
	
	
	//Adds a new Edge (with location of destination State) in the current State object
	public Boolean addNewEdge(String edgeName, Integer stateLocation)
	{
		if(edgeName == null || edgeName.isEmpty())
			return false;
		
		Edge edge = new Edge(edgeName, stateLocation);
		
		return addNewEdge(edge);
	}
	
	public Boolean addNewEdge(Edge edge)
	{
		if(edge == null)
			return false;
		
		Edge edge2 = getEdge(edge.getEdgeName());
		
		if(edge2==null || 
		  (edge2 != null && !edge2.equals(edge))){
			edges.add(edge);
		}	
		else return false;
		
		return true;
	}

	// NOT USED Functions for future

	//gives the # of edges that the State has 
	//what all State can current state transition to
	//could be used instead of StateType to identify Terminal State
	public Integer getCountOfNodes() {
		return edges.size();
	}

	// The method supports the iteration of different edges from this State
	// use with edges.size as it will keep on cycling
	public Edge getNextEdge() {
		// restart the counter when it reaches end
		if (currentEdgeCount >= edges.size())
			currentEdgeCount = 0;

		Edge edge = edges.get(currentEdgeCount);
		currentEdgeCount++;

		return edge;
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stateName == null) ? 0 : stateName.hashCode());
		result = prime * result + ((stateType == null) ? 0 : stateType.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (stateName == null) {
			if (other.stateName != null)
				return false;
		} else if (!stateName.equals(other.stateName))
			return false;
		if (stateType != other.stateType)
			return false;
		return true;
	}
	
}