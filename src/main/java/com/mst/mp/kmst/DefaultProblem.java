package com.mst.mp.kmst;

import com.google.common.base.Preconditions;

import java.util.Set;

public class DefaultProblem implements Problem {

	private final Graph graph;
	private final int k;

	public DefaultProblem(Graph graph, int k) {
		Preconditions.checkArgument(graph.getNodes() >= k);
		this.graph = graph;
		this.k = k;
	}

	@Override
	public int getK() {
		return k;
	}

	@Override
	public int getNodes() {
		return graph.getNodes();
	}

	@Override
	public int getEdgeCount() {
		return graph.getEdgeCount();
	}

	@Override
	public Set<Integer> getNeighbors(int v) {
		return graph.getNeighbors(v);
	}

	@Override
	public double getDistance(int v1, int v2) {
		return graph.getDistance(v1, v2);
	}

	@Override
	public Set<Edge> getEdges() {
		return graph.getEdges();
	}

	@Override
	public double getWeight(Edge e) {
		return graph.getWeight(e);
	}
}
