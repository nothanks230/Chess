 
import java.util.Scanner;
import java.util.ArrayList;

public class Bishop extends Piece implements Support{
  //static ArrayList<int[]> pathAttack = new ArrayList<int[]>();
  
    public Bishop(int row, int col, String name, boolean upcase){
      this.row = row;
      this.col = col;
      this.name = name;
      this.upcase = upcase;
      // this.pathAttack = pathAttack;
    }


  public static boolean checkBishopBlock(int row, int col, int drow, int dcol, boolean upcase){
    
    boolean validMove   = checkBishopMoveDetails(row, col, drow, dcol, upcase);
    boolean placeNotBlocked = checkBetweenBishopBlock(row, col, drow, dcol, upcase);
    // int counter = 0;
    // for(int[] Array : pathAttack){
    //   System.out.println("I'm on pathAttack's " + counter + " element");
    //   for(int a: Array){
    //     System.out.println(a);
    //   }
    //   counter++;
    // }

    return validMove && placeNotBlocked;
  }

  public static boolean checkBishopMove(int row, int col, int drow, int dcol, boolean upcase){
    
    boolean validMove   = checkBishopMoveDetails(row, col, drow, dcol, upcase);
    boolean placeNotBlocked = checkBetweenBishop(row, col, drow, dcol, upcase);
    // int counter = 0;
    // for(int[] Array : pathAttack){
    //   System.out.println("I'm on pathAttack's " + counter + " element");
    //   for(int a: Array){
    //     System.out.println(a);
    //   }
    //   counter++;
    // }

    return validMove && placeNotBlocked;
  }

  public static boolean checkBishopMoveDetails(int row, int col, int drow, int dcol, boolean upcase){
    pathAttack.clear();
    int rowChange = Math.abs(row - drow);
    int colChange = Math.abs(col - dcol);

    /*
    System.out.println("The row change is: "  + rowChange);
    System.out.println("The col change is: "  + colChange);
    */

    if(rowChange == colChange){
      return true;
    } else {
      return false;
    }

  }
   
  public static boolean checkBetweenBishop (int row, int col, int drow, int dcol, boolean upcase){

    char piece = board[row][col].name.charAt(0);

    if ( piece == 'B' || piece == 'b') {
     // System.out.println("\n I am a bishop!");
    }
    else if ( piece == 'q' || piece == 'Q') {
    //  System.out.println("\n I am a Queen!");
    }
    else {
     // System.out.println("\n INSIDE checkBetweenBishop: -> " + piece );
    }
    System.out.println("this fubction is running!");
    for(int i = 1; i < Math.abs(row - drow); i++){
      // starting higher than the target in terms of row
      // starting to the left of the target in terms of col
      System.out.println("The value of i is: " + i);
      System.out.println("row = " + row + " col is " + col + " drow is = " + drow + " dcol = " + dcol);
      if(row < drow && col < dcol && row < 8 && col < 8 && row > -1 && col > -1 && row + i < 8 && col + i < 8 && row + i > -1 && col + i > -1){
        /*
        System.out.println("Heading South-East");
        System.out.println("ROW + i is: " + (row + i));
        System.out.println("COL + i is: " + (col + i));
        */
        
        if(board[row + i][col + i] != null){

          char found = board[row + i][col + i].name.charAt(0);
          
          if(upcase == Character.isUpperCase(found)){
            //System.out.println("you already have a peice there");
            return false;
          } else {
            //System.out.println("a enemy peice is in your way");
            return false;
          }
        }
        System.out.println("row = " + row + " col is " + col + " i is " + i);
        int[] location = new int[2];
        location[0] = row + i;
        location[1] = col + i;
        System.out.println(location[0] + " " + location[1]);
        pathAttack.add(location);
      
      // starting lower than target in terms of row
      // starting to the left of target in terms of col
      } else if(row > drow && col < dcol && row < 8 && col < 8 && row > -1 && col > -1 && row - i < 8 && col + i < 8 && row - i > -1 && col + i > -1){
        //System.out.println("Heading North-East");
        System.out.println("col is: " + col);
        if(board[row - i][col + i]!= null){
          if(upcase == Character.isUpperCase(board[row - i][col + i].name.charAt(0))){
            //System.out.println("you already have a peice there");
            return false;
          } else {
            //System.out.println("a enemy peice is in your way");
            return false;
          }
        }
        System.out.println("row = " + row + " col is " + col + " i is " + i);
        int[] location = new int[2];
        location[0] = row - i;
        location[1] = col + i;
        System.out.println(location[0] + " " + location[1]);
        pathAttack.add(location);
      
      // starting higher than target in terms of row
      // starting to the right of target in terms of col
      } else if(row < drow && col > dcol && row < 8 && col < 8 && row > -1 && col > -1 && row + i < 8 && col - i < 8 && row + i > -1 && col - i > -1){
        //System.out.println("Heading South-West");
        if(board[row + i][col - i] != null){
          if(upcase == Character.isUpperCase(board[row + i][col - i].name.charAt(0))){
            //System.out.println("you already have a peice there");
            return false;
          } else {
            //System.out.println("a enemy peice is in your way");
            return false;
          }
        }
        System.out.println("row = " + row + " col is " + col + " i is " + i);
        int[] location = new int[2];
        location[0] = row + i;
        location[1] = col - i;
        System.out.println(location[0] + " " + location[1]);
        pathAttack.add(location);
      } else if (row > drow && col > dcol && row < 8 && col < 8 && row > -1 && col > -1 && row - i < 8 && col - i < 8 && row - i > -1 && col - i > -1){ //LOGIC IS WRONG
        //System.out.println("Heading North-West");
        if(board[row - i][col - i] != null){
          //System.out.println("upcase is: " + upcase);
          if(upcase == Character.isUpperCase(board[row - i][col - i].name.charAt(0))){
            //System.out.println("you already have a peice there");
            return false;
          } else {
            //System.out.println("a enemy peice is in your way");
            return false;
          }
        }
        System.out.println("row = " + row + " col is " + col + " i is " + i);
        int[] location = new int[2];
        location[0] = row - i;
        location[1] = col - i;
        System.out.println(location[0] + " " + location[1]);
        pathAttack.add(location);
      }
    }

    // NOTE -> Not sure true / false here 
    //
    
    return true;
  }

