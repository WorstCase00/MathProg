package com.mst.mp.kmst.experiment;

import com.google.common.collect.Lists;
import com.mst.mp.kmst.RunResult;
import com.mst.mp.kmst.Solver;
import com.mst.mp.kmst.io.BenchmarkLoader;
import com.mst.mp.kmst.solver.MultiFlowSolver;
import com.mst.mp.kmst.solver.SingleFlowSolver;

import java.io.IOException;
import java.util.List;

public class Experiment {

	private static final String ROW_TEMPLATE =
			"%s & %f & %d & %d & %f & %d & %d \\\\";

	private static final List<Solver> SOLVERS = Lists.newArrayList(
			SingleFlowSolver.create(),
			MultiFlowSolver.create()
	);

	private static BenchmarkLoader LOADER = new BenchmarkLoader();


	public static void main(String ... args) throws IOException {
		List<RunResult> results = Lists.newArrayList();
		for (String instanceKey : OptimalSolutions.getOrderedInstanceKeys()) {
			BenchmarkInstance loInstance = LOADER.getLoInstance(instanceKey);
			for (Solver solver : SOLVERS) {
				RunResult runResult = solver.solve(loInstance.getProblem());
				results.add(runResult);
				System.out.println(runResult);
				System.out.println(toLatexString(results));
			}
		}
	}

	private static String toLatexString(List<RunResult> results) {
		StringBuilder latex = new StringBuilder();
		for (int i = 0; i < results.size() / 2; i++) {
			if (results.size() == 2*i + 1) {

			} else {
				latex.append(
						String.format(ROW_TEMPLATE,
								"instance",
								results.get(i * 2).getObjectiveValue(),
								results.get(i * 2).getRunningTimeMs() / 1000,
								results.get(i * 2).getNumberOfBranchAndBoundNodes(),
								results.get(i * 2 + 1).getObjectiveValue(),
								results.get(i * 2 + 1).getRunningTimeMs() / 1000,
								results.get(i * 2 + 1).getNumberOfBranchAndBoundNodes()
				)).append("\n");
			}
		}
		return latex.toString();
	}

}
