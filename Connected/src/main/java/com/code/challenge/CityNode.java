package com.code.challenge;

import java.util.Objects;

public class CityNode {
	
	public CityNode ( final String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the visited
	 */
	public boolean isVisited() {
		return visited;
	}
	/**
	 * @param visited the visited to set
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	String name;
	boolean visited;
	

	@Override
	public boolean equals(final Object o) {
		if (this == o )  return true;
		if (o == null || this.getClass() != o.getClass()) return false;
		final CityNode cityNode = (CityNode) o;
		return Objects.equals(this.getName(), cityNode.getName());
	}
	
	@Override
	public int hashCode(){ return Objects.hash(this.getName());}
	
	
}
