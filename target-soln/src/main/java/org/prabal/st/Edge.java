package org.prabal.st;
//Defines transition 
public class Edge {

	// Name of the Edge eg 1R, 2R etc
	private String edgeName;

	// The destination state being pointed by this edge
	//Could have implemented a Link to the Object too
	private Integer destSatePosition;

	public Edge(String edgeName, Integer statePosition) {
		this.edgeName = edgeName;
		this.destSatePosition = statePosition;
	}

	//	Setters/getters
	public String getEdgeName() {
		return edgeName;
	}

	public void setEdgeName(String edgeName) {
		this.edgeName = edgeName;
	}

	public Integer getDestinationStatePosition() {
		return destSatePosition;
	}

	public void setDestinationStatePosition(Integer statePosition) {
		this.destSatePosition = statePosition;
	}

	//Hash code and equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((edgeName == null) ? 0 : edgeName.hashCode());
		result = prime * result + ((destSatePosition == null) ? 0 : destSatePosition.hashCode());
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
		Edge other = (Edge) obj;
		if (edgeName == null) {
			if (other.edgeName != null)
				return false;
		} else if (!edgeName.equals(other.edgeName))
			return false;
		if (destSatePosition == null) {
			if (other.destSatePosition != null)
				return false;
		} else if (!destSatePosition.equals(other.destSatePosition))
			return false;
		return true;
	}

}