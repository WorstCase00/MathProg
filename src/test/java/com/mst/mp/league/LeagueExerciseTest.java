package com.mst.mp.league;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LeagueExerciseTest {

	@Test
	public void testMinimalExampleFirst() throws Exception {
		int result = LeagueExercise.solve(2, 0);

		assertEquals(6, result);
	}

	@Test
	public void testMinimalExampleSecond() throws Exception {
		int result = LeagueExercise.solve(2, 1);

		assertEquals(3, result);
	}

	@Test
	public void testLargerExample() throws Exception {
		int result = LeagueExercise.solve(18, 0);

		assertEquals(34 * 3, result);
	}


	@Test
	public void testExcerciseExample() throws Exception {
		int result = LeagueExercise.solve(18, 15);

		assertEquals(57, result);
	}

}