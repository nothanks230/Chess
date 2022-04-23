/*
  ROOK PLAN

  1. Rooks can move up down left and right all the way to the edge of the board unless blocked 
  2. Rooks can attack any peice of the other player's in their path 
  3. Rooks can only attack peices not blocked by other peices
  4. Rooks can only attack once per turn
  

  R     p k q  

*/
import java.util.ArrayList;

public class Rook extends Piece implements Support{
  
  
    public Rook(int row, int col, String name, boolean upcase){
      this.row = row;
      this.col = col;
      this.name = name;
      this.upcase = upcase;
    }

  public static boolean checkRookBlock(int row, int col, int drow, int dcol, boolean upcase) {
    boolean validMove   = checkRookMoveDetails(row, col, drow, dcol, upcase);
    boolean vNotBlocked = checkVPathNotBlockedBlocking(row, col, drow, dcol, upcase);
    boolean hNotBlocked = checkHPathNotBlockedBlocking(row, col, drow, dcol, upcase);

    return validMove && vNotBlocked && hNotBlocked;

  }
  public static boolean checkRookMove(int row, int col, int drow, int dcol, boolean upcase) {
    pathAttack.clear();
    boolean validMove   = checkRookMoveDetails(row, col, drow, dcol, upcase);
    boolean vNotBlocked = checkVPathNotBlocked(row, col, drow, dcol, upcase);
    boolean hNotBlocked = checkHPathNotBlocked(row, col, drow, dcol, upcase);

    return validMove && vNotBlocked && hNotBlocked;

  }

  // Non-conforming rook-style move? 
  public static boolean checkRookMoveDetails(int row, int col, int drow, int dcol, boolean upcase) {
    
    if(drow != row && dcol == col){
       return true;
	  } else if (drow == row && dcol != col){
     return true;
    } else {
     return false;
    }

  // end of the checkRookMoveDetails method 
  }


  // might need to add boolean of upcade 
  public static boolean checkVPathNotBlocked (int row, int col, int drow, int dcol, boolean upcase) {
    
    // to check for a vertical 
    // while loop with a break! :D 
    // compare drow vs row for direction 
    int fullrow = 0;

    // This is a horizontal move so we return true early 
    if(row == drow){ return true; }

    // we are trying to move to the south
    if(drow > row){
      fullrow = row + 1;

      //int row_check = row + 1
      while(fullrow < drow){
        // System.out.printf("The fullrow of %s is less than the destination of %s.\n", fullrow, drow);
        // System.out.print(fullrow);
        if(board[fullrow][col] != null){

          char found = board[fullrow][col].name.charAt(0);

          if(Character.isUpperCase(found) != upcase){
            //System.out.print("your path to your destination is blocked by a enemy");
            drow = fullrow;
            return false;
          } else {
            //System.out.print("your path to your destination is blocked by one of your other peices, please pick a new desination");
            return false;
          }
        } 
        int[] location = new int[2];
        location[0] = fullrow;
        location[1] = col;
        System.out.println(location[0] + location[1]);
        pathAttack.add(location);
        
          
        fullrow ++;
        //System.out.print(fullrow);
      }

      // return true here? 
      // because we got there without having a problem?

    } else if (drow < row){
      
      // fullrow should be row -1 :O
      fullrow = row - 1;
      while(fullrow > drow) {
       
        // System.out.print(fullrow);
        if(board[fullrow][col] != null){
          
          char found = board[fullrow][col].name.charAt(0);

          if(Character.isUpperCase(found) != upcase){
            //System.out.print("your path to your destination is blocked by a enemy");
            drow = fullrow;
            return false;
          } else {
            //System.out.print("your path to your destination is blocked by one of your other peices, please pick a new desination");
            return false;
          }
        }
        int[] location = new int[2];
        location[0] = fullrow;
        location[1] = col;
        System.out.println(location[0] + location[1]);
        pathAttack.add(location);
        fullrow --;
        //System.out.print(fullrow);
      }
      
    }

    // default return area... 
    return true;
  }

