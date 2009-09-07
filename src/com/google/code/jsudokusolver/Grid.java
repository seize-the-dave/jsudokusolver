package com.google.code.jsudokusolver;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

public class Grid 
{
    private static final Logger LOGGER = Logger.getLogger(Grid.class.getCanonicalName());
    private static final int SIZE = 9;
    private final List<Row> rows = new LinkedList<Row>();
    private final List<Column> columns = new LinkedList<Column>();
    private final List<Box> boxes = new LinkedList<Box>();
    private final List<Floor> floors = new LinkedList<Floor>();
    private final List<Tower> towers = new LinkedList<Tower>();
    private final List<Cell> cells = new LinkedList<Cell>();
    private static int step = 1;
    
    private Grid() 
    {
    	// Initialize Chutes
    	for (int i = 0; i < 3; i++)
        {
    		towers.add(new Tower());
        	floors.add(new Floor());
        }
    	// Initialize Houses
    	for (int i = 0; i < 9; i++)
    	{
    		int o = i / 3;
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
    
    public Set<House> getHouses()
    {
    	Set<House> houses = new HashSet<House>();
    	
    	houses.addAll(rows);
    	houses.addAll(columns);
    	houses.addAll(boxes);
    	
    	return houses;
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
     * @throws InvalidSudokuException if the puzzle String is invalid
     */
    private void fromString(String puzzle) throws InvalidSudokuException {
        if (puzzle.length() != 81) {
            throw new InvalidSudokuException("Wrong Length");
        }
        for (int i = 0; i < 81; i++) 
        {
        	Cell cell = cells.get(i);
            
            Integer digit = Integer.valueOf(String.valueOf(puzzle.charAt(i)));
            if (digit != 0) 
            {
                cell.setDigit(digit);
            }
        }
    }
    
    private House getRow(int offset) {
        int row = offset / SIZE;
        return rows.get(row);
    }
    
    private House getColumn(int offset) {
        int column = offset % SIZE;
        return columns.get(column);
    }
    
    private House getBox(int offset) {
        int row = offset / SIZE;
        int column = offset % SIZE;
        int sqrt = (int) Math.sqrt((double) SIZE);
        
        int rowOffset = (row / sqrt);
        int columnOffset = (column / sqrt);
        int box = (rowOffset * sqrt) + columnOffset;
        return boxes.get(box);
    }
    
    public List<Row> getRows() {
        return rows;
    }
    
    public List<Column> getColumns() {
        return columns;
    }
    
    public List<Box> getBoxes() {
        return boxes;
    }
    
    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        for (House row : rows) 
        {
            sb.append(row.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
    
    public static void logCandidateRemoval(Cell cell, int candidate, String strategy, Set<Cell> reference) {
        log(step + " " + strategy + ": " + cell.getPosition() + " cannot contain " + candidate + " due to " + strategy + " in " + formatCells(reference));
    }
    
    public static void logCandidateRemoval(Cell cell, Set<Integer> candidates, String strategy, Set<Cell> reference) {
        log(step + " " + strategy + ": " + cell.getPosition() + " cannot contain " + formatCandidates(candidates, false) + " due to " + strategy + " in " + formatCells(reference));
    }
    
    public static void logCandidateRemoval(Cell cell, Set<Integer> candidates, String strategy, Cell... reference) {
        Set<Cell> referenceSet = new TreeSet<Cell>(Arrays.asList(reference));
        log(step + " " + strategy + ": " + cell.getPosition() + " cannot contain " + formatCandidates(candidates, false) + " due to " + strategy + " in " + formatCells(referenceSet));
    }
    
    public static void logCandidateRetention(Cell cell, int candidate, String strategy) {
        log(step + " " + strategy + ": " + cell.getPosition() + " can only contain " + candidate);
    }
    
    public static void logCandidateRetention(Cell cell, Set<Integer> candidates, String strategy) {
        log(step + " " + strategy + ": " + cell.getPosition() + " can only contain " + formatCandidates(candidates, true));
    }
    
    public static void logCandidateRetention(Set<Cell> cells, Set<Integer> candidates, String strategy) {
        log(step + " " + strategy + ": " + formatCells(cells) + " can only contain " + formatCandidates(candidates, true));
    }
    
    private static void log(String message) {
        LOGGER.info(message);
    }
    
    public static String formatCells(Set<Cell> cells) 
    {
        StringBuffer sb = new StringBuffer();
        Cell[] cellArray = cells.toArray(new Cell[]{});
        for (int i = 0; i < cellArray.length; i++) {
            sb.append(cellArray[i].getPosition());
            if (cellArray.length - i == 2) {
                sb.append(" and ");
            }
            if (cellArray.length - i > 2) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
    
    public static String formatCandidates(Set<Integer> cells, boolean and) {
        StringBuffer sb = new StringBuffer();
        Integer[] cellArray = cells.toArray(new Integer[]{});
        for (int i = 0; i < cellArray.length; i++) {
            sb.append(cellArray[i]);
            if (cellArray.length - i == 2) {
                if (and) {
                    sb.append(" and ");
                } else {
                    sb.append(" or ");
                }
            }
            if (cellArray.length - i > 2) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
    
    public static Grid parseGrid(String s) throws InvalidSudokuException
    {
    	Grid g = new Grid();
    	g.fromString(s);
    	
    	return g;
    }
}
