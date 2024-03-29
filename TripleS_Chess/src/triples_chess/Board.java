/**
 *  <b>PROGRAM</b>     : Triple S Chess<br>
 *  <b>AUTHORS</b>     : Stephen C., Sean B., Sid C.<br>
 *  <b>PURPOSE</b>     : To demonstrate full development process (Concept to Release)<br>
 *  <b>COURSE</b>      : CSC 478 [B] (Software Engineering Capstone)<br>
 *  <b>DATE</b>        : 01.23.2017 - 05.12.2017<br>
 */
package triples_chess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
<h3 style="color:white;">CLASS: Board</h3>
<HR>
<h3 style="color:white;">PARAMETERS: None</h3>
<HR>
<h3 style="color:white;">VARIABLES:</h3>
 *      <blockquote><i>a. gameB - 2D Array for game pieces location</i></blockquote>
 *      <blockquote><i>b. holdPiece - Type piece to hold value of piece being captured</i></blockquote>
 *      <blockquote><i>d. PanelOne - Board panel for 8x8 array of JButtons</i></blockquote>
 *      <blockquote><i>e. CB[][] - Actual 2D array of JButtons for Control</i></blockquote>
 *      <blockquote><i>f. mCount - Counter for move</i></blockquote>
 *      <blockquote><i>g. x1, y1, x2, y2, xHold, yHold - Variables for move requirements</i></blockquote>
 *      <blockquote><i>h. validMoves[][] - Generated 2D array for potential valid moves</i></blockquote>
 *      <blockquote><i>i. KBX, KBY - Current X / Y location for Black King</i></blockquote>
 *      <blockquote><i>j. KWX, KWY - Current X / Y location for White King</i></blockquote>
 *      <blockquote><i>k. wCheck - Boolean variable for White Check</i></blockquote>
 *      <blockquote><i>l. bCheck - Boolean variable for Black Check</i></blockquote>
 *      <blockquote><i>m. moveCount - Count of moves</i></blockquote>
 * <HR>
 * <h3 style="color:white;">PURPOSE:</h3><blockquote><pre>  
Set initial window size;
Create new boardpanel() class;
Load boardpanel into the frame container;
Initialize the board;</pre></blockquote>
*/
public class Board extends JFrame
{
    // Variables and 'Item' initialization
/**
 * 
 */
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
    
    
/**
<h3 style="color:white;">PROCEDURE: Board</h3>
<HR>
<h3 style="color:white;">PARAMETERS: None</h3>
<HR>
<h3 style="color:white;">VARIABLES: None</h3>
*/
    public Board()
    {
        int Row,Col;
   	 
   	Toolkit MyKit 	= Toolkit.getDefaultToolkit	();
   	Dimension XY 	= MyKit.getScreenSize		();
   	  
   	Row = XY.width;
   	Col = XY.height;
   	 
   	setSize	(800	, 800	);   //(25*Row/80	, Col/2		)
         
   	setLocation    (13*Row/32	, Col/4		);
   	 
   	setTitle						("Chess by Team Triple S" );
   	 
   	boardPanel PanelGame		= new boardPanel ();
   	Container ContentPanel01 	= getContentPane();
   	ContentPanel01.add(PanelGame);
        
        
        initBoard(); // Populate 
        
        
    } // End Board
    
    private class boardPanel extends JPanel {

