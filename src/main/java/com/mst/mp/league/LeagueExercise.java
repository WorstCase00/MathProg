package com.mst.mp.league;

import ilog.concert.IloException;
import ilog.concert.IloIntVar;
import ilog.cplex.IloCplex;

public class LeagueExercise {

	public static void main(String... args) throws Exception  {
		solve(18, 3);
	}

	public static int solve(int n, int position) throws IloException {
		IloCplex cplex = new IloCplex();
		IloIntVar[] points = createPointVars(n, cplex);
		IloIntVar[][] wins = createWinVars(n, cplex);
		IloIntVar[][] draws = createDrawVars(n, cplex);

		cplex.addMaximize(points[position]);

		// order constraints
		for (int i = 0; i < n - 1; i++) {
			cplex.addGe(points[i], points[i+1]);
		}

		// point constraints
		for (int i = 0; i < n; i++) {
			cplex.addEq(points[i],
					cplex.sum(
							cplex.prod(cplex.sum(wins[i]), 3),
							cplex.sum(draws[i])));
		}

		// game constraints
		for (int i = 0; i < n - 1; i++) {
			for (int j = i+1; j < n; j++) {

				cplex.addEq(draws[i][j], draws[j][i]);

				cplex.addEq(
						cplex.sum(wins[i][j], wins[j][i], draws[i][j]),
						2
				);
			}
		}

		for (int i = 0; i < n; i++) {
			cplex.addEq(wins[i][i], 0);
			cplex.addEq(draws[i][i], 0);
		}

		boolean solved = cplex.solve();

		return (new Double(cplex.getBestObjValue())).intValue();
	}

	private static IloIntVar[][] createDrawVars(int n, IloCplex cplex) throws IloException {
		IloIntVar[][] vars = new IloIntVar[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				vars[i][j] = cplex.intVar(0, 2);
			}
		}
		return vars;
	}

	private static IloIntVar[][] createWinVars(int n, IloCplex cplex) throws IloException {
		return createDrawVars(n, cplex);
	}

	private static IloIntVar[] createPointVars(int n, IloCplex cplex) throws IloException {
		IloIntVar[] vars = new IloIntVar[n];
		for (int i = 0; i < n; i++) {
			vars[i] = cplex.intVar(0, 3 * 2 * (n-1));
		}
		return vars;
	}
}
