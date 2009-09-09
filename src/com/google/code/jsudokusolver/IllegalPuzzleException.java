package com.google.code.jsudokusolver;

/**
 * This exception is thrown when a grid is filled with an invalid puzzle
 */
public class IllegalPuzzleException extends IllegalStateException 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructor
     * 
     * @param message description of this exception
     */
    public IllegalPuzzleException(String message) 
    {
        super(message);
    }
}
