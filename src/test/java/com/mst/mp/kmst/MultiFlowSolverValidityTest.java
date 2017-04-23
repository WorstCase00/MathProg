package com.mst.mp.kmst;

import com.mst.mp.kmst.solver.MultiFlowSolver;

public class MultiFlowSolverValidityTest extends BaseValidityTest {

	@Override
	protected Solver createSubject() {
		return new MultiFlowSolver();
	}
}