  public static boolean checkHPathNotBlocked (int row, int col, int drow, int dcol, boolean upcase) {
    

    // to check for a vertical 
    // while loop with a break! :D 
    // compare drow vs row for direction 
    int fullcol = 0;

    // System.out.print("The column choice      in question is: " + col);
    // System.out.print("The column destination in question is: " + dcol);
    // if there is no change in horizontal col vs desired col
    if(col == dcol){
      //System.out.print("Same column...");
      return true;
    }
  
    if(dcol > col){

      // System.out.print("\nThe destination is greater than the beginning.");
      fullcol = col + 1; 
      while(fullcol < dcol){
        //System.out.print(fullcol);
        if(board[row][fullcol] != null){
          char found = board[row][fullcol].name.charAt(0);

          if(Character.isUpperCase(found) != upcase){
            //System.out.print("your path to your destination is blocked by a enemy");
            dcol = fullcol;
            return false;
          } else {
            //System.out.print("your path to your destination is blocked by one of your other peices, please pick a new desination");
            return false;
          }
        } 
        int[] location = new int[2];
        location[0] = row;
        location[1] = fullcol;
        System.out.println(location[0] + location[1]);
        pathAttack.add(location);
        fullcol ++;
        //System.out.print(fullcol);
      }
      return true;
    } else if (dcol < col){
      // System.out.print("\nThe destination is less than the beginning.");
      fullcol = col - 1; 
      while(fullcol > dcol){
        //System.out.print(fullcol);
        if(board[row][fullcol] != null){
          char found = board[row][fullcol].name.charAt(0);

          if(Character.isUpperCase(found) != upcase){
            //System.out.print("your path to your destination is blocked by a enemy");
            dcol = fullcol;
            return false;
          } else {
            //System.out.print("your path to your destination is blocked by one of your other peices, please pick a new desination");
            return false;
          }
        }
        int[] location = new int[2];
        location[0] = row;
        location[1] = fullcol;
        System.out.println(location[0] + location[1]);
        pathAttack.add(location);
        fullcol --;
        //System.out.print(fullcol);
      }
      return true;
      
    }
    return true;
  }

  public static boolean checkVPathNotBlockedBlocking (int row, int col, int drow, int dcol, boolean upcase) {

    // to check for a vertical 
    // while loop with a break! :D 
    // compare drow vs row for direction 
    int fullrow = 0;

    // This is a horizontal move so we return true early 
    if(row == drow){ return true; }

    // we are trying to move to the south
    if(drow > row){
      fullrow = row + 1;

      //int row_check = row + 1
      while(fullrow < drow){
        // System.out.printf("The fullrow of %s is less than the destination of %s.\n", fullrow, drow);
        // System.out.print(fullrow);
        if(board[fullrow][col] != null){

          char found = board[fullrow][col].name.charAt(0);

          if(Character.isUpperCase(found) != upcase){
            //System.out.print("your path to your destination is blocked by a enemy");
            drow = fullrow;
            return false;
          } else {
            //System.out.print("your path to your destination is blocked by one of your other peices, please pick a new desination");
            return false;
          }
        } 
        fullrow ++;
        //System.out.print(fullrow);
      }

      // return true here? 
      // because we got there without having a problem?

    } else if (drow < row){
      
      // fullrow should be row -1 :O
      fullrow = row - 1;
      while(fullrow > drow) {
       
        // System.out.print(fullrow);
        if(board[fullrow][col] != null){
          
          char found = board[fullrow][col].name.charAt(0);

          if(Character.isUpperCase(found) != upcase){
            //System.out.print("your path to your destination is blocked by a enemy");
            drow = fullrow;
            return false;
          } else {
            //System.out.print("your path to your destination is blocked by one of your other peices, please pick a new desination");
            return false;
          }
        }
        fullrow --;
        //System.out.print(fullrow);
      }
      
    }

    // default return area... 
    return true;
  }

    public static boolean checkHPathNotBlockedBlocking (int row, int col, int drow, int dcol, boolean upcase) {
    // to check for a vertical 
    // while loop with a break! :D 
    // compare drow vs row for direction 
    int fullcol = 0;
    // System.out.print("The column choice      in question is: " + col);
    // System.out.print("The column destination in question is: " + dcol);
    // if there is no change in horizontal col vs desired col
    if(col == dcol){
      //System.out.print("Same column...");
      return true;
    }
    if(dcol > col){
      // System.out.print("\nThe destination is greater than the beginning.");
      fullcol = col + 1; 
      while(fullcol < dcol){
        //System.out.print(fullcol);
        if(board[row][fullcol] != null){
          char found = board[row][fullcol].name.charAt(0);
          if(Character.isUpperCase(found) != upcase){
            //System.out.print("your path to your destination is blocked by a enemy");
            dcol = fullcol;
            return false;
          } else {
            //System.out.print("your path to your destination is blocked by one of your other peices, please pick a new desination");
            return false;
          }
        }
        fullcol ++;
        //System.out.print(fullcol);
      }
      return true;
    } else if (dcol < col){
      // System.out.print("\nThe destination is less than the beginning.");
      fullcol = col - 1; 
      while(fullcol > dcol){
        //System.out.print(fullcol);
        if(board[row][fullcol] != null){
          char found = board[row][fullcol].name.charAt(0);

          if(Character.isUpperCase(found) != upcase){
            //System.out.print("your path to your destination is blocked by a enemy");
            dcol = fullcol;
            return false;
          } else {
            //System.out.print("your path to your destination is blocked by one of your other peices, please pick a new desination");
            return false;
          }
        }
        fullcol--;
        //System.out.print(fullcol);
      }
      return true;
    }
    return true;
  }
// end of the Rook class 
}
