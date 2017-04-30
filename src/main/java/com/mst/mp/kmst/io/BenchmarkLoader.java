package com.mst.mp.kmst.io;

import com.mst.mp.kmst.DefaultProblem;
import com.mst.mp.kmst.experiment.BenchmarkInstance;
import com.mst.mp.kmst.experiment.OptimalSolutions;

import java.io.IOException;

public class BenchmarkLoader {

	private static final GraphLoader GRAPH_LOADER = new GraphLoader();

	public static BenchmarkInstance getInstance01A() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(0);
		return load(key, OptimalSolutions.getLoK(key));
	}

	public static BenchmarkInstance getInstance02A() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(1);
		return load(key, OptimalSolutions.getLoK(key));
	}

	public static BenchmarkInstance getInstance03A() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(2);
		return load(key, OptimalSolutions.getLoK(key));
	}

	public static BenchmarkInstance getInstance04A() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(3);
		return load(key, OptimalSolutions.getLoK(key));
	}

	public static BenchmarkInstance getInstance05A() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(4);
		return load(key, OptimalSolutions.getLoK(key));
	}

	public static BenchmarkInstance getInstance06A() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(5);
		return load(key, OptimalSolutions.getLoK(key));
	}

	public static BenchmarkInstance getInstance07A() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(6);
		return load(key, OptimalSolutions.getLoK(key));
	}

	public static BenchmarkInstance getInstance08A() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(7);
		return load(key, OptimalSolutions.getLoK(key));
	}

	public static BenchmarkInstance getInstance01B() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(0);
		return load(key, OptimalSolutions.getHiK(key));
	}

	public static BenchmarkInstance getInstance02B() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(1);
		return load(key, OptimalSolutions.getHiK(key));
	}

	public static BenchmarkInstance getInstance03B() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(2);
		return load(key, OptimalSolutions.getHiK(key));
	}

	public static BenchmarkInstance getInstance04B() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(3);
		return load(key, OptimalSolutions.getHiK(key));
	}

	public static BenchmarkInstance getInstance05B() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(4);
		return load(key, OptimalSolutions.getHiK(key));
	}

	public static BenchmarkInstance getInstance06B() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(5);
		return load(key, OptimalSolutions.getHiK(key));
	}

	public static BenchmarkInstance getInstance07B() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(6);
		return load(key, OptimalSolutions.getHiK(key));
	}

	public static BenchmarkInstance getInstance08B() throws IOException {
		String key = OptimalSolutions.getOrderedInstanceKeys().get(7);
		return load(key, OptimalSolutions.getHiK(key));
	}

	private static BenchmarkInstance load(String key, int k) throws IOException {
		return new BenchmarkInstance(
				key,
				new DefaultProblem(
						GRAPH_LOADER.loadInstanceKey(key),
						k),
				OptimalSolutions.getValue(key, k));
	}

	public static BenchmarkInstance getLoInstance(String instanceKey) throws IOException {
		return load(instanceKey, OptimalSolutions.getLoK(instanceKey));
	}

	public static BenchmarkInstance getHiInstance(String instanceKey) throws IOException {
		return load(instanceKey, OptimalSolutions.getLoK(instanceKey));
	}
}
