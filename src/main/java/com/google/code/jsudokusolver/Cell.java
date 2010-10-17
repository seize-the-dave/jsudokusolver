package com.google.code.jsudokusolver;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @see http://www.sudopedia.org/wiki/Cell
 */
public class Cell implements Comparable<Cell> 
{
    private final Set<Integer> candidates;
    private final House row;
    private final House column;
    private final House box;
    private Integer digit;
    
    public Cell(House row, House column, House box, Set<Integer> candidates) 
    {
        this.candidates = candidates;
        this.row = row;
        this.column = column;
        this.box = box;
        
        row.addCell(this);
        column.addCell(this);
        box.addCell(this);
    }
    
    /**
     * Returns an unmodifiable set.
     * 
     * @return the candidates for this cell
     */
    public Set<Integer> getCandidates() 
    {
        return Collections.unmodifiableSet(candidates);
    }
    
    public Collection<House> getHouses()
    {
    	Collection<House> houses = new HashSet<House>();
    	
    	houses.add(row);
    	houses.add(column);
    	houses.add(box);
    	
    	return houses;
    }
    
    public Set<Cell> getPeers()
    {
    	Set<Cell> peers = new HashSet<Cell>();
    	
    	peers.addAll(row.getCells());
    	peers.addAll(column.getCells());
    	peers.addAll(box.getCells());
    	peers.remove(this);
    	
    	return peers;
    }
    
    /**
     * @return true if this cell has been solved; false otherwise
     */
    public boolean isSolved() 
    {
        return digit != null;
    }
    
    /**
     * 
     * @param digit
     * @return true if this 
     */
    public boolean contains(Integer digit) 
    {
        return candidates.contains(digit);
    }
    
    public boolean remove(Integer candidate, ReferenceReason reason) 
    {
        if (candidates.remove(candidate)) 
        {
        	if (candidates.size() == 0)
        	{
        		throw new NoCandidatesException(getPosition() + " has no remaining candidates.");
        	}
            Util.logCandidateRemoval(this, candidate, reason.getName(), reason.getReference());
            return true;
        }
        return false;
    }
    
    public boolean removeAll(Collection<Integer> candidates, ReferenceReason reason)
    {
        if (this.candidates.removeAll(candidates)) 
        {
        	if (candidates.size() == 0)
        	{
        		throw new NoCandidatesException(getPosition() + " has no remaining candidates.");
        	}
            Util.logCandidateRemoval(this, candidates, reason.getName(), reason.getReference());
            return true;
        }
        return false;
    }
    
    public boolean retainAll(Set<Integer> candidates, Reason reason) 
    {
        if (this.candidates.retainAll(candidates)) {
            Util.logCandidateRetention(this, candidates, reason.getName());
            return true;
        }
        return false;
    }
    
    public House getRow()
    {
        return row;
    }
    
    public House getColumn()
    {
        return column;
    }
    
    public House getBox()
    {
        return box;
    }
    
    public Integer getDigit()
    {
        return digit;
    }
    
    public void setDigit(Integer digit)
    {
    	Set<House> houses = new HashSet<House>();
		houses.add(row);
		houses.add(column);
		houses.add(box);
		
    	for (House house : houses)
    	{
	    	for (Cell cell : house.getCells())
	    	{
	    		if (cell.equals(this))
	    		{
	    			continue;
	    		}
    			if (cell.isSolved() && cell.getDigit().equals(digit))
    			{
    				throw new RepeatedDigitException(digit + " not permitted in " + getPosition() + "; already present in " + cell.getPosition());
    			}
	    	}
    	}
    	
        this.digit = digit;
        candidates.clear();
        
        Set<Cell> cells = new HashSet<Cell>();
        cells.add(this);
//        ReferenceReason reason = new ReferenceReason("Given Digit", cells);
//        row.removeCandidate(digit, reason);
//        column.removeCandidate(digit, reason);
//        box.removeCandidate(digit, reason);
    }
 
    @Override
    public String toString()
    {
        if (digit == null)
        {
            return ".";
        }
        return digit.toString();
    }
    
    public static Set<Integer> generateCandidateSet(Integer... candidates) {
        Set<Integer> candidateSet = new TreeSet<Integer>();
        for (Integer candidate : candidates) {
            candidateSet.add(candidate);
        }
        return candidateSet;
    }
    
    public static Set<Integer> generateCandidateSet(int from, int to) {
        Set<Integer> candidateSet = new TreeSet<Integer>();
        for (int i = from; i <= to; i++) {
            candidateSet.add(i);
        }
        return candidateSet;
    }
    
    public String getPosition() {
        return "r" + row.getOffset() + "c" + column.getOffset();
    }

    public int compareTo(Cell cell)
    {
        if (column.getOffset() > cell.column.getOffset()) {
            return 1;
        }
        if (column.getOffset() < cell.column.getOffset()) {
            return -1;
        }
        if (row.getOffset() > cell.row.getOffset()) {
            return 1;
        }
        if (row.getOffset() < cell.row.getOffset()) {
            return -1;
        }
        return 0;
    }
    
    public static Set<Set<Integer>> generateCombinations(Set<Integer> elements, Set<Set<Integer>> combinations, int size) {
        Set<Set<Integer>> newCombinations = new HashSet<Set<Integer>>();
        if (combinations.size() != 0) {
            for (Set<Integer> combination : combinations) {
                for (Integer element : elements) {
                    if (combination.contains(element)) {
                        continue;
                    }
                    Set<Integer> newCombination = new TreeSet<Integer>(combination);
                    newCombination.add(element);
                    newCombinations.add(newCombination);
                }
            }
        } else {
            for (Integer element : elements) {
                Set<Integer> newCombination = new TreeSet<Integer>();
                newCombination.add(element);
                newCombinations.add(newCombination);
            }
        }
        if (size == 0) {
            return combinations;
        } else {
            return generateCombinations(elements, newCombinations, --size);
        }
    }
}
