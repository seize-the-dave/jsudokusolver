package com.google.code.jsudokusolver;

import java.util.HashSet;
import java.util.Set;

public class ReferenceReason extends Reason 
{
    private final Set<Cell> reference;
    
    public ReferenceReason(String name, Set<Cell> cells) 
    {
    	super(name);
    	this.reference = cells;
    }
    
    public ReferenceReason(String name, Cell cell) 
    {
    	super(name);
    	this.reference = new HashSet<Cell>();
    	reference.add(cell);
    }
    
    public Set<Cell> getReference() 
    {
    	return reference;
    }
}
