import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class TestSwamp {
  // Need to monitor the output stream
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  
  @Before
  // Set up output stream to monitor System.out.println
  public void setUpStream() {
    System.setOut(new PrintStream(outContent));
  }
  
  @Test
  // Test create swamp with medium sized dimension.
  // Swamp should be a 10x10 array with random contents, 
  //   will only check size of output array.
  public void testCreateSwamp() {
    int testN = 10;
    Swamp testSwamp = new Swamp(testN);
    testSwamp.createSwamp();
    
    // Assert on both height and width of the array
    assertEquals(testN, testSwamp.swamp.length);
    assertEquals(testN, testSwamp.swamp[0].length);
  }
  
  @Test
  // Test display swamp
  // Given an already defined swamp, test expected output
  //  of displaySwamp function
  public void testDisplaySwamp() {
    StringBuilder tempSwampString = new StringBuilder();
    int testN = 5;
    Swamp testSwamp = new Swamp(testN);
    testSwamp.createSwamp();
    tempSwampString = testSwamp.swampToString();
    
    // Get contents line-by-line
    for(int i = 0; i < testN+2; i++) {
      String[] lines = tempSwampString.toString().split("\\n");
      if(i == 0 || i == testN+1)      // Check top and bottom border
        assertEquals(lines[i],"- - - - - - - ");
      else {                          // Check swamp data
        String[] contents = lines[i].split(" ");
        for(int j = 0; j < testN+2; j++) {
          if(j == 0 || j == testN+1)
            assertEquals(contents[j],"|");
          else {            
            if(contents[j].equals("0") || contents[j].equals("1"))
              assertTrue(true);
            else
              fail();
          }
        }
      }
    }
  }
  
  @Test
  // Test function to get the escape path.
  // Given a swamp with only one path, test
  //  that the path is correct.
  public void testGetEscapePathSuccess() {
    int testN = 4;
    Swamp testSwamp = new Swamp(testN);
    int dropInX = 1, dropInY = 2;
    
    // Set the created swamp to a set 2-D array with only 1 path
    int[][] testArr = {
      {0,0,0,0},
      {1,1,0,0},
      {0,1,0,0},
      {0,0,0,0}  
    };
    testSwamp.swamp = testArr;
    
    // Because swamp is set, assert on expected path
    String expectedPath = "(1,2)(1,1)(0,1)\n";
    StringBuilder path = new StringBuilder();
    testSwamp.getEscapePath(dropInX, dropInY, path);
    
    // Test outputted path to expected path
    assertEquals(expectedPath, outContent.toString());
  }
  
  @Test
  // Test function to get the escape path with an invalid
  //   drop in location.
  // Given a swamp with only one path, test
  //  that the location is invalid.
  public void testGetEscapePathInvalidDropIn() {
    int testN = 4;
    Swamp testSwamp = new Swamp(testN);
    int dropInX = 3, dropInY = 3;
    
    // Set the created swamp to a set 2-D array with only 1 path
    int[][] testArr = {
      {0,0,0,0},
      {1,1,0,0},
      {0,1,0,0},
      {0,0,0,0}  
    };
    testSwamp.swamp = testArr;
    
    // Because swamp is set, assert on expected path
    String expectedPath = "Drop in position invalid!\n";
    StringBuilder path = new StringBuilder();
    testSwamp.getEscapePath(dropInX, dropInY, path);
    
    // Test outputted path to expected path
    assertEquals(expectedPath, outContent.toString());
  }
  
  @Test
  // Test function to get the escape path with no path found.
  // Given a swamp with no path, test
  //  that no path is found.
  public void testGetEscapePathNoPath() {
    int testN = 4;
    Swamp testSwamp = new Swamp(testN);
    int dropInX = 1, dropInY = 2;
    
    // Set the created swamp to a set 2-D array with only 1 path
    int[][] testArr = {
      {0,0,0,0},
      {0,1,0,0},
      {0,1,0,0},
      {0,0,0,0}  
    };
    testSwamp.swamp = testArr;
    
    // Because swamp is set, assert on expected path
    String expectedPath = "No Path Found!\n";
    StringBuilder path = new StringBuilder();
    testSwamp.getEscapePath(dropInX, dropInY, path);
    
    // Test outputted path to expected path
    assertEquals(expectedPath, outContent.toString());
  }
}