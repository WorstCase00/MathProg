package com.mst.mp.kmst.solver;

import com.mst.mp.kmst.Edge;
import ilog.concert.IloIntVar;

public interface HasBinaryEdgeVariables {
	IloIntVar getBinaryEdgeVariable(Edge edge);

	IloIntVar getBinaryEdgeVariable(int source, int target);

	IloIntVar[] getBinaryEdgeVariables();

	IloIntVar[] getNodeVars();
}
