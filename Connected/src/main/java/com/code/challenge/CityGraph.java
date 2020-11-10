package com.code.challenge;

import java.util.HashMap;
import java.util.LinkedList;

public class CityGraph {
	
	
	private CityNode destCityNode = null;
	
	public CityGraph() {
		adjacencyCityMap = new HashMap<>();
	}
	private HashMap<CityNode, LinkedList<CityNode>> adjacencyCityMap;
	
	public void addCityEdgeHelper(CityNode a, CityNode b) {
		LinkedList<CityNode> tmp = adjacencyCityMap.get(a);
		if (tmp != null) {
			tmp.remove(b);
		} else {
			tmp = new LinkedList<>();
		}
		tmp.add(b);
		adjacencyCityMap.put(a,tmp);
	}
	
	public void addCityEdge(CityNode source, CityNode destination) {
		if (!adjacencyCityMap.keySet().contains(source)) {
			adjacencyCityMap.put(source, null);
		}
		if (!adjacencyCityMap.keySet().contains(destination)) {
			adjacencyCityMap.put(destination, null);
		}
		addCityEdgeHelper(source, destination);
		addCityEdgeHelper(destination, source);
	}
	
	public boolean searchDestinationUsingDfsAlg(CityNode source) {
		source.setVisited(true);
		LinkedList<CityNode> allSrcConnectedCities = adjacencyCityMap.get(source);
		if (allSrcConnectedCities == null || allSrcConnectedCities.size() == 0) {
			return false;
		}
		for (CityNode adjCity: allSrcConnectedCities) {
			if (adjCity.equals(destCityNode)) {
				return true;
			} else if (!adjCity.isVisited()) {
				searchDestinationUsingDfsAlg(adjCity);
			}
		}
		return false;
	}
	
	/**
	 * @return the destCityNode
	 */
	public CityNode getDestCityNode() {
		return destCityNode;
	}

	/**
	 * @param destCityNode the destCityNode to set
	 */
	public void setDestCityNode(CityNode destCityNode) {
		this.destCityNode = destCityNode;
	}
}
 