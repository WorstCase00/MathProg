package com.mst.mp.kmst;

public class RunResultImpl implements RunResult {
	private final Solution solution;
	private final long runningTime;
	private final long numberOfBranchAndBoundNodes;
	private final long numberOfViolatedInequalities;

	private RunResultImpl(
			Solution solution,
			long runningTime,
			long numberOfBranchAndBoundNodes,
			long numberOfViolatedInequalities) {
		this.solution = solution;
		this.runningTime = runningTime;
		this.numberOfBranchAndBoundNodes = numberOfBranchAndBoundNodes;
		this.numberOfViolatedInequalities = numberOfViolatedInequalities;
	}

	@Override
	public double getObjectiveValue() {
		return solution.getCost();
	}

	@Override
	public long getRunningTimeMs() {
		return runningTime;
	}

	@Override
	public long getNumberOfBranchAndBoundNodes() {
		return numberOfBranchAndBoundNodes;
	}

	@Override
	public long getNumberOfViolatedInequalities() {
		return numberOfViolatedInequalities;
	}

	@Override
	public Problem getProblem() {
		return solution.getProblem();
	}

	@Override
	public Solution getSolution() {
		return solution;
	}

	public static RunResultImpl createEmpty(Solution solution) {
		return new RunResultImpl(solution, 0L, 0L, 0L);
	}


	public static RunResultImpl createForStandard(
			Solution solution,
			long runningTime,
			long numberOfBranchAndBoundNodes) {
		return new RunResultImpl(solution, runningTime, numberOfBranchAndBoundNodes, 0L);
	}
}
