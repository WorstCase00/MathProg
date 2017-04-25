package com.mst.mp.kmst.solver;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mst.mp.kmst.Edge;
import com.mst.mp.kmst.Problem;
import ilog.concert.IloException;
import ilog.concert.IloIntVar;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

import java.util.List;
import java.util.Map;

public class MultiFlowCplexVariables extends BaseFlowCplexVariables {

	private static final String FLOW_VAR_TEMPLATE = "F_{%d, %d, %d}";
	private static final int SOURCE = 0;
	private static final int TARGET = 1;
	private static final int K = 2;

	public MultiFlowCplexVariables(Map<Edge, IloIntVar> selectionVariables, Map<List<Integer>, IloNumVar> flowVariables, Map<Integer, IloIntVar> nodeVariables) {
		super(selectionVariables, nodeVariables, flowVariables);
	}

	public static MultiFlowCplexVariables create(IloCplex cplex, Problem problem) throws IloException {
		Map<Edge, IloIntVar> selectionVars = createSelectionVariables(cplex, problem);
		return new MultiFlowCplexVariables(selectionVars, createFlowVariables(cplex, problem), createNodeDecisionVariables(cplex, problem));
	}

	private static Map<List<Integer>, IloNumVar> createFlowVariables(IloCplex cplex, Problem problem) throws IloException {
		Map<List<Integer>, IloNumVar> variables = Maps.newHashMap();
		for (int k = 1; k < problem.getNodes(); k++) {
			for (Edge edge : problem.getEdges()) {
				IloNumVar var = cplex.numVar(0d,
						1d,
						String.format(FLOW_VAR_TEMPLATE, edge.getSource(), edge.getTarget(), k));
				variables.put(Lists.newArrayList(edge.getSource(), edge.getTarget(), k), var);
				cplex.add(var);
				if (edge.getSource() != 0) {
					IloNumVar var2 = cplex.numVar(0d,
							1d,
							String.format(FLOW_VAR_TEMPLATE, edge.getTarget(), edge.getSource(), k));
					variables.put(Lists.newArrayList(edge.getTarget(), edge.getSource(), k), var2);
					cplex.add(var2);
				}
			}
		}
		return variables;
	}

	public IloNumVar[] getFlowVarsForEdge(Edge edge) {
		return flowVariables.entrySet().stream()
				.filter(entry -> (entry.getKey().get(SOURCE) == edge.getSource() && entry.getKey().get(TARGET) == edge.getTarget())
						|| (entry.getKey().get(TARGET) == edge.getSource() && entry.getKey().get(SOURCE) == edge.getTarget())
				)
				.map(entry -> entry.getValue())
				.toArray(size -> new IloNumVar[size]);
	}

	public IloNumVar getFlowVariable(int i, int j, int k) {
		return flowVariables.get(Lists.newArrayList(i, j, k));
	}

	public IloNumVar[] getIncomingFlow(int i, int k) {
		return flowVariables.entrySet().stream()
				.filter(entry -> (entry.getKey().get(K) == k && entry.getKey().get(TARGET) == i))
				.map(entry -> entry.getValue())
				.toArray(size -> new IloNumVar[size]);
	}

	public IloNumVar[] getOutgoingFlow(int i, int k) {
		return flowVariables.entrySet().stream()
				.filter(entry -> (entry.getKey().get(K) == k && entry.getKey().get(SOURCE) == i))
				.map(entry -> entry.getValue())
				.toArray(size -> new IloNumVar[size]);
	}

}
