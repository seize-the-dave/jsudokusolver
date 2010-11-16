package com.google.code.jsudokusolver.strategy;

/**
 * @see http://www.sudopedia.org/wiki/Naked_Pair
 */
public class NakedPair extends AbstractNakedSubset
{
    /**
     * {@inheritDoc}
     */
    protected int getSetSize() 
    {
        return 2;
    }
}
