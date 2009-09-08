package com.google.code.jsudokusolver;

import java.util.LinkedList;
import java.util.List;

public class Solver 
{
	private List<SolverStrategy> strategies;
	
	public Solver()
	{
		strategies = new LinkedList<SolverStrategy>();
	}
	
	public void addSolvingStrategy(SolverStrategy strategy)
	{
		strategies.add(strategy);
	}
	
	public void solve(Grid g)
	{
		for (SolverStrategy strategy : strategies)
		{
			strategy.solve(g);
		}
	}
}
