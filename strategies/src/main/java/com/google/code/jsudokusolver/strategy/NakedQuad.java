package com.google.code.jsudokusolver.strategy;

/**
 * @see http://www.sudopedia.org/wiki/Naked_Quad
 */
public class NakedQuad extends AbstractNakedSubset 
{
    /**
     * {@inheritDoc}
     */
    protected int getSetSize() 
    {
        return 4;
    }
}