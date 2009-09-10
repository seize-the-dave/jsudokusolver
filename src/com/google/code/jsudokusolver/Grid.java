package com.google.code.jsudokusolver;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @see http://www.sudopedia.org/wiki/Grid
 */
public class Grid 
{
    private static final int SIZE = 9;
    private final List<Row> rows = new LinkedList<Row>();
    private final List<Column> columns = new LinkedList<Column>();
    private final List<Box> boxes = new LinkedList<Box>();
    private final List<Floor> floors = new LinkedList<Floor>();
    private final List<Tower> towers = new LinkedList<Tower>();
    private final List<Cell> cells = new LinkedList<Cell>();
    
    private Grid() 
    {
    	// Initialize Chutes
    	for (int i = 0; i < Math.sqrt(SIZE); i++)
        {
    		towers.add(new Tower());
        	floors.add(new Floor());
        }
    	// Initialize Houses
    	for (int i = 0; i < SIZE; i++)
    	{
    		int o = i / (int) Math.sqrt(SIZE);
    		Row r = new Row(i);
    		rows.add(r);
    		floors.get(o).add(r);
    		
    		Column c = new Column(i);
    		columns.add(c);
    		towers.get(o).add(c);
    		
    		boxes.add(new Box(i));
    	}
    	// Initialize Cells
    	for (int i = 0; i < 81; i++)
    	{
    		Set<Integer> candidates = Cell.generateCandidateSet(1, 9);
    		House row = getRow(i);
            House column = getColumn(i);
            House box = getBox(i);
            
    		Cell cell = new Cell(row, column, box, candidates);
    		cells.add(cell);
    	}
    }
    
    public Collection<House> getHouses()
    {
    	Set<House> houses = new HashSet<House>();
    	
    	houses.addAll(rows);
    	houses.addAll(columns);
    	houses.addAll(boxes);
    	
    	return houses;
    }
    
    public Collection<Line> getLines()
    {
    	Set<Line> lines = new HashSet<Line>();
    	
    	lines.addAll(rows);
    	lines.addAll(columns);
    	
    	return lines;
    }
    
    public Set<Chute> getChutes()
    {
    	Set<Chute> chutes = new HashSet<Chute>();
    	
    	chutes.addAll(floors);
    	chutes.addAll(towers);
    	
    	return chutes;
    }
    
    public List<Cell> getCells()
    {
    	return Collections.unmodifiableList(cells);
    }
    
    /**
     * Fills in the puzzle.
     * 
     * This method expects a String of numbers equal to the square of the
     * puzzle size.  For example, a puzzle size of 9 would take a String length
     * of 81.  Numbers that are not filled in should use a zero, e.g.
     * 
     * 1302465798...
     * 
     * @param puzzle
     * @throws IllegalPuzzleException if the puzzle String is invalid
     */
    private void parseGrid(String puzzle) 
    {
        if (puzzle.length() != cells.size()) {
            throw new IllegalPuzzleException("Wrong Length");
        }
        for (int i = 0; i < cells.size(); i++) 
        {
        	Cell cell = cells.get(i);
            
        	int digit;
        	try
        	{
        		digit = Integer.parseInt(String.valueOf(puzzle.charAt(i)));
        	}
        	catch (NumberFormatException e)
        	{
        		digit = 0;
        	}
            if (digit != 0) 
            {
                cell.setDigit(digit);
            }
        }
    }
    
    private House getRow(int offset) 
    {
        int row = offset / rows.size();
        return rows.get(row);
    }
    
    private House getColumn(int offset) 
    {
        int column = offset % columns.size();
        return columns.get(column);
    }
    
    private House getBox(int offset) {
        int row = offset / rows.size();
        int column = offset % columns.size();
        int sqrt = (int) Math.sqrt((double) SIZE);
        
        int rowOffset = (row / sqrt);
        int columnOffset = (column / sqrt);
        int box = (rowOffset * sqrt) + columnOffset;
        
        return boxes.get(box);
    }
    
    public List<Row> getRows() 
    {
        return rows;
    }
    
    public List<Column> getColumns() 
    {
        return columns;
    }
    
    public List<Box> getBoxes() 
    {
        return boxes;
    }
    
    public boolean isSolved()
    {
    	for (Cell cell : cells)
    	{
    		if (cell.isSolved() == false)
    		{
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        for (House row : rows) 
        {
            sb.append(row.toString());
        }
        return sb.toString();
    }
    
    public static Grid fromString(String s) throws IllegalPuzzleException
    {
    	Grid g = new Grid();
    	g.parseGrid(s);
    	
    	return g;
    }
}
