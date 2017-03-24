/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triples_chess;

import java.awt.Color;
import javax.swing.JButton;


/**
 *
 * @author Stcof
 */
public class Pawn extends Piece
{
    public Pawn(pC color)
    {
        colr = color;
        type = pT.PAWN;
        hasMoved = 0;
        switch(colr)
        {
            case W :
                pImg = "W" + "Pwn";
                break;
            case B :
                pImg = "B" + "Pwn";
                break;
        }

    }
    
    public int[][] vMove(int col, int row, Piece[][] B, String type) 
    {
        if (B[col][row].colr() == colr.W && col > 0) 
        {
            if ((B[col][row]).hasMoved == 0) {
                moves[col - 1][row] = 1;
                moves[col - 2][row] = 1;
            } else {
                moves[col - 1][row] = 1;
            }
        }

        return moves;

    
        /**    
        for (int x = 0; x<8; col++)
        {
            for(int row = 0; row < 8; row++)
            {
                if (x == col && y == row)
                {
                    moves[col][row] = 1;
                }
                else
                {
                    moves[col][row] = 0;
                }
            }
                
        }
        * **/
        
        
        
        
    }
    
    
    
    /**
    public  String canMove()
    {
        // Do something here...
    }
    **/
    
    public String getImgID() 
    {
        return pImg;
    }
    
    public pC colr()
    {
        return colr;
    }
    
    public pT getType()
    {
        return type;
    }
    
    public String getTypeS()
    {
        return pImg;
    }
    
    public String toString() 
    {
        return colr + "" + type;
    }
    
    public String toStringC()
    {
        return colr + "";
    }
    
    //Initialize varibales
    pT type;
    pC colr;
    private String pImg;
    private int[][] moves = new int[8][8];
    private int hasMoved;
    
}
