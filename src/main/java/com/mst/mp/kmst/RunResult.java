package com.mst.mp.kmst;

public interface RunResult {

	double getObjectiveValue();

	long getRunningTimeMs();

	long getNumberOfBranchAndBoundNodes();

	long getNumberOfViolatedInequalities();

	Problem getProblem();

	Solution getSolution();
}
