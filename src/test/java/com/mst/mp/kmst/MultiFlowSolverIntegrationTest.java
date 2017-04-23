package com.mst.mp.kmst;

import com.mst.mp.kmst.solver.MultiFlowSolver;

public class MultiFlowSolverIntegrationTest extends BaseSolverIntegrationTest {

	protected Solver createSubject() {
		return new MultiFlowSolver();
	}

}
