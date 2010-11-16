package com.google.code.jsudokusolver.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.IllegalPuzzleException;
import com.google.code.jsudokusolver.SolverStrategy;

/**
 * This strategy checks for completed cells in the grid and eliminates the cell's value
 * from the candidate set for each cell in any of that completed cell's houses.
 */
public class Given implements SolverStrategy 
{
	private static Logger LOGGER = LoggerFactory.getLogger(Given.class.getName());
	/**
	 * {@inheritDoc}
	 */
	public boolean solve(Grid grid) throws IllegalPuzzleException 
	{
		boolean changed = false;
		
		for (Cell cell : grid.getCells())
		{
			if (cell.isSolved() == false)
			{
				continue;
			}
			LOGGER.info(cell.getPosition() + " is solved, removing " + cell.getDigit() + " from peers...");
			for (Cell peer : cell.getPeers())
			{
				if (peer.remove(cell.getDigit())) {
					LOGGER.info("Removed " + cell.getDigit() + " from " + peer.getPosition() + ", leaving " + peer);
					changed = true;
				}
			}
		}
		
		return changed;
	}

}
