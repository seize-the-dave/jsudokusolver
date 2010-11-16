package com.google.code.jsudokusolver.strategy;

/**
 * @see http://www.sudopedia.org/wiki/Jellyfish
 */
public class Jellyfish extends AbstractBasicFish 
{
    /**
     * {@inheritDoc}
     */
    protected int getFishSize() 
    {
        return 4;
    }
}