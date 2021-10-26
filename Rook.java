/*
  ROOK PLAN

  1. Rooks can move up down left and right all the way to the edge of the board unless blocked 
  2. Rooks can attack any peice of the other player's in their path 
  3. Rooks can only attack peices not blocked by other peices
  4. Rooks can only attack once per turn
  

  R     p k q  

*/


public class Rook extends Piece implements Support{
  
    public Rook(int row, int col, String name, boolean upcase){
      this.row = row;
      this.col = col;
      this.name = name;
      this.upcase = upcase;
    }

  public static boolean checkRookMove(int row, int col, int drow, int dcol, boolean upcase) {

    // TODO -> Integrate the support methods for Rook
    System.out.println("The row    choice      in question is: " + row);
    System.out.println("The row    destination in question is: " + drow);
    System.out.println("\nThe column choice      in question is: " + col);
    System.out.println("The column destination in question is: " + dcol);



    boolean validMove   = checkRookMoveDetails(row, col, drow, dcol, upcase);
    boolean vNotBlocked = checkVPathNotBlocked(row, col, drow, dcol, upcase);
    boolean hNotBlocked = checkHPathNotBlocked(row, col, drow, dcol, upcase);

    /*
      consider outputting if the move is valid
      is the vertical blocked... 
      is the horizontal not blocked..

    */

    System.out.println("\nThe result of valid move is: " + validMove);
    System.out.println("The result of vNotBlocked is: " + vNotBlocked);
    System.out.println("The result of hNotBlocked is: " + hNotBlocked);
    
    System.out.println("\n");


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
        System.out.printf("The fullrow of %s is less than the destination of %s.\n", fullrow, drow);
        System.out.println(fullrow);
        if(board[fullrow][col] != null){

          char found = board[fullrow][col].name.charAt(0);

          if(Character.isUpperCase(found) != upcase){
            System.out.println("your path to your destination is blocked by a enemy");
            drow = fullrow;
            return false;
          } else {
            System.out.println("your path to your destination is blocked by one of your other peices, please pick a new desination");
            return false;
          }
        } 
        fullrow ++;
        System.out.println(fullrow);
      }

      // return true here? 
      // because we got there without having a problem?

    } else if (drow < row){
      
      // fullrow should be row -1 :O
      fullrow = row - 1;
      while(fullrow > drow) {
       
        System.out.println(fullrow);
        if(board[fullrow][col] != null){
          
          char found = board[fullrow][col].name.charAt(0);

          if(Character.isUpperCase(found) != upcase){
            System.out.println("your path to your destination is blocked by a enemy");
            drow = fullrow;
            return false;
          } else {
            System.out.println("your path to your destination is blocked by one of your other peices, please pick a new desination");
            return false;
          }
        } 
        fullrow --;
        System.out.println(fullrow);
      }
      
    }

    // default return area... 
    return true;
  }

  public static boolean checkHPathNotBlocked (int row, int col, int drow, int dcol, boolean upcase) {
    
    System.out.println("\n My name is Rook and I am here to protect the royal family!!!\n");

    // to check for a vertical 
    // while loop with a break! :D 
    // compare drow vs row for direction 
    int fullcol = 0;

    System.out.println("The column choice      in question is: " + col);
    System.out.println("The column destination in question is: " + dcol);
    // if there is no change in horizontal col vs desired col
    if(col == dcol){
      System.out.println("Same column...");
      return true;
    }
  
    if(dcol > col){

      System.out.println("\nThe destination is greater than the beginning.");
      fullcol = col + 1; 
      while(fullcol < dcol){
        System.out.println(fullcol);
        if(board[row][fullcol] != null){
          char found = board[row][fullcol].name.charAt(0);

          if(Character.isUpperCase(found) != upcase){
            System.out.println("your path to your destination is blocked by a enemy");
            dcol = fullcol;
            return false;
          } else {
            System.out.println("your path to your destination is blocked by one of your other peices, please pick a new desination");
            return false;
          }
        } 
        fullcol ++;
        System.out.println(fullcol);
      }
      return true;
    } else if (dcol < col){
      System.out.println("\nThe destination is less than the beginning.");
      fullcol = col - 1; 
      while(fullcol > dcol){
        System.out.println(fullcol);
        if(board[row][fullcol] != null){
          char found = board[row][fullcol].name.charAt(0);

          if(Character.isUpperCase(found) != upcase){
            System.out.println("your path to your destination is blocked by a enemy");
            dcol = fullcol;
            return false;
          } else {
            System.out.println("your path to your destination is blocked by one of your other peices, please pick a new desination");
            return false;
          }
        } 
        fullcol --;
        System.out.println(fullcol);
      }
      return true;
      
    }
    return true;
  }
// end of the Rook class 
}
