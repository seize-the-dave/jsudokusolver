package com.google.code.jsudokusolver.strategy;

import org.junit.Assert;
import org.junit.Test;

import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.Solver;

public class HiddenSingleTest 
{
	@Test
	public void testSolve() 
	{
		Grid g = Grid.fromString("..2.3...8.....8....31.2.....6..5.27..1.....5.2.4.6..31....8.6.5.......13..531.4..");
		
		Solver s = new Solver();
		s.addStrategy(new Given());
		s.addStrategy(new HiddenSingle());
		s.solve(g);
		
		Assert.assertEquals("672435198549178362831629547368951274917243856254867931193784625486592713725316489", g.toString());
	}
}
