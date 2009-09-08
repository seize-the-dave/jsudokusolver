package com.google.code.jsudokusolver.strategy;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.House;
import com.google.code.jsudokusolver.ReferenceReason;
import com.google.code.jsudokusolver.SolverStrategy;

public class Given implements SolverStrategy 
{
	@Override
	public String getName() 
	{
		return "GivenDigit";
	}

	@Override
	public boolean solve(Grid grid) 
	{
		boolean changed = false;
		
		for (Cell cell : grid.getCells())
		{
			if (cell.isSolved() == false)
			{
				continue;
			}
			for (House house : cell.getHouses())
			{
				for (Cell other : house.getCells())
				{
					if (other.equals(cell))
					{
						continue;
					}
					if (other.isSolved())
					{
						continue;
					}
					if (other.contains(cell.getDigit()))
					{
						ReferenceReason reason = new ReferenceReason(getName(), cell);
						if (other.remove(cell.getDigit(), reason))
						{
							changed = true;
						}
					}
				}
			}
		}
		
		return changed;
	}

}
