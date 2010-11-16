package com.google.code.jsudokusolver.strategy;

import org.junit.Test;

import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.Solver;


public class GivenTest {
	@Test
	public void testSolve() {
		Grid g = Grid.fromString("..2.3...8.....8....31.2.....6..5.27..1.....5.2.4.6..31....8.6.5.......13..531.4..");
		Solver s = new Solver();
		s.addStrategy(new Given());
		s.solve(g);
	}
}
