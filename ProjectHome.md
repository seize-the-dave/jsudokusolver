jsudokusolver is a sudoku solver written in java.  It takes a modular approach using the [strategy](http://en.wikipedia.org/wiki/Strategy_pattern) pattern.  The goal of the project is to feature as many strategies as possible, and -- to facilitate that -- make the implementation of new strategies as simple as possible.

# Getting JSudokuSolver #

To run JSudokuSolver directly, you need to obtain the executable JAR file.  You can either download it directly (see our [downloads](http://code.google.com/p/jsudokusolver/downloads/list) page), or build it from source.

## Building From Source ##

**Requirements:** Apache Ant, Subversion

Follow the instructions from the [source checkout](http://code.google.com/p/jsudokusolver/source/checkout) page, then do the following:

```
cd jsudokusolver-read-only
ant build
```

This will place a JAR file called jsudokusolver-r

&lt;revision&gt;

.jar in the target directory, where 

&lt;revision&gt;

 is the number of the SVN revision you've got checked out (e.g. _jsudokusolver-[r59](https://code.google.com/p/jsudokusolver/source/detail?r=59).jar_).

# Running JSudokuSolver #

You can run it against a puzzle using:

```
java -jar <jarfile> <puzzle>
```

For example:

```
java -jar target/jsudokusolver.jar 900000000000000500080006290020500000008060073030079800000091000300000107051000048
```

Currently, the `main` method sets up JSudokuSolver to use all of the [currently released strategies](Strategies.md), but you can write your own client to configure this.

Running the JAR file without a puzzle will run JSudokuSolver against a very difficult puzzle that is currently hard-coded into the class file.  This puzzle **cannot currently be solved** by JSudokuSolver.