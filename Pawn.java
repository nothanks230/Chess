//upcase
import java.util.Scanner;

public class Pawn extends Piece implements Support{

    public Pawn(int row, int col, String name, boolean upcase){
      this.row = row;
      this.col = col;
      this.name = name;
      this.upcase = upcase;
    }
    

  	 public static boolean checkPawnMove(int row, int col, int drow, int dcol, boolean upcase) {
		 boolean moveTest0 = checkPawnMovePlace(row, col, drow, dcol, upcase);
		 if(moveTest0) {
			 if(checkPawnMoveDetails(row, col, drow, upcase)) {
				 return true;
			 }
		 }	
    
		 
		if(!upcase) {
			if(drow == row - 1 && ((dcol == col - 1) || (dcol == col + 1))){
				char found = board[drow][dcol].name.charAt(0);
				if(found != e) {
					if(Character.isUpperCase(found)) {
						return true;
					}
				}
			}
		}
		 
		if(upcase) {
			if(drow == row + 1 && ((dcol == col - 1) || (dcol == col + 1))){
				char found = board[drow][dcol].name.charAt(0);
				if(found != e) {
					if(!Character.isUpperCase(found)) {
						return true;
					}
				}
			}
		}
    
		 return false;
    //end of checkPawnMove
	 }

   	
	 public static boolean checkPawnMovePlace(int row, int col, int drow, int dcol, boolean upcase) {
		 if(upcase){
			 if(row == 1) {
				if(dcol == col) {
					if(drow == row + 2 || drow == row + 1) {
						return true;
					}
				}
			 } else {
				if(dcol == col) {
					if(drow == row + 1) {
						return true;
					}
				}
			 }
		 } else if (!upcase) {
			 if(row == 6) {
				if(dcol == col) {
					if(drow == row - 2 || drow == row - 1) {
						return true;
					}
				}
			 } else {
				 if(dcol == col) {
					 if(drow == row - 1) {
						 return true;
					 }
				 }
			 
			 }
			 
		 }
		 return false;
	 }
	 
	 public static boolean checkPawnMoveDetails(int row, int col, int drow, boolean upcase) {
		 if(upcase) {
			 int dif = drow - row;
			 if(dif == 2) {
				 return board[drow - 1][col] == null && board[drow][col] == null;
			 } else if (dif == 1) {
				 return board[drow][col] == null; 
			 }
		 } else {
			 int dif = row - drow;
			 if(dif == 2) {
				 return board[drow + 1][col] == null && board[drow][col] == null;
			 } else if (dif == 1) {
				 return board[drow][col] == null; 
			 } 
		 }
		 return false;
	 }
	 
	 public static boolean pawnPromote(int drow, int dcol, boolean upcase) {
		 if(!upcase) {
				if(drow == 0) {
					System.out.println("you made it to the other end of the board! now you can get promoted. what peice to you want your pawn to become? (case sensitive)");
					String g = "";
					Scanner f = new Scanner(System.in);
					String peice = f.nextLine();
					if(peice.isEmpty()) {
						board[drow][dcol].name = "q1";
					} else {
						if(promotionsLow.contains(peice.charAt(0))) {
							board[drow][dcol].name = peice.charAt(0) + "1";
						} else {
							System.out.println("the charcter you provided was not identified as a peice type. giving defualt peice");
							board[drow][dcol].name = "q1";
						}
					}
					return false;
				}
			}
			
//Create a piece, assign its name to whatever you read from the scanner, and then assign it to the location where the pawn just was.
//you'll need a bunch of ifs to check what kind of piece the player wants to place, and then create an instance of the proper class

			if(upcase) {
				if(drow == 7) {
					System.out.println("you made it to the other end of the board! now you can get promoted. what peice to you want your pawn to become? (case sensitive)");
					String g = "";
					Scanner f = new Scanner(System.in);
					String peice = f.nextLine();
					if(peice.isEmpty()) {
						board[drow][dcol].name = "Q1";
					} else {
						if(promotionsUp.contains(peice.charAt(0))) {
							board[drow][dcol].name = peice.charAt(0) + "1";
						} else {
							System.out.println("the charcter you provided was not identified as a peice type. giving defualt peice");
							board[drow][dcol].name = "Q1";
						}
					}
					return true;
				}
			}
			return false;
	 }

  // end of the Pawn class 
}