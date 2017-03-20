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
   	 
   	 Image MyImage = MyKit.getImage	("merlin.gif"					);
   	 setIconImage					(MyImage						);
   	 setTitle						("Testing of Chess Movement for TripleS" );
   	 
   	 boardPanel PanelGame			= new boardPanel ();
   	 Container ContentPanel01 	= getContentPane();
   	 ContentPanel01.add(PanelGame);
        
        
        initBoard();
        
        
    } // End Board
    
    private class boardPanel extends JPanel
    {
        public boardPanel()
        {
            setLayout(new BorderLayout());
            
            Font BIG = new Font("TimesRoman", Font.BOLD, 30);
            
            PanelOne.setLayout(new GridLayout(8,8, 5, 5));
            
            for (int col = 0; col<8; col++)
            {
                for(int row = 0; row < 8; row++)
    		{
        		CB[col][row] = new JButton (col+""+row);
    			CB[col][row].setForeground (Color.blue		);
                        if (col % 2 == row % 2)
                        {
                            CB[col][row].setBackground (Color.cyan		);
                        }
                        else
                        {
                            CB[col][row].setBackground (Color.white );
                        }
        		CB[col][row].setFont       (BIG		);
                        CB[col][row].putClientProperty("Col", col);
                        CB[col][row].putClientProperty("Row", row);
        		PanelOne.add        (CB[col][row]		);
                        ClickThem Dummy = new ClickThem(	);
        		CB[col][row].addActionListener(Dummy	 	);
    			
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
                    System.out.println(ButtonPressed.getClientProperty("Col")+ " , " + ButtonPressed.getClientProperty("Row"));
                    //System.out.println(((Piece) gameB[xHold][yHold]).getImgID());
                    
                    if (mCount == 0)
                    {
                        x1 = xHold;
                        y1 = yHold;
                        mCount++;
                    }
                    else if (mCount == 1)
                    {
                        x2 = xHold;
                        y2 = yHold;
                        movePiece(x1, y1, x2, y2);
                        mCount = 0;
                        
                    }
        		
    				
    			
    		}
    }
    
    public void movePiece(int xa, int ya, int xb, int yb)
    {
        HoldObj = gameB[xb][yb];
        gameB[xb][yb] = gameB[xa][ya];
        CB[xb][yb].setText((((Piece) gameB[xb][yb]).getImgID()));
        gameB[xa][ya] = null;
        CB[xa][ya].setText(xa+" "+ya);
        
    }
    
    
    
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
                                        CB[i][j].setText((((Piece) gameB[i][j]).getImgID()));
				}
				else if ( i == 6)
				{
					gameB[i][j] = ( new Pawn ( pColor.W ));
                                        CB[i][j].setText((((Piece) gameB[i][j]).getImgID()));
				}
			}
		}
		
		// Rooks
		gameB[0][0] = ( new Rook ( pColor.B ));
                CB[0][0].setText((((Piece) gameB[0][0]).getImgID()));
		gameB[0][7] = ( new Rook ( pColor.B ));
                CB[0][7].setText((((Piece) gameB[0][7]).getImgID()));
		gameB[7][0] = ( new Rook ( pColor.W ));
                CB[7][0].setText((((Piece) gameB[7][0]).getImgID()));
		gameB[7][7] = ( new Rook ( pColor.W ));
                CB[7][7].setText((((Piece) gameB[7][7]).getImgID()));
		
		//Knights
		gameB[0][1] = ( new Knight ( pColor.B ));
                CB[0][1].setText((((Piece) gameB[0][1]).getImgID()));
		gameB[0][6] = ( new Knight ( pColor.B ));
                CB[0][6].setText((((Piece) gameB[0][6]).getImgID()));
		gameB[7][1] = ( new Knight ( pColor.W ));
		CB[7][1].setText((((Piece) gameB[7][1]).getImgID()));
                gameB[7][6] = ( new Knight ( pColor.W ));
		CB[7][6].setText((((Piece) gameB[7][6]).getImgID()));
                
		//Bishops
		gameB[0][2] = ( new Bishop ( pColor.B ));
                CB[0][2].setText((((Piece) gameB[0][2]).getImgID()));
		gameB[0][5] = ( new Bishop ( pColor.B ));
		CB[0][5].setText((((Piece) gameB[0][5]).getImgID()));
                gameB[7][2] = ( new Bishop ( pColor.W ));
		CB[7][2].setText((((Piece) gameB[7][2]).getImgID()));
                gameB[7][5] = ( new Bishop ( pColor.W ));
		CB[7][5].setText((((Piece) gameB[7][5]).getImgID()));
                
		//Queens
		gameB[0][3] = ( new Queen ( pColor.B ));
                CB[0][3].setText((((Piece) gameB[0][3]).getImgID()));
		gameB[7][3] = ( new Queen ( pColor.B ));
		CB[7][3].setText((((Piece) gameB[7][3]).getImgID()));
                
		//Kings
		gameB[0][4] = ( new King ( pColor.B ));
		CB[0][4].setText((((Piece) gameB[0][4]).getImgID()));
                gameB[7][4] = ( new King ( pColor.B ));
		CB[7][4].setText((((Piece) gameB[7][4]).getImgID()));
                
		
		
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
    Object HoldObj = new Object();
    JPanel PanelOne  = new JPanel();
    JButton CB[][] = new JButton[8][8];
    int mCount = 0;
    int x1, y1, x2, y2, xHold, yHold;

    
}
