package com.mst.mp.kmst.solver;

import com.mst.mp.kmst.Edge;
import com.mst.mp.kmst.Problem;
import ilog.concert.IloException;
import ilog.cplex.IloCplex;

public class SingleFlowSolver extends BaseSolver<SingleFlowCplexVariables> {

	@Override
	protected SingleFlowCplexVariables createModel(Problem problem, IloCplex cplex) throws IloException {
		SingleFlowCplexVariables model = SingleFlowCplexVariables.create(cplex, problem);
		addObjectiveFunction(model, problem, cplex);
		addSelectionEqualities(model, problem, cplex);
		addFlowSupplyConstraint(model, cplex, problem);
		addFlowConsumptionConstraints(model, cplex, problem);
		addLinkingConstraints(model, cplex, problem);
		return model;
	}

	private void addLinkingConstraints(SingleFlowCplexVariables model, IloCplex cplex, Problem problem) throws IloException {
		for (Edge edge : problem.getEdges()) {
			cplex.addLe(
					cplex.sum(model.getFlowVariables(edge)),
					cplex.prod(problem.getK(), model.getBinaryEdgeVariable(edge))
			);
		}
	}

	private void addFlowSupplyConstraint(SingleFlowCplexVariables model, IloCplex cplex, Problem problem) throws IloException {
		cplex.addEq(problem.getK(), cplex.sum(model.getOutgoingFlowVars(0)));
	}

	private void addFlowConsumptionConstraints(SingleFlowCplexVariables model, IloCplex cplex, Problem problem) throws IloException {
		for (int i = 1; i < problem.getNodes(); i++) {
			cplex.addEq(
					cplex.diff(
							cplex.sum(model.getIncomingFlowVars(i)),
							cplex.sum(model.getOutgoingFlowVars(i))
					),
					model.getNodeVariable(i)
			);
		}
	}

}
