/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triples_chess;

/**
 *
 * @author Stcof
 * Called By TripleS_Chess
 */
public class Board
{
    public Board()
    {
        initBoard();
        
        
    } // End Board
       
    public void initBoard()
    {
        //Pawns
		for ( int i = 0 ; i<8 ; i++)
		{
			for (int j = 0 ; j < 8 ; j++)
			{
				if (i == 1)
				{
					gameB[i][j] = (new Piece(Piece.PieceR.PAWN, Piece.pColor.B));
				}
				else if ( i == 6)
				{
					gameB[i][j] = (new Piece(Piece.PieceR.PAWN, Piece.pColor.W));
				}
			}
		}
		
		// Rooks
		gameB[0][0] = (new Piece(Piece.PieceR.ROOK, Piece.pColor.B));
		gameB[0][7] = (new Piece(Piece.PieceR.ROOK, Piece.pColor.B));
		gameB[7][0] = (new Piece(Piece.PieceR.ROOK, Piece.pColor.W));
		gameB[7][7] = (new Piece(Piece.PieceR.ROOK, Piece.pColor.W));
		
		//Knights
		gameB[0][1] = (new Piece(Piece.PieceR.KNIGHT, Piece.pColor.B));
		gameB[0][6] = (new Piece(Piece.PieceR.KNIGHT, Piece.pColor.B));
		gameB[7][1] = (new Piece(Piece.PieceR.KNIGHT, Piece.pColor.W));
		gameB[7][6] = (new Piece(Piece.PieceR.KNIGHT, Piece.pColor.W));
		
		//Bishops
		gameB[0][2] = (new Piece(Piece.PieceR.BISHOP, Piece.pColor.B));
		gameB[0][5] = (new Piece(Piece.PieceR.BISHOP, Piece.pColor.B));
		gameB[7][2] = (new Piece(Piece.PieceR.BISHOP, Piece.pColor.W));
		gameB[7][5] = (new Piece(Piece.PieceR.BISHOP, Piece.pColor.W));
		
		//Queens
		gameB[0][3] = (new Piece(Piece.PieceR.QUEEN, Piece.pColor.B));
		gameB[7][3] = (new Piece(Piece.PieceR.QUEEN, Piece.pColor.B));
		
		//Kings
		gameB[0][4] = (new Piece(Piece.PieceR.KING, Piece.pColor.B));
		gameB[7][4] = (new Piece(Piece.PieceR.KING, Piece.pColor.B));
		
		
		
		//print list of pieces
		for (int i = 0; i < 8 ; i++)
		{
			for (int j = 0 ; j < 8 ; j++)
			{
				if (gameB[i][j] == null)
				{
					System.out.print(" ~~ " + " | ");
				}
				else
				{
					System.out.print((((Piece) gameB[i][j]).getImgID()) + " | ");
				}
			}
			System.out.println();
		}
        
        
        
    }
    
    
    // Variables and 'Item' initialization

    Object[][] gameB = new Object[8][8];
    

    
}
