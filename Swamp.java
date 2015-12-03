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
  private int[][] swamp;
  private int dimension;
  private boolean foundPath = false;
  
  /*
   * Constuctor that will create the swamp
   * @param dimensions NxN swamp
   */  
  public Swamp(int dimension) {
    this.dimension = dimension;
    this.swamp = createSwamp(dimension);
  }
  
  /*
   * Function to create NxN swamp
   * @param n NxN swamp
   * @return The swamp
   */
  public int[][] createSwamp(int n) {
    int[][] tempSwamp = new int[n][n];
    
    // Use random number to determine if good/bad position
    Random randomObj = new Random();
    final long SEED_VALUE = System.currentTimeMillis();
    randomObj.setSeed(SEED_VALUE);
    
    // Build swamp with randomly chosen 0 or 1 for each position
    for(int r = 0; r < n ; r++) {
      for(int c = 0; c < n; c++)
        tempSwamp[r][c] = randomObj.nextInt(2);
    }

    return tempSwamp;
  }
  
  /*
   * Function to display the swamp
   */
  public void displaySwamp() {
    // Display the swamp with a border
    for(int i = 0; i < dimension+2; i++) {
      for(int j = 0; j < dimension+2; j++) {
        if(i == 0 || i == dimension+1)      // Add top and bottom border
          System.out.print("- ");
        else if(j == 0 || j == dimension+1) // Add left and right border
          System.out.print("| ");
        else                                // Add swamp data
          System.out.print(swamp[i-1][j-1]+" ");
      }
      
      System.out.println();
    }
  }
  
  /*
   * Function to find an escape path given a starting point.
   *   Because of randomness, only find one escape path
   * @param currRow Current Y position in the swamp
   * @param currCol Current X position in the swamp
   * @param path The current string represenation of the path
   */ 
  public void getEscapePath(int currRow, int currCol, StringBuilder path) {
    int [] deltaRow = {-1, -1, 0, 1, 1, 1, 0, -1};
    int [] deltaCol = {0, 1, 1, 1, 0, -1, -1, -1};
    path.append("(" + currRow + "," + currCol + ")");
    
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
          getEscapePath(currRow+deltaRow[i], currCol+deltaCol[i], path);
          
          // Upon return, mark the locations back to 1 to find more paths
          swamp[currRow][currCol] = 1;
        }
      }
      
      // Check that a path has been found
      if(path.equals("(" + currRow + "," + currCol + ")"))
        System.out.println("No Path Found!");
      
      return;
    }
  }
}