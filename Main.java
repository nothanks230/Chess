import java.util.Collection;
import java.util.Iterator; 
import java.util.Scanner;
//HELLO AI AM FE
//git add Main.java
public class Main implements Support{
	/* pawn = forward 1
	 * rook = forward, back, right, left (any amount)
	 * knight = L
	 * bishop = diagonal on color placed
	 * king = one in any direction
	 * queen = any direction
	 */
	
	public static int turn = 0;

  public static boolean WERookMoved = false;
  public static boolean WWRookMoved = false;
  public static boolean BERookMoved = false;
  public static boolean BWRookMoved = false;

  public static boolean WKingMoved = false;
  public static boolean BKingMoved = false;

	public static void main(String [] args) {
		
		Main game = new Main();
		
		game.innit();
		game.turn();
		
	}
  
	void innit() {

    Rook R1 = new Rook(0, 0, "R1", true);
    Knight H1 = new Knight(0, 1, "H1", true);
    Bishop B1 = new Bishop(0, 2, "B1", true);
    Queen Q1 = new Queen(0, 3, "Q1", true);
    King K1 = new King(0, 4, "K1", true);
    Bishop B2 = new Bishop(0, 5, "B2", true);
    Knight H2 = new Knight(0, 6, "H2", true);
    Rook R2 = new Rook(0, 7, "R2", true);
		
		board[0][0] = R1;
		board[0][1] = H1;
		board[0][2] = B1;
		board[0][3] = Q1;
		board[0][4] = K1;
		board[0][5] = B2;
		board[0][6] = H2;
		board[0][7] = R2;

    Pawn P1 = new Pawn(1, 0, "P1", true);
    Pawn P2 = new Pawn(1, 1, "P2", true);
    Pawn P3 = new Pawn(1, 2, "P3", true);
    Pawn P4 = new Pawn(1, 3, "P4", true);
    Pawn P5 = new Pawn(1, 4, "P5", true);
    Pawn P6 = new Pawn(1, 5, "P6", true);
    Pawn P7 = new Pawn(1, 6, "P7", true);
    Pawn P8 = new Pawn(1, 7, "P8", true);
		
		board[1][0] = P1;
		board[1][1] = P2;
		board[1][2] = P3;
		board[1][3] = P4;
		board[1][4] = P5;
		board[1][5] = P6;
		board[1][6] = P7;
		board[1][7] = P8;
		
		Pawn p1 = new Pawn(6, 0, "p1", false);
    Pawn p2 = new Pawn(6, 1, "p2", false);
    Pawn p3 = new Pawn(6, 2, "p3", false);
    Pawn p4 = new Pawn(6, 3, "p4", false);
    Pawn p5 = new Pawn(6, 4, "p5", false);
    Pawn p6 = new Pawn(6, 5, "p6", false);
    Pawn p7 = new Pawn(6, 6, "p7", false);
    Pawn p8 = new Pawn(6, 7, "p7", false);
    
		board[6][0] = p1;
		board[6][1] = p2;
		board[6][2] = p3;
		board[6][3] = p4;
		board[6][4] = p5;
		board[6][5] = p6;
		board[6][6] = p7;
		board[6][7] = p8;

    Rook r1 = new Rook(7, 0, "r1", false);
    Knight h1 = new Knight(7, 1, "h1", false);
    Bishop b1 = new Bishop(7, 2, "b1", false);
    Queen q1 = new Queen(7, 3, "q1", false);
    King k1 = new King(7, 4, "k1", false);
    Bishop b2 = new Bishop(6, 5, "b2", false);
    Knight h2 = new Knight(7, 6, "h2", false);
    Rook r2 = new Rook(7, 7, "r2", false);
		
		board[7][0] = r1;
		board[7][1] = h1;
		board[7][2] = b1;
		board[7][3] = q1;
		board[7][4] = k1;
		board[7][5] = b2;
		board[7][6] = h2;
		board[7][7] = r2;
		
		promotionsLow.add('q');
		promotionsLow.add('b');
		promotionsLow.add('r');
		promotionsLow.add('k');
		
		promotionsUp.add('Q');
		promotionsUp.add('B');
		promotionsUp.add('R');
		promotionsUp.add('K');


    // Populate the hasmaps for both teams map's to have 
    // P -> 8 for black team etc.
    whiteHashMap.put('p', 8);
    whiteHashMap.put('q', 1);
    whiteHashMap.put('k', 1);
    whiteHashMap.put('b', 2);
    whiteHashMap.put('h', 2);
    whiteHashMap.put('r', 2);

    blackHashMap.put('P', 8);
    blackHashMap.put('Q', 1);
    blackHashMap.put('K', 1);
    blackHashMap.put('B', 2);
    blackHashMap.put('H', 2);
    blackHashMap.put('R', 2);
    /*
      PAWN -> 1
      BISHOP -> 7
      HORSE -> 2
      QUEEN -> 7
      ROOK -> 7
      KING -> 1
    */

    // assocaite the map 
    distHashMap.put('p', 1);
    distHashMap.put('P', 1);
    distHashMap.put('k', 1);
    distHashMap.put('K', 1);
    distHashMap.put('h', 2);
    distHashMap.put('H', 2);
    distHashMap.put('b', 7);
    distHashMap.put('B', 7);
    distHashMap.put('r', 7);
    distHashMap.put('R', 7);
    distHashMap.put('q', 7);
    distHashMap.put('Q', 7);


		
		
	}
	
