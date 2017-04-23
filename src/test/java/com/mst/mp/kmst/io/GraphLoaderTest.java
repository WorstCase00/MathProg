package com.mst.mp.kmst.io;

import com.google.common.collect.Sets;
import com.mst.mp.kmst.Graph;
import com.mst.mp.kmst.solver.Constants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GraphLoaderTest {

	private static final String TEST_INSTANCES_FOLDER = "testInstances";

	@Test
	public void testLoadRootOnly() throws Exception {
		GraphLoader subject = createSubject();

		Graph graph = subject.load(TEST_INSTANCES_FOLDER + "/one.dat");

		assertEquals(2, graph.getNodes());
		assertEquals(1, graph.getEdgeCount());
	}

	@Test
	public void testCompleteGraphWithThree() throws Exception {
		GraphLoader subject = createSubject();

		Graph graph = subject.load(TEST_INSTANCES_FOLDER + "/two.dat");

		assertEquals(3, graph.getNodes());
		assertEquals(3, graph.getEdgeCount());
		assertEquals(Sets.newHashSet(1, 2), graph.getNeighbors(0));
		assertEquals(Sets.newHashSet(0, 2), graph.getNeighbors(1));
		assertEquals(Sets.newHashSet(0, 1), graph.getNeighbors(2));
		assertEquals(0d, graph.getDistance(0, 1), Constants.EPS);
		assertEquals(0d, graph.getDistance(0, 2), Constants.EPS);
		assertEquals(1d, graph.getDistance(1, 2), Constants.EPS);
	}

	private GraphLoader createSubject() {
		return new GraphLoader();
	}

}