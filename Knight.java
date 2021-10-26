import java.util.Scanner;



public class Knight extends Piece implements Support {

  public Knight(int row, int col, String name,boolean upcase){     
    this.row = row;
    this.col = col;
    this.name = name;
    this.upcase = upcase;
  }

  public static boolean checkKnightMove (int row, int col, int drow, int dcol, boolean upcase){
    
    boolean validMove   = checkKnightMoveDetails(row, col, drow, dcol, upcase);

    return validMove;
  }
  
  public static boolean checkKnightMoveDetails(int row, int col, int drow, int dcol, boolean upcase){
    if(        drow == row - 1 && dcol == col + 2){return true;
    } else if (drow == row - 2 && dcol == col + 1){return true;
    } else if (drow == row - 2 && dcol == col - 1){return true;
    } else if (drow == row - 1 && dcol == col - 2){return true;
    } else if (drow == row + 1 && dcol == col - 2){return true; 
    } else if (drow == row + 2 && dcol == col - 1){return true;
    } else if (drow == row + 2 && dcol == col + 1){return true;
    } else if (drow == row + 1 && dcol == col + 2){return true;
    } else {
      return false;
    }
  }
}