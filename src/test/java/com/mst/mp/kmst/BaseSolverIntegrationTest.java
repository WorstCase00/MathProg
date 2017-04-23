package com.mst.mp.kmst;

import com.google.common.collect.Sets;
import com.mst.mp.kmst.io.GraphLoader;
import com.mst.mp.kmst.solver.Constants;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class BaseSolverIntegrationTest {


	private static final GraphLoader LOADER = new GraphLoader();
	private static final String TEST_INSTANCES_FOLDER = "testInstances";
	
	@Test
	public void testSingleVertex() throws Exception {
		Problem problem = loadInstance(TEST_INSTANCES_FOLDER + "/one.dat", 1);
		Solver solver = createSubject();

		Solution result = solver.solve(problem).getSolution();

		assertEquals(Sets.newHashSet(1), result.getNodes());
		assertTrue(result.getEdges().isEmpty());
		assertEquals(0d, result.getCost(), Constants.EPS);
	}

	@Test
	public void testOneEdgeInstance() throws Exception {
		Problem problem = loadInstance(TEST_INSTANCES_FOLDER + "/two.dat", 2);
		Solver solver = createSubject();

		Solution result = solver.solve(problem).getSolution();

		assertEquals(Sets.newHashSet(1, 2), result.getNodes());
		assertEquals(1, result.getEdges().size());
		assertEquals(1d, result.getCost(), Constants.EPS);
	}

	@Test
	public void testThreeSelectThree() throws Exception {
		Problem problem = loadInstance(TEST_INSTANCES_FOLDER + "/three.dat", 3);
		Solver solver = createSubject();

		Solution result = solver.solve(problem).getSolution();

		assertEquals(Sets.newHashSet(1, 2, 3), result.getNodes());
		assertEquals(2, result.getEdges().size());
		assertEquals(3d, result.getCost(), Constants.EPS);
	}

	@Test
	public void testThreeSelectTwo() throws Exception {
		Problem problem = loadInstance(TEST_INSTANCES_FOLDER + "/three.dat", 2);
		Solver solver = createSubject();

		Solution result = solver.solve(problem).getSolution();

		assertEquals(Sets.newHashSet(1, 2), result.getNodes());
		assertEquals(1, result.getEdges().size());
		assertEquals(1d, result.getCost(), Constants.EPS);
	}

	protected Problem loadInstance(String file, int k) throws IOException {
		return new DefaultProblem(LOADER.load(file), k);
	}


	protected abstract Solver createSubject();
}
