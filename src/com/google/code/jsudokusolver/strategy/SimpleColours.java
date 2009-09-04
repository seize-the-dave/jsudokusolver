package com.google.code.jsudokusolver.strategy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.code.jsudokusolver.Box;
import com.google.code.jsudokusolver.Cell;
import com.google.code.jsudokusolver.Column;
import com.google.code.jsudokusolver.Grid;
import com.google.code.jsudokusolver.House;
import com.google.code.jsudokusolver.ReferenceReason;
import com.google.code.jsudokusolver.Row;
import com.google.code.jsudokusolver.SolvingStrategy;

public class SimpleColours implements SolvingStrategy {
    private Grid grid;
    
    public String getName() {
	return "Simple Colours";
    }

    public void setGrid(Grid grid) {
	this.grid = grid;
    }

    // Each pair must be split between colours!
    public boolean solve() {
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
		ReferenceReason reason = new ReferenceReason(getName(), colour1);
		for (Cell cell : colour2) {
		    cell.remove(i, reason);
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
    
    private Set<Cell> findPairs(List<? extends House> houses, int candidate) {
	Set<Cell> pairCells = new HashSet<Cell>();
	for (House house : houses) {
	    Set<Cell> houseCells = house.getCellsWithCandidate(candidate);
	    if (houseCells.size() == 2) {
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