  public static boolean checkBetweenBishopBlock (int row, int col, int drow, int dcol, boolean upcase){

    char piece = board[row][col].name.charAt(0);

    if ( piece == 'B' || piece == 'b') {
     // System.out.println("\n I am a bishop!");
    }
    else if ( piece == 'q' || piece == 'Q') {
    //  System.out.println("\n I am a Queen!");
    }
    else {
     // System.out.println("\n INSIDE checkBetweenBishop: -> " + piece );
    }
    System.out.println("this fubction is running!");
    for(int i = 1; i < Math.abs(row - drow); i++){
      // starting higher than the target in terms of row
      // starting to the left of the target in terms of col
      System.out.println("The value of i is: " + i);
      System.out.println("row = " + row + " col is " + col + " drow is = " + drow + " dcol = " + dcol);
      if(row < drow && col < dcol && row < 8 && col < 8 && row > -1 && col > -1 && row + i < 8 && col + i < 8 && row + i > -1 && col + i > -1){
        /*
        System.out.println("Heading South-East");
        System.out.println("ROW + i is: " + (row + i));
        System.out.println("COL + i is: " + (col + i));
        */
        
        if(board[row + i][col + i] != null){

          char found = board[row + i][col + i].name.charAt(0);
          
          if(upcase == Character.isUpperCase(found)){
            //System.out.println("you already have a peice there");
            return false;
          } else {
            //System.out.println("a enemy peice is in your way");
            return false;
          }
        }
      
      // starting lower than target in terms of row
      // starting to the left of target in terms of col
      } else if(row > drow && col < dcol && row < 8 && col < 8 && row > -1 && col > -1 && row - i < 8 && col + i < 8 && row - i > -1 && col + i > -1){
        //System.out.println("Heading North-East");
        System.out.println("col is: " + col);
        if(board[row - i][col + i]!= null){
          if(upcase == Character.isUpperCase(board[row - i][col + i].name.charAt(0))){
            //System.out.println("you already have a peice there");
            return false;
          } else {
            //System.out.println("a enemy peice is in your way");
            return false;
          }
        }
      
      // starting higher than target in terms of row
      // starting to the right of target in terms of col
      } else if(row < drow && col > dcol && row < 8 && col < 8 && row > -1 && col > -1 && row + i < 8 && col - i < 8 && row + i > -1 && col - i > -1){
        //System.out.println("Heading South-West");
        if(board[row + i][col - i] != null){
          if(upcase == Character.isUpperCase(board[row + i][col - i].name.charAt(0))){
            //System.out.println("you already have a peice there");
            return false;
          } else {
            //System.out.println("a enemy peice is in your way");
            return false;
          }
        }
        
      } else if (row > drow && col > dcol && row < 8 && col < 8 && row > -1 && col > -1 && row - i < 8 && col - i < 8 && row - i > -1 && col - i > -1){ //LOGIC IS WRONG
        //System.out.println("Heading North-West");
        if(board[row - i][col - i] != null){
          //System.out.println("upcase is: " + upcase);
          if(upcase == Character.isUpperCase(board[row - i][col - i].name.charAt(0))){
            //System.out.println("you already have a peice there");
            return false;
          } else {
            //System.out.println("a enemy peice is in your way");
            return false;
          }
        }
      }
    }

    // NOTE -> Not sure true / false here 
    //
    
    return true;
  }
}