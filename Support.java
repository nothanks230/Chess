import java.util.HashSet;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;

public interface Support {
	
	Piece [][] board = new Piece[8][8];

  // System.out.println(board);
  
	
	//String e = new String("");
  // note to future self, define a "null" string value in Piece.java and check for that instead of trying to check for the literal null. Takes slightly more computation time but solves the turning a null into a string problem.
   
  char e = '\u0000';
	Knight empty = new Knight(-1, -1, "", true);
	
	HashSet<Character> promotionsLow = new HashSet<Character>();
	HashSet<Character> promotionsUp = new HashSet<Character>();

  // Let's make a HashMap<Character , Integer> for white team and one for black team! 

  HashMap<Character, Integer> whiteHashMap = new HashMap<Character, Integer>();
  HashMap<Character, Integer> blackHashMap = new HashMap<Character, Integer>();
  HashMap<String, int[]> whiteLocations = new HashMap<String, int[]>();
  HashMap<String, int[]> blackLocations = new HashMap<String, int[]>();
  HashMap<Character, Integer> distHashMap = new HashMap<Character, Integer>();

  

}