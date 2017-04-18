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
    // Variables and 'Item' initialization

    public Piece[][] gameB = new Piece[8][8];
    Piece holdPiece = new Piece();
    JPanel PanelOne  = new JPanel();
    JButton CB[][] = new JButton[8][8];
    int mCount = 0;
    int x1, y1, x2, y2, xHold, yHold;
    int[][] validMoves ;
    int KBX, KBY ;
    int KWX, KWY ;
    boolean wCheck;
    boolean bCheck;
    int moveCount = 0;
    
    
    
    public Board()
    {
        int Row,Col;
   	 
   	 Toolkit MyKit 	= Toolkit.getDefaultToolkit	();
   	 Dimension XY 	= MyKit.getScreenSize		();
   	  
   	 Row = XY.width;
   	 Col = XY.height;
   	 
   	 setSize	(25*Row/80	, Col/2		);
   	 setLocation    (13*Row/32	, Col/4		);
   	 
   	 setTitle						("Testing of Chess Movement for TripleS" );
   	 
   	 boardPanel PanelGame		= new boardPanel ();
   	 Container ContentPanel01 	= getContentPane();
   	 ContentPanel01.add(PanelGame);
        
        
        initBoard(); // Populate 
        
        
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
                    CB[col][row] = new JButton(); //col + "" + row
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
            

            if (mCount == 0) 
            {
                x1 = xHold;
                y1 = yHold;
                if(gameB[xHold][yHold] != null)
                {
                	System.out.println(gameB[xHold][yHold].getImgID());
                	validMoves = gameB[x1][y1].vMove(x1, y1, gameB);
                	paintPotMoves(validMoves);
                	mCount++;
                }
            } 
            else if (mCount == 1 && gameB[xHold][yHold] != null) 
            {
                x2 = xHold;
                y2 = yHold;
                if (gameB[x1][y1].colr == gameB[x2][y2].colr) 
                {
                    x1 = x2;
                    y1 = y2;
                    System.out.println("Same Color Piece Selection, Recalculating");
                    unpaintPotMoves(validMoves);
                    validMoves = gameB[x1][y1].vMove(x1, y1, gameB);
                    paintPotMoves(validMoves);

                } 
                else
                {
                    unpaintPotMoves(validMoves);
                    movePiece(x1, y1, x2, y2);
                    wCheck = isCheck(KWX, KWY);
                    bCheck = isCheck(KBX, KBY);
                    paintCheck();
                    System.out.println("in check b? " + bCheck);
                    System.out.println("in check w? " + wCheck);
                    mCount = 0;

                }
            }
            else 
            {
                x2 = xHold;
                y2 = yHold;
                unpaintPotMoves(validMoves);
                movePiece(x1, y1, x2, y2);
                wCheck = isCheck(KWX, KWY);
                bCheck = isCheck(KBX, KBY);
                paintCheck();
                System.out.println("in check b? " + bCheck);
                System.out.println("in check w? " + wCheck);      
                mCount = 0;

            }

        }
    } // End Clickthem Actionlistener
    
    public void movePiece(int xa, int ya, int xb, int yb) 
    {
        int row = 0;
        for (int col = 0; col < validMoves.length; col++) 
        {
            if (xb == validMoves[col][row] && yb == validMoves[col][row + 1]) 
            {
                // if valid move is a king
                if ((gameB[xb][yb] == null || gameB[xb][yb] != null) && gameB[xa][ya].type == pT.KING) 
                {
                    if ((gameB[xb][yb] == null || gameB[xb][yb] != null) && gameB[xa][ya].colr == pC.W) 
                    {
                        KWX = xb;
                        KWY = yb;
                        System.out.println("new King White Position X: " + KWX + " Y: " + KWY);
                    } 
                    else if ((gameB[xb][yb] == null || gameB[xb][yb] != null) && gameB[xa][ya].colr == pC.B) 
                    {
                        KBX = xb;
                        KBY = yb;
                        System.out.println("new King White Position X: " + KBX + " Y: " + KBY);
                    }
                    holdPiece = gameB[xb][yb];
                    gameB[xb][yb] = gameB[xa][ya];
                    CB[xb][yb].setText(gameB[xb][yb].getImgID());
                    gameB[xb][yb].isMoved();
                    gameB[xa][ya] = null;
                    CB[xa][ya].setText(" ");
                    
                } 
                // if any piece besides a king
                else 
                {
                    holdPiece = gameB[xb][yb];
                    gameB[xb][yb] = gameB[xa][ya];
                    CB[xb][yb].setText(gameB[xb][yb].getImgID());
                    gameB[xb][yb].isMoved();
                    gameB[xa][ya] = null;
                    CB[xa][ya].setText(" ");
                }
            }
        }
    

    }// End movePiece()
    
    // method to paint potential valid moves that a user can select
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
                CB[col][row].setBackground(Color.green);
    		}
    		else
    		{
    			break;
    		}
    	}
    } // End paintPotMoves()
    
    // Method to unpaint potential moves a user can make
    public void unpaintPotMoves(int[][] posit) {
        int col = 0;
        int row = 0;
        int k = 0;

        for (int i = 0; i < posit.length; i++) {
            if (posit[i][k] != 8) 
            {
                col = posit[i][k];
                row = posit[i][k + 1];
                if (col % 2 == row % 2) 
                {
                    CB[col][row].setBackground(Color.gray);
                } 
                else 
                {
                    CB[col][row].setBackground(Color.white);
                }

            } 
            else 
            {
                break;
            }
        }
    } // End unpaintPotMoves()
    
    // Method to Paint the current status of Check: RED == In-check, standard BLUE == not-in-check
    public void paintCheck()
    {
        if (wCheck == true)
        {
            CB[KWX][KWY].setForeground(Color.red);
        }
        else if (wCheck == false)
        {
            CB[KWX][KWY].setForeground(Color.blue);
        }
        if (bCheck == true)
        {
            CB[KBX][KBY].setForeground(Color.red);
        }
        else if (bCheck == false)
        {
            CB[KBX][KBY].setForeground(Color.blue);
        }
    } //End paintCheck()
    
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
        bCheck = false;
        KBX = 0;
        KBY = 4;
        gameB[7][4] = (new Piece (pT.KING, pC.W));
        CB[7][4].setText( gameB[7][4].getImgID());
        wCheck = false;
        KWX = 7;
        KWY = 4;
        
        moveCount = 0;


    } // End initBoard()
 

    
    // Method to Calculate If in check
    public boolean isCheck(int x, int y)
    {
        boolean arthur  = false       ; // Couldn't help myself..                

        //look for Check by Pawn if Black King
        if (gameB[x][y].colr == pC.B) 
        {
            if ((y + 1) != 8 && gameB[x + 1][y + 1] != null && gameB[x + 1][y + 1].colr == pC.W && gameB[x + 1][y + 1].type == pT.PAWN) 
            {
                arthur = true;

            }
            if (y != 0 && gameB[x + 1][y - 1] != null && gameB[x + 1][y - 1].colr == pC.W && gameB[x + 1][y - 1].type == pT.PAWN) 
            {
                arthur = true;

            } 
        } 
        //look for Check by Pawn if White King
        else if (gameB[x][y].colr == pC.W) 
        {
            if ((y + 1) != 8 && gameB[x - 1][y + 1] != null && gameB[x - 1][y + 1].colr == pC.B && gameB[x - 1][y + 1].type == pT.PAWN) 
            {
                arthur = true;
            }
            if (y != 0 && gameB[x - 1][y - 1] != null && gameB[x - 1][y - 1].colr == pC.B && gameB[x - 1][y - 1].type == pT.PAWN) 
            {
                arthur = true;
            }
        } 
        // Knight Moves
        //(-2,-1)
        if ((x - 2) >= 0 && (y - 1) >= 0 && gameB[x - 2][y - 1] != null && gameB[x - 2][y - 1].type == pT.KNIGHT && gameB[x][y].colr != gameB[x - 2][y - 1].colr) 
        {
            arthur = true;
        }
        //(-2,+1)
        if ((x - 2) >= 0 && (y + 1) <= 7 && gameB[x - 2][y + 1] != null && gameB[x - 2][y + 1].type == pT.KNIGHT && gameB[x][y].colr != gameB[x - 2][y + 1].colr) 
        {
            arthur = true;
        }
        //(+2, -1)
        if ((x + 2) <= 7 && (y - 1) >= 0 && gameB[x + 2][y - 1] != null && gameB[x + 2][y - 1].type == pT.KNIGHT && gameB[x][y].colr != gameB[x + 2][y - 1].colr) 
        {
            arthur = true;
        }

        //(+2,+1)
        if ((x + 2) <= 7 && (y + 1) <= 7 &&  gameB[x + 2][y + 1] != null && gameB[x + 2][y + 1].type == pT.KNIGHT && gameB[x][y].colr != gameB[x + 2][y + 1].colr) 
        {
            arthur = true;
        }
        //(-1,-2)
        if ((x - 1) >= 0 && (y - 2) >= 0 && gameB[x - 1][y - 2] != null && gameB[x - 1][y - 2].type == pT.KNIGHT && gameB[x][y].colr != gameB[x - 1][y - 2].colr) 
        {
            arthur = true;
        }
        //(-1,+2)
        if ((x - 1) >= 0 && (y + 2) <= 7 &&  gameB[x - 1][y + 2] != null && gameB[x - 1][y + 2].type == pT.KNIGHT && gameB[x][y].colr != gameB[x - 1][y + 2].colr) 
        {
            arthur = true;
        }
        //(+1,-2)
        if ((x + 1) <= 7 && (y - 2) >= 0 && gameB[x + 1][y - 2] != null && gameB[x + 1][y - 2].type == pT.KNIGHT && gameB[x][y].colr != gameB[x + 1][y - 2].colr) 
        {
            arthur = true;
        }
        //(+1,+2)
        if ((x + 1) <= 7 && (y + 2) <= 7 && gameB[x + 1][y + 2] != null && gameB[x + 1][y + 2].type == pT.KNIGHT && gameB[x][y].colr != gameB[x + 1][y + 2].colr) 
        {
            arthur = true;
        }
        
        // Horizontal Right ( Queen or Rook)
        for (int h = (y + 1); h <= 7; h++) 
        {
            if (gameB[x][h] != null && (gameB[x][h].colr == gameB[x][y].colr || ( gameB[x][h].colr != gameB[x][y].colr && gameB[x][h].type == pT.PAWN))) 
            {
                break;
            } 
            else if (gameB[x][h] != null && gameB[x][h].colr != gameB[x][y].colr && (gameB[x][h].type == pT.QUEEN || gameB[x][h].type == pT.ROOK)) 
            {
                arthur = true;
                break;
            } 
        }
        
        // Horizontal Left (Queen or ROOK)
        for (int h = (y - 1); h >= 0; h--) 
        {
            if (gameB[x][h] != null && (gameB[x][h].colr == gameB[x][y].colr || ( gameB[x][h].colr != gameB[x][y].colr && gameB[x][h].type == pT.PAWN)))
            {
                break;
            } 
            else if (gameB[x][h] != null && gameB[x][h].colr != gameB[x][y].colr && (gameB[x][h].type == pT.QUEEN || gameB[x][h].type == pT.ROOK)) 
            {
                arthur = true;
                break;
            } 
        }
        
        //Vertical Up (Queen or Rook)
        for (int v = (x - 1); v >= 0; v--) 
        {
            if (gameB[v][y] != null && (gameB[v][y].colr == gameB[x][y].colr || (gameB[v][y].colr != gameB[x][y].colr && gameB[v][y].type == pT.PAWN))) 
            {
                break;
            } 
            else if (gameB[v][y] != null && gameB[v][y].colr != gameB[x][y].colr && (gameB[v][y].type == pT.QUEEN || gameB[v][y].type == pT.ROOK)) 
            {
                arthur = true;
                break;
            }
        }
        
        //Vertical Down (Queen or Rook)
        for (int v = (x + 1); v <= 7; v++) 
        {
            if (gameB[v][y] != null && (gameB[v][y].colr == gameB[x][y].colr || (gameB[v][y].colr != gameB[x][y].colr && gameB[v][y].type == pT.PAWN))) 
            {
                break;
            } 
            else if (gameB[v][y] != null && gameB[v][y].colr != gameB[x][y].colr && (gameB[v][y].type == pT.QUEEN || gameB[v][y].type == pT.ROOK)) 
            {
                arthur = true;
                break;
            }
        }
        
        // Diagonal DR going right (+, +) (Queen or Bishop)
	for (int a = (x+1), b = (y+1); a <= 7 && b <= 7 ; a++, b++)
	{
            if (gameB[a][b] != null && (gameB[a][b].colr == gameB[x][y].colr || (gameB[a][b].colr != gameB[x][y].colr && gameB[a][b].type == pT.PAWN))) 
            {
                break;
            } 
            else if (gameB[a][b] != null && gameB[a][b].colr != gameB[x][y].colr && (gameB[a][b].type == pT.QUEEN || gameB[a][b].type == pT.BISHOP)) 
            {
                arthur = true;
                break;
            } 
        }
        
        // Diagonal DR going to the left (-, -) (Queen or Bishop)
        for (int a = (x-1), b = (y-1); a >= 0 && b >= 0 ; a--, b--)
        {
            if (gameB[a][b] != null && (gameB[a][b].colr == gameB[x][y].colr || (gameB[a][b].colr != gameB[x][y].colr && gameB[a][b].type == pT.PAWN))) 
            {
                break;
            } 
            else if (gameB[a][b] != null && gameB[a][b].colr != gameB[x][y].colr && (gameB[a][b].type == pT.QUEEN || gameB[a][b].type == pT.BISHOP)) 
            {
                arthur = true;
                break;
            } 
        }
        
        // Diagonal UR going right (-, +) (Queen or Bishop)
        for (int c = (x-1), d = (y+1); c >= 0 && d <= 7 ; c--, d++)
        {
            if (gameB[c][d] != null && (gameB[c][d].colr == gameB[x][y].colr || (gameB[c][d].colr != gameB[x][y].colr && gameB[c][d].type == pT.PAWN))) 
            {
                break;
            } 
            else if (gameB[c][d] != null && gameB[c][d].colr != gameB[x][y].colr && (gameB[c][d].type == pT.QUEEN || gameB[c][d].type == pT.BISHOP)) 
            {
                arthur = true;
                break;
            }
        }
        
        // Diagonal UR going left (+, -) (Queen or Bishop)
	for (int c = (x + 1), d = (y - 1); c <= 7 && d >= 0; c++, d--) 
        {
            if (gameB[c][d] != null && (gameB[c][d].colr == gameB[x][y].colr || (gameB[c][d].colr != gameB[x][y].colr && gameB[c][d].type == pT.PAWN))) 
            {
                break;
            } 
            else if (gameB[c][d] != null && gameB[c][d].colr != gameB[x][y].colr && (gameB[c][d].type == pT.QUEEN || gameB[c][d].type == pT.BISHOP)) 
            {
                arthur = true;
                break;
            }
        }
        

        return arthur;
    }// End isCheck() method;
    
    
    
}
