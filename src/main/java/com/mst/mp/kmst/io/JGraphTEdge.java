package com.mst.mp.kmst.io;

import com.mst.mp.kmst.Edge;
import org.jgrapht.graph.DefaultWeightedEdge;

public class JGraphTEdge extends DefaultWeightedEdge {

	private final Edge wrapped;
	private final double weight;

	public JGraphTEdge(Edge wrapped, double weight) {
		this.wrapped = wrapped;
		this.weight = weight;
	}

	protected double getWeight() {
		return weight;
	}

	public Edge getEdge() {
		return wrapped;
	}
}
