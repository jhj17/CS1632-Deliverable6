/*
 * Jeffrey Josephs
 * Deliverable 6
 * Project: Escape The Swamp
 * File: TheSwamp.java
 * Description: Interface for Swamp class
 */

interface TheSwamp {
  public void createSwamp();
  public StringBuilder swampToString();
  public void getEscapePath(int currRow, int currCol, StringBuilder path);
}