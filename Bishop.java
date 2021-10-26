
import java.util.Scanner;

public class Bishop extends Piece implements Support{

    public Bishop(int row, int col, String name, boolean upcase){
      this.row = row;
      this.col = col;
      this.name = name;
      this.upcase = upcase;
    }


  public static boolean checkBishopMove(int row, int col, int drow, int dcol, boolean upcase){
    
    boolean validMove   = checkBishopMoveDetails(row, col, drow, dcol, upcase);
    boolean placeNotBlocked = checkBetweenBishop(row, col, drow, dcol, upcase);

    return validMove && placeNotBlocked;
  }


  public static boolean checkBishopMoveDetails(int row, int col, int drow, int dcol, boolean upcase){
    int rowChange = Math.abs(row - drow);
    int colChange = Math.abs(col - dcol);

    System.out.println("The row change is: "  + rowChange);
    System.out.println("The col change is: "  + colChange);

    if(rowChange == colChange){
      return true;
    } else {
      return false;
    }

  }
   
  public static boolean checkBetweenBishop (int row, int col, int drow, int dcol, boolean upcase){

    char piece = board[row][col].name.charAt(0);

    if ( piece == 'B' || piece == 'b') {
      System.out.println("\n I am a bishop!");
    }
    else if ( piece == 'q' || piece == 'Q') {
      System.out.println("\n I am a Queen!");
    }
    else {
      System.out.println("\n INSIDE checkBetweenBishop: -> " + piece );
    }

    for(int i = 1; i < Math.abs(row - drow); i++){
      // starting higher than the target in terms of row
      // starting to the left of the target in terms of col
      System.out.println("The value of i is: " + i);
      if(row < drow && col < dcol){
        System.out.println("Heading South-East");
        System.out.println("ROW + i is: " + (row + i));
        System.out.println("COL + i is: " + (col + i));

        
        if(board[row + i][col + i] != null){

          char found = board[row + i][col + i].name.charAt(0);
          
          if(upcase == Character.isUpperCase(found)){
            System.out.println("you already have a peice there");
            return false;
          } else {
            System.out.println("a enemy peice is in your way");
            return false;
          }
        }
      
      // starting lower than target in terms of row
      // starting to the left of target in terms of col
      } else if(row > drow && col < dcol){
        System.out.println("Heading North-East");
        if(board[row - i][col + i]!= null){
          if(upcase == Character.isUpperCase(board[row - i][col + i].name.charAt(0))){
            System.out.println("you already have a peice there");
            return false;
          } else {
            System.out.println("a enemy peice is in your way");
            return false;
          }
        }
      
      // starting higher than target in terms of row
      // starting to the right of target in terms of col
      } else if(row < drow && col > dcol){
        System.out.println("Heading South-West");
        if(board[row + i][col - i] != null){
          if(upcase == Character.isUpperCase(board[row + i][col - i].name.charAt(0))){
            System.out.println("you already have a peice there");
            return false;
          } else {
            System.out.println("a enemy peice is in your way");
            return false;
          }
        }
      } else { //LOGIC IS WRONG
        System.out.println("Heading North-West");
        if(board[row - i][col - i] != null){
          //System.out.println("upcase is: " + upcase);
          if(upcase == Character.isUpperCase(board[row - i][col - i].name.charAt(0))){
            System.out.println("you already have a peice there");
            return false;
          } else {
            System.out.println("a enemy peice is in your way");
            return false;
          }
        }
      }
    }

    // NOTE -> Not sure true / false here 
    return true;
  }

}