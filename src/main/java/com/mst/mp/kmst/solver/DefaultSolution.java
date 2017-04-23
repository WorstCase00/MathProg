package com.mst.mp.kmst.solver;

import com.google.common.collect.Sets;
import com.mst.mp.kmst.Edge;
import com.mst.mp.kmst.Problem;
import com.mst.mp.kmst.Solution;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultSolution implements Solution {

	private final Set<Edge> edges;
	private final Set<Integer> nodes;
	private final Problem problem;

	private DefaultSolution(Set<Edge> edges, Set<Integer> nodes, Problem problem) {
		this.edges = edges;
		this.nodes = nodes;
		this.problem = problem;
	}

	@Override
	public double getCost() {
		return edges.stream().map(e -> problem.getWeight(e)).reduce((d1, d2) -> d1 + d2).orElse(0d);
	}

	@Override
	public Problem getProblem() {
		return problem;
	}

	@Override
	public Set<Edge> getEdges() {
		return edges;
	}

	@Override
	public Set<Integer> getNodes() {
		return nodes;
	}

	public static Solution createEmpty(Problem problem) {
		if (problem.getK() == 0) {
			return new DefaultSolution(Collections.emptySet(), Collections.emptySet(), problem);
		} else {
			return new DefaultSolution(Collections.emptySet(), Sets.newHashSet(1), problem);
		}
	}

	public static Solution create(Set<Edge> edges, Problem problem) {
		Set<Edge> solutionEdges = edges.stream().filter(e -> e.getSource() != 0).collect(Collectors.toSet());
		Set<Integer> solutionNodes = Sets.newHashSet();
		edges.stream().forEach(edge -> {
			solutionNodes.add(edge.getSource());
			solutionNodes.add(edge.getTarget());
		});
		solutionNodes.remove(0);
		return new DefaultSolution(solutionEdges, solutionNodes, problem);
	}
}
