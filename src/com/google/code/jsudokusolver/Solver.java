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
	
	public List<SolvingStrategy> getStrategies()
	{
		return strategies;
	}
	
	public void setGrid(Grid g)
	{
		for (SolvingStrategy s : strategies)
		{
			s.setGrid(g);
		}
	}
}
