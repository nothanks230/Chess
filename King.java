import java.util.Scanner;

public class King extends Piece implements Support{

    public King(int row, int col, String name, boolean upcase){
      this.row = row;
      this.col = col;
      this.name = name;
      this.upcase = upcase;
    }

  public static boolean checkKingMove(int row, int col, int drow, int dcol, boolean upcase){

    boolean validMove = checkKingMoveDetails(row, col, drow, dcol, upcase);

    return validMove;

  }


  public static boolean checkCastling(int row, int col, int drow, int dcol, boolean upcase){

    boolean notMoved = castlingMoved(row, col, drow, dcol, upcase);
    boolean spaceEmpty = castlingEmpty(row, col, drow, dcol, upcase);

    return notMoved && spaceEmpty;

  }
  
  public static boolean checkKingMoveDetails(int row, int col, int drow, int dcol, boolean upcase){

    // distance has to be one
    int rowDistance = Math.abs(drow - row);
    int colDistance = Math.abs(dcol - col);

    if(rowDistance == 1 || rowDistance == 0){
      if(colDistance == 1 || colDistance == 0){
      return Threat.isSpotUnderAttack(row, col, drow, dcol, upcase);
      }
      return false;
    }
    return false;
  }

  public static boolean castlingMoved(int row, int col, int drow, int dcol, boolean upcase){
    if(!upcase){
      if(!Main.WKingMoved){
        if(drow == 7 && dcol == 6){
          if(Main.WERookMoved == false){
            return true;
          } else {
            return false;
          }
        } else if(drow == 7 && dcol == 2){
          if(Main.WWRookMoved == false){
            return true;
          } else {
            return false;
          }
        } 
      } else {
        return false;
      }
    } else {
      if(!Main.BKingMoved){
        if(drow == 0 && dcol == 6){
          if(Main.BERookMoved == false){
            return true;
          } else {
            return false;
          }
        } else if(drow == 0 && dcol == 2){
          if(Main.BWRookMoved == false){
            return true;
          } else {
            return false;
          }
        } 
      } else {
        return false;
      }
    }
    return false;
  }

  public static boolean castlingEmpty(int row, int col, int drow, int dcol, boolean upcase){
    if(!upcase){
      if(drow == 7 && dcol == 6){
        if(board[7][6].name.charAt(0) == e && board[7][5].name.charAt(0) == e){
          return true;
        } else {
          return false;
        }
      } else if (drow == 7 && dcol == 2){
        if(board[7][1].name.charAt(0) == e && board[7][2].name.charAt(0) == e){
          return true;
        } else {
          return false;
        }
      }
    } else {
      if(drow == 0 && dcol == 6){
        if(board[0][6].name.charAt(0) == e && board[0][5].name.charAt(0) == e){
          return true;
        } else {
          return false;
        }
      } else if (drow == 0 && dcol == 2){
        if(board[0][1].name.charAt(0) == e && board[0][2].name.charAt(0) == e){
          return true;
        } else {
          return false;
        }
      }
    }
    return false;
  }
  
}