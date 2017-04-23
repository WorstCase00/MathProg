package com.mst.mp.kmst;

import com.mst.mp.kmst.solver.SingleFlowSolver;

public class SingleFlowSolverIntegrationTest extends BaseSolverIntegrationTest {

	protected Solver createSubject() {
		return new SingleFlowSolver();
	}

}
