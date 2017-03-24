/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triples_chess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Stcof
 * Called By TripleS_Chess
 */


import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JFrame
{
	public Board()
    {
        int Row,Col;
   	 
   	 Toolkit MyKit 	= Toolkit.getDefaultToolkit	();
   	 Dimension XY 	= MyKit.getScreenSize		();
   	  
   	 Row = XY.width;
   	 Col = XY.height;
   	 
   	 setSize	(25*Row/80	, Col/2		);
   	 setLocation(13*Row/32	, Col/4		);
   	 
   	 setTitle						("Testing of Chess Movement for TripleS" );
   	 
   	 boardPanel PanelGame			= new boardPanel ();
   	 Container ContentPanel01 	= getContentPane();
   	 ContentPanel01.add(PanelGame);
        
        
        initBoard();
        
        
    } // End Board
    
    private class boardPanel extends JPanel {

        public boardPanel() 
        {
            setLayout(new BorderLayout());

            Font BIG = new Font("TimesRoman", Font.BOLD, 30);

            PanelOne.setLayout(new GridLayout(8, 8, 5, 5));
            
            //Build out 2d Grid Array of Control Buttons
            for (int col = 0; col < 8; col++) 
            {
                for (int row = 0; row < 8; row++) 
                {
                    CB[col][row] = new JButton(col + "" + row);
                    CB[col][row].setForeground(Color.blue);
                    if (col % 2 == row % 2) 
                    {
                        CB[col][row].setBackground(Color.gray);
                    } 
                    else 
                    {
                        CB[col][row].setBackground(Color.white);
                    }
                    CB[col][row].setFont(BIG);
                    CB[col][row].putClientProperty("Col", col);
                    CB[col][row].putClientProperty("Row", row);
                    PanelOne.add(CB[col][row]);
                    ClickThem Dummy = new ClickThem();
                    CB[col][row].addActionListener(Dummy);

                }

            }

            add(PanelOne, BorderLayout.CENTER);

        }
    }
    
    private class ClickThem implements ActionListener 
    {

        public void actionPerformed(ActionEvent Punch) 
        {
            JButton ButtonPressed = (JButton) Punch.getSource();
            xHold = Integer.parseInt(ButtonPressed.getClientProperty("Col").toString());
            yHold = Integer.parseInt(ButtonPressed.getClientProperty("Row").toString());
            System.out.println(ButtonPressed.getClientProperty("Col") + " , " + ButtonPressed.getClientProperty("Row"));
            
            //System.out.println(((Piece) gameB[xHold][yHold]).getImgID());

            if (mCount == 0) 
            {
                x1 = xHold;
                y1 = yHold;
                if(gameB[xHold][yHold] != null)
                {
                	System.out.println(gameB[xHold][yHold].getImgID());
                	validMoves = gameB[x1][y1].vMove(x1, y1, gameB);
                	paintPotMoves(validMoves);
                	print2Array(validMoves);
                	mCount++;
                }
            } 
            else if (mCount == 1 && gameB[xHold][yHold] != null) 
            {
                x2 = xHold;
                y2 = yHold;
                pC A = (gameB[x1][y1]).colr();
                pC B = (gameB[x2][y2]).colr();
                if (A == B) 
                {
                    x1 = x2;
                    y1 = y2;
                    System.out.println("New Calculation");
                    unpaintPotMoves(validMoves);
                    validMoves = gameB[x1][y1].vMove(x1, y1, gameB);
                    print2Array(validMoves);
                    paintPotMoves(validMoves);

                } 
                else
                {
                     unpaintPotMoves(validMoves);
                     movePiece(x1, y1, x2, y2);
                     mCount = 0;

                }
            }
            else 
            {
                x2 = xHold;
                y2 = yHold;
                unpaintPotMoves(validMoves);
                movePiece(x1, y1, x2, y2);
                mCount = 0;

            }

        }
    }
    
    public void movePiece(int xa, int ya, int xb, int yb)
    {
    	int row = 0 ;
    	
    	for (int col = 0; col < validMoves.length; col++)
    	{
    		if (xb == validMoves[col][row] && yb == validMoves[col][row+1])
    		{
    			holdPiece = gameB[xb][yb];
    			gameB[xb][yb] = gameB[xa][ya];
    	        CB[xb][yb].setText( gameB[xb][yb].getImgID() );
    	        gameB[xb][yb].isMoved();
    	        gameB[xa][ya] = null;
    	        CB[xa][ya].setText(xa+" "+ya);
    	        break;
    		}
    	}
    	
        
    }
    
    
    public void paintPotMoves(int[][] posit)
    {
    	int col = 0;
    	int row = 0;
    	int k	= 0;
    	
    	for (int i = 0; i < posit.length; i ++)
    	{
    		if (posit[i][k] != 8)
    		{
    		col = posit[i][k];
    		row = posit[i][k+1];
    		CB[col][row].setForeground(Color.RED);
    		}
    		else
    		{
    			break;
    		}
    	}
    }
    
    public void unpaintPotMoves(int[][] posit)
    {
    	int col = 0;
    	int row = 0;
    	int k	= 0;
    	
    	for (int i = 0; i < posit.length; i ++)
    	{
    		if (posit[i][k] != 8)
    		{
    		col = posit[i][k];
    		row = posit[i][k+1];
    		CB[col][row].setForeground(Color.blue);
    		}
    		else
    		{
    			break;
    		}
    	}
    }
    
    // Initialize gameB Board and print out basic setup, ~ = null spaces
    public void initBoard() 
    {
        //Pawns
        for (int i = 0; i < 8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                if (i == 1) 
                {
                    gameB[i][j] = (new Piece (pT.PAWN, pC.B));
                    CB[i][j].setText( gameB[i][j].getImgID() );
                } 
                else if (i == 6) 
                {
                    gameB[i][j] = (new Piece (pT.PAWN, pC.W));
                    CB[i][j].setText( gameB[i][j].getImgID() );
                    
                }
            }
        }

        // Rooks
        gameB[0][0] = (new Piece (pT.ROOK, pC.B));
        CB[0][0].setText( gameB[0][0].getImgID());
        gameB[0][7] = (new Piece (pT.ROOK, pC.B));
        CB[0][7].setText( gameB[0][7].getImgID());
        gameB[7][0] = (new Piece (pT.ROOK, pC.W));
        CB[7][0].setText( gameB[7][0].getImgID());
        gameB[7][7] = (new Piece (pT.ROOK, pC.W));
        CB[7][7].setText( gameB[7][7].getImgID());

        //Knights
        gameB[0][1] = (new Piece (pT.KNIGHT, pC.B));
        CB[0][1].setText( gameB[0][1].getImgID());
        gameB[0][6] = (new Piece (pT.KNIGHT, pC.B));
        CB[0][6].setText( gameB[0][6].getImgID());
        gameB[7][1] = (new Piece (pT.KNIGHT, pC.W));
        CB[7][1].setText( gameB[7][1].getImgID());
        gameB[7][6] = (new Piece (pT.KNIGHT, pC.W));
        CB[7][6].setText( gameB[7][6].getImgID());

        //Bishops
        gameB[0][2] = (new Piece (pT.BISHOP, pC.B));
        CB[0][2].setText( gameB[0][2].getImgID());
        gameB[0][5] = (new Piece (pT.BISHOP, pC.B));
        CB[0][5].setText( gameB[0][5].getImgID());
        gameB[7][2] = (new Piece (pT.BISHOP, pC.W));
        CB[7][2].setText( gameB[7][2].getImgID());
        gameB[7][5] = (new Piece (pT.BISHOP, pC.W));
        CB[7][5].setText( gameB[7][5].getImgID());

        //Queens
        gameB[0][3] = (new Piece (pT.QUEEN, pC.B ));
        CB[0][3].setText( gameB[0][3].getImgID());
        gameB[7][3] = (new Piece (pT.QUEEN, pC.W));
        CB[7][3].setText( gameB[7][3].getImgID());

        //Kings
        gameB[0][4] = (new Piece (pT.KING, pC.B));
        CB[0][4].setText( gameB[0][4].getImgID());
        gameB[7][4] = (new Piece (pT.KING, pC.W));
        CB[7][4].setText( gameB[7][4].getImgID());

        //print list of pieces
        for (int i = 0; i < 8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                if (gameB[i][j] == null) 
                {
                    System.out.print(" ~~ " + " | ");
                } 
                else 
                {
                    System.out.print( gameB[i][j].getImgID() + " | ");
                }
            }
            System.out.println();
        }

    }
    
    
    // Test method for Valid Move Print out.
    public void print2Array(int[][] mark)
    {
        for (int i = 0; i < mark.length ; i++)
		{
			for (int j = 0 ; j < 2 ; j++)
			{
				
					System.out.print(mark[i][j] + " , ");
			}
			System.out.println();
		}
    }
    
    
    // Variables and 'Item' initialization

    
    public Piece[][] gameB = new Piece[8][8];
    Piece holdPiece = new Piece();
    JPanel PanelOne  = new JPanel();
    JButton CB[][] = new JButton[8][8];
    int mCount = 0;
    int x1, y1, x2, y2, xHold, yHold;
    int[][] validMoves ;
    
}
