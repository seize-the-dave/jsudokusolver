package com.google.code.jsudokusolver;

import java.util.Collection;
import java.util.HashSet;

public class ReferenceReason extends Reason 
{
    private final Collection<Cell> reference;
    
    public ReferenceReason(String name, Collection<Cell> cells) 
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
    
    public Collection<Cell> getReference() 
    {
    	return reference;
    }
}
