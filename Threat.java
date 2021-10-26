import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Threat implements Support {



//https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html




  /*
    PARAM: row, col -> Represents the row and column of the spot where
                       
                       we want to know if anyone on the other team 
            is attacking this spot
           arow, acol -> Represents the potential attacking square from which
           an attack (*MIGHT) be coming from

           upcase -> Represents team of the potential victim

  */
  
  public static boolean isSpotUnderAttack(int row, int col, int arow, int acol, boolean upcase){

//make this work ^
    char a = board[arow][acol].name.charAt(0);
    boolean aTeam = Character.isUpperCase(a);

    
    // find upcase status both spots 
    // only do the work if they're different
    if(board[arow][acol].name.charAt(0) == e){
      return false;
    } else if (upcase == aTeam){
      return false; 
    }
    

    int rowDist = Math.abs(row - arow);
    int colDist = Math.abs(col - acol);
    int dist = Math.max(rowDist, colDist);

    System.out.println("\nThe distance between the target and the attacker is: " + dist);


    // At this point all major edge cases are handled
    // so we can not have too many in and do the work

    // we need an integer representing the attack range of the attacker
    int attackRange = distHashMap.get(a);

    // if the attackRange is less than the distance exit early with false
    if(attackRange < dist) return false;

    System.out.println("The attacker is in range! :o");
    // use a switch to determine if the attacker can attack this piece

    if (a == 'p' || a == 'P'){
      return Pawn.checkPawnMovePlace(arow, acol, row,col, aTeam);
    }

    else if (a == 'k' || a == 'K'){
      return true;
    }

    return false; 
    
  // end of the isSpotUnderAttack method 
  }

public static boolean isSpotSafe(int row, int col, int drow, int dcol, boolean upcase){

  //loops through and stores all possible locations the king can move to
  //need to check if any of the possible spots are under potential attack from other team's peices
  int rowDistance = Math.abs(drow - row);
  int colDistance = Math.abs(dcol - col);
  //array list syntax here
  ArrayList<int[]> a = new ArrayList<int[]>();

  for(int i = -1; i <= 1; i++){
    for(int j = - 1; j <= 1; j++){
      int[] location = new int[]{i, j};
      a.add(location);
    }
  }


   
  //https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
  // this is a cool syntax to iterate over all elements of an array list
  Main.checkLocations(board, upcase);

  Collection whiteLocationsCollection = whiteLocations.values();

  Collection blackLocationsCollection = blackLocations.values();

  Iterator<int []> whiteIterator = whiteLocationsCollection.iterator();

  Iterator<int []> blackIterator = blackLocationsCollection.iterator();
  
  for(int[] location : a){
    
    if(upcase){
      while (whiteIterator.hasNext()) {
        int [] b = whiteIterator.next();
        return isSpotUnderAttack(location[0], location[1], b[0], b[1], upcase);
      }
    } else {
        while (blackIterator.hasNext()) {
        int [] b = blackIterator.next();
        return isSpotUnderAttack(location[0], location[1], b[0], b[1], upcase);
      }
    }
  }

//incorperate new method checking attackers locations

  return true;
  
}


}


/*

  for every square on the board

  check to see if a piece is there... 
  if a piece is there... 

  check to see if that piece 

  MAX Threaten distance... 

  PAWN -> 1
  BISHOP -> 7
  HORSE -> 2
  QUEEN -> 7
  ROOK -> 7
  KING -> 1
  


*/