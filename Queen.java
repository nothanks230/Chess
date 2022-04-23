import java.util.Scanner;

public class Queen extends Piece implements Support{

    public Queen(int row, int col, String name, boolean upcase){
      this.row = row;
      this.col = col;
      this.name = name;
      this.upcase = upcase;
    }

  public static boolean checkQueenMove(int row, int col, int drow, int dcol, boolean upcase){

    // need abs data 



    int rowDistance = Math.abs(drow - row);
    int colDistance = Math.abs(dcol - col);

    if(rowDistance >= 1 && colDistance >= 1){
      // System.out.println("\n Diagonal Check ...");
      boolean validMoveB   = Bishop.checkBishopMoveDetails(row, col, drow, dcol, upcase);
      
      boolean placeNotBlockedB = Bishop.checkBetweenBishop(row, col, drow, dcol, upcase);

      return validMoveB && placeNotBlockedB;
    } else if (rowDistance >= 1 ^ colDistance >= 1){
      
      // System.out.println("\n Rook-Style Check ...");

      boolean validMoveR = Rook.checkRookMoveDetails(row, col, drow, dcol, upcase);
      boolean vNotBlocked = Rook.checkVPathNotBlocked(row, col, drow, dcol, upcase);
      boolean hNotBlocked = Rook.checkHPathNotBlocked(row, col, drow, dcol, upcase);

      return validMoveR && vNotBlocked && hNotBlocked;
    } else {
       System.out.println("\n Something is weird ...");
      return false;
    }
  }

  public static boolean checkQueenBlock(int row, int col, int drow, int dcol, boolean upcase){

    // need abs data 



    int rowDistance = Math.abs(drow - row);
    int colDistance = Math.abs(dcol - col);

    if(rowDistance >= 1 && colDistance >= 1){
      // System.out.println("\n Diagonal Check ...");
      boolean validMoveB   = Bishop.checkBishopMoveDetails(row, col, drow, dcol, upcase);
      
      boolean placeNotBlockedB = Bishop.checkBetweenBishopBlock(row, col, drow, dcol, upcase);

      return validMoveB && placeNotBlockedB;
    } else if (rowDistance >= 1 ^ colDistance >= 1){
      
      // System.out.println("\n Rook-Style Check ...");

      boolean validMoveR = Rook.checkRookMoveDetails(row, col, drow, dcol, upcase);
      boolean vNotBlocked = Rook.checkVPathNotBlockedBlocking(row, col, drow, dcol, upcase);
      boolean hNotBlocked = Rook.checkHPathNotBlockedBlocking(row, col, drow, dcol, upcase);

      return validMoveR && vNotBlocked && hNotBlocked;
    } else {
       System.out.println("\n Something is weird ...");
      return false;
    }
  }
}