	void display() {
		for(int i = 0; i < board.length; i++) {
			System.out.print(" " + i + "  ");
		}
		System.out.println();
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(board[i][j] == null) {
					System.out.print("[  ]");
				} else {
					System.out.print("[" + board[i][j].name + "]");
				}
			}
			System.out.println("row: " + i);
		}	
	}


  public static void checkLocations(Piece[][] board, boolean upcase){
    for(int i = 0; i <= 7; i++){
      for(int j = 0; j <= 7; j++){
        if(board[i][j] != null){
          String a = board[i][j].name;
          if(!upcase){
            whiteLocations.put(a, new int[] {i, j});
          } else {
            blackLocations.put(a, new int[] {i, j});
          }
        }
      }
    }
  }

  
  
	
	void turn() {
		boolean upcase = false;
		if(turn % 2 == 0) {
			System.out.println();
			System.out.println("It's white's turn");
      System.out.println("White Team Stats: " + whiteHashMap);
		} else {
			System.out.println();
			System.out.println("It's black's turn");
      System.out.println("Black Team Stats: " + blackHashMap);
			upcase = true;
		}
		System.out.println();
		display();
		
		System.out.print("select the row of the piece you want to move\n");
		
		Scanner r = new Scanner(System.in);
		int row = 0;
    try {
      row = r.nextInt();
    } catch (Exception e){
      System.out.println("\n There was a problem with your row selection.");
      System.out.println("\n Please press enter to continue.");
      r.nextLine();

      turn();
      return;
    }

		
		
		
		System.out.print("select the column of the piece you want to move\n");
		
		Scanner c = new Scanner(System.in);
    int col = 0;
    try {
      col = c.nextInt();
    } catch (Exception e){
      System.out.println("\n There was a problem with your collumn selection.");
      System.out.println("\n Please press enter to continue.");

      turn();
      return;
    }
		
		

		
		System.out.println("you chose the peice at row " + row + " and column " + col);

    if(!bounds1(row, col)){
      System.out.print("\nThe peice you are trying to pick does not exist");
      turn();
      return;
    }
		
    char piece;
		
		if(board[row][col] == null) {
			System.out.println("no piece was found at that spot. please choose another");
			turn();
			return; //take first letter of string, turn into character, check if character is uppercase
		} else {
      piece = board[row][col].name.charAt(0);
      if(Character.isUpperCase(piece) && !upcase) {
        System.out.println("that isn't a piece you can use. please choose another.");
        turn();
        return;
      } else if(!Character.isUpperCase(piece) && upcase) {
        System.out.println("that isn't a piece you can use. please choose another.");
        turn();
        return;
		  }
    }

    // Create the integers for the destination for to be initialized to zero by default
		int drow = 0;
		int dcol = 0;
		
		System.out.print("select the row of the place you want to move to\n");
		
		Scanner dr = new Scanner(System.in);

    try {
      drow = dr.nextInt();
    } catch (Exception e){
      System.out.println("\n There was a problem with your destination row selection.");
      System.out.println("\n Please press enter to continue.");

      turn();
      return;
    }
		
		
		
		System.out.print("select the column of the place you want to move to\n");
		
		Scanner dc = new Scanner(System.in);
    try {
     dcol = dc.nextInt();
    } catch (Exception e) {
      System.out.println("\n There was a problem with your destination collumn selection.");
       System.out.println("\n Please press enter to continue.");

      turn();
      return;
    }
		
		
		
		System.out.println("you chose to move to " + drow + " and column " + dcol);
		
    // here? 
    if(!bounds2(row, col, drow, dcol)){
      System.out.print("\nThe place you are trying to move to is out of bounds");
      turn();
      return;
    }
    if(board[drow][dcol] == null){
      System.out.println("this spot is good");
    } else {
      char place = board[drow][dcol].name.charAt(0);

      if(Character.isUpperCase(place) && upcase){
        System.out.println("you already have a piece there");
        turn();
        return;
		  } else if(!Character.isUpperCase(place) && !upcase) {
			  System.out.println("you already have a piece there");
			  turn();
			  return;
		  }
    }

    if (piece != e){
      if(piece == 'p' || piece == 'P') {
        if(!Pawn.checkPawnMove(row, col, drow, dcol, upcase)) {
          System.out.println("you cannot move your pawn that way");
          turn();
          return;
        }
        
      }

      // :D
      if(piece == 'r' || piece == 'R'){
        if(!Rook.checkRookMove(row, col, drow, dcol, upcase)) {
          System.out.println("you cannot move your rook that way");
          turn();
          return;
        } else {
          if(row == 7){
            if(col == 7){
              WERookMoved = true;
            } else {
              WWRookMoved = true;
            } 
          } else {
            if(col == 7){
              BERookMoved = true;
            } else {
              BWRookMoved = true;
            }
          }
        }
      }
      // HERE 
      if(piece == 'h' || piece == 'H'){
        if(!Knight.checkKnightMove(row, col, drow, dcol, upcase)){
          System.out.println("you cannot move your knight that way");
          turn();
          return;
        }
      }

      /// :O  !!!! 
      if(piece == 'b' || piece == 'B'){
        if(!Bishop.checkBishopMove(row, col, drow, dcol, upcase)){
          System.out.println("you cannot move your bishop that way");
          turn();
          return;
        }
      }

      if(piece == 'q' || piece == 'Q'){
        if(!Queen.checkQueenMove(row, col, drow, dcol, upcase)){
          System.out.println("you cannot move your queen that way");
          turn();
          return;
        }
      }

      if(piece == 'k' || piece == 'K'){

        if(!King.checkKingMove(row, col, drow, dcol, upcase)){
          if(King.checkCastling(row, col, drow, dcol, upcase)){
            System.out.println("Castling...");
            if(dcol == 2){
              if(upcase){
                board[row][3] = board[row][0];
                board[row][0] = empty;
              } else {
                board[row][3] = board[row][0];
                board[row][0] = empty;
              }
            } else if (dcol == 6){
              if(upcase){
                board[row][5] = board[row][7];
                board[row][7] = empty;
              } else {
                board[row][5] = board[row][7];
                board[row][7] = empty;
              }
            }
            System.out.println("Castling...");
          } else {
            System.out.println("you cannot move your king that way");
            turn();
            return;
          }
        } else {
          if(!upcase){
            WKingMoved = true;
          } else {
            BKingMoved = true;
          }
        }
      } 
    }

    int wunits = 0;
    int bunits = 0;
    
    if(board[drow][dcol] == null){
      // don't care 
    } else {
      char pre = board[drow][dcol].name.charAt(0);
      System.out.println("The pre is: " + pre);
      if (Character.isUpperCase(pre)){
        bunits = blackHashMap.get(pre);
        blackHashMap.put(pre, bunits - 1);
      } else if (!Character.isUpperCase(pre)){
        wunits = whiteHashMap.get(pre);
        whiteHashMap.put(pre, wunits - 1);
      } 
    }
    
    
    
    
    // YOU moved! 
		board[drow][dcol] = board[row][col];

    // Nullify your previous position to be empty space
		board[row][col] = null;

    

    // here 
    // make a boolean here 
    // that is true if piece is a p or P 
    boolean pawn = piece == 'p' || piece == 'P';

    // Let's verify if this is working
		if(pawn && Pawn.pawnPromote(drow, dcol, upcase)) {
			System.out.println("promotion (yay!)");
		}

    
    checkLocations(board, upcase);

 
    

  }
	// make a method that checks the row / col and destination row /col 
  // boolean method if anything is out of bounds it should fail 

  boolean bounds1(int row,  int col) {
   if (row > 7 || row < 0){
      return false;
    } else if (col > 7 || col < 0){
      return false;
    } else {
      return true;
    }  
  } 

  boolean bounds2(int row,  int col, int drow, int dcol) {
    if(drow > 7|| drow < 0){
      return false;
    } else if (dcol > 7 || dcol < 0){
      return false;
    } else {
      return true;
    }  
  }
	 

// end of the Main class 
}

