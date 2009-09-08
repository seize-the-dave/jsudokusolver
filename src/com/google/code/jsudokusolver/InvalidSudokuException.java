package com.google.code.jsudokusolver;

/**
 * This exception is thrown when a grid is filled with an invalid puzzle
 */
public class InvalidSudokuException extends Exception 
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
    public InvalidSudokuException(String message) 
    {
        super(message);
    }
}
