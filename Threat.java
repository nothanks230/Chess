import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Threat implements Support {

 public static int brow = 0;
 public static int bcol = 0;

//https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html




  /*
    PARAM: row, col -> Represents the row and column of the spot where
                       
                       we want to know if anyone on the other team 
            is attacking this spot
           arow, acol -> Represents the potential attacking square from which
           an attack (*MIGHT) be coming from

           upcase -> Represents team of the potential victim


  */
  
  public static boolean canPieceBlock(int row, int col, int arow, int acol, boolean upcase){
    char a = board[arow][acol].name.charAt(0);
    boolean aTeam = Character.isUpperCase(a);

    if(board[arow][acol].name.charAt(0) == e){
      return false;
    } else if (upcase == aTeam){
      return false; 
    }

    int rowDist = Math.abs(row - arow);
    int colDist = Math.abs(col - acol);
    int dist = Math.max(rowDist, colDist);

    if (a == 'p' || a == 'P'){
      System.out.println("checking for pawn");
      if(Pawn.checkPawnMovePlace(arow, acol, row, col, aTeam)){
        brow = arow;
        bcol = acol;
      }
      return Pawn.checkPawnMovePlace(arow, acol, row, col, aTeam);
    }

    if (a == 'q' || a == 'Q'){
      
      System.out.println("checking if the queen can block");
        if(Queen.checkQueenBlock(arow, acol, row,col, aTeam)){
        brow = arow;
        bcol = acol;
      }
     
      boolean buddy =  Queen.checkQueenMove(arow, acol, row,col, aTeam);
      System.out.println("i am no longer checking if the queen can block!\n");
      return buddy;
    }

    if (a == 'r' || a == 'R'){
      if(Rook.checkRookBlock(arow, acol, row,col, aTeam)){
        brow = arow;
        bcol = acol;
      }
      return Rook.checkRookBlock(arow, acol, row,col, aTeam);
    }

    if (a == 'b' || a == 'B'){
           
      System.out.println("i am checking if the bishop can block!");
       if(Bishop.checkBishopBlock(arow, acol, row,col, aTeam)){
        brow = arow;
        bcol = acol;
      }
      boolean buddy = Bishop.checkBishopBlock(arow, acol, row,col, aTeam);
      System.out.println("i am no longer checking the bishop!\n");
      return buddy;
    }

    if (a == 'h' || a == 'H'){
      if(Knight.checkKnightMove(arow, acol, row,col, aTeam)){
        brow = arow;
        bcol = acol;
      }
      return Knight.checkKnightMove(arow, acol, row,col, aTeam);
    }


    else if (a == 'k' || a == 'K'){
      brow = arow;
      bcol = acol;
      return false;
    }

    return false; 
    
  }

  
  public static boolean isSpotUnderAttack(int row, int col, int arow, int acol, boolean upcase){
    
    char a = board[arow][acol].name.charAt(0);
    boolean aTeam = Character.isUpperCase(a);

    if(board[arow][acol].name.charAt(0) == e){
      return false;
    } else if (upcase == aTeam){
      return false; 
    }

    int rowDist = Math.abs(row - arow);
    int colDist = Math.abs(col - acol);
    int dist = Math.max(rowDist, colDist);

    int attackRange = distHashMap.get(a);

    if(attackRange < dist) return false;

    if (a == 'p' || a == 'P'){
      if(Pawn.checksForKing(arow, acol, row, col, aTeam)){
        brow = arow;
        bcol = acol;
      }
      return Pawn.checksForKing(arow, acol, row, col, aTeam);
    }

    if (a == 'q' || a == 'Q'){
      
      System.out.println("i am is spot uunder attck nder for the queen!");
        if(Queen.checkQueenMove(arow, acol, row,col, aTeam)){
        brow = arow;
        bcol = acol;
      }
     
      boolean buddy =  Queen.checkQueenMove(arow, acol, row,col, aTeam);
      System.out.println("i am no longer is spot uunder attck nder for the queen!\n");
      return buddy;
    }

    if (a == 'r' || a == 'R'){
      if(Rook.checkRookMove(arow, acol, row,col, aTeam)){
        brow = arow;
        bcol = acol;
      }
      return Rook.checkRookMove(arow, acol, row,col, aTeam);
    }

    if (a == 'b' || a == 'B'){
           
      System.out.println("i am is spot under attack for the bishop!");
       if(Bishop.checkBishopMove(arow, acol, row,col, aTeam)){
        brow = arow;
        bcol = acol;
      }
      boolean buddy = Bishop.checkBishopMove(arow, acol, row,col, aTeam);
      System.out.println("i am no longer is spot under attack for the bishop!\n");
      return buddy;
    }

    if (a == 'h' || a == 'H'){
      if(Knight.checkKnightMove(arow, acol, row,col, aTeam)){
        brow = arow;
        bcol = acol;
      }
      return Knight.checkKnightMove(arow, acol, row,col, aTeam);
    }


    else if (a == 'k' || a == 'K'){
      brow = arow;
      bcol = acol;
      return true;
    }

    return false; 
    
  // end of the isSpotUnderAttack method 
  }

public static boolean isSpotSafe(int row, int col, int drow, int dcol, boolean upcase){
  //returns true if spot is safe and good to move to

  //loops through and stores all possible locations the king can move to
  //need to check if any of the possible spots are under potential attack from other team's peices
  /*
  int rowDistance = Math.abs(drow - row);
  int colDistance = Math.abs(dcol - col);
  //array list syntax here
  ArrayList<int[]> a = new ArrayList<int[]>();

 
    
  }
  */


   
  //https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
  // this is a cool syntax to iterate over all elements of an array list
  Main.checkLocations(board, upcase);

  Collection whiteLocationsCollection = whiteLocations.values();

  Collection blackLocationsCollection = blackLocations.values();

  Iterator<int []> whiteIterator = whiteLocationsCollection.iterator();

  Iterator<int []> blackIterator = blackLocationsCollection.iterator();
  
  //for(int[] location : a){ //location[0] and location[1] to undo
    boolean theSpotIsSafe = true;
    if(upcase){
      while (whiteIterator.hasNext()) {
        int [] b = whiteIterator.next();
        // System.out.println("current peice is " + board[b[0]][b[1]].name);
        if(isSpotUnderAttack(drow, dcol, b[0], b[1], upcase)){
          //the spot is dangerous if it is under attack, so return false. it's not safe!
          theSpotIsSafe = false;
          break;
        } else {
          theSpotIsSafe = true;
        }
      }
      return theSpotIsSafe;
    } else {
        while (blackIterator.hasNext()) {
        int [] b = blackIterator.next();
        // System.out.println("current peice is " + board[b[0]][b[1]].name);
        if(isSpotUnderAttack(drow, dcol, b[0], b[1], upcase)){
        //the spot is dangerous if it is under attack, so return false. it's not safe!
          theSpotIsSafe = false;
          break;
        } else {
          theSpotIsSafe = true;
        }
      }
      return theSpotIsSafe;
    }
}

public static boolean isKingsSpotSafe(int row, int col, boolean upcase){

  Collection whiteLocationsCollection = whiteLocations.values();

  Collection blackLocationsCollection = blackLocations.values();

  Iterator<int []> whiteIterator = whiteLocationsCollection.iterator();

  Iterator<int []> blackIterator = blackLocationsCollection.iterator();
  
  //for(int[] location : a){ //location[0] and location[1] to undo
    boolean theKingsSpotIsSafe = true;
    if(upcase){
      while (whiteIterator.hasNext()) {
        int [] b = whiteIterator.next();
        // System.out.println("current peice is " + board[b[0]][b[1]].name);
        if(isSpotUnderAttack(row, col, b[0], b[1], upcase)){
          //the spot is dangerous if it is under attack, so return false. it's not safe!
          theKingsSpotIsSafe = false;
          break;
        } else {
          theKingsSpotIsSafe = true;
        }
      }
      return theKingsSpotIsSafe;
    } else {
        while (blackIterator.hasNext()) {
        int [] b = blackIterator.next();
        // System.out.println("current peice is " + board[b[0]][b[1]].name);
        if(isSpotUnderAttack(row, col, b[0], b[1], upcase)){
        //the spot is dangerous if it is under attack, so return false. it's not safe!
          theKingsSpotIsSafe = false;
          break;
        } else {
          theKingsSpotIsSafe = true;
        }
      }
      return theKingsSpotIsSafe;
    }
  } 
//incorporate new method checking attackers locations

  public static boolean isKingInCheckmate(int row, int col, boolean upcase){
  
    boolean isKingDoomed = false;

    //System.out.println("brow is: " + brow + "and bcol is: " + bcol);

    //System.out.println("Checking if your king is in checkmate...");

    Collection whiteLocationsCollection = whiteLocations.values();

    Collection blackLocationsCollection = blackLocations.values();

    Iterator<int []> whiteIterator = whiteLocationsCollection.iterator();

    Iterator<int []> blackIterator = blackLocationsCollection.iterator();

      // assume we are not in checkmate 
      // arr = [false false false]
      
      
      //base check -- if surrounding to the king is safe

    int[][] location = new int[8][2];
      

    location[0][0] = row - 1;
    location[0][1] = col - 1;
    location[1][0] = row - 1; 
    location[1][1] = col;
    location[2][0] = row - 1;
    location[2][1] = col + 1;
    location[3][0] = row;
    location[3][1] = col - 1;
    location[4][0] = row;
    location[4][1] = col + 1;
    location[5][0] = row + 1;
    location[5][1] = col - 1;
    location[6][0] = row + 1;
    location[6][1] = col;
    location[7][0] = row + 1;
    location[7][1] = col + 1;
    
    for(int i = 0; i <= 7; i++){

      if (location[i][0] < 0 || location[i][1] < 0 || location[i][0] > 7 || location[i][1] > 7) {
        } else { // checks if the king can run away safely

          // this checks for the square around if they can move
          //arow acol is cols of attacker
          if(isSpotSafe(row, col, location[i][0], location[i][1], upcase) && board[location[i][0]][location[i][1]] == null){
            System.out.println("\n\n\n\nyour king can escape! move away from your attacker to get out of check");
            isKingDoomed = false;
            return isKingDoomed; 
        }
      }
    }
    // this sees if we can attack the attacker
    if(!upcase){
      while (whiteIterator.hasNext()) {
        int [] b = whiteIterator.next();
        if(isSpotUnderAttack(brow, bcol, b[0], b[1], upcase)){
        //the spot is dangerous if it is under attack, so return false. it's not safe!
        isKingDoomed = false;
        System.out.println("you can attack! attack the piece attacking your king to get out of check.");
        return isKingDoomed;
        } else {
        isKingDoomed = true;
        System.out.println("you cannot attack! you are in checkmate");
        }
      }
      // isSpotUnderAttack(row, col, brow, bcol, upcase);
      // System.out.println("pathAttack: " + pathAttack);
      // isKingDoomed = true;
    } else {
      while (blackIterator.hasNext()) {
        int [] b = blackIterator.next();
        //System.out.println("array list b is: " + b[0] + " " + b[1]);
        //System.out.println("current peice is " + board[b[0]][b[1]].name);
        if(isSpotUnderAttack(brow, bcol, b[0], b[1], !upcase)){
        //the spot is dangerous if it is under attack, so return false. it's not safe!
          isKingDoomed = false;
          System.out.println("you can attack! attack the piece attacking your king to get out of check.");
          return isKingDoomed;
        } else {
          
          System.out.println("you cannot attack! checking if  you can block");

          isSpotUnderAttack(row, col, brow, bcol, upcase);
          System.out.println("pathAttack is : " + pathAttack);
          int counter = 0;
          for(int[] Array : pathAttack){
            Collection blackPathCollection = blackLocations.values();
            Iterator<int []> blackPathIterator = blackLocationsCollection.iterator();
            System.out.println("I'm on pathAttack's " + counter + " element" + "the location is: " + Array[0] + ", " + Array[1]);
            
            while(blackPathIterator.hasNext()){
              int [] c = blackPathIterator.next();
              System.out.println("the peice we are checking is: " + board[c[0]][c[1]].name);
    
              if(canPieceBlock(Array[0], Array[1], c[0], c[1], !upcase)){
              //the spot is dangerous if it is under attack, so return false. it's not safe!
                isKingDoomed = false;
                System.out.println("you can block! block the piece attacking your king to get out of check.");
                return isKingDoomed;
              } else {
                  isKingDoomed = true;
                  System.out.println("the peice " + board[c[0]][c[1]].name + " cannot block");
                }
              }
            
            counter++;
          }  
          System.out.println("you cannot block! you are in checkmate");
          
          // System.out.println("pathAttack row: " + pathAttack[] + "pathAttack col: " + pathAttack[1]);
          return isKingDoomed;
        }
      }
    }

      
  // assume we found the attacker
  // which square are in the path btween attacker and the king
  // do we have any pieces that can block

  //do not check for pawn & horse

  // start with rook attack
  // get attacking rook coord
  // get king coord
  // calc difference in coords
  // if row is 0 then horizontal attack
  // if col is 0 then vertical attack

  //find all available places in between king and attacker, then
  //for each place, see if you can get one of your pieces there, looping through white or black locations 
  
  /*
  if(!upcase){
    while (whiteIterator.hasNext()) {
      int [] b = whiteIterator.next();
          if(brow - row == 0){
            if(bcol - col > 0){
              for(int k = bcol; k < (bcol - col); k++){
                return isSpotUnderAttack(brow, bcol, b[0], b[1] + k, upcase);
              }
              //attacker to the left
            } else if (bcol - col < 0){
              //attacker to the right
              for(int k = bcol; k < (bcol - col); k--){
                return isSpotUnderAttack(brow, bcol, b[0], b[1] + k, upcase);
              }
            }     
          } else if (bcol - col == 0){
              if(brow - row > 0){
                for(int k = brow; k < (brow - row); k--){
                  return isSpotUnderAttack(brow, bcol, b[0], b[1] + k, upcase);
              }
                //attacker to the bottom
              } else if (brow - row < 0){
                for(int k = brow; k < (brow - row); k++){
                  return isSpotUnderAttack(brow, bcol, b[0], b[1] + k, upcase);
                //attacker to the above
              }
            }
          }
        }
        
  // figure out who the attacker is and their path


  // arow & acol

  // figure out if we can attack the piece giving check

  // figure out if we can block
        }
*/          
    
    // return false;
  
    return false;
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