        public boardPanel() 
        {
            setLayout(new BorderLayout());

            PanelOne.setLayout(new GridLayout(8, 8, 5, 5));
            
            //Build out 2d Grid Array of Control Buttons
            for (int col = 0; col < 8; col++) 
            {
                for (int row = 0; row < 8; row++) 
                {
                    CB[col][row] = new JButton();
                    CB[col][row].setForeground(Color.blue);
                    if (col % 2 == row % 2) 
                    {
                        CB[col][row].setBackground(Color.gray);
                    } 
                    else 
                    {
                        CB[col][row].setBackground(Color.white);
                    }
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

    /**
     * <h3 style="color:white;">PROCEDURE: ClickThem </h3>
     * <HR>
     * <h3 style="color:white;">PARAMETERS: None </h3>
     * <HR>
     * <h3 style="color:white;">VARIABLES: </h3>
     *      <blockquote><i>a. ButtonPressed - Event reader when a square is clicked</i></blockquote>
     * <HR>
     * <h3 style="color:white;">PURPOSE: </h3><blockquote>
     * <pre>
     Read the button clicked and get the x and y values for its location;
     If (its the beginning of a new move )
        Assign the current position to position one(the position looking to move);
     If (the position is actually a piece and not a blank square)
        calculate potential valid moves and 'paint' them;
     Else If ( not the first of a new move, and not an empty square )
        Assign current position to position two ( position to be moved to);

     If ( the color of position one and position two are the same )
        Assign position two to position one, recalculate potential moves and 'paint' them;
     Else
        Unpaint the potential moves;
        Move the piece;
        Check to see if white or black is in check from the move and paint the check status;
        Set move back to 0 to initialize a new move;
     Else (if the piece is an empty square)
        Unpaint the potential moves;
        Move the piece;
        Check to see if white or black is in check from the move and paint the check status;
        Set move back to 0 to initialize a new move;</pre></blockquote>
*/    
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
    
    /**
     * <h3 style="color:white;">PROCEDURE: movePiece</h3>
     * <HR>
     * <h3 style="color:white;">PARAMETERS:</h3>
            @param xa - Piece being moved
     *      @param ya - Piece being moved
     *      @param xb - Piece being captured
     *      @param yb - Piece being captured
     * <HR>
     * <h3 style="color:white;">VARIABLES:</h3>
     *      <blockquote><i>a. row - </i></blockquote>
     * <HR>
     * <h3 style="color:white;">PURPOSE: </h3><blockquote>
     * <pre>
(Takes x and y coordinates for first position(a) and second position(b))
Check to make sure that proposed move is a valid move
If (the piece being moved is a King check color and change the corresponding colors position location);
    Hold the current value of location two;
    Move piece from location one to location two;
    Load the image of the piece into the new location;
    Set the value of location one to null;
    Remove the image from the old location;
Else(If piece not a King )
    Hold the current value of location two;
    Move piece from location one to location two;
    Load the image of the piece into the new location;
    Set the value of location one to null;
    Remove the image from the old location;</pre></blockquote>
* 
*/       
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
                        if (wCheck == true)
                        {
                            if (xa % 2 == ya % 2) 
                            {
                                CB[xa][ya].setBackground(Color.gray);
                            } 
                            else 
                            {
                                CB[xa][ya].setBackground(Color.white);
                            }
                        }
                    
                    
                    } 
                    else if ((gameB[xb][yb] == null || gameB[xb][yb] != null) && gameB[xa][ya].colr == pC.B) 
                    {
                        KBX = xb;
                        KBY = yb;
                        System.out.println("new King White Position X: " + KBX + " Y: " + KBY);
                        if (bCheck == true)
                        {
                            if (xa % 2 == ya % 2) 
                            {
                                CB[xa][ya].setBackground(Color.gray);
                            } 
                            else 
                            {
                                CB[xa][ya].setBackground(Color.white);
                            }
                        }
                    }
                    holdPiece = gameB[xb][yb];
                    gameB[xb][yb] = gameB[xa][ya];
                    CB[xb][yb].setIcon( new javax.swing.ImageIcon(getClass().getResource(gameB[xb][yb].getImgID())) );
                    gameB[xb][yb].isMoved();
                    gameB[xa][ya] = null;
                    CB[xa][ya].setIcon( new javax.swing.ImageIcon(getClass().getResource("Empty.png")) );
                    
                } 
                // if any piece besides a king
                else 
                {
                    holdPiece = gameB[xb][yb];
                    gameB[xb][yb] = gameB[xa][ya];
                    CB[xb][yb].setIcon( new javax.swing.ImageIcon(getClass().getResource(gameB[xb][yb].getImgID())) );
                    gameB[xb][yb].isMoved();
                    gameB[xa][ya] = null;
                    CB[xa][ya].setIcon( new javax.swing.ImageIcon(getClass().getResource("Empty.png")) );
                }
            }
        }
    

    }// End movePiece()
    
    /**
     * <h3 style="color:white;">PROCEDURE: paintPotMoves</h3>
     * <HR>
     * <h3 style="color:white;">PARAMETERS:</h3>
     *      @param posit - Takes the 2dArray of valid moves
     * <HR>
     * <h3 style="color:white;">VARIABLES:</h3>
     *      <blockquote><i>a. col - X value of the potential square</i></blockquote>
     *      <blockquote><i>a. row - Y value of the potential square</i></blockquote>
     *      <blockquote><i>a. k - For loop iterator counter</i></blockquote>
     * <HR>
     * <h3 style="color:white;">PURPOSE:</h3><blockquote>
     * <pre>
Set the background of the locations from the 2dArray to green showing potential moves;</pre></blockquote>
*/    
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
    
    /**
     * <h3 style="color:white;">PROCEDURE: unpaintPotMoves</h3>
     * <HR>
     * <h3 style="color:white;">PARAMETERS: </h3>
     *      @param posit - Takes the 2dArray of valid moves
     * <HR>
     * <h3 style="color:white;">VARIABLES:</h3>
     *      <blockquote><i>a. col - X value of the potential square</i></blockquote>
     *      <blockquote><i>a. row - Y value of the potential square</i></blockquote>
     *      <blockquote><i>a. k - For loop iterator counter</i></blockquote>
     * <HR>
     * <h3 style="color:white;">PURPOSE:</h3><blockquote>
     * <pre>
Set the background of the locations from the 2dArray to gray or white depending on its relative location;</pre></blockquote>
*/  
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
    
    /**
     * <h3 style="color:white;">PROCEDURE: paintCheck</h3>
     * <HR>
     * <h3 style="color:white;">PARAMETERS: None</h3>
     * <HR>
     * <h3 style="color:white;">VARIABLES: None</h3>
     * <HR>
     * <h3 style="color:white;">PURPOSE:</h3><blockquote>
     * <pre>
If (black or white are in check )
    Change the respective background to red marking in check;
Else If (black or white are not in check )
    Change the respective background back to white or gray;</pre></blockquote>
*/
    public void paintCheck()
    {
        if (wCheck == true)
        {
            CB[KWX][KWY].setBackground(Color.red);
        }
        else if (wCheck == false)
        {
            if (KWX % 2 == KWY % 2) 
            {
                CB[KWX][KWY].setBackground(Color.gray);
            } 
            else 
            {
                CB[KWX][KWY].setBackground(Color.white);
            }
        }
        if (bCheck == true)
        {
            CB[KBX][KBY].setBackground(Color.red);
        }
        else if (bCheck == false)
        {
            if (KBX % 2 == KBY % 2) 
            {
                CB[KBX][KBY].setBackground(Color.gray);
            } 
            else 
            {
                CB[KBX][KBY].setBackground(Color.white);
            }
        }
    } //End paintCheck()
    
    /**
     * <h3 style="color:white;">PROCEDURE: initBoard</h3>
     * <HR>
     * <h3 style="color:white;">PARAMETERS: None</h3>
     * <HR>
     * <h3 style="color:white;">VARIABLES: None</h3>
     * <HR>
     * <h3 style="color:white;">PURPOSE:</h3><blockquote>
     * <pre>
Intialize black and white Pawn pieces into the piece 2dArray and set Pawn image to respective location;
Initialize black and white Rook pieces into the piece 2dArray and set Rook image to respective location;
Initialize black and white Knight pieces into the piece 2dArray and set Knight image to respective location;
Initialize black and white Bishop pieces into the piece 2dArray and set Bishop image to respective location;
Initialize black and white Queen pieces into the piece 2dArray and set Queen image to respective location;
Initialize black and white King pieces into the piece 2dArray and set King image to respective location;
</pre></blockquote>
*/
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
                    CB[i][j].setIcon( new javax.swing.ImageIcon(getClass().getResource(gameB[i][j].getImgID())) );
                } 
                else if (i == 6) 
                {
                    gameB[i][j] = (new Piece (pT.PAWN, pC.W));
                    CB[i][j].setIcon( new javax.swing.ImageIcon(getClass().getResource(gameB[i][j].getImgID())) );
                    
                }
            }
        }

        // Rooks
        gameB[0][0] = (new Piece (pT.ROOK, pC.B));
        CB[0][0].setIcon( new javax.swing.ImageIcon(getClass().getResource(gameB[0][0].getImgID())) );
        gameB[0][7] = (new Piece (pT.ROOK, pC.B));
        CB[0][7].setIcon( new javax.swing.ImageIcon(getClass().getResource(gameB[0][7].getImgID())) );
        gameB[7][0] = (new Piece (pT.ROOK, pC.W));
        CB[7][0].setIcon( new javax.swing.ImageIcon(getClass().getResource(gameB[7][0].getImgID())) );
        gameB[7][7] = (new Piece (pT.ROOK, pC.W));
        CB[7][7].setIcon( new javax.swing.ImageIcon(getClass().getResource(gameB[7][7].getImgID())) );

        //Knights
        gameB[0][1] = (new Piece (pT.KNIGHT, pC.B));
        CB[0][1].setIcon( new javax.swing.ImageIcon(getClass().getResource(gameB[0][1].getImgID())) );
        gameB[0][6] = (new Piece (pT.KNIGHT, pC.B));
        CB[0][6].setIcon( new javax.swing.ImageIcon(getClass().getResource(gameB[0][6].getImgID())) );
        gameB[7][1] = (new Piece (pT.KNIGHT, pC.W));
        CB[7][1].setIcon( new javax.swing.ImageIcon(getClass().getResource(gameB[7][1].getImgID())) );
        gameB[7][6] = (new Piece (pT.KNIGHT, pC.W));
        CB[7][6].setIcon( new javax.swing.ImageIcon(getClass().getResource(gameB[7][6].getImgID())) );

        //Bishops
        gameB[0][2] = (new Piece (pT.BISHOP, pC.B));
        CB[0][2].setIcon( new javax.swing.ImageIcon(getClass().getResource(gameB[0][2].getImgID())) );
        gameB[0][5] = (new Piece (pT.BISHOP, pC.B));
        CB[0][5].setIcon( new javax.swing.ImageIcon(getClass().getResource(gameB[0][5].getImgID())) );
        gameB[7][2] = (new Piece (pT.BISHOP, pC.W));
        CB[7][2].setIcon( new javax.swing.ImageIcon(getClass().getResource(gameB[7][2].getImgID())) );
        gameB[7][5] = (new Piece (pT.BISHOP, pC.W));
        CB[7][5].setIcon( new javax.swing.ImageIcon(getClass().getResource(gameB[7][5].getImgID())) );

        //Queens
        gameB[0][3] = (new Piece (pT.QUEEN, pC.B ));
        CB[0][3].setIcon( new javax.swing.ImageIcon(getClass().getResource(gameB[0][3].getImgID())) );
        gameB[7][3] = (new Piece (pT.QUEEN, pC.W));
        CB[7][3].setIcon( new javax.swing.ImageIcon(getClass().getResource(gameB[7][3].getImgID())) );

        //Kings
        gameB[0][4] = (new Piece (pT.KING, pC.B));
        CB[0][4].setIcon( new javax.swing.ImageIcon(getClass().getResource(gameB[0][4].getImgID())) );
        bCheck = false;
        KBX = 0;
        KBY = 4;
        gameB[7][4] = (new Piece (pT.KING, pC.W));
        CB[7][4].setIcon( new javax.swing.ImageIcon(getClass().getResource(gameB[7][4].getImgID())) );
        wCheck = false;
        KWX = 7;
        KWY = 4;
        
        moveCount = 0;


    } // End initBoard()
 
    
    /**
     * <h3 style="color:white;">PROCEDURE: isCheck</h3>
     * <HR>
     * <h3 style="color:white;">PARAMETERS:</h3>
     *      @param x - X location for the King being looked at for check
     *      @param y - Y location for the King being looked at for check
     * <HR>
     * <h3 style="color:white;">VARIABLES:</h3>
     *      <blockquote><i>a. arthur - Boolean value for check status</i></blockquote>
     * <HR>
     * <h3 style="color:white;">RETURN:</h3>
     *      @return arthur - Boolean value for check status
     * <HR>
     * <h3 style="color:white;">PURPOSE:</h3><blockquote>
     * <pre>Check to see if White and black king are in check:
By Pawn Diagonally;
By potential Knight locations;
By Horizontally and vertically by Queen or Rook;
By Diagonally in all directions by Bishop or Queen;
Return true if in check by any of these methods;</pre></blockquote>
*/
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
