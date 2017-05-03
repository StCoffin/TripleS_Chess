/**
 *  <b>PROGRAM</b>     : Triple S Chess<br>
 *  <b>AUTHORS</b>     : Stephen C., Sean B., Sid C.<br>
 *  <b>PURPOSE</b>     : To demonstrate full development process (Concept to Release)<br>
 *  <b>COURSE</b>      : CSC 478 [B] (Software Engineering Capstone)<br>
 *  <b>DATE</b>        : 01.23.2017 - 05.12.2017<br>
 */
package triples_chess;

import javax.swing.*;

/**
* <h3 style="color:yellow;">"Triple S Chess Icons" by en:User:Cburnett is licensed under CC BY 3.0</h3>
* <br>
* <h3 style="color:white;">CLASS: Piece </h3>
* <HR>
* <h3 style="color:white;">PARAMETERS: None</h3>
* <HR>
* <h3 style="color:white;">VARIABLES: </h3>
*      <blockquote><i>a. pImg - String name for image file location</i></blockquote>
*      <blockquote><i>b. type - Type of piece</i></blockquote>
*      <blockquote><i>c. colr - Color of piece</i></blockquote>
*      <blockquote><i>c. hasmoved - Integer counter for no. of moves </i></blockquote>
*      <blockquote><i>c. moves[][] - Array output of valid moves</i></blockquote>
* <HR>
* <h3 style="color:white;">PURPOSE: </h3>
* <pre>
Piece(Takes the Type(pT) and Color(pC) assignments)
Assigns the type and color and initializes movement to zero;
Looks to see whether piece is black or white()
Looks to see what type of piece it is and sets it to its corresponding Image type;</pre>
*/
public class Piece
{
    	public String   pImg    ;                                                                                                                                                                                                                                                               
	pT              type    ;
	pC              colr    ;
	public int      hasMoved;
	private int[][] moves   ;
        
        String WPwn = "PW60.png" ;
        String BPwn = "PB60.png" ;
        String WRok = "RW60.png" ;
        String BRok = "RB60.png" ;
        String WKnt = "KnW60.png";
        String BKnt = "KnB60.png";
        String WBis = "BW60.png" ;
        String BBis = "BB60.png" ;
        String WQue = "QW60.png" ;
        String BQue = "QB60.png" ;
        String WAce = "KW60.png" ;
        String BAce = "KB60.png" ;
        
        
        /**
         * 
         */
	public Piece()
	{	}
	
