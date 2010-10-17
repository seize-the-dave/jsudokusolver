package com.google.code.jsudokusolver.strategy;

import java.util.ServiceLoader;

import org.junit.Assert;
import org.junit.Test;

import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.Solver;
import com.google.code.jsudokusolver.SolverStrategy;

public class HiddenSingleTest 
{
	@Test
	public void testSolve() 
	{
		Grid g = Grid.fromString("..2.3...8.....8....31.2.....6..5.27..1.....5.2.4.6..31....8.6.5.......13..531.4..");
		
		final Solver s = new Solver();
		final ServiceLoader<SolverStrategy> strategies = ServiceLoader.load(SolverStrategy.class);
		for (SolverStrategy strategy : strategies) {
			System.out.println(strategy);
			s.addStrategy(strategy);
		}
		s.solve(g);
		
		Assert.assertEquals("672435198549178362831629547368951274917243856254867931193784625486592713725316489", g.toString());
	}
}
