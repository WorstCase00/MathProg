package com.mst.mp.kmst.solver;

import com.google.common.collect.Maps;
import com.mst.mp.kmst.Edge;
import com.mst.mp.kmst.EdgeImpl;
import com.mst.mp.kmst.Problem;
import ilog.concert.IloException;
import ilog.concert.IloIntVar;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class BaseFlowCplexVariables implements HasBinaryEdgeVariables {

	private static final String SELECTION_VAR_TEMAPLATE = "X_{%d, %d}";
	private static final String NODE_VAR_TEMPLATE = "X_{%d}";

	private final Map<Edge, IloIntVar> selectionVariables;
	private final Map<Integer, IloIntVar> nodeVariables;

	protected final Map<List<Integer>, IloNumVar> flowVariables;

	public BaseFlowCplexVariables(Map<Edge, IloIntVar> selectionVariables, Map<Integer, IloIntVar> nodeVariables, Map<List<Integer>, IloNumVar> flowVariables) {
		this.selectionVariables = selectionVariables;
		this.nodeVariables = nodeVariables;
		this.flowVariables = flowVariables;
	}

	protected static Map<Edge, IloIntVar> createSelectionVariables(IloCplex cplex, Problem problem) throws IloException {
		Map<Edge, IloIntVar> variables = Maps.newHashMapWithExpectedSize(problem.getEdgeCount());
		for (Edge edge : problem.getEdges()) {
			IloIntVar var = cplex.boolVar(String.format(SELECTION_VAR_TEMAPLATE,
					edge.getSource(),
					edge.getTarget()));
			variables.put(edge, var);
			cplex.add(var);
		}
		return variables;
	}

	protected static Map<Integer, IloIntVar> createNodeDecisionVariables(IloCplex cplex, Problem problem) throws IloException {
		Map<Integer, IloIntVar> vars = Maps.newHashMap();
		for (int i = 0; i < problem.getNodes(); i++) {
			IloIntVar var = cplex.boolVar(String.format(NODE_VAR_TEMPLATE, i));
			vars.put(i, var);
			cplex.add(var);
		}
		return vars;
	}

	@Override
	public IloIntVar getBinaryEdgeVariable(Edge edge) {
		return selectionVariables.get(edge);
	}

	@Override
	public IloIntVar getBinaryEdgeVariable(int source, int target) {
		return selectionVariables.get(new EdgeImpl(source, target));
	}

	public IloIntVar[] getBinaryEdgeVariables() {
		return selectionVariables.values().toArray(new IloIntVar[selectionVariables.size()]);
	}

	public IloIntVar getNodeVariable(int i) {
		return nodeVariables.get(i);
	}

	public Collection<IloIntVar> getEdgeVars() {
		return selectionVariables.values();
	}
	public Collection<IloNumVar> getFlowVars() {
		return flowVariables.values();
	}

	public IloIntVar[] getNodeVars() {
		return nodeVariables.values().stream().toArray(size -> new IloIntVar[size]);
	}
}
