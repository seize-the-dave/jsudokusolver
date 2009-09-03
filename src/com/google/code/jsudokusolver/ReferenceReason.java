package com.google.code.jsudokusolver;

import java.util.Set;

public class ReferenceReason extends Reason 
{
    private final Set<Cell> reference;
    
    public ReferenceReason(String name, Set<Cell> reference) 
    {
    	super(name);
    	this.reference = reference;
    }
    
    public Set<Cell> getReference() 
    {
    	return reference;
    }
}
