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
            
        }
        
            
    }
        
    
    JPanel gamePanel = new JPanel();
    
    JButton peiceButton[][] = new JButton[8][8];
    
    String gamePeices = " ";
    
    private String canCapture;
    
}
