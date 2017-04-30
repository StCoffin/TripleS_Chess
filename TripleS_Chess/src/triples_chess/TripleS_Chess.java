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
 *
 * @author Triple S Development =)
 */
public class TripleS_Chess 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        //Just threw something in here as a placeholder
        System.out.println("Initializing new chess board!");
        
        
        Board newBoard = new Board();
        
        newBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newBoard.setVisible(true);
        
    }
}
