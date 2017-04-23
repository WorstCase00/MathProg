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

public class SingleFlowCplexVariables extends BaseFlowCplexVariables {

	private static final String FLOW_VAR_TEMPLATE = "F_{%d, %d}";
	private static final int SOURCE = 0;
	private static final int TARGET = 1;
	private final Map<List<Integer>, IloNumVar> flowVariables;

	public SingleFlowCplexVariables(
			Map<Edge, IloIntVar> selectionVariables,
			Map<List<Integer>, IloNumVar> flowVariables,
			Map<Integer, IloIntVar> nodeVariables) {
		super(selectionVariables, nodeVariables, flowVariables);
		this.flowVariables = flowVariables;
	}

	public static SingleFlowCplexVariables create(IloCplex cplex, Problem problem) throws IloException {
		return new SingleFlowCplexVariables(
				createSelectionVariables(cplex, problem),
				createFlowVariables(cplex, problem),
				createNodeDecisionVariables(cplex, problem));
	}

	private static Map<List<Integer>, IloNumVar> createFlowVariables(IloCplex cplex, Problem problem) throws IloException {
		Map<List<Integer>, IloNumVar> variables = Maps.newHashMap();
		for (Edge edge : problem.getEdges()) {
			IloNumVar var = cplex.numVar(0,
					problem.getNodes(),
					String.format(FLOW_VAR_TEMPLATE, edge.getSource(), edge.getTarget()));
			variables.put(Lists.newArrayList(edge.getSource(), edge.getTarget()), var);
			cplex.add(var);
			if (edge.getSource() != 0) {
				IloNumVar var2 = cplex.numVar(0d,
						problem.getNodes(),
						String.format(FLOW_VAR_TEMPLATE, edge.getTarget(), edge.getSource()));
				variables.put(Lists.newArrayList(edge.getTarget(), edge.getSource()), var2);
				cplex.add(var2);
			}
		}
		return variables;
	}

	public IloNumVar[] getOutgoingFlowVars(int v) {
		return  flowVariables.entrySet().stream()
				.filter(e -> e.getKey().get(SOURCE) == v)
				.map(e -> e.getValue())
				.toArray(size -> new IloNumVar[size]);
	}

	public IloNumVar[] getIncomingFlowVars(int v) {
		return flowVariables.entrySet().stream()
				.filter(e -> e.getKey().get(TARGET) == v)
				.map(e -> e.getValue())
				.toArray(size -> new IloNumVar[size]);
	}

	public IloNumVar[] getFlowVariables(Edge edge) {
		return flowVariables.entrySet().stream()
				.filter(e -> (e.getKey().get(SOURCE) == edge.getSource() && e.getKey().get(TARGET) == edge.getTarget())
				|| (e.getKey().get(SOURCE) == edge.getTarget() && e.getKey().get(TARGET) == edge.getSource()))
				.map(e -> e.getValue())
				.toArray(size -> new IloNumVar[size]);
	}

}
