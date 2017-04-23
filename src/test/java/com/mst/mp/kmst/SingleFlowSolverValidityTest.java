package com.mst.mp.kmst;

import com.mst.mp.kmst.solver.SingleFlowSolver;

public class SingleFlowSolverValidityTest extends BaseValidityTest {

	@Override
	protected Solver createSubject() {
		return new SingleFlowSolver();
	}
}
