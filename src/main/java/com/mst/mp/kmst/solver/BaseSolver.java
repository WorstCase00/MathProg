package com.mst.mp.kmst.solver;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mst.mp.kmst.Edge;
import com.mst.mp.kmst.Problem;
import com.mst.mp.kmst.RunResult;
import com.mst.mp.kmst.RunResultImpl;
import com.mst.mp.kmst.Solution;
import com.mst.mp.kmst.Solver;
import ilog.concert.IloException;
import ilog.concert.IloIntVar;
import ilog.concert.IloNumExpr;
import ilog.concert.IloObjectiveSense;
import ilog.cplex.IloCplex;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class BaseSolver<T extends HasBinaryEdgeVariables> implements Solver {

	@Override
	public RunResult solve(Problem problem) {
		try {
			if (problem.getK() < 2) {
				return RunResultImpl.createEmpty(DefaultSolution.createEmpty(problem));
			}
			IloCplex cplex = new IloCplex();
			T model = createModel(problem, cplex);
			cplex.solve();
			if(!cplex.getStatus().equals(IloCplex.Status.Optimal)) {
				throw new RuntimeException(cplex.getStatus().toString());
			}
			Solution solution = createSolution(cplex, model, problem);
			RunResult runResults = RunResultImpl.createForStandard(
					solution,
					(long) cplex.getCplexTime(),
					(long) cplex.getNnodes());
			cplex.end();
			return runResults;
		} catch (IloException e) {
			throw new RuntimeException(e);
		}
	}



	private Solution createSolution(IloCplex cplex, T model, Problem problem) throws IloException {
		Set<Edge> edges = Sets.newHashSetWithExpectedSize(problem.getK() - 1);
		for (Edge edge : problem.getEdges()) {
			if (cplex.getValue(model.getBinaryEdgeVariable(edge)) > 0d) {
				edges.add(edge);
			}
		}
		return DefaultSolution.create(edges, problem);
	}

	protected void addObjectiveFunction(HasBinaryEdgeVariables model, Problem problem, IloCplex cplex) throws IloException {
		cplex.addObjective(IloObjectiveSense.Minimize, createObjective(model, cplex, problem));
	}

	protected IloNumExpr createObjective(HasBinaryEdgeVariables model, IloCplex cplex, Problem problem) throws IloException {
		List<IloNumExpr> expressions = Lists.newArrayList();
		for (Edge edge : problem.getEdges()) {
			double weight = problem.getWeight(edge);
			if (edge.getSource() == 0) {
				weight = problem.getWeightSum();
			}
			IloIntVar variable = model.getBinaryEdgeVariable(edge);
			expressions.add(cplex.prod(weight, variable));
		}
		return cplex.sum(expressions.toArray(new IloNumExpr[expressions.size()]));
	}

	protected void addSelectionEqualities(HasBinaryEdgeVariables model, Problem problem, IloCplex cplex) throws IloException {
		cplex.addEq(
				cplex.sum(model.getBinaryEdgeVariables()),
				cplex.constant(problem.getK())
		);
		cplex.addEq(
				cplex.sum(model.getNodeVars()),
				cplex.constant(problem.getK() + 1)
		);
	}

	protected abstract T createModel(Problem problem, IloCplex cplex) throws IloException;

	// static helper for debugging
	static Map<String, Double> filterVars(Collection<? extends IloNumExpr> vars, IloCplex cplex) throws IloException {
		Map<String, Double> assignments = Maps.newHashMap();
		for (IloNumExpr var : vars) {
			Double value = cplex.getValue(var);
			if (value > 0) {
				assignments.put(var.toString(), value);
			}
		}
		return assignments;
	}
}
