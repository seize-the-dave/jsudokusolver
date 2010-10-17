package com.google.code.jsudokusolver.strategy;

/**
 * @see http://www.sudopedia.org/wiki/Naked_Triple
 */
public class NakedTriple extends AbstractNakedSubset 
{
    /**
     * {@inheritDoc}
     */
    protected int getSetSize() 
    {
        return 3;
    }
}
