/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triples_chess;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Stcof
 */
public class Piece 
{
    
    
    public String setColor(char a)
    {
        if( a == 'W')
        {
            color = "White";
        }
        else if( a == 'B')
        {
            color = "Black";
        }
        return color;
    }
    
    JButton holdButton;
    public String color;
}
