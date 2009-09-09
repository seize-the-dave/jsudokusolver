package com.google.code.jsudokusolver.strategy;

import org.junit.Assert;
import org.junit.Test;

import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.IllegalPuzzleException;
import com.google.code.jsudokusolver.Solver;

public class NakedSingleTest 
{
	@Test
	public void testSolve()
	{
		Grid g = Grid.fromString("3.542.81.4879.15.6.29.5637485.793.416132.8957.74.6528.2413.9.655.867.192.965124.8");
		
		Solver s = new Solver();
		s.addStrategy(new Given());
		s.addStrategy(new NakedSingle());
		s.solve(g);
		
		Assert.assertEquals("365427819487931526129856374852793641613248957974165283241389765538674192796512438", g.toString());
	}
}
