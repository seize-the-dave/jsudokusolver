package com.google.code.jsudokusolver;

import java.util.LinkedList;
import java.util.List;

public class Solver 
{
	private List<SolvingStrategy> strategies;
	
	public Solver()
	{
		strategies = new LinkedList<SolvingStrategy>();
	}
	
	public void addSolvingStrategy(SolvingStrategy strategy)
	{
		strategies.add(strategy);
	}
	
	public void solve(Grid g)
	{
		for (SolvingStrategy strategy : strategies)
		{
			strategy.solve(g);
		}
	}
}
