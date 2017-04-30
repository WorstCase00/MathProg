package com.mst.mp.kmst.experiment;

import com.mst.mp.kmst.RunResult;
import com.mst.mp.kmst.Solver;
import com.mst.mp.kmst.io.BenchmarkLoader;
import com.mst.mp.kmst.solver.SingleFlowSolver;

import java.io.IOException;

public class SingleExperiment {

	static final Solver SOLVER = SingleFlowSolver.create();
//	static final Solver SOLVER = MultiFlowSolver.create();
	static BenchmarkInstance INSTANCE;
	static {
		try {
			INSTANCE = BenchmarkLoader.getInstance01A();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String... args) {
		RunResult result = SOLVER.solve(INSTANCE.getProblem());
		System.out.println(result);
	}
}
