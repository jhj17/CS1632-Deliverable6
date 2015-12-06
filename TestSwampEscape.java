import static org.junit.Assert.*;
import org.junit.Test;

public class TestSwampEscape {
  
  @Test
  // Test parse arguments with all positive integers and 
  //  within the bounds of the swamp.
  public void testParseArgumentsSuccess() {
    String[] testArgs = new String[]{"20","5","5"};
    int[] target = new int[]{20,5,5};        
    int[] res = SwampEscape.parseArguments(testArgs);
    assertArrayEquals(res, target);
  }
  
  @Test
  // Test parse arguments with a string as an input.
  // Test should result in the first value being the error value.
  public void testParseArgumentsNotInteger() {
    String[] testArgs = new String[]{"20","abc","4"};
    int target = -1;        
    int[] res = SwampEscape.parseArguments(testArgs);
    assertEquals(res[0], target);
  }
  
  @Test
  // Test parse arguments with a negative integer as an input
  public void testParseArgumentsNegative() {
    String[] testArgs = new String[]{"20","-5","5"};
    int target = -1;        
    int[] res = SwampEscape.parseArguments(testArgs);
    assertEquals(res[0], target);
  }
  
  @Test
  // Test parse arguments with an incorrect number of arguments.
  public void testParseArgumentsIncorrectNumberArgs() {
    String[] testArgs = new String[]{"20","-5"};
    int target = -1;        
    int[] res = SwampEscape.parseArguments(testArgs);
    assertEquals(res[0], target);
  }
  
  @Test
  // Test parse arguments with a drop in position outside of the bounds
  //   of the swamp.
  public void testParseArgumentsPositionOutOfBounds() {
    String[] testArgs = new String[]{"20","5","25"};
    int target = -1;        
    int[] res = SwampEscape.parseArguments(testArgs);
    assertEquals(res[0], target);
  }
}
