package com.google.code.jsudokusolver.strategy;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.code.jsudokusolver.Box;
import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Column;
import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.House;
import com.google.code.jsudokusolver.Row;
import com.google.code.jsudokusolver.SolverStrategy;

/**
 * @see http://www.sudopedia.org/wiki/Simple_Colors
 */
public class SimpleColours implements SolverStrategy 
{
    /**
	 * {@inheritDoc}
	 */
    public boolean solve(Grid grid)
    {
	List<Row> rows = grid.getRows();
	List<Column> columns = grid.getColumns();
	List<Box> boxes = grid.getBoxes();
	for (int i = 1; i <= 9; i++) {
	    Set<Cell> pairCells = new HashSet<Cell>();
	    pairCells.addAll(findPairs(rows, i));
	    pairCells.addAll(findPairs(columns, i));
	    pairCells.addAll(findPairs(boxes, i));
	    
	    Set<Cell> colour1 = new HashSet<Cell>();
	    Set<Cell> colour2 = new HashSet<Cell>();
	    for (Cell firstCell : pairCells) {
		boolean foundPair = false;
		for (Cell secondCell : colour1) {
		    if (isConjugatePair(firstCell, secondCell)) {
			foundPair = true;
		    }
		}
		if (foundPair == true) {
		    colour2.add(firstCell);
		} else {
		    colour1.add(firstCell);
		}
//		colour1.add(firstCell);
//		System.out.println(firstCell.getPosition());
//		for (Cell secondCell : pairCells) {
//		    if (firstCell.equals(secondCell)) {
//			continue;
//		    }
//		    if (isConjugatePair(firstCell, secondCell) == false) {
//			System.out.println(secondCell.getPosition() + " is NOT a conjugate pair with " + firstCell.getPosition());
//			colour1.add(secondCell);
//		    }
//		}
	    }
	    System.out.println("Green");
	    for (Cell cell : colour1) {
		System.out.println(cell.getPosition());
	    }
	    System.out.println("Blue");
	    for (Cell cell : colour2) {
		System.out.println(cell.getPosition());
	    }
	    if (hasContradiction(colour2)) {
		for (Cell cell : colour2) {
		    cell.remove(i);
		}
		return true;
	    }
	}
	return false;
    }
    
    private boolean hasContradiction(Set<Cell> cellSet) {
	for (Cell firstCell : cellSet) {
	    for (Cell secondCell : cellSet) {
		if (isConjugatePair(firstCell, secondCell)) {
		    return true;
		}
	    }
	}
	return false;
    }
    
    private Collection<Cell> findPairs(List<? extends House> houses, int candidate) 
    {
    	Collection<Cell> pairCells = new HashSet<Cell>();
    	for (House house : houses) 
    	{
    		Collection<Cell> houseCells = house.getCellsWithCandidate(candidate);
    		if (houseCells.size() == 2) 
    		{
    			pairCells.addAll(houseCells);
    		}
    	}
    	return pairCells;
    }
    
    private boolean isConjugatePair(Cell firstCell, Cell secondCell) {
	if (firstCell.equals(secondCell)) {
	    return false;
	}
	if (firstCell.getColumn().equals(secondCell.getColumn())) {
	    return true;
	}
	if (firstCell.getRow().equals(secondCell.getRow())) {
	    return true;
	}
	if (firstCell.getBox().equals(secondCell.getBox())) {
	    return true;
	}
	return false;
    }
}