	/**
         * 
         * @param type
         * @param colr 
         */
        public Piece( pT type, pC colr)
	{
		
		this.type 	= type	;
		this.colr 	= colr 	;
		this.hasMoved	= 0	;
            if (colr == pC.W) 
            {

                if (type == pT.PAWN) 
                {
                    pImg = WPwn;
                } 
                else if (type == pT.ROOK) 
                {
                    pImg = WRok;
                } 
                else if (type == pT.KNIGHT) 
                {
                    pImg = WKnt;
                } 
                else if (type == pT.BISHOP) 
                {
                    pImg = WBis;
                } 
                else if (type == pT.QUEEN) 
                {
                    pImg = WQue;
                } 
                else if (type == pT.KING) 
                {
                    pImg = WAce;
                }
            }
            if (colr == pC.B)
            {
                //pImg = "B";
                if (type == pT.PAWN) 
                {
                    pImg = BPwn;
                } 
                else if (type == pT.ROOK) 
                {
                    pImg = BRok;
                } 
                else if (type == pT.KNIGHT) 
                {
                    pImg = BKnt;
                } 
                else if (type == pT.BISHOP) 
                {
                    pImg = BBis;
                } 
                else if (type == pT.QUEEN) 
                {
                    pImg = BQue;
                } 
                else if (type == pT.KING) 
                {
                    pImg = BAce;
                }
            }
		
	}
	
/**
* <h3 style="color:white;">PROCEDURE: vMove </h3>
* <HR>
* <h3 style="color:white;">PARAMETERS:  </h3>
*           @param x - Actual piece location for valid move
*           @param y - Actual piece location for valid move
*           @param B - Board Array of pieces currently
* <HR>
* <h3 style="color:white;">VARIABLES:</h3>
*   @return moves
* <HR>
* <h3 style="color:white;">RETURN:</h3> None
* <HR>
* <h3 style="color:white;"> PURPOSE:</h3>
* <pre>
Takes in the position location of the piece trying to move(x,y) and the current array of pieces
Looks to see what type piece is being moved:
Sends it to the corresponding moves class and populates the moves array;
Returns the populated moves array;</pre>
*/
	public int[][] vMove(int x, int y, Piece[][] B)
    {   
		switch (B[x][y].type())
		{
			case PAWN:
			{
				moves = PawnMoves(x, y, B);
				break;
			}
			
			case ROOK:
			{
				moves = RookMoves(x,y, B);
				break;
			}
			
			case KNIGHT:
			{
				moves = KnightMoves(x, y, B);
				break;
			}
		
			case BISHOP:
			{
				moves = BishopMoves(x, y, B);
				break;
			}
			
			case QUEEN:
			{
				moves = QueenMoves(x,y,B);
				break;
			}
			
			case KING:
			{
				moves = KingMoves(x, y, B);
				break;
			}
		}
		
        return moves;
    }
/**    
* <h3 style="color:white;">PROCEDURE: PawnMoves</h3>
* <HR>
* <h3 style="color:white;">PARAMETERS:</h3>
*           @param x - Actual Pawn location for valid move
*           @param y - Actual Pawn location for valid move
*           @param B - Board Array of pieces currently
* <HR>
* <h3 style="color:white;">VARIABLES:</h3>
*      <blockquote><i>a. col - </i></blockquote>
*      <blockquote><i>b. row - </i></blockquote>
* <HR>
* <h3 style="color:white;">RETURN: </h3>
*      @return moves -
* <HR>
* <h3 style="color:white;"> PURPOSE: </h3>
* <pre>
Takes in the position location of the piece trying to move(x,y) and the current array of pieces
Looks to see if any valid Pawn moves can be made;
If valid move can be made it adds value to moves array;
Returns moves;</pre>
*/   
	public int[][] PawnMoves(int x, int y, Piece[][] B) 
	{
		int col = 0;
		int row = 0;
		moves = new int[4][2]; // Maximum number of moves for White possible = 4

			// Black Moves
			if (B[x][y].colr == pC.B) 
			{
				// basic movement
				if (B[x + 1][y] == null) 
				{
					moves[col][row] = x + 1;
					moves[col][row + 1] = y;
					col++;
					
					if (B[x][y].hasMoved() == 0 && B[x + 2][y] == null) 
					{
						moves[col][row] = x + 2;
						moves[col][row + 1] = y;
						col++;
					}

				}

				// Black captures
				if ((y + 1) != 8 && B[x + 1][y + 1] != null && B[x + 1][y + 1].colr == pC.W) 
				{
					moves[col][row] = x + 1;
					moves[col][row + 1] = y + 1;
					col++;
				}
				if (y != 0 && B[x + 1][y - 1] != null && B[x + 1][y - 1].colr == pC.W) 
				{
					moves[col][row] = x + 1;
					moves[col][row + 1] = y - 1;
					col++;
				}

			}
			// White Moves
			else if (B[x][y].colr == pC.W) 
			{
				if (B[x - 1][y] == null) 
				{
					moves[col][row] = x - 1;
					moves[col][row + 1] = y;
					col++;
					
					if (B[x][y].hasMoved() == 0 && B[x - 2][y] == null) 
					{
						moves[col][row] = x - 2;
						moves[col][row + 1] = y;
						col++;
					}

				}

				// White Captures
				if ((y + 1) != 8 && B[x - 1][y + 1] != null && B[x - 1][y + 1].colr == pC.B) 
				{
					moves[col][row] = x - 1;
					moves[col][row + 1] = y + 1;
					col++;
				}
				if (y != 0 && B[x - 1][y - 1] != null && B[x - 1][y - 1].colr == pC.B) 
				{
					moves[col][row] = x - 1;
					moves[col][row + 1] = y - 1;
					col++;
				} 
			}	
			if (col < moves.length)
			{
				while (col < moves.length)
				{
					moves[col][row] 	= 8;
					moves[col][row+1] 	= 8;
					col++;
				}
			}

		return moves;
	} // End PawnMoves()

/**
* <h3 style="color:white;">PROCEDURE: KnightMoves</h3>
* <HR>
* <h3 style="color:white;">PARAMETERS:</h3>
*           @param x - Actual Knight location for valid move
*           @param y - Actual Knight location for valid move
*           @param B - Board Array of pieces currently
* <HR>
* <h3 style="color:white;">VARIABLES:</h3>
*      <blockquote><i>a. col - </i></blockquote>
*      <blockquote><i>b. row - </i></blockquote>
* <HR>
* <h3 style="color:white;">RETURN: </h3>
*      @return moves
* <HR>
* <h3 style="color:white;"> PURPOSE: </h3>
* <pre>
Takes in the position location of the piece trying to move(x,y) and the current array of pieces
Looks to see if any valid Knight moves can be made;
If valid move can be made it adds value to moves array;
Returns moves;</pre>
*/        
        public int[][] KnightMoves(int x, int y, Piece[][] B)
	{
		int col = 0;
		int row = 0;
		moves = new int[8][2]; // Maximum number of moves 8

		// Knight Moves
		//(-2,-1), (-2,+1), (+2, -1), (+2,+1)
		if ((x-2) >= 0 && (y-1) >= 0 && ((B[x-2][y-1] == null)||(B[x][y].colr != B[x-2][y-1].colr)) ) 
		{
			moves[col][row] = x - 2;
			moves[col][row + 1] = y - 1;
			col++;
		}
		//(-2,+1)
		if((x-2) >= 0 && (y+1) <= 7 && ((B[x-2][y+1] == null)||(B[x][y].colr != B[x-2][y+1].colr)) )
		{
			moves[col][row] = x - 2;
			moves[col][row + 1] = y + 1;
			col++;
		}
		//(+2, -1)
		if((x+2) <= 7 && (y-1) >= 0 && ((B[x+2][y-1] == null)||(B[x][y].colr != B[x+2][y-1].colr)) )
		{
			moves[col][row] = x + 2;
			moves[col][row + 1] = y - 1;
			col++;
		}
		
		//(+2,+1)
		if((x+2) <= 7 && (y+1) <= 7 && ((B[x+2][y+1] == null)||(B[x][y].colr != B[x+2][y+1].colr)) )
		{
			moves[col][row] = x + 2;
			moves[col][row + 1] = y + 1;
			col++;
		}
		//(-1,-2)
		if((x-1) >= 0 && (y-2) >= 0 && ((B[x-1][y-2] == null)||(B[x][y].colr != B[x-1][y-2].colr)) )
		{
			moves[col][row] = x - 1;
			moves[col][row + 1] = y - 2;
			col++;
		}
		//(-1,+2)
		if((x-1) >= 0 && (y+2) <= 7 && ((B[x-1][y+2] == null)||(B[x][y].colr != B[x-1][y+2].colr)) )
		{
			moves[col][row] = x - 1;
			moves[col][row + 1] = y + 2;
			col++;
		}
		//(+1,-2)
		if((x+1) <= 7 && (y-2) >= 0 && ((B[x+1][y-2] == null)||(B[x][y].colr != B[x+1][y-2].colr)) )
		{
			moves[col][row] = x + 1;
			moves[col][row + 1] = y - 2;
			col++;
		}
		//(+1,+2)
		if((x+1) <= 7 && (y+2) <= 7 && ((B[x+1][y+2] == null)||(B[x][y].colr != B[x+1][y+2].colr)) )
		{
			moves[col][row] = x + 1;
			moves[col][row + 1] = y + 2;
			col++;
		}
		
		
		if (col < moves.length) 
		{
			while (col < moves.length) 
			{
				moves[col][row] = 8;
				moves[col][row + 1] = 8;
				col++;
			}
		}

		return moves;
	}// End KnightMoves()

/**
* <h3 style="color:white;">PROCEDURE: RookMoves</h3>
* <HR>
* <h3 style="color:white;">PARAMETERS:</h3>
*           @param x - Actual Rook location for valid move
*           @param y - Actual Rook location for valid move
*           @param B - Board Array of pieces currently
* <HR>
* <h3 style="color:white;">VARIABLES:</h3>
*      <blockquote><i>a. col - </i></blockquote>
*      <blockquote><i>b. row - </i></blockquote>
* <HR>
* <h3 style="color:white;">RETURN: </h3>
*      @return moves -
* <HR>
* <h3 style="color:white;"> PURPOSE: </h3>
* <pre>
Takes in the position location of the piece trying to move(x,y) and the current array of pieces
Looks to see if any valid Rook moves can be made;
If valid move can be made it adds value to moves array;
Returns moves;</pre>
*/       
	public int[][] RookMoves(int x, int y, Piece[][] B)
	{
		int col = 0;
		int row = 0;
		moves = new int[14][2]; // Maximum number of moves 14

		// Rook Moves
		// all moves in vertical down
		for (int a = (x+1); a <= 7 ; a++)
		{
			if (B[a][y] != null && B[a][y].colr == B[x][y].colr)
			{
				break;
			}
			else if (B[a][y] != null && B[a][y].colr != B[x][y].colr)
			{
				moves[col][row] = a;
				moves[col][row + 1] = y;
				col++;
				break;
			}
			else
			{	
				moves[col][row] = a;
				moves[col][row + 1] = y;
				col++;
			}
		}
		// all moves in vertical up
		for (int a = (x - 1); a >= 0 ; a--)
		{
			if (B[a][y] != null && B[a][y].colr == B[x][y].colr )
			{
				break;
			}
			else if (B[a][y] != null && B[a][y].colr != B[x][y].colr)
			{
				moves[col][row] = a ;
				moves[col][row + 1] = y ;
				col++;
				break;
			}
			else
			{
				moves[col][row] = a ;
				moves[col][row + 1] = y ;
				col++;
			}
		}
		// all moves in horizontal right
		for (int c = (y+1); c <= 7 ; c++)
		{
			if (B[x][c] != null && B[x][c].colr == B[x][y].colr)
			{
				break;
			}
			else if (B[x][c] != null && B[x][c].colr != B[x][y].colr)
			{
				moves[col][row] = x;
				moves[col][row + 1] = c;
				col++;
				break;
			}
			else
			{	
				moves[col][row] = x;
				moves[col][row + 1] = c;
				col++;
			}
		}
		
		// all moves in horizontal left
		for (int c = (y - 1); c >= 0 ; c--)
		{
			if (B[x][c] != null && B[x][c].colr == B[x][y].colr )
			{
				break;
			}
			else if (B[x][c] != null && B[x][c].colr != B[x][y].colr)
			{
				moves[col][row] = x ;
				moves[col][row + 1] = c ;
				col++;
				break;
			}
			else
			{
				moves[col][row] = x ;
				moves[col][row + 1] = c ;
				col++;
			}
		}
		
		if (col < moves.length) 
		{
			while (col < moves.length) 
			{
				moves[col][row] = 8;
				moves[col][row + 1] = 8;
				col++;
			}
		}

		return moves;
	}// End RookMoves()

/**
* <h3 style="color:white;">PROCEDURE: BishopMoves</h3>
* <HR>
* <h3 style="color:white;">PARAMETERS:</h3>
*           @param x - Actual Bishop location for valid move
*           @param y - Actual Bishop location for valid move
*           @param B - Board Array of pieces currently
* <HR>
* <h3 style="color:white;">VARIABLES:</h3>
*      <blockquote><i>a. col - </i></blockquote>
*      <blockquote><i>b. row - </i></blockquote>
* <HR>
* <h3 style="color:white;">RETURN: </h3>
*      @return moves -
* <HR>
* <h3 style="color:white;"> PURPOSE: </h3>
* <pre>
Takes in the position location of the piece trying to move(x,y) and the current array of pieces
Looks to see if any valid Bishop moves can be made;
If valid move can be made it adds value to moves array;
* Returns moves;</pre>
*/       
	public int[][] BishopMoves(int x, int y, Piece[][] B)
	{
		int col = 0;
		int row = 0;
		moves = new int[14][2]; // Maximum number of moves 14

		// Bishop Moves
		// all moves in diagonal DR going right (+, +)
		for (int a = (x+1), b = (y+1); a <= 7 && b <= 7 ; a++, b++)
		{
			if (B[a][b] != null && B[a][b].colr == B[x][y].colr)
			{
				break;
			}
			else if (B[a][b] != null && B[a][b].colr != B[x][y].colr)
			{

				moves[col][row] = a;
				moves[col][row + 1] = b;
				col++;
				break;
			}
			else
			{	
				moves[col][row] = a;
				moves[col][row + 1] = b;
				col++;
			}
		}
		
		// all moves in diagonal DR going left (-, -)
		for (int a = (x-1), b = (y-1); a >= 0 && b >= 0 ; a--, b--)
		{
			if (B[a][b] != null && B[a][b].colr == B[x][y].colr )
			{
				break;
			}
			else if (B[a][b] != null && B[a][b].colr != B[x][y].colr)
			{
				moves[col][row] = a ;
				moves[col][row + 1] = b ;
				col++;
				break;
			}
			else
			{
				moves[col][row] = a ;
				moves[col][row + 1] = b ;
				col++;
			}
		}
		
		// all moves in diagonal UR going right (-, +)
		for (int c = (x-1), d = (y+1); c >= 0 && d <= 7 ; c--, d++)
		{
			if (B[c][d] != null && B[c][d].colr == B[x][y].colr)
			{
				break;
			}
			else if (B[c][d] != null && B[c][d].colr != B[x][y].colr)
			{
				moves[col][row] = c;
				moves[col][row + 1] = d;
				col++;
				break;
			}
			else
			{	
				moves[col][row] = c;
				moves[col][row + 1] = d;
				col++;
			}
		}
		
		// all moves in diagonal UR going left (+, -)
		for (int c = (x+1), d = (y-1); c <= 7 && d >= 0 ; c++, d--)
		{
			if (B[c][d] != null && B[c][d].colr == B[x][y].colr )
			{
				break;
			}
			else if (B[c][d] != null && B[c][d].colr != B[x][y].colr)
			{
				moves[col][row] = c ;
				moves[col][row + 1] = d ;
				col++;
				break;
			}
			else
			{
				moves[col][row] = c ;
				moves[col][row + 1] = d ;
				col++;
			}
		}
		
		if (col < moves.length) 
		{
			while (col < moves.length) 
			{
				moves[col][row] = 8;
				moves[col][row + 1] = 8;
				col++;
			}
		}

		return moves;
	}// End BishopMoves()

/**
* <h3 style="color:white;">PROCEDURE: QueenMoves</h3>
* <HR>
* <h3 style="color:white;">PARAMETERS:</h3>
*           @param x - Actual Queen location for valid move
*           @param y - Actual Queen location for valid move
*           @param B - Board Array of pieces currently
* <HR>
* <h3 style="color:white;">VARIABLES:</h3>
*      <blockquote><i>a. col - </i></blockquote>
*      <blockquote><i>b. row - </i></blockquote>
* <HR>
* <h3 style="color:white;">RETURN: </h3>
*      @return moves -
* <HR>
* <h3 style="color:white;"> PURPOSE: </h3>
* <pre>
Takes in the position location of the piece trying to move(x,y) and the current array of pieces
Looks to see if any valid Queen moves can be made
If valid move can be made it adds value to moves array;
Returns moves;</pre>
*/     
	public int[][] QueenMoves(int x, int y, Piece[][] B) 
	{
		int col = 0;
		int row = 0;
		moves = new int[27][2];   // Maximum number of moves 27

		// all moves in vertical down
		for (int a = (x + 1); a <= 7; a++) 
		{
			if (B[a][y] != null && B[a][y].colr() == B[x][y].colr()) 
			{
				break;
			} 
			else if (B[a][y] != null && B[a][y].colr() != B[x][y].colr()) 
			{
				moves[col][row] = a;
				moves[col][row + 1] = y;
				col++;
				break;
			} 
			else 
			{
				moves[col][row] = a;
				moves[col][row + 1] = y;
				col++;
			}
		}
		// all moves in vertical up
		for (int a = (x - 1); a >= 0; a--) 
		{
			if (B[a][y] != null && B[a][y].colr() == B[x][y].colr()) 
			{
				break;
			} 
			else if (B[a][y] != null && B[a][y].colr() != B[x][y].colr()) 
			{
				moves[col][row] = a;
				moves[col][row + 1] = y;
				col++;
				break;
			} 
			else 
			{
				moves[col][row] = a;
				moves[col][row + 1] = y;
				col++;
			}

		}
		// all moves in horizontal right
		for (int c = (y + 1); c <= 7; c++) 
		{
			if (B[x][c] != null && B[x][c].colr() == B[x][y].colr()) 
			{
				break;
			} 
			else if (B[x][c] != null && B[x][c].colr() != B[x][y].colr()) 
			{
				moves[col][row] = x;
				moves[col][row + 1] = c;
				col++;
				break;
			} 
			else 
			{
				moves[col][row] = x;
				moves[col][row + 1] = c;
				col++;
			}
		}

		// all moves in horizontal left
		for (int c = (y - 1); c >= 0; c--) 
		{
			if (B[x][c] != null && B[x][c].colr == B[x][y].colr) 
			{
				break;
			} 
			else if (B[x][c] != null && B[x][c].colr != B[x][y].colr) 
			{
				moves[col][row] = x;
				moves[col][row + 1] = c;
				col++;
				break;
			} 
			else 
			{
				moves[col][row] = x;
				moves[col][row + 1] = c;
				col++;
			}
		}

		// all moves in diagonal DR going right (+, +)
		for (int a = (x + 1), b = (y + 1); a <= 7 && b <= 7; a++, b++) 
		{
			if (B[a][b] != null && B[a][b].colr() == B[x][y].colr()) 
			{
				break;
			} 
			else if (B[a][b] != null && B[a][b].colr() != B[x][y].colr()) 
			{
				moves[col][row] = a;
				moves[col][row + 1] = b;
				col++;
				break;
			} 
			else 
			{
				moves[col][row] = a;
				moves[col][row + 1] = b;
				col++;
			}
		}

		// all moves in diagonal DR going left (-, -)
		for (int a = (x - 1), b = (y - 1); a >= 0 && b >= 0; a--, b--) 
		{
			if (B[a][b] != null && B[a][b].colr() == B[x][y].colr()) 
			{
				break;
			} 
			else if (B[a][b] != null && B[a][b].colr() != B[x][y].colr()) 
			{
				moves[col][row] = a;
				moves[col][row + 1] = b;
				col++;
				break;
			} 
			else 
			{
				moves[col][row] = a;
				moves[col][row + 1] = b;
				col++;
			}
		}

		// all moves in diagonal UR going right (-, +)
		for (int c = (x - 1), d = (y + 1); c >= 0 && d <= 7; c--, d++) 
		{
			if (B[c][d] != null && B[c][d].colr() == B[x][y].colr()) 
			{
				break;
			} 
			else if (B[c][d] != null && B[c][d].colr() != B[x][y].colr()) 
			{
				moves[col][row] = c;
				moves[col][row + 1] = d;
				col++;
				break;
			} 
			else 
			{
				moves[col][row] = c;
				moves[col][row + 1] = d;
				col++;
			}
		}

		// all moves in diagonal UR going left (+, -)
		for (int c = (x + 1), d = (y - 1); c <= 7 && d >= 0; c++, d--) 
		{
			if (B[c][d] != null && B[c][d].colr == B[x][y].colr) 
			{
				break;
			} 
			else if (B[c][d] != null && B[c][d].colr != B[x][y].colr) 
			{
				moves[col][row] = c;
				moves[col][row + 1] = d;
				col++;
				break;
			} 
			else 
			{
				moves[col][row] = c;
				moves[col][row + 1] = d;
				col++;
			}
		}

		if (col < moves.length) 
		{
			while (col < moves.length) 
			{
				moves[col][row] = 8;
				moves[col][row + 1] = 8;
				col++;
			}
		}

		return moves;
	} // End QueenMoves()

/**
* <h3 style="color:white;">PROCEDURE: KingMoves</h3>
* <HR>
* <h3 style="color:white;">PARAMETERS:</h3>
*           @param x - Actual King location for valid move
*           @param y - Actual King location for valid move
*           @param B - Board Array of pieces currently 
* <HR>
* <h3 style="color:white;">VARIABLES:</h3>
*      <blockquote><i>a. col - </i></blockquote>
*      <blockquote><i>b. row - </i></blockquote>
* <HR>
* <h3 style="color:white;">RETURN: </h3>
*      @return moves -
* <HR>
* <h3 style="color:white;"> PURPOSE: </h3>
* <pre>
Takes in the position location of the piece trying to move(x,y) and the current array of pieces
Looks to see if any valid King moves can be made;
If valid move can be made it adds value to moves array;
Returns moves;</pre>
*/       
	public int[][] KingMoves(int x, int y, Piece[][] B) 
	{
		int col = 0;
		int row = 0;
		moves = new int[8][2]; // Maximum number of moves for White possible = 4

		// Movement UP (-1, 0)
		if ((x - 1) >= 0 && (B[x -  1][y] == null || B[x - 1][y].colr != B[x][y].colr)) 
		{
			moves[col][row] = x - 1;
			moves[col][row + 1] = y;
			col++;
		}
		
		// Movement UR (-1, 1)
		if ((x - 1) >= 0  && (y + 1) <= 7 && (B[x -  1][y + 1] == null || B[x - 1][y + 1].colr != B[x][y].colr)) 
		{
			moves[col][row] = x - 1;
			moves[col][row + 1] = y + 1;
			col++;
		}
		
		// Movement RIGHT (0, 1)
		if ((y + 1) <= 7 && (B[x][y + 1] == null || B[x][y + 1].colr != B[x][y].colr)) 
		{
			moves[col][row] = x;
			moves[col][row + 1] = y + 1;
			col++;
		}
		
		// Movement DR (1, 1)
		if ((x + 1) <= 7  && (y + 1) <= 7 && (B[x + 1][y + 1] == null || B[x + 1][y + 1].colr != B[x][y].colr)) 
		{
			moves[col][row] = x + 1;
			moves[col][row + 1] = y + 1;
			col++;
		}
		
		// Movement DOWN (0, 1)
		if ((x + 1) <= 7 && (B[x + 1][y] == null || B[x + 1][y].colr != B[x][y].colr)) 
		{
			moves[col][row] = x + 1;
			moves[col][row + 1] = y;
			col++;
		}
		
		// Movement DL (1, -1)
		if ((x + 1) <= 7  && (y - 1) >= 0 && (B[x + 1][y - 1] == null || B[x + 1][y - 1].colr != B[x][y].colr)) 
		{
			moves[col][row] = x + 1;
			moves[col][row + 1] = y - 1;
			col++;
		}
		
		// Movement LEFT (0, -1)
		if ( (y - 1) >= 0 && (B[x][y - 1] == null || B[x][y - 1].colr != B[x][y].colr)) 
		{
			moves[col][row] = x;
			moves[col][row + 1] = y - 1;
			col++;
		}
		
		
		
		// Movement UL (-1, -1)
		if ((x - 1) >= 0  && (y - 1) >= 0 && (B[x -  1][y - 1] == null || B[x - 1][y - 1].colr != B[x][y].colr)) 
		{
			moves[col][row] = x - 1;
			moves[col][row + 1] = y - 1;
			col++;
		}
		
		
		
		if (col < moves.length) 
		{
			while (col < moves.length) 
			{
				moves[col][row] = 8;
				moves[col][row + 1] = 8;
				col++;
			}
		}

		return moves;
	} // End KingMoves()
	
/**
* <h3 style="color:white;">PROCEDURE: type</h3>
* <HR>
* <h3 style="color:white;">PARAMETERS: None</h3>
* <HR>
* <h3 style="color:white;">VARIABLES: None</h3>
* <HR>
* <h3 style="color:white;">RETURN: </h3>
*       @return type - type of piece
* <HR>
* <h3 style="color:white;">PURPOSE:</h3>
* <pre>Returns piece type</pre>
*/ 
        public pT type()
	{
		return type;
	}

/**
* <h3 style="color:white;">PROCEDURE: colr </h3>
* <HR>
* <h3 style="color:white;">PARAMETERS: None </h3>
* <HR>
* <h3 style="color:white;">VARIABLES: None </h3>
* <h3 style="color:white;">RETURN: </h3>
*       @return colr - color of piece
* <HR>
* <h3 style="color:white;">PURPOSE: </h3>
* <pre>Returns piece color</pre>
*/         
	public pC colr()
	{
		return colr;
	}

/**
* <h3 style="color:white;">PROCEDURE: getImgID </h3>
* <HR>
* <h3 style="color:white;">PARAMETERS: None </h3>
* <HR>
* <h3 style="color:white;">VARIABLES: None </h3>
* <h3 style="color:white;">RETURN: </h3>
*       @return pImg - Image ID of piece
* <HR>
* <h3 style="color:white;">PURPOSE: </h3>
* <pre>Returns the string value for the location of the image file</pre>
*/         
	public String getImgID()
	{
		return pImg;
	}
        
/**
* <h3 style="color:white;">PROCEDURE: toString</h3>
* <HR>
* <h3 style="color:white;">PARAMETERS: None </h3>
* <HR>
* <h3 style="color:white;">VARIABLES: None </h3>
* <h3 style="color:white;">RETURN: </h3>
*       @return colr - Color and type of piece 
* <HR>
* <h3 style="color:white;">PURPOSE: </h3>
* <pre>Returns the piece color and type in a string format</pre>
*/ 
        public String toString()
	{
		return colr + "" + type;
	}

/**
* <h3 style="color:white;">PROCEDURE: has moved</h3>
* <HR>
* <h3 style="color:white;">PARAMETERS:  None</h3>
* <HR>
* <h3 style="color:white;">VARIABLES: None</h3>
* <h3 style="color:white;">RETURN: </h3>
*       @return hasMoved - Count of moves 
* <HR>
* <h3 style="color:white;">PURPOSE: </h3>
* <pre>Returns int value for hasMoved</pre>
*/         
	public int hasMoved()
	{
		return hasMoved;
	}

/**
* <h3 style="color:white;">PROCEDURE: isMoved</h3>
* <HR>
* <h3 style="color:white;">PARAMETERS:  None</h3>
* <HR>
* <h3 style="color:white;">VARIABLES: None</h3>
* <HR>
* <h3 style="color:white;">PURPOSE: </h3>
* <pre>Adds one to hasMoved for the piece when moved</pre>
*/         
	public void isMoved()
	{
		hasMoved = hasMoved + 1;
	}
}

