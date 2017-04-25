package com.mst.mp.kmst.io;

import com.google.common.collect.Sets;
import com.mst.mp.kmst.Edge;
import com.mst.mp.kmst.EdgeImpl;
import com.mst.mp.kmst.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.graph.builder.UndirectedWeightedGraphBuilderBase;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GraphJGraphTImpl implements Graph {
	private final UndirectedGraph<Integer, JGraphTEdge> graph;

	public GraphJGraphTImpl(UndirectedGraph<Integer, JGraphTEdge> graph) {
		this.graph = graph;
	}

	@Override
	public int getNodes() {
		return graph.vertexSet().size();
	}

	@Override
	public int getEdgeCount() {
		return graph.edgeSet().size();
	}

	@Override
	public Set<Integer> getNeighbors(int v) {
		return Sets.newHashSet(Graphs.neighborListOf(graph, v));
	}

	@Override
	public double getDistance(int v1, int v2) {
		return graph.getEdgeWeight(graph.getEdge(v1, v2));
	}

	@Override
	public Set<Edge> getEdges() {
		return graph.edgeSet().stream().map(e -> e.getEdge()).collect(Collectors.toSet());
	}

	@Override
	public double getWeight(Edge edge) {
		return graph.edgeSet().stream()
				.filter(e -> e.getEdge().equals(edge))
				.findFirst().get().getWeight();
	}

	@Override
	public double getWeightSum() {
		return graph.edgeSet().stream()
				.map(e -> e.getWeight())
				.reduce((w1, w2) -> w1 + w2).orElse(0d);
	}

	public static Builder builder(int nodes, int edges) {
		return new Builder(nodes);
	}

	public static class Builder {
		private final UndirectedWeightedGraphBuilderBase<
				Integer,
				JGraphTEdge,
				? extends SimpleWeightedGraph<Integer, JGraphTEdge>, ?> graph;

		Builder(int nodes) {
			graph = SimpleWeightedGraph.builder(JGraphTEdge.class);
			graph.addVertices(IntStream.range(0, nodes).boxed().toArray(Integer[]::new));
		}

		public GraphJGraphTImpl build() {
			return new GraphJGraphTImpl(graph.buildUnmodifiable());
		}

		public void addEdge(int v1, int v2, double weight) {
			graph.addEdge(v1, v2, new JGraphTEdge(new EdgeImpl(v1, v2), weight), weight);
		}
	}
}
