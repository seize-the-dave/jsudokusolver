package com.google.code.jsudokusolver.strategy;

/**
 * @see http://www.sudopedia.org/wiki/Hidden_Pair
 */
public class HiddenPair extends AbstractHiddenSubset 
{
    /**
     * {@inheritDoc}
     */
    protected int getSetSize() 
    {
        return 2;
    }
}
