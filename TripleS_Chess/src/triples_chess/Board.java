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
					gameB[i][j] = ( new Pawn ( pColor.B ));
				}
				else if ( i == 6)
				{
					gameB[i][j] = ( new Pawn ( pColor.W ));
				}
			}
		}
		
		// Rooks
		gameB[0][0] = ( new Rook ( pColor.B ));
		gameB[0][7] = ( new Rook ( pColor.B ));
		gameB[7][0] = ( new Rook ( pColor.W ));
		gameB[7][7] = ( new Rook ( pColor.W ));
		
		//Knights
		gameB[0][1] = ( new Knight ( pColor.B ));
		gameB[0][6] = ( new Knight ( pColor.B ));
		gameB[7][1] = ( new Knight ( pColor.W ));
		gameB[7][6] = ( new Knight ( pColor.W ));
		
		//Bishops
		gameB[0][2] = ( new Bishop ( pColor.B ));
		gameB[0][5] = ( new Bishop ( pColor.B ));
		gameB[7][2] = ( new Bishop ( pColor.W ));
		gameB[7][5] = ( new Bishop ( pColor.W ));
		
		//Queens
		gameB[0][3] = ( new Queen ( pColor.B ));
		gameB[7][3] = ( new Queen ( pColor.B ));
		
		//Kings
		gameB[0][4] = ( new King ( pColor.B ));
		gameB[7][4] = ( new King ( pColor.B ));
		
		
		
		//print list of pieces
		for (int i = 0; i < 8 ; i++)
		{
			for (int j = 0 ; j < 8 ; j++)
			{
				if ( gameB[i][j] == null )
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
