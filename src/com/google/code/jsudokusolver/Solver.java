package com.google.code.jsudokusolver;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Solver 
{
	private List<SolverStrategy> strategies;
	
	public Solver()
	{
		strategies = new LinkedList<SolverStrategy>();
	}
	
	public void addStrategy(SolverStrategy strategy)
	{
		strategies.add(strategy);
	}
	
	public void solve(Grid g) throws InvalidPuzzleException
	{
		Iterator<SolverStrategy> iter = strategies.iterator();
		while (iter.hasNext())
		{
			SolverStrategy strategy = iter.next();
			// If the strategy was successful, reset the iterator.
			if (strategy.solve(g))
			{
				iter = strategies.iterator();
			}
		}
	}
}
