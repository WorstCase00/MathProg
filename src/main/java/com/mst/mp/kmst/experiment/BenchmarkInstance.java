package com.mst.mp.kmst.experiment;

import com.mst.mp.kmst.Problem;

public class BenchmarkInstance {
	
	private final String key;
	private final Problem problem;
	private final double solution;

	public BenchmarkInstance(String key, Problem problem, double solution) {
		this.key = key;
		this.problem = problem;
		this.solution = solution;
	}

	public String getKey() {
		return key;
	}

	public Problem getProblem() {
		return problem;
	}

	public double getSolutionValue() {
		return solution;
	}
}
