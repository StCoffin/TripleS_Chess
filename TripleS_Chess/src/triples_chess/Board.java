/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triples_chess;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Stcof
 */
public class Board extends JFrame
{
    public Board()
    {
        int Row, Col;
        
        // Implement toolkit and get screen Size for Frame
        Toolkit aKit = Toolkit.getDefaultToolkit();
        Dimension XY = aKit.getScreenSize       ();
        
        // Set max Fram Measurements to Row and Col
        Row = XY.width ;
        Col = XY.height;
        
        // Set the Size and Location for the Frame
        setSize	(25*Row/80	, Col/2		);
   	setLocation(11*Row/32	, Col/4		);
        
        // Set the Title for the Frame
        setTitle ("Triple S Chess Game!");
        
        boardPanel startPane = new boardPanel();
        Container contentStartPane = getContentPane();
        contentStartPane.add(startPane);
        
    }
        
    private class boardPanel extends JPanel
    {
        public boardPanel()
        {
           setLayout(new BorderLayout());
           
           Font Large = new Font("TimesRoman", Font.BOLD, 30);
           
           gamePanel.setLayout(new GridLayout(8, 8, 5, 5));
           
           
           place = 0;
           for (int a = 0; a<8; a++)
           {
               
               for(int b = 0; b < 8; b++)
               {
                   peiceButton[a][b] = new JButton("" + gamePeices.substring(place, place + 3));
                   peiceButton[a][b].setBackground(Color.WHITE);
                   peiceButton[a][b].setFont      (Large);
                   gamePanel.add(peiceButton[a][b]);
                   place = place + 4;
                      
               }
           }
           add(gamePanel);
           
        }
        
            
    }
        
    
    JPanel gamePanel = new JPanel();
    
    JButton peiceButton[][] = new JButton[8][8];
    
    String gamePeices = "WR1,WP1,   ,   ,   ,   ,BP1,BR1,WK1,WP2,   ,   ,   ,   ,BP2,BK1,WB1,WP3,   ,   ,   ,   ,BP3,BB1,WQ1,WP4,   ,   ,   ,   ,BP4,BQ1,WA1,WP5,   ,   ,   ,   ,BP5,BK1,WB2,WP6,   ,   ,   ,   ,BP6,BB2,WK2,WP7,   ,   ,   ,   ,BP7,BK2,WR2,WP8,   ,   ,   ,   ,BP8,BR2,";
    
    private String canCapture;
    
    int place = 0;

    
}
