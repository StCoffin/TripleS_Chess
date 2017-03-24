/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triples_chess;

/**
 *
 * @author Stcof
 */
public class Rook extends Piece
{
    
    public Rook(pC color)
    {
        colr = color;
        type = pT.ROOK;
        switch(colr)
        {
            case W :
                pImg = "W" + "Rok";
                break;
            case B :
                pImg = "B" + "Rok";
                break;
        }

    }
    
    public int[][] vMove(Object[][] B)
    {
        int[][] moves = new int[8][8];
        for (int col = 0; col<8; col++)
        {
            for(int row = 0; row < 8; row++)
            {
        	moves[col][row] = 0;
                
            }
                
        }
        
        
        
        return moves;
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
    
    public pC getColor()
    {
        return colr;
    }
    
    //Initialize varibales
    pT type;
    pC colr;
    private String pImg;
    
}
