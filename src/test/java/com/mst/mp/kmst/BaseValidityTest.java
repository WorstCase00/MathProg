package com.mst.mp.kmst;

import com.mst.mp.kmst.experiment.BenchmarkInstance;
import com.mst.mp.kmst.io.BenchmarkLoader;
import com.mst.mp.kmst.solver.Constants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class BaseValidityTest {
	private static final BenchmarkLoader BENCHMARK_LOADER = new BenchmarkLoader();

	@Test
	public void testInstance01A() throws Exception {
		runInstance(BENCHMARK_LOADER.getInstance01A());
	}

	@Test
	public void testInstance01B() throws Exception {
		runInstance(BENCHMARK_LOADER.getInstance01B());
	}

	@Test
	public void testInstance02A() throws Exception {
		runInstance(BENCHMARK_LOADER.getInstance02A());
	}

	@Test
	public void testInstance02B() throws Exception {
		runInstance(BENCHMARK_LOADER.getInstance02B());
	}

//	@Test
//	public void testInstance03A() throws Exception {
//		runInstance(BENCHMARK_LOADER.getInstance03A());
//	}
//
//	@Test
//	public void testInstance04A() throws Exception {
//		runInstance(BENCHMARK_LOADER.getInstance04A());
//	}
//
//	@Test
//	public void testInstance05A() throws Exception {
//		runInstance(BENCHMARK_LOADER.getInstance05A());
//	}
//
//	@Test
//	public void testInstance06A() throws Exception {
//		runInstance(BENCHMARK_LOADER.getInstance06A());
//	}
//
//	@Test
//	public void testInstance07A() throws Exception {
//		runInstance(BENCHMARK_LOADER.getInstance07A());
//	}
//
//	@Test
//	public void testInstance08A() throws Exception {
//		runInstance(BENCHMARK_LOADER.getInstance08A());
//	}

	private void runInstance(BenchmarkInstance instance) {
		Solver solver = createSubject();

		RunResult result = solver.solve(instance.getProblem());

		assertEquals(instance.getSolutionValue(), result.getObjectiveValue(), Constants.EPS);
	}

	protected abstract Solver createSubject();
}
