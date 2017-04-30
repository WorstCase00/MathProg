package com.mst.mp.kmst.solver;

import com.mst.mp.kmst.Edge;
import com.mst.mp.kmst.Problem;
import ilog.concert.IloException;
import ilog.cplex.IloCplex;

public class MultiFlowSolver extends BaseSolver<MultiFlowCplexVariables>  {

	@Override
	protected MultiFlowCplexVariables createModel(Problem problem, IloCplex cplex) throws IloException {
		MultiFlowCplexVariables model = MultiFlowCplexVariables.create(cplex, problem);
		addSelectionEqualities(model, problem, cplex);
		addFlowConstraints(model, problem, cplex);
		addFlowEdgeLinking(model, problem, cplex);
		addObjectiveFunction(model, problem, cplex);
		return model;
	}

	private void addFlowConstraints(MultiFlowCplexVariables model, Problem problem, IloCplex cplex) throws IloException {
		addFlowSupplyConstraints(model, problem, cplex);
		addFlowConservationConstraints(model, problem, cplex);
		addFlowConsumptionConstraints(model, problem, cplex);
	}

	private void addFlowConsumptionConstraints(MultiFlowCplexVariables model, Problem problem, IloCplex cplex) throws IloException {
		for (int i = 1; i < problem.getNodes(); i++) {
			cplex.addEq(
					cplex.sum(model.getIncomingFlow(i, i)),
					model.getNodeVariable(i)
			);
		}
	}

	private void addFlowConservationConstraints(MultiFlowCplexVariables model, Problem problem, IloCplex cplex) throws IloException {
		for (int i = 1; i < problem.getNodes(); i++) {
			for (int k = 1; k < problem.getNodes(); k++) {
				if (k == i) {
					continue;
				}
				cplex.addEq(
						cplex.sum(model.getIncomingFlow(i, k)),
						cplex.sum(model.getOutgoingFlow(i, k))
				);
			}

		}
	}

	private void addFlowSupplyConstraints(MultiFlowCplexVariables model, Problem problem, IloCplex cplex) throws IloException {
		for (int k = 1; k < problem.getNodes(); k++) {
			cplex.addEq(
					cplex.sum(model.getOutgoingFlow(0, k)),
					model.getNodeVariable(k)
			);
		}
	}

	private void addFlowEdgeLinking(MultiFlowCplexVariables model, Problem problem, IloCplex cplex) throws IloException {
		for (Edge edge : problem.getEdges()) {
			cplex.addLe(
					cplex.sum(model.getFlowVarsForEdge(edge)),
					cplex.prod((problem.getK() + 1) * 2, model.getBinaryEdgeVariable(edge))
			);
		}
	}

	public static MultiFlowSolver create() {
		return new MultiFlowSolver();
	}
}
