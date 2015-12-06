/*
 * Jeffrey Josephs
 * CS 1632
 * Deliverable 6
 * Project: Escape The Swamp
 * File: Swamp.java
 * Description: Swamp class that contains implementation of escaping the swamp
 */ 

import java.util.Random;
import java.lang.StringBuilder;
import java.lang.System;

class Swamp implements TheSwamp {
  public int[][] swamp;
  public int dimension;
  private boolean foundPath = false;
  private int initialDropInX;
  private int initialDropInY;
  
  /*
   * Constructor that will create the swamp
   * @param dimensions NxN swamp
   */  
  public Swamp(int dimension) {
    this.dimension = dimension;
    this.initialDropInX = -1;
    this.initialDropInY = -1;
  }
  
  /*
   * Function to create NxN swamp
   */
  public void createSwamp() {
    int[][] tempSwamp = new int[dimension][dimension];
    
    // Use random number to determine if good/bad position
    Random randomObj = new Random();
    final long SEED_VALUE = System.currentTimeMillis();
    randomObj.setSeed(SEED_VALUE);
    
    // Build swamp with randomly chosen 0 or 1 for each position
    for(int r = 0; r < dimension; r++) {
      for(int c = 0; c < dimension; c++)
        tempSwamp[r][c] = randomObj.nextInt(2);
    }

    this.swamp = tempSwamp;
  }
  
  /*
   * Function to generate display of swamp
   * @return String consisting of swamp and border
   */
  public StringBuilder swampToString() {
    StringBuilder swampString = new StringBuilder();
    
    // Display the swamp with a border
    for(int i = 0; i < dimension+2; i++) {
      for(int j = 0; j < dimension+2; j++) {
        if(i == 0 || i == dimension+1)      // Add top and bottom border
          swampString.append("- ");
        else if(j == 0 || j == dimension+1) // Add left and right border
          swampString.append("| ");
        else                                // Add swamp data
          swampString.append(swamp[i-1][j-1]+" ");
      }
      
      swampString.append('\n');
    }
    
    return swampString;
  }
  
  /*
   * Function to find an escape path given a starting point.
   *   Because of randomness, only find one escape path
   * @param currRow Current Y position in the swamp
   * @param currCol Current X position in the swamp
   * @param path The current string representation of the path
   */ 
  public void getEscapePath(int currCol, int currRow, StringBuilder path) {
    int [] deltaRow = {-1, -1, 0, 1, 1, 1, 0, -1};
    int [] deltaCol = {0, 1, 1, 1, 0, -1, -1, -1};
    
    // Keep track of initial drop in to monitor no path found
    if(this.initialDropInX == -1 && this.initialDropInY == -1) {
	    this.initialDropInX = currCol;
	    this.initialDropInY = currRow;
    }
    
    // Check that drop in position is valid
    if(swamp[currRow][currCol] == 0) {
    	System.out.println("Drop in position invalid!");
    	return;
    }
    
    path.append("(" + currCol + "," + currRow + ")");
    
    // Check if a path has already been found
    if(!foundPath) {
      // Check the bounds before trying to advance
      if (currRow == 0 || currRow == swamp.length-1 || currCol == 0 || currCol == swamp.length-1) {
        // Display the path once you have found a way out
        System.out.println(path.toString());
        foundPath = true;
        return;
      }
      
      // Iterate through possible movements to find next position
      for(int i = 0; i < 8; i++) {
        // Can advance if position is a 1
        if(swamp[currRow+deltaRow[i]][currCol+deltaCol[i]] == 1) {
          // Advance and mark previous position to -1 to ensure no cycle
          swamp[currRow][currCol] = -1;
          
          // Recursive call to advance
          getEscapePath(currCol+deltaCol[i], currRow+deltaRow[i], path);
          
          // Upon return, mark the locations back to 1 to find more paths
          swamp[currRow][currCol] = 1;
        }
      }
      
      if(!foundPath && currCol == this.initialDropInX && currRow == this.initialDropInY)
    	  System.out.println("No Path Found!");
      
      return;
    }
  }
}