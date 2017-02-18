/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triples_chess;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Stcof
 * Called By TripleS_Chess
 */
public class Board extends JFrame
{
    public Board()
    {
        int Row, Col;
        
        // Implement toolkit and get screen Size for Frame
        Toolkit aKit = Toolkit.getDefaultToolkit();
        Dimension XY = aKit.getScreenSize       ();
        
        // Set max Frame Measurements to Row and Col
        Row = XY.width ;
        Col = XY.height;
        
        // Set the Size and Location for the Frame
        setSize	(25*Row/80	, Col/2		);
   	setLocation(11*Row/32	, Col/4		);
        
        // Set the Title for the Frame
        setTitle ("TripleS Chess Game!");
        
        boardPanel startPane = new boardPanel();
        Container contentStartPane = getContentPane();
        contentStartPane.add(startPane);
        
    } // End Board
        
    private class boardPanel extends JPanel
    {
        public boardPanel()
        {
           setLayout(new BorderLayout());

           gamePanel.setLayout(new GridLayout(8, 8, 5, 5));
          
           initBoard();
           add(gamePanel);

        } // End BoardPanel Constructor
        
        
        public void initBoard()
        {
           Font Large = new Font("TimesRoman", Font.BOLD, 30);
           boardPlace = 0;
           

           
            for ( int a = 0 ; a < 8 ; a++ )
            {
                for( int b = 0 ; b < 8 ; b++ )
                {
                    pieceButton[a][b] = new JButton();
                    
                    if ((a % 2) == (b % 2))
                    {
                        pieceButton[a][b].setBackground(Color.WHITE);
                    }
                    else
                    {
                        pieceButton[a][b].setBackground(Color.GRAY);
                    }
                    //pieceButton[a][b].setName("" + boardStart.substring(boardPlace, boardPlace + 2));
                    pieceButton [a][b].setName      (gamePieces.substring( boardGPlace, boardGPlace + 3));
                    pieceButton [a][b].setText      (gamePieces.substring( boardGPlace, boardGPlace + 3));
                    pieceButton [a][b].setForeground(Color.BLUE                                         );
                    pieceButton [a][b].setFont      (Large                                              );
                    gamePanel         .add          (pieceButton[a][b]                                  );
                    ClickThem squareClicked = new ClickThem(                                            );
                    pieceButton[a][b].addActionListener(squareClicked                                   );
                    boardPlace  = boardPlace  + 3;
                    boardGPlace = boardGPlace + 4;
  
                }
            }
            // Initialize Piece locations and values
           
           
           
           
           
        } // End initBoard
          
        private class ClickThem implements ActionListener
    	{
            public void actionPerformed(ActionEvent Pressed)
            {
                Object ButtonPressed =  Pressed.getSource();
                for( int x = 0; x < 8; x++)
                {
                    for ( int y = 0 ; y < 8 ; y++)
                    {
                        if(Pressed.getSource() == pieceButton[x][y])
                        {
                            String a = pieceButton[x][y].getText();
                            if(holdCount == 0)
                            {
                                holdA = a;
                                countOA = x;
                                countOB = y;
                                System.out.println(holdA);
                                holdCount++;
                            }
                            else
                            {
                                holdB = a;
                                countTA = x;
                                countTB = y;
                                System.out.println(holdB);
                                System.out.println("A:"+holdA + " B:" + holdB);
                                holdCount = 0;
                                break;
                            }
                        }
                    }
                }	
            }
    	}// End Actionlistener Class
        
        // Validate color and class and attempt to move.
        public void moveIt()
        {

   
        } 
        

        
    } // End boardPanel Class
        
    // Variables and 'Item' initialization
    JPanel gamePanel = new JPanel();
    
    JButton holdMove[][] = new JButton[1][1];
    
    JButton pieceButton[][] = new JButton[8][8];
    
    String boardStart   = "A8,B8,C8,D8,E8,F8,G8,H8,"
                        + "A7,B7,C7,D7,E7,F7,G7,H7,"
                        + "A6,B6,C6,D6,E6,F6,G6,H6,"
                        + "A5,B5,C5,D5,E5,F5,G5,H5,"
                        + "A4,B4,C4,D4,E4,F4,G4,H4,"
                        + "A3,B3,C3,D3,E3,F3,G3,H3,"
                        + "A2,B2,C2,D2,E2,F2,G2,H2,"
                        + "A1,B1,C1,D1,E1,F1,G1,H1";
    
    String gamePieces   = "BR1,BK1,BB1,BA1,BQ1,BB2,BK2,BR2,"
                        + "BP1,BP2,BP3,BP4,BP5,BP6,BP7,BP8,"
                        + "   ,   ,   ,   ,   ,   ,   ,   ,"
                        + "   ,   ,   ,   ,   ,   ,   ,   ,"
                        + "   ,   ,   ,   ,   ,   ,   ,   ,"
                        + "   ,   ,   ,   ,   ,   ,   ,   ,"
                        + "WP1,WP2,WP3,WP4,WP5,WP6,WP7,WP8,"
                        + "WR1,WK1,WB1,WA1,WQ1,WB2,WK2,WR2,";
    
    private String canCapture;
    
    int holdCount, countOA, countOB, countTA, countTB;
    int moveIt;
    int boardPlace = 0;
    int boardGPlace = 0;
    String holdA;
    String holdB;
    
    Pawn WP1 = new Pawn();

    
}
