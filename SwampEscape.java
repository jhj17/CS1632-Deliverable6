/*
 * Jeffrey Josephs
 * Escape the Swamp
 * CS 1632: Deliverable 6
 * Modify existing program to be tested with JUnit
 *
 * Description: Program will take in the size of an array (swamp), and the 
 *   program will generate a swamp from it and then find all possible paths 
 *   to get out of the swamp
 * 
 * USAGE: java SwampEscape <dimension> <dropInX> <dropInY>
 */

import java.io.*;
import java.util.*;
import java.lang.Exception;

public class SwampEscape
{
  static final int RETURN_ERROR = -1;
  
  public static void main(String[] args) {
    // Arguments should be dimension, dropInX, and then dropInY
    int[] parsedArguments = new int[3];
    parsedArguments = parseArguments(args);
    
    // Error Check, dimension will be -1 if error parsing
    // Extract values from returned array
    int n = 0, dropInX = 0, dropInY = 0;
    if(parsedArguments[0] != RETURN_ERROR) {
      n = parsedArguments[0];
      dropInX = parsedArguments[1];
      dropInY = parsedArguments[2];
 
      // Create the swamp
      Swamp theSwamp = new Swamp(n);
      
      // Display the swamp
      theSwamp.displaySwamp();
      
      // Find all possible paths out of the swamp
      StringBuilder path = new StringBuilder();
      System.out.println("\nEscape Paths:");
      theSwamp.getEscapePath(dropInX, dropInY, path);
    }
  }
  
  /*
   * Function to parse the command line arguments
   * @param args Command line arguments
   * @return Three element integer array with dimension, drop in X, and drop in Y
   */ 
  public static int[] parseArguments(String[] args) {
    int[] return_array = new int[3];
    int n = 0, dropInX = 0, dropInY = 0;
    
    if(args.length != 3) {       
      // Check first that number of arguments is correct
      // Display error message and make dimension an error value
      System.out.println("Not enough arguments!");
      System.out.println("USAGE: java SwampEscape <dimension> <dropInX> <dropInY>");
      return_array[0] = RETURN_ERROR;
    }
    else {
      // Check that arguments are integers
      try {
        n = Integer.parseInt(args[0]);
        dropInX = Integer.parseInt(args[1]);
        dropInY = Integer.parseInt(args[2]);
      }
      catch(NumberFormatException e) {
        System.out.println("Command line arguments not an integer!");
        return_array[0] = RETURN_ERROR;
        return return_array;
      }
      
      if(n < 0 || dropInX < 0 || dropInY < 0) {
        // Check that all arguments are postive
        System.out.println("Arguments must be positive integers!");
        return_array[0] = RETURN_ERROR;
      }
      else if(dropInX  >= n || dropInX  < 0 || dropInY  >= n || dropInY  < 0) {
        // Check that drop in position is within the bounds of the swamp
        // Display error message and make dimension an error value
        System.out.println("Drop in position not within bounds of swamp!");
        return_array[0] = RETURN_ERROR;
      }
      else {
       // Arguments are valid, assign to array 
        return_array[0] = n;
        return_array[1] = dropInX;
        return_array[2] = dropInY;
      }
    }
    
    return return_array;
  }
}
