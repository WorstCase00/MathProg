package com.mst.mp.kmst.io;

import com.mst.mp.kmst.DefaultProblem;
import com.mst.mp.kmst.experiment.BenchmarkInstance;
import com.mst.mp.kmst.experiment.OptimalSolutions;

import java.io.IOException;

public class BenchmarkLoader {

	private static final GraphLoader GRAPH_LOADER = new GraphLoader();

	public BenchmarkInstance getInstance01A() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(0);
		return runTest(key, OptimalSolutions.getLoK(key));
	}

	public BenchmarkInstance getInstance02A() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(1);
		return runTest(key, OptimalSolutions.getLoK(key));
	}

	public BenchmarkInstance getInstance03A() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(2);
		return runTest(key, OptimalSolutions.getLoK(key));
	}

	public BenchmarkInstance getInstance04A() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(3);
		return runTest(key, OptimalSolutions.getLoK(key));
	}

	public BenchmarkInstance getInstance05A() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(4);
		return runTest(key, OptimalSolutions.getLoK(key));
	}

	public BenchmarkInstance getInstance06A() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(5);
		return runTest(key, OptimalSolutions.getLoK(key));
	}

	public BenchmarkInstance getInstance07A() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(6);
		return runTest(key, OptimalSolutions.getLoK(key));
	}

	public BenchmarkInstance getInstance08A() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(7);
		return runTest(key, OptimalSolutions.getLoK(key));
	}

	public BenchmarkInstance getInstance01B() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(0);
		return runTest(key, OptimalSolutions.getHiK(key));
	}

	public BenchmarkInstance getInstance02B() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(1);
		return runTest(key, OptimalSolutions.getHiK(key));
	}

	public BenchmarkInstance getInstance03B() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(2);
		return runTest(key, OptimalSolutions.getHiK(key));
	}

	public BenchmarkInstance getInstance04B() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(3);
		return runTest(key, OptimalSolutions.getHiK(key));
	}

	public BenchmarkInstance getInstance05B() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(4);
		return runTest(key, OptimalSolutions.getHiK(key));
	}

	public BenchmarkInstance getInstance06B() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(5);
		return runTest(key, OptimalSolutions.getHiK(key));
	}

	public BenchmarkInstance getInstance07B() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(6);
		return runTest(key, OptimalSolutions.getHiK(key));
	}

	public BenchmarkInstance getInstance08B() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(7);
		return runTest(key, OptimalSolutions.getHiK(key));
	}

	private BenchmarkInstance runTest(String key, int k) throws IOException {
		return new BenchmarkInstance(
				key,
				new DefaultProblem(
						GRAPH_LOADER.loadInstanceKey(key),
						k),
				OptimalSolutions.getValue(key, k));
	}
}
